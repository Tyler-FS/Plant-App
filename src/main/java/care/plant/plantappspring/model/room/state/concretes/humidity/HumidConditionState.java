package care.plant.plantappspring.model.room.state.concretes.humidity;

import care.plant.plantappspring.model.room.state.RoomConditionState;

public class HumidConditionState implements RoomConditionState {
    @Override
    public String getNotification() {
        return "The room is too humid. Consider using a dehumidifier.";
    }

    @Override
    public String suggestAdjustment() {
        return "Reduce humidity to medium levels.";
    }
}