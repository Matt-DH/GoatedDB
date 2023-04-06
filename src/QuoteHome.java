/**
 * 'BLUEPRINT'
 * Child class for HomeQuote records from DB
 */

public class QuoteHome extends Quote {

    // VARIABLES
    private final int BASE = 500;

    private int id;
    private double homeAge;
    private int dwellingType;
    private int heatingType;

    // CONSTRUCTORS
    public QuoteHome() {
        this.id = -1;
        this.homeAge = 0;
        this.dwellingType = 0;
        this.heatingType = 0;
    }

    public QuoteHome(int id, double homeAge, int dwellingType, int heatingType) {
        this.id = id;
        this.homeAge = homeAge;
        this.dwellingType = dwellingType;
        this.heatingType = heatingType;
    }

    // SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setHomeAge(float homeAge) {
        this.homeAge = homeAge;
    }

    public void setDwellingType(int dwellingType) {
        this.dwellingType = dwellingType;
    }

    public void setHeatingType(int heatingType) {
        this.heatingType = heatingType;
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public double getHomeAge() {
        return homeAge;
    }

    public int getDwellingType() {
        return dwellingType;
    }

    public int getHeatingType() {
        return heatingType;
    }

}
