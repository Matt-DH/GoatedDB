import java.sql.*;
import java.text.Format;
import java.util.LinkedList;
import java.util.List;

/**
 * This class communicates with the interface, calculator, and 'blueprint' classes,
 * and handles the communication with the db.
 */

public class DBManager {

    public static RecordLibrary prepareRecords() {
        RecordLibrary recordLibrary = loadRecords();
        loadRelationships(recordLibrary);
        return recordLibrary;
    }

    // Function to instantiate and return a quotelibrary object containing quoteauto and quotehome objects for every record in the db
    public static RecordLibrary loadRecords() {
        RecordLibrary recordLibrary = new RecordLibrary(
                loadQuoteAutoList(),
                loadQuoteHomeList(),
                loadCustomerList()
        );
        return recordLibrary;
    }

    public static void loadRelationships(RecordLibrary recordLibrary) {
        buildQuotesAutoRelationships(recordLibrary.getCustomerList(), recordLibrary.getAutoList());
        buildQuotesHomeRelationships(recordLibrary.getCustomerList(), recordLibrary.getHomeList());
    }

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
     * Function to instantiate objects for each AutoQuote record in the db, append them to a new LinkedList, and return the LinkedList
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

    public static void connectionClose(Connection connection) {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
