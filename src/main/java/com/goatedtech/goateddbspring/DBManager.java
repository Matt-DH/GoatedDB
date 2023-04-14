package com.goatedtech.goateddbspring;

import com.goatedtech.goateddbspring.Customer.Customer;
import com.goatedtech.goateddbspring.QuoteAuto.QuoteAuto;
import com.goatedtech.goateddbspring.QuoteHome.QuoteHome;
import com.goatedtech.goateddbspring.RecordLibrary.RecordLibrary;

import java.sql.*;
import java.text.Format;
import java.util.LinkedList;
import java.util.List;

/**
 * This class communicates with the interface, calculator, and 'blueprint' classes,
 * and handles the communication with the db.
 */

public class DBManager {

    public static RecordLibrary recordLibrary;
    public static Calculator calculator = new Calculator();

    /**
     * This function instantiates and loads the data and relationships for the recordLibrary
     * @return
     */
    public static RecordLibrary prepareRecords() {
        recordLibrary = loadRecords();
        calculatePremiums(recordLibrary);
        loadRelationships(recordLibrary);
        return recordLibrary;
    }

    /**
     * Loads the records into the recordLibrary object
     * @return
     */
    public static RecordLibrary loadRecords() {
        RecordLibrary recordLibrary = new RecordLibrary(
                loadQuoteAutoList(),
                loadQuoteHomeList(),
                loadCustomerList()
        );
        return recordLibrary;
    }

    /**
     * Runs through all quotes in the recordLibrary object and calculates the total premium
     * using the calculator class
     * @param recordLibrary
     */
    public static void calculatePremiums(RecordLibrary recordLibrary) {
        for (QuoteAuto quoteAuto : recordLibrary.getAutoList()) {
            quoteAuto.setTotal(calculator.calculateAutoPremium(quoteAuto));
        }
        for (QuoteHome quoteHome : recordLibrary.getHomeList()) {
            quoteHome.setTotal(calculator.calculateHomePremium(quoteHome));
        }
    }

    /**
     * Loads the relationships for all the quotes and customers in the recordLibrary
     * @param recordLibrary
     */
    public static void loadRelationships(RecordLibrary recordLibrary) {
        buildQuotesAutoRelationships(recordLibrary.getCustomerList(), recordLibrary.getAutoList());
        buildQuotesHomeRelationships(recordLibrary.getCustomerList(), recordLibrary.getHomeList());
    }

