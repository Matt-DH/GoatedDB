package com.goatedtech.goateddbspring.QuoteAuto;

import com.goatedtech.goateddbspring.Quote.Quote;

/**
 * 'BLUEPRINT'
 * Child class for AutoQuote records from DB
 */

public class QuoteAuto extends Quote {

    // VARIABLES
    public final double BASE = 750.00;

    private int id;
    private double total;
    private double carValue;
    private int driverAge;
    private int vehicleAge;
    private int location;
    private int accidents;

    // CONSTRUCTORS
    public QuoteAuto() {
        this.id = -1;
        this.carValue = 0;
        this.driverAge = 0;
        this.vehicleAge = 0;
        this.location = 0;
        this.accidents = 0;
    }

    public QuoteAuto(int id, double carValue, int driverAge, int vehicleAge, int location, int accidents) {
        this.id = id;
        this.carValue = carValue;
        this.driverAge = driverAge;
        this.vehicleAge = vehicleAge;
        this.location = location;
        this.accidents = accidents;
    }

    // SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setCarValue(double carValue) {
        this.carValue = carValue;
    }

    public void setDriverAge(int driverAge) {
        this.driverAge = driverAge;
    }

    public void setVehicleAge(int vehicleAge) {
        this.vehicleAge = vehicleAge;
    }

    public void setLocation(int location) { this.location = location; }

    public void setAccidents(int accidents) {
        this.accidents = accidents;
    }

    // GETTERS

    public double getBase() {
        return BASE;
    }

    public double getTotal() {
        return total;
    }

    public int getId() {
        return id;
    }

    public double getCarValue() {
        return carValue;
    }

    public int getDriverAge() {
        return driverAge;
    }

    public int getVehicleAge() {
        return vehicleAge;
    }

    public int getLocation() { return location; }

    public int getAccidents() {
        return accidents;
    }

    // METHODS
    public String getCustomerDetails() {
        return String.format("%d | $s $s",
                this.getCustomer().getId(),
                this.getCustomer().getNameFirst(),
                this.getCustomer().getNameLast());
    }

}
