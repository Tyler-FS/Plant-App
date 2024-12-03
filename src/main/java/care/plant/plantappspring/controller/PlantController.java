package care.plant.plantappspring.controller;

import care.plant.plantappspring.model.plant.Plant;
import care.plant.plantappspring.service.PlantService;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plants")
public class PlantController {

    private final PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    // Add a new plant
    @PostMapping
    public Plant addPlant(@RequestBody Plant plant) {
        return plantService.addPlant(plant);
    }

    // Add a new plant from API data
    @SneakyThrows
    @PostMapping("/from-api")
    public Plant addPlantFromApi(@RequestBody String plantJsonString) {
        JSONObject plantJson = new JSONObject(plantJsonString);
        return plantService.addPlantFromApiData(plantJson);
    }

    // Get a plant by ID
    @GetMapping("/{id}")
    public Optional<Plant> getPlantById(@PathVariable Long id) {
        return plantService.getPlantById(id);
    }

    // Get a plant by name
    @GetMapping("/by-name")
    public Optional<Plant> getPlantByName(@RequestParam String name) {
        return plantService.getPlantByName(name);
    }

    // Get all plants
    @GetMapping
    public List<Plant> getAllPlants() {
        return plantService.getAllPlants();
    }

    // Get plants by water frequency
    @GetMapping("/by-water-frequency")
    public List<Plant> getPlantsByWaterFrequency(@RequestParam String waterFrequency) {
        return plantService.getPlantsByWaterFrequency(waterFrequency);
    }

    // Get plants by sunlight needs
    @GetMapping("/by-sunlight")
    public List<Plant> getPlantsBySunlightNeeds(@RequestParam String sunlightNeeds) {
        return plantService.getPlantsBySunlightNeeds(sunlightNeeds);
    }

    // Get plants sorted by name
    @GetMapping("/sorted")
    public List<Plant> getPlantsSortedByName() {
        return plantService.getPlantsSortedByName();
    }

    // Get paginated plants
    @GetMapping("/paginated")
    public Page<Plant> getPlantsPaginated(@RequestParam int page, @RequestParam int size) {
        return plantService.getPlantsPaginated(page, size);
    }

    // Delete a plant by ID
    @DeleteMapping("/{id}")
    public void deletePlantById(@PathVariable Long id) {
        plantService.deletePlantById(id);
    }
}