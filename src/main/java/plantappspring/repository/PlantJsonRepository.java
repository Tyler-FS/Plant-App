package plantappspring.repository;

import plantappspring.model.plant.PlantJson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlantJsonRepository extends JpaRepository<PlantJson, Long> {
    Optional<PlantJson> findByPlantName(String plantName);
}