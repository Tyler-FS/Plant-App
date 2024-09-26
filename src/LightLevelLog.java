import java.util.ArrayList;
import java.util.List;

public class LightLevelLog {
    private List<LightLevel> lightLevels;

    public LightLevelLog() {
        this.lightLevels = new ArrayList<>();
    }

    public void logLightLevel(LightLevel level) {
        lightLevels.add(level);
    }

    public List<LightLevel> getLightLevels() {
        return lightLevels;
    }

    public static void main(String[] args) {
        LightLevelLog logger = new LightLevelLog();

        System.out.println("Is the plant outside or inside?");
        System.out.println("1. Outside");
        System.out.println("2. Inside");

        int choice = getUserChoice();
        if (choice == 1) {
            logger.logLightLevel(LightLevel.OUTSIDE);
        } else if (choice == 2) {
            logger.logLightLevel(LightLevel.INSIDE);
        } else {
            System.out.println("Invalid choice!");
            return;
        }

        System.out.println("Does the plant receive direct, filtered, or low light?");
        System.out.println("1. Direct");
        System.out.println("2. Filtered");
        System.out.println("3. Low Light");

        choice = getUserChoice();
        if (choice == 1) {
            logger.logLightLevel(LightLevel.DIRECT);
        } else if (choice == 2) {
            logger.logLightLevel(LightLevel.FILTERED);
        } else if (choice == 3) {
            logger.logLightLevel(LightLevel.LOW_LIGHT);
        } else {
            System.out.println("Invalid choice!");
            return;
        }

        for (LightLevel level : logger.getLightLevels()) {
            System.out.println(level);
        }
    }

    private static int getUserChoice() {
        int choice;
        try {
            choice = Integer.parseInt(System.console().readLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
            return getUserChoice();
        }
        return choice;
    }

    private int lightScore = 100;
    public void logLightLevel(LightLevel location, LightLevel lightLevel) {
        if (location == LightLevel.INSIDE) {
            lightScore -= 25;
        }

        switch (lightLevel) {
            case DIRECT:
                break;
            case FILTERED:
                lightScore -= 25;
                break;
            case LOW_LIGHT:
                lightScore -= 50;
                break;
        }

        System.out.println("Light Level Score: " + lightScore);
    }
}