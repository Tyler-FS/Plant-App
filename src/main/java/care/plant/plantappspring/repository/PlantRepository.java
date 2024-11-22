package care.plant.plantappspring.repository;

import care.plant.plantappspring.model.plant.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlantRepository extends JpaRepository<Plant, Long> {
    // Query by water frequency
    List<Plant> findByWaterFrequency(String waterFrequency);

    // Query by sunlight needs
    List<Plant> findBySunlightNeeds(String sunlightNeeds);

    // Query for partial name matching
    List<Plant> findByNameContaining(String name);

    // Query for sorted plants by name
    List<Plant> findAllByOrderByNameAsc();

    // Query combining water frequency and sunlight needs
    List<Plant> findByWaterFrequencyAndSunlightNeeds(String waterFrequency, String sunlightNeeds);
}