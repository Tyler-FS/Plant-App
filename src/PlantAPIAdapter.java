import org.json.JSONObject;
import java.sql.*;

public class PlantAPIAdapter implements PlantDataAdapter {
    private PerenualAPI apiClient = PerenualAPI.getInstance();

    @Override
    public JSONObject fetchPlantDataFromAPI(String plantName) {
        return apiClient.fetchPlantData(plantName);
    }

    @Override
    public void savePlantDataToDatabase(String plantName) {
        JSONObject plantJson = fetchPlantDataFromAPI(plantName);

        if (plantJson != null) {
            String sql = "INSERT INTO plants (plant_data) VALUES (?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, plantJson.toString());
                pstmt.executeUpdate();
                System.out.println("Plant JSON data saved to the database!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Plant getPlantFromDatabase(int plantId) {
        String sql = "SELECT plant_data FROM plants WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, plantId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String jsonData = rs.getString("plant_data");
                JSONObject plantJson = new JSONObject(jsonData);
                return adaptToPlant(plantJson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Plant adaptToPlant(JSONObject plantJson) {
        // Extract data from JSON to populate the Plant object
        String name = plantJson.getString("common_name");
        String species = plantJson.getJSONArray("scientific_name").getString(0);
        String waterFrequency = plantJson.getString("watering");
        String sunlightNeeds = plantJson.optString("sunlight", "Unknown");

        return new Plant(name, species, waterFrequency, sunlightNeeds);
    }
}