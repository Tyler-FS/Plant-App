package care.plant.plantappspring.model.room.state;

public interface RoomConditionState {
    String getNotification(); // Notification for the current state
    String suggestAdjustment(); // Suggestion for improving the condition
}