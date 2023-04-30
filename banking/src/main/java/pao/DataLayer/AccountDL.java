package pao.DataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import pao.Account.Account;
import pao.BankException.AccountException;
import pao.Customer.Customer;

public class AccountDL {

    // placeholder for database service
    // private HashMap<String, Account> accounts;
    private Connection connection;

    public AccountDL(Connection connection) {
        // accounts = new HashMap<String, Account>();
        this.connection = connection;
    }

    public void createAccount(Account account) throws AccountException {

        try {
            String query = "INSERT INTO Account (id, interest, owner, amount) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, account.getId());
            preparedStmt.setInt(2, account.getInterest());
            preparedStmt.setString(3, account.getOwner().getId());
            preparedStmt.setInt(4, account.getAmount());
            preparedStmt.execute();
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new AccountException("Failed to add new account");
        }
    }

    public void deleteAccount(String id) throws AccountException {

        try {
            String query = "DELETE FROM Account WHERE id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, id);
            preparedStmt.execute();
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new AccountException("Failed to delete account. ID " + id + " does not exist!");
        }
    }

    public void updateAccount(Account account) throws AccountException {

        try {
            String query = "UPDATE Account SET interest = ?, owner = ?, amount = ? WHERE id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, account.getInterest());
            preparedStmt.setString(2, account.getOwner().getId());
            preparedStmt.setInt(3, account.getAmount());
            preparedStmt.setString(4, account.getId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new AccountException("Failed to update account data. ID " + account.getId() + " does not exist!");
        }
    }

    public Account readAccount(String id) throws AccountException {
        Account account = null;
        try {

            String query = "SELECT * FROM Account WHERE id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, id);

            ResultSet result = preparedStmt.executeQuery();
            result.next();

            CustomerDL cDl = new CustomerDL(connection);
            Customer owner = cDl.readCustomer(result.getString("owner"));

            account = new Account(id, owner, result);
            preparedStmt.close();

        } catch (Exception e) {
            System.out.println(e.toString());
            throw new AccountException("Failed to retrieve account data. ID " + id + " does not exist!");
        }

        return account;
    }
}
