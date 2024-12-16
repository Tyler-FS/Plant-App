// src/main/java/plantappspring/init/DataPopulator.java
package plantappspring.init;

import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.client.RestTemplate;
import plantappspring.model.plant.PlantJson;
import plantappspring.repository.PlantJsonRepository;

@EnableJpaRepositories(basePackages = "plantappspring.repository")
@EntityScan(basePackages = "plantappspring.model")
@SpringBootApplication(scanBasePackages = "plantappspring")
public class DataPopulator {

    private final PlantJsonRepository plantJsonRepository;
    private final RestTemplate restTemplate;

    public DataPopulator(PlantJsonRepository plantJsonRepository, RestTemplate restTemplate) {
        this.plantJsonRepository = plantJsonRepository;
        this.restTemplate = restTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(DataPopulator.class, args);
    }

    @Bean
    public CommandLineRunner run(ApplicationContext context) {
        return args -> {
            DataPopulator dataPopulator = context.getBean(DataPopulator.class);
            String apiUrl = context.getEnvironment().getProperty("api.url");
            dataPopulator.populateInitialData(apiUrl);
        };
    }

    @SneakyThrows
    public void populateInitialData(String apiUrl) {
        for (int id = 1; id <= 100; id++) {
            String formattedUrl = apiUrl.replace("[PLANT_ID]", String.valueOf(id));
            String response = restTemplate.getForObject(formattedUrl, String.class);
            JSONObject plantJson = new JSONObject(response);

            PlantJson newPlantJson = new PlantJson();
            newPlantJson.setPlantName(plantJson.getString("common_name"));
            newPlantJson.setJsonData(response);
            plantJsonRepository.save(newPlantJson);
        }
    }
}