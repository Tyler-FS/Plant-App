public class Plant {
    // Attributes of the plant
    private String name;
    private String species;
    private int waterFrequency; // in days
    private String sunlightNeeds; // for example: Full Sun, Partial Shade, Shade, Direct, Indirect
    private String notes;

    // Constructor
    public Plant(String name, String species, int waterFrequency, String sunlightNeeds, String notes) {
        this.name = name;
        this.species = species;
        this.waterFrequency = waterFrequency;
        this.sunlightNeeds = sunlightNeeds;
        this.notes = notes;
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

    public int getWaterFrequency() {
        return waterFrequency;
    }

    public void setWaterFrequency(int waterFrequency) {
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
        System.out.println("Water Frequency: Every " + waterFrequency + " day(s)");
        System.out.println("Sunlight Needs: " + sunlightNeeds);
        System.out.println("Notes: " + notes);
        System.out.println("----------------------------");
    }


    public static void main(String[] args) {

    }
}