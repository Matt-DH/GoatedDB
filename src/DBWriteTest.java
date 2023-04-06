import java.util.Scanner;

/**
 * 'TEST'
 * For writing records to the DB
 */

public class DBWriteTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Pick table: 1 - QuoteAuto, 2 - QuoteHome, 3 - Customer");
        String input = sc.next();
        switch (input) {
            case "1":
                writeAutoQuoteRecord();
                break;
            case "2":
                writeHomeQuoteRecord();
                break;
            case "3":
                writeCustomerRecord();
                break;
        }
    }

    public static void writeAutoQuoteRecord() {
        DBManager.addQuoteAuto(-1, 0.00, 0, 0, 0, 0);
    }

    public static void writeHomeQuoteRecord() {

    }

    public static void writeCustomerRecord() {

    }

}
