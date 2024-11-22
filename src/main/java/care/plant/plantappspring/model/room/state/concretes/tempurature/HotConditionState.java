package care.plant.plantappspring.model.room.state.concretes.tempurature;

import care.plant.plantappspring.model.room.state.RoomConditionState;

public class HotConditionState implements RoomConditionState {
    @Override
    public String getNotification() {
        return "The room is too hot. Consider improving ventilation.";
    }

    @Override
    public String suggestAdjustment() {
        return "Lower the temperature to a moderate level.";
    }
}