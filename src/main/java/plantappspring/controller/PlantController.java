package plantappspring.controller;

import org.springframework.http.ResponseEntity;
import plantappspring.model.plant.Plant;
import plantappspring.service.PlantService;
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

    /**
     * Adds a new plant to the repository.
     *
     * @param plant the plant to add
     * @return the saved plant
     */
    @PostMapping
    public Plant addPlant(@RequestBody Plant plant) {
        return plantService.addPlant(plant);
    }

    /**
     * Adds a new plant from API data.
     *
     * @param plantName the name of the plant
     * @param apiUrl the URL of the API to query
     * @return the saved plant
     */
    @PostMapping("/from-api")
    public Plant addPlantFromApi(@RequestParam String plantName, @RequestParam String apiUrl) {
        return plantService.addPlantFromApiData(plantName);
    }

    /**
     * \[NEW\] Updates a plant with the specified room.
     *
     * @param plantId the ID of the plant
     * @param roomId the ID of the room
     * @return the updated plant
     */
    @PutMapping("/{plantId}/room/{roomId}")
    public ResponseEntity<Plant> updatePlantWithRoom(@PathVariable Long plantId, @PathVariable Long roomId) {
        Plant updatedPlant = plantService.updatePlantWithRoom(plantId, roomId);
        return ResponseEntity.ok(updatedPlant);
    }

    /**
     * Retrieves a plant by its ID.
     *
     * @param id the ID of the plant
     * @return an Optional containing the plant if found, or empty if not found
     */
    @GetMapping("/{id}")
    public Optional<Plant> getPlantById(@PathVariable Long id) {
        return plantService.getPlantById(id);
    }

    /**
     * Retrieves a plant by its name.
     *
     * @param name the name of the plant
     * @return an Optional containing the plant if found, or empty if not found
     */
    @GetMapping("/by-name")
    public Optional<Plant> getPlantByName(@RequestParam String name) {
        return plantService.getPlantByName(name);
    }

    /**
     * Retrieves all plants.
     *
     * @return a list of all plants
     */
    @GetMapping
    public List<Plant> getAllPlants() {
        return plantService.getAllPlants();
    }

    /**
     * Retrieves plants by their water frequency.
     *
     * @param waterFrequency the water frequency of the plants
     * @return a list of plants with the specified water frequency
     */
    @GetMapping("/by-water-frequency")
    public List<Plant> getPlantsByWaterFrequency(@RequestParam String waterFrequency) {
        return plantService.getPlantsByWaterFrequency(waterFrequency);
    }

    /**
     * Retrieves plants by their sunlight needs.
     *
     * @param sunlightNeeds the sunlight needs of the plants
     * @return a list of plants with the specified sunlight needs
     */
    @GetMapping("/by-sunlight")
    public List<Plant> getPlantsBySunlightNeeds(@RequestParam String sunlightNeeds) {
        return plantService.getPlantsBySunlightNeeds(sunlightNeeds);
    }

    /**
     * Retrieves all plants sorted by name.
     *
     * @return a list of all plants sorted by name
     */
    @GetMapping("/sorted")
    public List<Plant> getPlantsSortedByName() {
        return plantService.getPlantsSortedByName();
    }

    /**
     * Retrieves a paginated list of plants.
     *
     * @param page the page number to retrieve
     * @param size the number of plants per page
     * @return a paginated list of plants
     */
    @GetMapping("/paginated")
    public Page<Plant> getPlantsPaginated(@RequestParam int page, @RequestParam int size) {
        return plantService.getPlantsPaginated(page, size);
    }

    /**
     * Deletes a plant by its ID.
     *
     * @param id the ID of the plant to delete
     */
    @DeleteMapping("/{id}")
    public void deletePlantById(@PathVariable Long id) {
        plantService.deletePlantById(id);
    }

    /**
     * Retrieves plants with names containing the specified string.
     *
     * @param name the string to search for in plant names
     * @return a list of plants with names containing the specified string
     */
    @GetMapping("/search")
    public List<Plant> getPlantsByNameContaining(@RequestParam String name) {
        return plantService.getPlantsByNameContaining(name);
    }

    /**
     * Retrieves plants by their water frequency and sunlight needs.
     *
     * @param waterFrequency the water frequency of the plants
     * @param sunlightNeeds the sunlight needs of the plants
     * @return a list of plants with the specified water frequency and sunlight needs
     */
    @GetMapping("/filter")
    public List<Plant> getPlantsByWaterAndSunlight(@RequestParam String waterFrequency, @RequestParam String sunlightNeeds) {
        return plantService.getPlantsByWaterAndSunlight(waterFrequency, sunlightNeeds);
    }
}