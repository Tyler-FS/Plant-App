import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PlantManager {
    private List<Plant> plants;

    public PlantManager() {
        plants = new ArrayList<>();
    }

    // Method to add a plant to the list
    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    // Sort plants by name
    public void sortByName() {
        Collections.sort(plants, Comparator.comparing(Plant::getName));
    }

    // Sort plants by species
    public void sortBySpecies() {
        Collections.sort(plants, Comparator.comparing(Plant::getSpecies));
    }

    // Sort plants by sunlight needs (Full Sun, partial shade, shade)
    public void sortBySunlightNeeds() {
        Collections.sort(plants, Comparator.comparing(Plant::getSunlightNeeds));
    }

    // Sort plants by water frequency (ascending order)
    public void sortByWaterFrequency() {
        Collections.sort(plants, Comparator.comparingInt(Plant::getWaterFrequency));
    }

    // Display all plants in the list
    public void displayPlants() {
        for (Plant plant : plants) {
            plant.displayPlantInfo();  // Use the display method from the Plant class
        }
    }
}
