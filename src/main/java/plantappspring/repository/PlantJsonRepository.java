package plantappspring.repository;

import plantappspring.model.plant.PlantJson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlantJsonRepository extends JpaRepository<PlantJson, Long> {
    Optional<PlantJson> findByPlantName(String plantName);
}