import java.util.List;
import java.util.ArrayList;

public class WateringLogFunctions {
    private List<WateringLog> wateringLog = new ArrayList<>();

    //adding a new log
    public void addLog(WateringLog Log) {
        wateringLog.add(Log);
        System.out.println("Watering log added for: " + Log.getPlantName());
    }
    //to retreive all logs
    public List<WateringLog> getAllLogs() {
        return wateringLog;
    }
    //find watering log by plant name
    public List<WateringLog> findLogsByPlantName(String plantName) {
        List<WateringLog> result = new ArrayList<>();
        for (WateringLog log : wateringLog) {
            if (log.getPlantName().equals(plantName)) {
                result.add(log);
            }
        }
        return result;
    }
}
