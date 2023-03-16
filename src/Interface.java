import java.sql.*;
import java.util.Scanner;

public class Interface {

    public static void main(String[] args) {
        QuoteLibrary quoteLibrary = DBManager.loadQuoteLibrary();

        for (QuoteAuto quoteAuto : quoteLibrary.getAutoList()) {
            // print out the index of the quoteAuto
            System.out.println("quoteauto loop");
            System.out.println(quoteLibrary.getAutoList().indexOf(quoteAuto));
        }
        for (QuoteHome quoteHome : quoteLibrary.getHomeList()) {
            // print out the index of the quoteHome
            System.out.println("quotehome loop");
            System.out.println(quoteLibrary.getHomeList().indexOf(quoteHome));

        }

    }

//    public static void main(String[] args) {
//
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter your email :");
//        String email = scanner.nextLine();
//
//        System.out.print("Enter your passkey :");
//        String passKey = scanner.nextLine();
//
//        try {
//            Connection conn = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASS);
//            Statement stmt = conn.createStatement();
//            String sql = "SELECT * FROM users WHERE email='" + email + "' AND passkey='" + passKey + "'";
//            ResultSet resultSet = stmt.executeQuery(sql);
//
//            if (resultSet.next()) {
//                System.out.println("You have logged in");
//
//                System.out.print("Enter your age :");
//                int age = scanner.nextInt();
//
//                System.out.print("Enter your vehicle age :");
//                int vehicleAge = scanner.nextInt();
//
//                System.out.print("Enter your incident count :");
//                int incidents = scanner.nextInt();
//
//                float premium = 0;
//
//                if (age < 25) {
//                    premium += 150.0000;
//                }
//
//                else if (age > 25) {
//                    premium += 750.00;
//                }
//
//                if (vehicleAge > 50) {
//                    premium += 500.00;
//                }
//
//                else if (vehicleAge < 30) {
//                    premium += 203.00;
//                }
//
//                if (incidents < 3) {
//                    premium += 324.00;
//                }
//
//                else if (incidents > 3) {
//                    System.out.println("You do not qualify for premium");
//                    System.exit(500);
//                }
//
//                else if (incidents > 30) {
//                    System.out.println("You need help, not from us though");
//                    System.exit(500);
//                }
//
//                System.out.printf("The insurance is premium :" + premium);
//            }
//            else {
//                System.out.println("Security breach");
//                System.exit(5000);
//            }
//
//            resultSet.close();
//            stmt.close();
//            conn.close();
//        } catch (Exception var14) {
//            var14.printStackTrace();
//        }
//    }


}