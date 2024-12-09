package plantappspring.model.room.state.concretes.light;

import plantappspring.model.room.state.RoomConditionState;

public class LowLightState implements RoomConditionState {
    @Override
    public String getNotification() {
        return "The room is too dark. Consider increasing light exposure.";
    }

    @Override
    public String suggestAdjustment() {
        return "Move plants closer to a light source or use artificial lighting.";
    }
}