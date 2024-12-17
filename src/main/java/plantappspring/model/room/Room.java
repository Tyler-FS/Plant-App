package plantappspring.model.room;

import plantappspring.model.plant.Plant;
import plantappspring.model.room.state.RoomConditionState;
import plantappspring.model.room.state.concretes.humidity.DryConditionState;
import plantappspring.model.room.state.concretes.humidity.HumidConditionState;
import plantappspring.model.room.state.concretes.humidity.OptimalConditionState;
import plantappspring.model.room.state.concretes.light.BrightLightState;
import plantappspring.model.room.state.concretes.light.LowLightState;
import plantappspring.model.room.state.concretes.light.OptimalLightState;
import plantappspring.model.room.state.concretes.tempurature.ColdConditionState;
import plantappspring.model.room.state.concretes.tempurature.HotConditionState;
import plantappspring.model.room.state.concretes.tempurature.OptimalTemperatureState;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include // Include `id` in equality comparison
    private Long id;

    private String roomName;

    private String humidityLevel; // "Low", "Medium", "High"
    private String temperatureLevel; // "Cold", "Moderate", "Hot"
    private String lightLevel; // "Low", "Medium", "Bright"
    private String windowFacing; // "North", "South", "East", "West"

    @Lob
    private String notes; // Additional details about the room

    private String humidityStateName; // Persist the current humidity state
    private String temperatureStateName; // Persist the current temperature state
    private String lightStateName; // Persist the current light state

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "room_id")
    private List<Plant> plants = new ArrayList<>();

    @Transient
    private RoomConditionState humidityState;

    @Transient
    private RoomConditionState temperatureState;

    @Transient
    private RoomConditionState lightState;

    // Constructor with essential fields
    public Room(String roomName, String humidityLevel, String temperatureLevel, String lightLevel, String windowFacing, String notes) {
        this.roomName = roomName;
        this.humidityLevel = humidityLevel;
        this.temperatureLevel = temperatureLevel;
        this.lightLevel = lightLevel;
        this.windowFacing = windowFacing;
        this.notes = notes;
        checkConditions(); // Initialize all states
    }

    /**
     * Checks the conditions of the room and updates its states.
     */
    public void checkConditions() {
        // Set humidity state
        switch (humidityLevel) {
            case "Low":
                setHumidityState(new DryConditionState());
                break;
            case "High":
                setHumidityState(new HumidConditionState());
                break;
            default:
                setHumidityState(new OptimalConditionState());
        }

        // Set temperature state
        switch (temperatureLevel) {
            case "Cold":
                setTemperatureState(new ColdConditionState());
                break;
            case "Hot":
                setTemperatureState(new HotConditionState());
                break;
            default:
                setTemperatureState(new OptimalTemperatureState());
        }

        // Set light state
        switch (lightLevel) {
            case "Low":
                setLightState(new LowLightState());
                break;
            case "Bright":
                setLightState(new BrightLightState());
                break;
            default:
                setLightState(new OptimalLightState());
        }
    }

    /**
     * Restores states based on persisted state names.
     */
    public void restoreStates() {
        // Restore humidity state
        switch (humidityStateName) {
            case "DryConditionState":
                this.humidityState = new DryConditionState();
                break;
            case "HumidConditionState":
                this.humidityState = new HumidConditionState();
                break;
            default:
                this.humidityState = new OptimalConditionState();
        }

        // Restore temperature state
        switch (temperatureStateName) {
            case "ColdConditionState":
                this.temperatureState = new ColdConditionState();
                break;
            case "HotConditionState":
                this.temperatureState = new HotConditionState();
                break;
            default:
                this.temperatureState = new OptimalTemperatureState();
        }

        // Restore light state
        switch (lightStateName) {
            case "LowLightState":
                this.lightState = new LowLightState();
                break;
            case "BrightLightState":
                this.lightState = new BrightLightState();
                break;
            default:
                this.lightState = new OptimalLightState();
        }
    }

    public void setHumidityState(RoomConditionState state) {
        this.humidityState = state;
        this.humidityStateName = state.getClass().getSimpleName();
    }

    public void setTemperatureState(RoomConditionState state) {
        this.temperatureState = state;
        this.temperatureStateName = state.getClass().getSimpleName();
    }

    public void setLightState(RoomConditionState state) {
        this.lightState = state;
        this.lightStateName = state.getClass().getSimpleName();
    }
}