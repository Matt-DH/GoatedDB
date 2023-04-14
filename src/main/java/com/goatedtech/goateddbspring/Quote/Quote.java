package com.goatedtech.goateddbspring.Quote;

import com.goatedtech.goateddbspring.Customer.Customer;

/**
 * 'BLUEPRINT'
 * Quote parent class
 */

public abstract class Quote {

    // VARIABLES
    private int id;
    private double base;
    private double total;
    private Customer customer;

    // SETTERS

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // GETTERS

    public Customer getCustomer() {
        return customer;
    }

}