package plantappspring.model.room.state.concretes.humidity;

import plantappspring.model.room.state.RoomConditionState;

public class OptimalConditionState implements RoomConditionState {
    @Override
    public String getNotification() {
        return "The room conditions are optimal.";
    }

    @Override
    public String suggestAdjustment() {
        return "No adjustments needed.";
    }
}