import java.time.LocalDateTime;

public class WateringLog {
    private LocalDateTime dateTime;
    private String plantName;
    private int amount;

    public WateringLog(LocalDateTime dateTime, String plantName, int amount) {
        this.dateTime = dateTime;
        this.plantName = plantName;
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getPlantName() {
        return plantName;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
