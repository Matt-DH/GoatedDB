/**
 * 'TEST'
 * Class for testing connections with the DB
 */

public class DBReadTest {

    /**
     * Read quote records from the DB
     */
    public static void main(String args[]) {
        RecordLibrary recordLibrary = DBManager.prepareRecords();
        System.out.println();
        System.out.println("CUSTOMER RECORDS:");
        System.out.println();
        readCustomerRecords(recordLibrary);
        System.out.println("----------");
        System.out.println();
        System.out.println("AUTO QUOTE RECORDS:");
        System.out.println();
        readAutoQuoteRecords(recordLibrary);
        System.out.println("----------");
        System.out.println();
        System.out.println("HOME QUOTE RECORDS:");
        System.out.println();
        readHomeQuoteRecords(recordLibrary);
    }

    public static void readAutoQuoteRecords(RecordLibrary recordLibrary) {
        // Loop through
        for (QuoteAuto quoteAuto : recordLibrary.getAutoList()) {
            // print out the id of the quoteAuto
            System.out.println("Auto quote ID: " + quoteAuto.getId());
            System.out.println("Auto quote customer: " + quoteAuto.getCustomer().getId() + " | " + quoteAuto.getCustomer().getNameFirst() + " " + quoteAuto.getCustomer().getNameLast());
            System.out.println();
        }
    }

    public static void readHomeQuoteRecords(RecordLibrary recordLibrary) {
        // Loop through
        for (QuoteHome quoteHome : recordLibrary.getHomeList()) {
            // print out the id of the quoteHome
            System.out.println("Home quote ID: " + quoteHome.getId());
            System.out.println("Home quote customer: " + quoteHome.getCustomer().getId() + " | " + quoteHome.getCustomer().getNameFirst() + " " + quoteHome.getCustomer().getNameLast());
            System.out.println();
        }
    }

    public static void readCustomerRecords(RecordLibrary recordLibrary) {
        // Loop through
        for (Customer customer : recordLibrary.getCustomerList()) {
            // print out the id of the customer
            System.out.println("Customer ID: " + customer.getId());
            System.out.println("Customer name: " + customer.getNameFirst() + " " + customer.getNameLast());
            System.out.println();
            System.out.println("Customer auto quotes:");
            for (QuoteAuto quoteAuto : customer.getAutoList()) {
                System.out.println(quoteAuto.getId());
            }
            System.out.println("Customer home quotes:");
            for (QuoteHome quoteHome : customer.getHomeList()) {
                System.out.println(quoteHome.getId());
            }
            System.out.println();
        }
    }

}
