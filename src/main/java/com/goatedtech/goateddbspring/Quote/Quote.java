package com.goatedtech.goateddbspring.Quote;

import com.goatedtech.goateddbspring.Customer.Customer;

/**
 * 'BLUEPRINT'
 * Quote parent class
 */

public abstract class Quote {

    // VARIABLES
    private int id = 0;
    private double base = 0.00;
    private double total = 0.00;
    private Customer customer;

    // CONSTRUCTORS
    public Quote() {
        this.base = 0;
        this.total = 0;
    }

    public Quote(double base, double total) {
        this.base = base;
        this.total = total;
    }

    // SETTERS
    public void setBase(double base) {
        this.base = base;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // GETTERS
    public double getBase() {
        return base;
    }

    public double getTotal() {
        return total;
    }

    public Customer getCustomer() {
        return customer;
    }

}