    /**
     * Uses the bridge table in the DB to build relationships between auto quotes and customers
     * @param customerList
     * @param autoList
     */
    private static void buildQuotesAutoRelationships(List<Customer> customerList, List<QuoteAuto> autoList) {
        try {
            String sql = String.format("SELECT * FROM %s", DBConfig.DB_GOAT_BRIDGEQUOTESAUTO_TABLENAME);
            ResultSet resultSet = executeQuery(sql);
            while (resultSet.next()) {
                int customerId = resultSet.getInt(DBConfig.DB_GOAT_BRIDGE_CUSTOMERID);
                int quoteId = resultSet.getInt(DBConfig.DB_GOAT_BRIDGE_QUOTEID);
                Customer customer = null;
                QuoteAuto quoteAuto = null;

                for (Customer c : customerList) {
                    if (c.getId() == customerId) {
                        customer = c;
                    }
                }

                for (QuoteAuto q : autoList) {
                    if (q.getId() == quoteId) {
                        quoteAuto = q;
                    }
                }

                customer.addQuoteAuto(quoteAuto);
                quoteAuto.setCustomer(customer);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Uses the bridge table in the DB to build relationships between home quotes and customers
     * @param customerList
     * @param homeList
     */
    private static void buildQuotesHomeRelationships(List<Customer> customerList, List<QuoteHome> homeList) {
        try {
            String sql = String.format("SELECT * FROM %s", DBConfig.DB_GOAT_BRIDGEQUOTESHOME_TABLENAME);
            ResultSet resultSet = executeQuery(sql);
            while (resultSet.next()) {
                int customerId = resultSet.getInt(DBConfig.DB_GOAT_BRIDGE_CUSTOMERID);
                int quoteId = resultSet.getInt(DBConfig.DB_GOAT_BRIDGE_QUOTEID);
                Customer customer = null;
                QuoteHome quoteHome = null;

                for (Customer c : customerList) {
                    if (c.getId() == customerId) {
                        customer = c;
                    }
                }

                for (QuoteHome q : homeList) {
                    if (q.getId() == quoteId) {
                        quoteHome = q;
                    }
                }

                customer.addQuoteHome(quoteHome);
                quoteHome.setCustomer(customer);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function to instantiate objects for each AutoQuote record in the db,
     * append them to a new LinkedList, and return the LinkedList
     * @return
     */
    public static LinkedList<QuoteAuto> loadQuoteAutoList() {
        LinkedList<QuoteAuto> quoteAutoList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM " + DBConfig.DB_GOAT_QUOTESAUTO_TABLENAME + ";";
            ResultSet resultSet = executeQuery(sql);
            while (resultSet.next()) {
                QuoteAuto quoteAuto = new QuoteAuto(
                        resultSet.getInt(DBConfig.DB_GOAT_ID),
                        resultSet.getDouble(DBConfig.DB_GOAT_QUOTESAUTO_CARVALUE),
                        resultSet.getInt(DBConfig.DB_GOAT_QUOTESAUTO_DRIVERAGE),
                        resultSet.getInt(DBConfig.DB_GOAT_QUOTESAUTO_VEHICLEAGE),
                        resultSet.getInt(DBConfig.DB_GOAT_QUOTESAUTO_LOCATION),
                        resultSet.getInt(DBConfig.DB_GOAT_QUOTESAUTO_ACCIDENTS)
                );
                quoteAutoList.add(quoteAuto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quoteAutoList;
    }

    /**
     * Function to instantiate objects for each HomeQuote record in the db,
     * append them to a new LinkedList, and return the LinkedList
     * @return
     */
    public static LinkedList<QuoteHome> loadQuoteHomeList() {
        LinkedList<QuoteHome> quoteHomeList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM " + DBConfig.DB_GOAT_QUOTESHOME_TABLENAME + ";";
            ResultSet resultSet = executeQuery(sql);
            while (resultSet.next()) {
                QuoteHome quoteHome = new QuoteHome(
                        resultSet.getInt(DBConfig.DB_GOAT_ID),
                        resultSet.getDouble(DBConfig.DB_GOAT_QUOTESHOME_HOUSEAGE),
                        resultSet.getInt(DBConfig.DB_GOAT_QUOTESHOME_DWELLINGTYPE),
                        resultSet.getInt(DBConfig.DB_GOAT_QUOTESHOME_HEATINGTYPE)
                );
                quoteHomeList.add(quoteHome);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quoteHomeList;
    }

    /**
     * Function to instantiate objects for each Customer record in the db,
     * append them to a new LinkedList, and return the LinkedList
     * @return
     */
    public static LinkedList<Customer> loadCustomerList() {
        LinkedList<Customer> customerList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM " + DBConfig.DB_GOAT_CUSTOMERS_TABLENAME +";";
            ResultSet resultSet = executeQuery(sql);
            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getInt(DBConfig.DB_GOAT_ID),
                        resultSet.getString(DBConfig.DB_GOAT_CUSTOMERS_NAMELAST),
                        resultSet.getString(DBConfig.DB_GOAT_CUSTOMERS_NAMEFIRST)
                );
                customerList.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerList;
    }

    /**
     * Performs CRUD operation Delete on all records in the Customer
     * table in the DB
     */
    public static void deleteAllCustomers() {
        try {
            String sql = "DELETE FROM " + DBConfig.DB_GOAT_CUSTOMERS_TABLENAME + ";";
            System.out.println(sql);
            executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Performs CRUD operation CREATE on the quoteauto table in the DB
     * @param id
     * @param value
     * @param driverAge
     * @param vehicleAge
     * @param accidents
     * @param location
     */
    public static void addQuoteAuto(int id, double value, int driverAge, int vehicleAge, int accidents, int location) {
        try {
            String sql = "INSERT INTO " + DBConfig.DB_GOAT_QUOTESAUTO_TABLENAME + " VALUES (" +
                    id + ", " +
                    value + ", " +
                    driverAge + ", " +
                    vehicleAge + ", " +
                    accidents + ", " +
                    location +
                    ");";
            executeUpdate(sql);
        }   catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Performs CRUD operation CREATE on quotehome table in the db
     * @param id
     * @param houseAge
     * @param heatingType
     * @param dwellingType
     */
    public static void addQuoteHome(int id, double houseAge, int heatingType, int dwellingType) {
        try {
            String sql = "INSERT INTO " + DBConfig.DB_GOAT_QUOTESHOME_TABLENAME + " VALUES (" +
                    id + ", " +
                    houseAge + ", " +
                    heatingType + ", " +
                    dwellingType +
                    ");";
            executeUpdate(sql);
        }   catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Performs CRUD operation CREATE on customer table in the DB
     * @param id
     * @param nameLast
     * @param nameFirst
     */
    public static void addCustomer(int id, String nameLast, String nameFirst) {
        try {
            String sql = "INSERT INTO " + DBConfig.DB_GOAT_CUSTOMERS_TABLENAME + " VALUES (" +
                    id + ", " +
                    "\"" + nameLast + "\"" + ", " +
                    "\"" + nameFirst + "\"" +
                    ");";
            executeUpdate(sql);
        }   catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Perform CRUD operation UPDATE on customer table in the db
     * @param id
     * @param nameLast
     * @param nameFirst
     */
    public static void updateCustomer(int id, String nameLast, String nameFirst) {
        try {
            String sql = String.format("UPDATE %s SET %s = \"%s\", %s = \"%s\" WHERE %s = \"%s\";",
                    DBConfig.DB_GOAT_CUSTOMERS_TABLENAME,
                    DBConfig.DB_GOAT_CUSTOMERS_NAMELAST,
                    nameLast,
                    DBConfig.DB_GOAT_CUSTOMERS_NAMEFIRST,
                    nameFirst,
                    DBConfig.DB_GOAT_ID,
                    id);
            System.out.println(sql);
            executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Perform CRUD operation CREATE on quoteauto bridge table in the DB
     * @param customerid
     * @param quoteid
     */
    public static void addQuoteAutoBridge(int customerid, int quoteid) {
        try {
            String sql = "INSERT INTO " + DBConfig.DB_GOAT_BRIDGEQUOTESAUTO_TABLENAME + " VALUES (" +
                    customerid + ", " +
                    "\"" + quoteid + "\"" +
                    ");";
            executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Perform CRUD operation CREATE on quotehome bridge table in the DB
     * @param customerid
     * @param quoteid
     */
    public static void addQuoteHomeBridge(int customerid, int quoteid) {
        try {
            String sql = "INSERT INTO " + DBConfig.DB_GOAT_BRIDGEQUOTESHOME_TABLENAME + " VALUES (" +
                    customerid + ", " +
                    "\"" + quoteid + "\"" +
                    ");";
            executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Try creating and returning a new connection object
     * @return
     */
    public static Connection connectionOpen() {
        Connection returnConnection = null;
        try {
            Connection connection = DriverManager.getConnection(DBConfig.DB_URL + DBConfig.DB_GOAT, DBConfig.DB_USER, DBConfig.DB_PASS);
            returnConnection = connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnConnection;
    }

    /**
     * Gets passed a connection object to try closing
     * @param connection
     */
    public static void connectionClose(Connection connection) {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a passed String to the DB as an update
     * @param query
     */
    public static void executeUpdate(String query) {
        Connection connection = connectionOpen();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionClose(connection);
        }
    }

    /**
     * Sends a passed String to the DB as a query and returns query result set
     * @param query
     * @return
     */
    public static ResultSet executeQuery(String query) {
        Connection connection = connectionOpen();
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionClose(connection);
        }
        return resultSet;
    }
}
