package pao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class Main {
    private static final Set<String> commands = Set.of("create_natural_customer", "create_artificial_customer",
            "read_customer",
            "update_customer", "delete_customer", "create_base_account", "read_account", "update_account",
            "delete_account", "create_credit_card", "create_debit_card", "read_card", "update_card", "delete_card",
            "create_deposit_transaction", "create_withdraw_transaction", "create_transfer_transaction",
            "read_transaction", "update_transaction", "delete_transaction");

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/bank";
            String user = "root";
            String password = "1234";

            Connection connection = DriverManager.getConnection(url, user, password);
            // System.out.println("It works");
            return connection;
        } catch (SQLException e) {
            System.out.println(e.toString());

            return null;
        }
    }

    public static void main(String[] args) {
        Connection connection = getConnection();
        SystemFacade systemFacade = new SystemFacade(connection);
        // Demo demo = new Demo(connection);
        // // demo customer service
        // demo.customer();

        // // demo account service
        // demo.account();

        // // demo transaction service
        // demo.transaction();

        // // demo card service
        // demo.card();

        Scanner in = new Scanner(System.in);

        boolean end = false;
        while (!end) {
            System.out.println("Insert command: (help - see commands)");
            String command = in.nextLine().toLowerCase();
            try {
                switch (command) {
                    case "create_natural_customer" -> systemFacade.createNaturalCustomer(in);
                    case "create_artificial_customer" -> systemFacade.createArtificialCustomer(in);
                    case "read_customer" -> systemFacade.readCustomer(in);
                    case "update_customer" -> systemFacade.updateCustomer(in);
                    case "delete_customer" -> systemFacade.deleteCustomer(in);
                    case "create_base_account" -> systemFacade.createBaseAccount(in);
                    case "read_account" -> systemFacade.readAccount(in);
                    case "update_account" -> systemFacade.updateAccount(in);
                    case "delete_account" -> systemFacade.deleteAccount(in);
                    case "create_credit_card" -> systemFacade.createCreditCard(in);
                    case "create_debit_card" -> systemFacade.createDebitCard(in);
                    case "read_card" -> systemFacade.readCard(in);
                    case "update_card" -> systemFacade.updateCard(in);
                    case "delete_card" -> systemFacade.deleteCard(in);
                    case "create_deposit_transaction" -> systemFacade.createTransactionDeposit(in);
                    case "create_withdraw_transaction" -> systemFacade.createTransactionWithDraw(in);
                    case "create_transfer_transaction" -> systemFacade.createTransactionTransfer(in);
                    case "read_transaction" -> systemFacade.readTransaction(in);
                    case "update_transaction" -> systemFacade.updateTransaction(in);
                    case "delete_transaction" -> systemFacade.deleteTransaction(in);
                    case "help" -> System.out.println(commands.toString());
                    case "end" -> end = true;
                }
                if (commands.contains(command))
                    AuditService.getInstance().logAction(command);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        try {
            assert connection != null;
            connection.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
