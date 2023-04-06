/**
 * Interface containing all information related to the db
 */

public interface DBConfig {

    // MariaDB Driver
    String MARIA_DB_DRIVER = "org.mariadb.jdbc.Driver";

    // Database connection details
    String DB_URL = "jdbc:mariadb://localhost:3306";
    String DB_USER = "root";
    String DB_PASS = "qwerty";
    String DB_GOAT = "/goateddb";

    // DB tables names
    String DB_GOAT_QUOTESAUTO_TABLENAME = "quotesauto";
    String DB_GOAT_QUOTESHOME_TABLENAME = "quoteshome";
    String DB_GOAT_CUSTOMERS_TABLENAME = "customers";

    // Bridge table names
    String DB_GOAT_BRIDGEQUOTESAUTO = "bridge_quotesauto";
    String DB_GOAT_BRIDGEQUOTESHOME = "bridge_quoteshome";

    // Data table id field (applies to 3 data tables)
    String DB_GOAT_ID = "id";

    // quotes_auto table fields
    String DB_GOAT_QUOTESAUTO_CARVALUE = "car_value";
    String DB_GOAT_QUOTESAUTO_DRIVERAGE = "driver_age";
    String DB_GOAT_QUOTESAUTO_VEHICLEAGE = "vehicle_age";
    String DB_GOAT_QUOTESAUTO_LOCATION = "location";
    String DB_GOAT_QUOTESAUTO_ACCIDENTS = "accidents";

    // quotes_home table fields
    String DB_GOAT_QUOTESHOME_HOUSEAGE = "house_age";
    String DB_GOAT_QUOTESHOME_HEATINGTYPE = "heating_type";
    String DB_GOAT_QUOTESHOME_DWELLINGTYPE = "dwelling_type";

    // customer table fields
    String DB_GOAT_CUSTOMERS_NAMEFIRST = "name_first";
    String DB_GOAT_CUSTOMERS_NAMELAST = "name_last";

    // bridge table fields (applies to both bridge tables)
    String DB_GOAT_BRIDGE_CUSTOMERID = "customer_id";
    String DB_GOAT_BRIDGE_QUOTEID = "quote_id";

}
