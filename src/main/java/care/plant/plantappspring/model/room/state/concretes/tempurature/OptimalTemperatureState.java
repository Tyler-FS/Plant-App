package care.plant.plantappspring.model.room.state.concretes.tempurature;

import care.plant.plantappspring.model.room.state.RoomConditionState;

public class OptimalTemperatureState implements RoomConditionState {
    @Override
    public String getNotification() {
        return "The room temperature is ideal.";
    }

    @Override
    public String suggestAdjustment() {
        return "No adjustments needed.";
    }
}