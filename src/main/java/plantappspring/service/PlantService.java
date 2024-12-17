package plantappspring.service;

import lombok.SneakyThrows;
import plantappspring.config.ApiConfig;
import plantappspring.model.plant.Plant;
import plantappspring.model.plant.PlantJson;
import plantappspring.model.room.Room;
import plantappspring.repository.PlantJsonRepository;
import plantappspring.repository.PlantRepository;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import plantappspring.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlantService {

    private final PlantRepository plantRepository;
    private final PlantJsonRepository plantJsonRepository;
    private final RestTemplate restTemplate;
    private final ApiConfig apiConfig;
    private final RoomRepository roomRepository;

    @Autowired
    public PlantService(PlantRepository plantRepository, PlantJsonRepository plantJsonRepository, RestTemplate restTemplate, ApiConfig apiConfig, RoomRepository roomRepository) {
        this.plantRepository = plantRepository;
        this.plantJsonRepository = plantJsonRepository;
        this.restTemplate = restTemplate;
        this.apiConfig = apiConfig;
        this.roomRepository = roomRepository;
    }

    /**
     * Adds a new plant to the repository.
     *
     * @param plant the plant to add
     * @return the saved plant
     */
    @Transactional
    public Plant addPlant(Plant plant) {
        return plantRepository.save(plant);
    }

    /**
     * Adds a new plant from API data. Checks the local table for existing JSON data before querying the API.
     *
     * @param plantName the name of the plant
     * @return the saved plant
     */
    @SneakyThrows
    @Transactional
    public Plant addPlantFromApiData(@NotNull String plantName) {
        Optional<PlantJson> existingJson = plantJsonRepository.findByPlantName(plantName);
        JSONObject plantJson;

        if (existingJson.isPresent()) {
            plantJson = new JSONObject(existingJson.get().getJsonData());
        } else {
            String formattedUrl = apiConfig.getApiUrl().replace("[PLANT_ID]", plantName);
            String response = restTemplate.getForObject(formattedUrl, String.class);
            plantJson = new JSONObject(response);
            PlantJson newPlantJson = new PlantJson();
            newPlantJson.setPlantName(plantName);
            newPlantJson.setJsonData(response);
            plantJsonRepository.save(newPlantJson);
        }

        return savePlantFromJson(plantJson);
    }

    /**
     * Saves a plant from JSON data.
     *
     * @param plantJson the JSON data of the plant
     * @return the saved plant
     */
    private Plant savePlantFromJson(@NotNull JSONObject plantJson) {
        Plant plant;
        try {
            plant = new Plant(plantJson.getString("common_name"),
                    plantJson.getJSONArray("scientific_name").getString(0),
                    plantJson.optString("watering", "Unknown"),
                    plantJson.optString("sunlight", "Unknown"),
                    plantJson.optString("notes", "No additional notes"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return plantRepository.save(plant);
    }

    /**
     * Updates a plant with the specified room.
     *
     * @param plantId the ID of the plant
     * @param roomId the ID of the room
     * @return the updated plant
     */
    @Transactional
    public Plant updatePlantWithRoom(Long plantId, Long roomId) {
        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() -> new IllegalArgumentException("Plant not found"));
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));
        plant.setRoom(room);
        return plantRepository.save(plant);
    }

    /**
     * Retrieves a plant by its ID.
     *
     * @param id the ID of the plant
     * @return an Optional containing the plant if found, or empty if not found
     */
    public Optional<Plant> getPlantById(Long id) {
        return plantRepository.findById(id);
    }

    /**
     * Retrieves a plant by its name.
     *
     * @param name the name of the plant
     * @return an Optional containing the plant if found, or empty if not found
     */
    public Optional<Plant> getPlantByName(String name) {
        return plantRepository.findByName(name);
    }

    /**
     * Retrieves all plants.
     *
     * @return a list of all plants
     */
    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    /**
     * Retrieves plants by their water frequency.
     *
     * @param waterFrequency the water frequency of the plants
     * @return a list of plants with the specified water frequency
     */
    public List<Plant> getPlantsByWaterFrequency(String waterFrequency) {
        return plantRepository.findByWaterFrequency(waterFrequency);
    }

    /**
     * Retrieves plants by their sunlight needs.
     *
     * @param sunlightNeeds the sunlight needs of the plants
     * @return a list of plants with the specified sunlight needs
     */
    public List<Plant> getPlantsBySunlightNeeds(String sunlightNeeds) {
        return plantRepository.findBySunlightNeeds(sunlightNeeds);
    }

    /**
     * Retrieves plants with names containing the specified string.
     *
     * @param name the string to search for in plant names
     * @return a list of plants with names containing the specified string
     */
    public List<Plant> getPlantsByNameContaining(String name) {
        return plantRepository.findByNameContaining(name);
    }

    /**
     * Retrieves all plants sorted by name.
     *
     * @return a list of all plants sorted by name
     */
    public List<Plant> getPlantsSortedByName() {
        return plantRepository.findAllByOrderByNameAsc();
    }

    /**
     * Retrieves plants by their water frequency and sunlight needs.
     *
     * @param waterFrequency the water frequency of the plants
     * @param sunlightNeeds the sunlight needs of the plants
     * @return a list of plants with the specified water frequency and sunlight needs
     */
    public List<Plant> getPlantsByWaterAndSunlight(String waterFrequency, String sunlightNeeds) {
        return plantRepository.findByWaterFrequencyAndSunlightNeeds(waterFrequency, sunlightNeeds);
    }

    /**
     * Retrieves a paginated list of plants.
     *
     * @param page the page number to retrieve
     * @param size the number of plants per page
     * @return a paginated list of plants
     */
    public Page<Plant> getPlantsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return plantRepository.findAll(pageable);
    }

    /**
     * Deletes a plant by its ID.
     *
     * @param id the ID of the plant to delete
     */
    @Transactional
    public void deletePlantById(Long id) {
        plantRepository.deleteById(id);
    }
}