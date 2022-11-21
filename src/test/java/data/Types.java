package data;

public enum Types {
    ANALYST("Analyst"),
    COMPETITOR("Competitor"),
    CUSTOMER("Customer"),
    INTEGRATOR("Integrator"),
    INVESTOR("Investor"),
    PARTNER("Partner"),
    PRESS("Press"),
    PROSPECT("Prospect"),
    RESELLER("Reseler"),
    OTHER("Other");

    private final String description;

    // Konstruktor
    private Types(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
