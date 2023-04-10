import java.util.Scanner;

/**
 * Class for removing all records for db and optionally filling it with dummy data
 */
public class DBReset {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1 - remove all records, 2 - add dummy data, 3 - both");
        String input = sc.next();
        switch (input) {
            case "1":
                removeRecords();
                break;
            case "2":
                addDummyData();
                break;
            case "3":
                removeRecords();
                addDummyData();
                break;
        }
    }

    public static void removeRecords() {
        DBManager.executeUpdate("DELETE FROM " + DBConfig.DB_GOAT_QUOTESAUTO_TABLENAME);
        DBManager.executeUpdate("DELETE FROM " + DBConfig.DB_GOAT_QUOTESHOME_TABLENAME);
        DBManager.executeUpdate("DELETE FROM " + DBConfig.DB_GOAT_CUSTOMERS_TABLENAME);
        DBManager.executeUpdate("DELETE FROM " + DBConfig.DB_GOAT_BRIDGEQUOTESAUTO_TABLENAME);
        DBManager.executeUpdate("DELETE FROM " + DBConfig.DB_GOAT_BRIDGEQUOTESHOME_TABLENAME);
    }

    public static void addDummyData() {
        DBManager.addQuoteAuto(1, 12000.00, 25, 4, 0, 1);
        DBManager.addQuoteAuto(2, 16000.00, 32, 1, 1, 2);
        DBManager.addQuoteAuto(3, 18000.00, 28, 2, 3, 3);
        DBManager.addQuoteAuto(4, 12000.00, 25, 4, 0, 1);
        DBManager.addQuoteAuto(5, 16000.00, 32, 1, 1, 2);
        DBManager.addQuoteAuto(6, 18000.00, 28, 2, 3, 3);

        DBManager.addQuoteHome(1, 5, 1, 1);
        DBManager.addQuoteHome(2, 10, 2, 2);
        DBManager.addQuoteHome(3, 10, 2, 2);
        DBManager.addQuoteHome(4, 12, 3, 3);

        DBManager.addCustomer(1, "Humphrey", "Hubert");
        DBManager.addCustomer(2, "Plumbert", "Johnald");
        DBManager.addCustomer(3, "Lee", "Bart");

        DBManager.addQuoteAutoBridge(1, 1);
        DBManager.addQuoteAutoBridge(2, 2);
        DBManager.addQuoteAutoBridge(2, 3);
        DBManager.addQuoteAutoBridge(2, 4);
        DBManager.addQuoteAutoBridge(3, 5);
        DBManager.addQuoteAutoBridge(3, 6);

        DBManager.addQuoteHomeBridge(1, 1);
        DBManager.addQuoteHomeBridge(2, 2);
        DBManager.addQuoteHomeBridge(2, 3);
        DBManager.addQuoteHomeBridge(3, 4);
    }

}
