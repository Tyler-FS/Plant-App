package plantappspring.service;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import plantappspring.config.ApiConfig;
import plantappspring.model.plant.Plant;
import plantappspring.model.plant.PlantJson;
import plantappspring.repository.PlantJsonRepository;
import plantappspring.repository.PlantRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class PlantServiceTest {

    @Mock
    private PlantRepository plantRepository;

    @Mock
    private PlantJsonRepository plantJsonRepository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ApiConfig apiConfig;

    @InjectMocks
    private PlantService plantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPlantFromApiData_existingJson() throws Exception {
        String plantName = "Rose";
        String apiUrl = "https://api.example.com";
        String jsonData = "{\"common_name\":\"Rose\",\"scientific_name\":[\"Rosa\"],\"watering\":\"Frequent\",\"sunlight\":\"Full Sun\",\"notes\":\"Beautiful flower\"}";
        PlantJson plantJson = new PlantJson();
        plantJson.setPlantName(plantName);
        plantJson.setJsonData(jsonData);

        when(plantJsonRepository.findByPlantName(plantName)).thenReturn(Optional.of(plantJson));

        Plant savedPlant = new Plant("Rose", "Rosa", "Frequent", "Full Sun", "Beautiful flower", plantJson);
        when(plantRepository.save(any(Plant.class))).thenReturn(savedPlant);

        Plant result = plantService.addPlantFromApiData(plantName);

        assertEquals(savedPlant, result);
        verify(plantJsonRepository, never()).save(any(PlantJson.class));
    }

    @Test
    void testAddPlantFromApiData_newJson() throws Exception {
        String plantName = "Tulip";
        String apiUrl = "https://api.example.com";
        String jsonData = "{\"common_name\":\"Tulip\",\"scientific_name\":[\"Tulipa\"],\"watering\":\"Moderate\",\"sunlight\":\"Partial Sun\",\"notes\":\"Spring flower\"}";

        when(plantJsonRepository.findByPlantName(plantName)).thenReturn(Optional.empty());
        when(apiConfig.getApiUrl()).thenReturn(apiUrl);
        when(restTemplate.getForObject(apiUrl, String.class)).thenReturn(jsonData);

        PlantJson newPlantJson = new PlantJson();
        newPlantJson.setPlantName(plantName);
        newPlantJson.setJsonData(jsonData);

        Plant savedPlant = new Plant("Tulip", "Tulipa", "Moderate", "Partial Sun", "Spring flower", newPlantJson);
        when(plantRepository.save(any(Plant.class))).thenReturn(savedPlant);

        Plant result = plantService.addPlantFromApiData(plantName);

        assertEquals(savedPlant, result);
        verify(plantJsonRepository).save(any(PlantJson.class));
    }
}