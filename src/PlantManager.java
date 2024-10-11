import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PlantManager {
    private List<Plant> plantList;

  // Bill Pugh Singleton pattern implementation
    private PlantManager() {
        plantList = new ArrayList<>();
    }

    // Static inner helper class to hold the single instance of PlantManager
    private static class PlantManagerHelper {
        private static final PlantManager INSTANCE = new PlantManager();
    }

    public static PlantManager getInstance() {
        return PlantManagerHelper.INSTANCE;
    }

    // Method to add a plant to the list
    public void addPlant(Plant plant) {
        plantList.add(plant);
    }

    // Sort plants by name
    public void sortByName() {
        Collections.sort(plantList, Comparator.comparing(Plant::getName));
    }

    // Sort plants by species
    public void sortBySpecies() {
        Collections.sort(plantList, Comparator.comparing(Plant::getSpecies));
    }

    // Sort plants by sunlight needs (Full Sun, partial shade, shade)
    public void sortBySunlightNeeds() {
        Collections.sort(plantList, Comparator.comparing(Plant::getSunlightNeeds));
    }

    // Sort plants by water frequency (ascending order)
    public void sortByWaterFrequency() {
        Collections.sort(plantList, Comparator.comparing(Plant::getWaterFrequency));
    }

    // Display all plants in the list
    public void displayPlants() {
        for (Plant plant : plantList) {
            plant.displayPlantInfo();  // Use the display method from the Plant class
        }
    }
}
