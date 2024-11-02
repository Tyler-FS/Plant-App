public class Main {
    public static void main(String[] args) {
        PlantAPIAdapter plantAdapter = new PlantAPIAdapter();

        // Fetch and save plant data to the database
        plantAdapter.savePlantDataToDatabase("Pothos");

        // Retrieve plant data from the database and create a Plant object
        Plant plant = plantAdapter.getPlantFromDatabase(1);
        if (plant != null) {
            plant.displayPlantInfo();
        } else {
            System.out.println("No plant data found.");
        }
    }
}