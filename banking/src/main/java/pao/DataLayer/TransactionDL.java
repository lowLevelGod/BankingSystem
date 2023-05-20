package pao.DataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pao.Account.Account;
import pao.BankException.TransactionException;
import pao.Customer.Customer;
import pao.Transaction.Deposit;
import pao.Transaction.Transaction;
import pao.Transaction.Withdraw;

public class TransactionDL {

    // placeholder for database service
    // private HashMap<String, Transaction> transactions;
    private final Connection connection;

    public TransactionDL(Connection connection) {
        // transactions = new HashMap<String, Transaction>();
        this.connection = connection;
    }

    public void createTransaction(Transaction transaction) throws TransactionException {
        try {
            String query = "INSERT INTO Transaction (id, details, date, amount, customer, account, type) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, transaction.getId());
            preparedStmt.setString(2, transaction.getDetails());
            preparedStmt.setDate(3, new java.sql.Date(transaction.getDate().getTime()));
            preparedStmt.setInt(4, transaction.getAmount());
            preparedStmt.setString(5, transaction.getCustomer().getId());
            preparedStmt.setString(6, transaction.getAccount().getId());
            preparedStmt.setString(7, transaction.getType());
            preparedStmt.execute();
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new TransactionException("Failed to add new transaction.");
        }

    }

    public void deleteTransaction(String id) throws TransactionException {

        int res = 0;
        try {
            String query = "DELETE FROM Transaction WHERE id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, id);
            res = preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new TransactionException("Failed to delete transaction. ID " + id + " does not exist!");
        }

        if (res == 0) {
            throw new TransactionException("Failed to delete transaction. ID " + id + " does not exist!");
        }
    }

    public Transaction readTransaction(String id) throws TransactionException {

        Transaction transaction = null;
        try {

            String query = "SELECT * FROM Transaction WHERE id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, id);

            ResultSet result = preparedStmt.executeQuery();
            result.next();

            AccountDL aDl = new AccountDL(connection);
            CustomerDL cDl = new CustomerDL(connection);

            Customer customer = cDl.readCustomer(result.getString("customer"));
            Account account = aDl.readAccount(result.getString("account"));

            if (result.getString("type").equals("Withdraw Transaction")) {
                transaction = new Withdraw(id, customer, account, result);
            } else {
                transaction = new Deposit(id, customer, account, result);
            }
            preparedStmt.close();

        } catch (Exception e) {
            System.out.println(e.toString());
            throw new TransactionException("Failed to retrieve transaction data. ID " + id + " does not exist!");
        }

        return transaction;
    }

    public void updateTransaction(Transaction transaction) throws TransactionException {

        int res = 0;
        try {
            String query = "UPDATE Transaction SET details = ? WHERE id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, transaction.getDetails());
            preparedStmt.setString(2, transaction.getId());
            res = preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new TransactionException(
                    "Failed to update transaction data. ID " + transaction.getId() + " does not exist!");
        }

        if (res == 0) {
            throw new TransactionException(
                    "Failed to update transaction data. ID " + transaction.getId() + " does not exist!");
        }
    }

    // only successful transactions will be stored
    public List<Transaction> storePendingTransactions(Customer customer) throws TransactionException {

        List<Transaction> res = new ArrayList<Transaction>();
        for (Transaction t : customer.performPendingTransactions()) {

            this.createTransaction(t);
            res.add(t);

        }
        return res;
    }
}
