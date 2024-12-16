package plantappspring.model.plant;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
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

    public Plant(String commonName, String scientificName, String s, String s1, String s2) {
    }

    // Constructor for quick creation (optional)
//    public Plant(String name, String species, String waterFrequency, String sunlightNeeds, String notes) {
//        this.name = name;
//        this.species = species;
//        this.waterFrequency = waterFrequency;
//        this.sunlightNeeds = sunlightNeeds;
//        this.notes = notes;
//    }

    //linking to PlantJson
    @ManyToOne
    @JoinColumn(name = "json_id")
    private PlantJson plantJson;

    public Plant(String commonName, String scientificName, String waterFrequency, String sunlightNeeds, String notes, PlantJson plantJson) {
        this.name = commonName;
        this.species = scientificName;
        this.waterFrequency = waterFrequency;
        this.sunlightNeeds = sunlightNeeds;
        this.notes = notes;
        this.plantJson = plantJson;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Plant plant = (Plant) o;
        return getId() != null && Objects.equals(getId(), plant.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}