package care.plant.plantappspring.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomName;
    private String humidityLevel; // Example: "Low", "Medium", "High"
    private String lightLevel;    // Example: "Bright", "Medium", "Low"
    private String windowFacing;  // Example: "North", "South"
    private Double temperature;   // Optional: e.g., 22.5°C

    @Lob
    private String notes; // Longer text for additional details about the room

    // Constructor for quick creation (optional)
    public Room(String roomName, String humidityLevel, String lightLevel, String windowFacing, Double temperature, String notes) {
        this.roomName = roomName;
        this.humidityLevel = humidityLevel;
        this.lightLevel = lightLevel;
        this.windowFacing = windowFacing;
        this.temperature = temperature;
        this.notes = notes;
    }
}