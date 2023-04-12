package com.goatedtech.goateddbspring.RecordLibrary;

import com.goatedtech.goateddbspring.Customer.Customer;
import com.goatedtech.goateddbspring.QuoteAuto.QuoteAuto;
import com.goatedtech.goateddbspring.QuoteHome.QuoteHome;

import java.util.LinkedList;
import java.util.List;

/**
 * 'BLUEPRINT'
 * A QuoteLibrary holds a list of all AutoQuote and HomeQuote objects
 */

public class RecordLibrary {

    // VARIABLES
    private List<QuoteAuto> autoList;
    private List<QuoteHome> homeList;
    private List<Customer> customerList;

    // CONSTRUCTORS
    public RecordLibrary(){
        this.autoList = new LinkedList<>();
        this.homeList = new LinkedList<>();
        this.customerList = new LinkedList<>();
    }

    public RecordLibrary(LinkedList<QuoteAuto> autoList, LinkedList<QuoteHome> homeList, LinkedList<Customer> customerList) {
        this.autoList = autoList;
        this.homeList = homeList;
        this.customerList = customerList;
    }

    // SETTERS
    public void setAutoList(List<QuoteAuto> autoList){
        this.autoList = autoList;
    }

    public void setHomeList(List<QuoteHome> homeList){
        this.homeList = homeList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    // GETTERS
    public List<QuoteAuto> getAutoList(){
        return autoList;
    }

    public List<QuoteHome> getHomeList(){
        return homeList;
    }

    public List<Customer> getCustomerList() {
        return this.customerList;
    }

    // METHODS
    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public void addQuoteAuto(QuoteAuto quoteAuto) {
        autoList.add(quoteAuto);
    }

    public void addQuoteHome(QuoteHome quoteHome) {
        homeList.add(quoteHome);
    }

}
