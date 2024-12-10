package plantappspring.model.room.state.concretes.tempurature;

import plantappspring.model.room.state.RoomConditionState;

public class ColdConditionState implements RoomConditionState {
    @Override
    public String getNotification() {
        return "The room is too cold. Consider using a heater.";
    }

    @Override
    public String suggestAdjustment() {
        return "Raise the temperature to a moderate level.";
    }
}