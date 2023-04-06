import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

/**
 * This class communicates with the interface, calculator, and 'blueprint' classes,
 * and handles the communication with the db.
 */

public class DBManager {

    // Function to instantiate and return a quotelibrary object containing quoteauto and quotehome objects for every record in the db
    public static RecordLibrary loadRecords() {
        RecordLibrary recordLibrary = new RecordLibrary(
                loadQuoteAutoList(),
                loadQuoteHomeList(),
                loadCustomerList()
        );
        return recordLibrary;
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
