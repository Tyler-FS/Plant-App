public class Room {
    private String roomName;
    private String humidityLevel; // Low, Medium, High
    private String lightLevel;    // Bright, Medium, Low
    private String windowFacing;  // North, South, East, West
    private double temperature;   // in degrees
    private String notes;         //additional information about the room

    public Room(String roomName, String humidityLevel, String lightLevel, String windowFacing, double temperature, String notes) {
        this.roomName = roomName;
        this.humidityLevel = humidityLevel;
        this.lightLevel = lightLevel;
        this.windowFacing = windowFacing;
        this.temperature = temperature;
        this.notes = notes;
    }

    // Getters and setters
    public String getRoomName() {
        return roomName;
    }

    public String getHumidityLevel() {
        return humidityLevel;
    }

    public String getLightLevel() {
        return lightLevel;
    }

    public String getWindowFacing() {
        return windowFacing;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getNotes() {
        return notes;
    }

    public void displayRoomInfo() {
        System.out.println("Room Name: " + roomName);
        System.out.println("Humidity Level: " + humidityLevel);
        System.out.println("Light Level: " + lightLevel);
        System.out.println("Window Facing: " + windowFacing);
        System.out.println("Temperature: " + temperature + "°C");
        System.out.println("Notes: " + notes);
        System.out.println("----------------------------");
    }
}
