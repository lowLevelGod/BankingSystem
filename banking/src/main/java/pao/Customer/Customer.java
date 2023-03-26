package pao.Customer;

import java.util.ArrayList;
import java.util.TreeSet;

import pao.Account.Account;
import pao.BankException.AccountException;
import pao.Transaction.Transaction;

public abstract class Customer {

    private String id;
    // using TreeSet to keep transactions ordered by date
    private TreeSet<Transaction> pendingTransactions;

    public Customer(String id) {
        this.id = id;
        pendingTransactions = new TreeSet<Transaction>();
    }

    public abstract String getName();
    public abstract void setName(String name);

    public abstract Account createAccount(String accId);

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addPendingTransaction(Transaction t) {
        this.pendingTransactions.add(t);
    }

    // returns successsful transactions
    // successful transactions are removed from pending list
    public ArrayList<Transaction> performPendingTransactions() {

        ArrayList<Transaction> successful = new ArrayList<Transaction>();

        for (Transaction t : this.pendingTransactions) {
            try {
                t.performTransaction();
                successful.add(t);
            } catch (AccountException exception) {
                System.out.println(exception.getMessage());
            }
        }

        // remove successful transactions
        for (Transaction t : successful)
            this.pendingTransactions.remove(t);

        return successful;
    }
}
