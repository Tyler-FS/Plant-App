package care.plant.plantappspring.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String species;
    private String waterFrequency; // Stored as descriptive text (e.g., "Frequent")
    private String sunlightNeeds;  // Example: "Full Sun", "Shade"

    @Lob
    private String notes; // Longer text for additional plant details

    // Constructor for quick creation (optional)
    public Plant(String name, String species, String waterFrequency, String sunlightNeeds, String notes) {
        this.name = name;
        this.species = species;
        this.waterFrequency = waterFrequency;
        this.sunlightNeeds = sunlightNeeds;
        this.notes = notes;
    }
}