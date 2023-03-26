package pao.DataLayer;

import java.util.ArrayList;
import java.util.HashMap;

import pao.BankException.TransactionException;
import pao.Customer.Customer;
import pao.Transaction.Transaction;

public class TransactionDL {

    // placeholder for database service
    private HashMap<String, Transaction> transactions;

    public TransactionDL() {
        transactions = new HashMap<String, Transaction>();
    }

    private void createTransaction(Transaction transaction) throws TransactionException {

        String id = transaction.getId();
        if (!transactions.containsKey(id)) {
            transactions.put(id, transaction);
        } else {
            throw new TransactionException("Failed to add new transaction. ID " + id + " already exists!");
        }
    }

    public void deleteTransaction(String id) throws TransactionException {

        if (transactions.remove(id) == null) {
            throw new TransactionException("Failed to delete transaction. ID " + id + " does not exist!");
        }
    }

    public Transaction readTransaction(String id) throws TransactionException {

        Transaction transaction = transactions.get(id);
        if (transaction == null) {
            throw new TransactionException("Failed to retrieve transaction data. ID " + id + " does not exist!");
        }

        return transaction;
    }

    // only successful transactions will be stored
    public ArrayList<Transaction> storePendingTransactions(Customer customer) {

        ArrayList<Transaction> res = new ArrayList<Transaction>();
        for (Transaction t : customer.performPendingTransactions()) {
            try {
                this.createTransaction(t);
                res.add(t);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return res;
    }
}
