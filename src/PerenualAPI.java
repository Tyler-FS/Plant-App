import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PerenualAPI {
    private static final String API_KEY = "sk-VPSa67057ae5de2c77156";
    private static final String BASE_URL = "https://perenual.com/api/species-list";

    // Fetch plant data by name
    public static JSONObject fetchPlantData(String plantName) {
        try {
            String urlString = BASE_URL + "?key=" + API_KEY + "&q=" + plantName;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            connection.disconnect();

            JSONObject response = new JSONObject(content.toString());
            return response.getJSONArray("data").getJSONObject(0); // Return first plant match

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}