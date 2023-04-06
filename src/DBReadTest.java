/**
 * 'TEST'
 * Class for testing connections with the DB
 */

public class DBReadTest {

    /**
     * Read quote records from the DB
     */
    public static void main(String args[]) {
        RecordLibrary recordLibrary = DBManager.loadRecords();
        readAutoQuoteRecords(recordLibrary);
        readHomeQuoteRecords(recordLibrary);
        readCustomerRecords(recordLibrary);
    }

    public static void readAutoQuoteRecords(RecordLibrary recordLibrary) {
        // Loop through
        for (QuoteAuto quoteAuto : recordLibrary.getAutoList()) {
            // print out the id of the quoteAuto
            System.out.println("Auto quote ID: " + quoteAuto.getId());
        }
    }

    public static void readHomeQuoteRecords(RecordLibrary recordLibrary) {
        // Loop through
        for (QuoteHome quoteHome : recordLibrary.getHomeList()) {
            // print out the id of the quoteHome
            System.out.println("Home quote ID: " + quoteHome.getId());
        }
    }

    public static void readCustomerRecords(RecordLibrary recordLibrary) {
        // Loop through
        for (Customer customer : recordLibrary.getCustomerList()) {
            // print out the id of the customer
            System.out.println("Customer ID: " + customer.getId());
        }
    }

}
