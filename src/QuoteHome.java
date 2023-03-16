public class QuoteHome extends Quote {
    private final int BASE = 500;

    private double homeAge;
    private int dwellingType;
    private int heatingType;

    public QuoteHome(int base, double homeAge, int dwellingType, int heatingType) {
        this.homeAge = homeAge;
        this.dwellingType = dwellingType;
        this.heatingType = heatingType;
    }

    public double getHomeAge() {
        return homeAge;
    }
    public void setHomeAge(float homeAge) {
        this.homeAge = homeAge;
    }

    public int getDwellingType() {
        return dwellingType;
    }
    public void setDwellingType(int dwellingType) {
        this.dwellingType = dwellingType;
    }

    public int getHeatingType() {
        return heatingType;
    }
    public void setHeatingType(int heatingType) {
        this.heatingType = heatingType;
    }
}
