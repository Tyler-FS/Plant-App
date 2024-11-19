package care.plant.plantappspring.service;

import care.plant.plantappspring.model.Plant;
import care.plant.plantappspring.repository.PlantRepository;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantService {

    private final PlantRepository plantRepository;

    // Constructor for dependency injection
    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    // Add a new plant directly
    public Plant addPlant(Plant plant) {
        return plantRepository.save(plant);
    }

    // Add a new plant from Perenual API data
    public Plant addPlantFromApiData(JSONObject plantJson) {
        Plant plant = new Plant(
                plantJson.getString("common_name"),
                plantJson.getJSONArray("scientific_name").getString(0),
                plantJson.optString("watering", "Unknown"),
                plantJson.optString("sunlight", "Unknown"),
                plantJson.optString("notes", "No additional notes")
        );
        return plantRepository.save(plant);
    }

    // Get a plant by ID
    public Optional<Plant> getPlantById(Long id) {
        return plantRepository.findById(id);
    }

    // Get a plant by name
    public Optional<Plant> getPlantByName(String name) {
        return plantRepository.findByName(name);
    }

    // Get all plants
    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    // Query plants by water frequency
    public List<Plant> getPlantsByWaterFrequency(String waterFrequency) {
        return plantRepository.findByWaterFrequency(waterFrequency);
    }

    // Query plants by sunlight needs
    public List<Plant> getPlantsBySunlightNeeds(String sunlightNeeds) {
        return plantRepository.findBySunlightNeeds(sunlightNeeds);
    }

    // Query plants with partial name matching
    public List<Plant> getPlantsByNameContaining(String name) {
        return plantRepository.findByNameContaining(name);
    }

    // Query plants sorted by name
    public List<Plant> getPlantsSortedByName() {
        return plantRepository.findAllByOrderByNameAsc();
    }

    // Query plants by water frequency and sunlight needs
    public List<Plant> getPlantsByWaterAndSunlight(String waterFrequency, String sunlightNeeds) {
        return plantRepository.findByWaterFrequencyAndSunlightNeeds(waterFrequency, sunlightNeeds);
    }

    // Pagination example
    public Page<Plant> getPlantsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return plantRepository.findAll(pageable);
    }

    // Delete a plant by ID
    public void deletePlantById(Long id) {
        plantRepository.deleteById(id);
    }
}