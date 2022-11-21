package data;

public enum Industry {
    AGRICULTURE("Agriculture"),
    APPAREL("Apparel");


    private final String description;

    private Industry(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
