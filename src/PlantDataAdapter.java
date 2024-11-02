import org.json.JSONObject;

public interface PlantDataAdapter {
    JSONObject fetchPlantDataFromAPI(String plantName); // Fetch JSON from the API
    void savePlantDataToDatabase(String plantName);     // Save JSON to the database
    Plant adaptToPlant(JSONObject plantJson);           // Convert JSON to Plant object
    Plant getPlantFromDatabase(int plantId);            // Retrieve Plant from database
}