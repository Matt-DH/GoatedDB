/**
 * Class which contains functions for calculating and returning values based on given values
 */

public class Calculator {

    /**
     * Calculation for Auto premium.
     * Premium = base (750) * age factor * location factor * accident factor
     * @param quoteAuto
     * @return
     */

    public double calculateAutoPremium(QuoteAuto quoteAuto) {
        double driverAgeFactor = 0;
        double locationFactor = 0;
        double accidentFactor = 0;

        if(quoteAuto.getDriverAge() < 25) {
            driverAgeFactor = 2.0;
        } else {
            driverAgeFactor = 1.0;
        }
        if(quoteAuto.getLocation() == 1) {
            locationFactor = 1.50;
        } else if(quoteAuto.getLocation() == 2) {
            locationFactor = 1.25;
        } else if(quoteAuto.getLocation() == 3) {
            locationFactor = 1.00;
        }
        if(quoteAuto.getAccidents() == 0) {
            accidentFactor = 1.0;
        } else if(quoteAuto.getAccidents() == 1) {
            accidentFactor = 1.25;
        } else if(quoteAuto.getAccidents() == 2) {
            accidentFactor = 2.50;
        }

        double premium = quoteAuto.getBase() * driverAgeFactor * locationFactor * accidentFactor;
        return premium;
    }

    /**
     * Calculation for Home premium.
     * Premium = base (500) * age factor * dwelling factor * heating factor
     * @param quoteHome
     * @return
     */

    public double calculateHomePremium(QuoteHome quoteHome) {
        double ageFactor = 0;
        double dwellingTypeFactor = 0;
        double heatingTypeFactor = 0;

        if(quoteHome.getHomeAge() < 25) {
            ageFactor = 1.0;
        } else if(50 > quoteHome.getHomeAge() && quoteHome.getHomeAge() >= 25) {
            ageFactor = 1.25;
        } else if(quoteHome.getHomeAge() >= 50) {
            ageFactor = 1.5;
        }

        if(quoteHome.getDwellingType() == 1) {
            dwellingTypeFactor = 1.0;
        } else if(quoteHome.getDwellingType() == 2) {
            dwellingTypeFactor = 0.75;
        } else if(quoteHome.getDwellingType() == 3) {
            dwellingTypeFactor = 1.0;
        } else if(quoteHome.getDwellingType() == 4) {
            dwellingTypeFactor = 1.15;
        }

        if(quoteHome.getHeatingType() == 1) {
            heatingTypeFactor = 1.0;
        } else if(quoteHome.getHeatingType() == 2) {
            heatingTypeFactor = 2.0;
        } else if(quoteHome.getHeatingType() == 3) {
            heatingTypeFactor = 1.25;
        } else if(quoteHome.getHeatingType() == 4) {
            heatingTypeFactor = 1.0;
        } else if(quoteHome.getHeatingType() == 5) {
            heatingTypeFactor = 1.0;
        }

        double premium = quoteHome.getBase() * ageFactor * dwellingTypeFactor * heatingTypeFactor;
        return premium;
    }

}
