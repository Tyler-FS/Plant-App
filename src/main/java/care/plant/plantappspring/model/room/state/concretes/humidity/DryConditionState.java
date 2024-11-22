package care.plant.plantappspring.model.room.state.concretes.humidity;

import care.plant.plantappspring.model.room.state.RoomConditionState;

public class DryConditionState implements RoomConditionState {
    @Override
    public String getNotification() {
        return "The room is too dry. Consider using a humidifier.";
    }

    @Override
    public String suggestAdjustment() {
        return "Increase humidity to at least medium levels.";
    }
}