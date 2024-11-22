package care.plant.plantappspring.model.room.state.concretes.light;

import care.plant.plantappspring.model.room.state.RoomConditionState;

public class OptimalLightState implements RoomConditionState {
    @Override
    public String getNotification() {
        return "The light level is optimal.";
    }

    @Override
    public String suggestAdjustment() {
        return "No adjustments needed.";
    }
}