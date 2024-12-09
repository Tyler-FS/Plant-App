package plantappspring.model.room.state.concretes.light;

import plantappspring.model.room.state.RoomConditionState;

public class BrightLightState implements RoomConditionState {
    @Override
    public String getNotification() {
        return "The room is too bright. Consider using curtains or moving plants.";
    }

    @Override
    public String suggestAdjustment() {
        return "Reduce direct light exposure to medium levels.";
    }
}