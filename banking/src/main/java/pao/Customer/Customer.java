package pao.Customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import pao.Account.Account;
import pao.BankException.AccountException;
import pao.Transaction.Transaction;

public abstract class Customer {

    private String id;
    private ArrayList<Transaction> pendingTransactions;

    private void sortTransactionsByDate(){
        Collections.sort(pendingTransactions,
                (t1, t2) -> t1.getDate().compareTo(t2.getDate()));
    }

    public Customer(String id) {
        this.id = id;
        pendingTransactions = new ArrayList<Transaction>();
    }

    public abstract String getName();

    public abstract Account createAccount(String accId);

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addPendingTransaction(Transaction t) {
        this.pendingTransactions.add(t);

        // keep transactions sorted after adding new pending
        sortTransactionsByDate();
    }

    // returns successsful transactions
    // successful transactions are removed from pending list
    public ArrayList<Transaction> performPendingTransactions() {

        ArrayList<Transaction> successful = new ArrayList<Transaction>();

        for (Transaction t : this.pendingTransactions) {
            try {
                t.performTransaction();
                successful.add(t);
                this.pendingTransactions.remove(t);
            } catch (AccountException exception) {
                System.out.println(exception.getMessage());
            }
        }

        // keep transactions sorted after removing successful ones
        sortTransactionsByDate();

        return successful;
    }
}
