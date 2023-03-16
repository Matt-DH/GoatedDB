import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DBManager {

    // Function to generate a quotelibrary object containing quoteauto and quotehome objects for every record in the db
    public static QuoteLibrary loadQuoteLibrary() {
        QuoteLibrary quoteLibrary = new QuoteLibrary(
                loadQuoteAutoList(),
                loadQuoteHomeList()
        );
        return quoteLibrary;
    }

    public static LinkedList<QuoteAuto> loadQuoteAutoList() {
        LinkedList<QuoteAuto> quoteAutoList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM " + DBConfig.DB_GOAT_QUOTESAUTO_TABLENAME + ";";
            ResultSet resultSet = executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("loadQuoteAutoList loop test msg");
                QuoteAuto quoteAuto = new QuoteAuto(
                        resultSet.getInt(DBConfig.DB_GOAT_ID),
                        resultSet.getDouble(DBConfig.DB_GOAT_QUOTESAUTO_CARVALUE),
                        resultSet.getInt(DBConfig.DB_GOAT_QUOTESAUTO_DRIVERAGE),
                        resultSet.getInt(DBConfig.DB_GOAT_QUOTESAUTO_VEHICLEAGE),
                        resultSet.getInt(DBConfig.DB_GOAT_QUOTESAUTO_LOCATION),
                        resultSet.getInt(DBConfig.DB_GOAT_QUOTESAUTO_ACCIDENTS)
                );
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
                System.out.println("loadQuoteHomeList loop test msg");
                QuoteHome quoteHome = new QuoteHome(
                        resultSet.getInt(DBConfig.DB_GOAT_ID),
                        resultSet.getDouble(DBConfig.DB_GOAT_QUOTESHOME_HOUSEAGE),
                        resultSet.getInt(DBConfig.DB_GOAT_QUOTESHOME_DWELLINGTYPE),
                        resultSet.getInt(DBConfig.DB_GOAT_QUOTESHOME_HEATINGTYPE)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quoteHomeList;
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
