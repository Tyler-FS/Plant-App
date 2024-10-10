import org.json.JSONArray;
import org.json.JSONObject;

public class Plant {
    // Attributes of the plant
    private String name;
    private String species;
    private String waterFrequency; // in days
    private String sunlightNeeds; // for example: Full Sun, Partial Shade, Shade, Direct, Indirect
    private String notes;
    private String currentlight;
    private String problems;

    /* No API Constructor
    public Plant(String name, String species, int waterFrequency, String sunlightNeeds, String notes) {
        this.name = name;
        this.species = species;
        this.waterFrequency = waterFrequency;
        this.sunlightNeeds = sunlightNeeds;
        this.notes = notes;
    }
     */

    // Constructor using API data
    public Plant(String name) {
        JSONObject plantData = PerenualAPI.fetchPlantData(name);
        if (plantData != null) {
            this.name = plantData.getString("common_name");

            // Handling scientific_name as JSONArray
            JSONArray scientificNames = plantData.getJSONArray("scientific_name");
            this.species = scientificNames.getString(0); // Use the first scientific name

            // Handling other fields
            this.waterFrequency = plantData.getString("watering");
            this.sunlightNeeds = plantData.optString("sunlight", "Unknown");
            this.notes = plantData.optString("other_care", "No additional notes");
        } else {
            System.out.println("Plant data not found for: " + name);
        }
    }


    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getWaterFrequency() {
        return waterFrequency;
    }

    public void setWaterFrequency(String waterFrequency) {
        this.waterFrequency = waterFrequency;
    }

    public String getSunlightNeeds() {
        return sunlightNeeds;
    }

    public void setSunlightNeeds(String sunlightNeeds) {
        this.sunlightNeeds = sunlightNeeds;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Display plant details
    public void displayPlantInfo() {
        System.out.println("Plant Name: " + name);
        System.out.println("Species: " + species);
        System.out.println("Water Frequency: " + waterFrequency);
        System.out.println("Sunlight Needs: " + sunlightNeeds);
        System.out.println("Notes: " + notes);
        System.out.println("----------------------------");
    }


    public static void main(String[] args) {

    }
}