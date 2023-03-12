package pao.Customer;

import java.util.ArrayList;
import java.util.Collections;

import pao.Account.Account;
import pao.Transaction.Transaction;

public abstract class Customer {

    private String id;
    private ArrayList<Transaction> pendingTransactions;

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
        Collections.sort(pendingTransactions,
                (t1, t2) -> t1.getDate().compareTo(t2.getDate()));
    }

    public ArrayList<Transaction> performPendingTransactions() {
        ArrayList<Transaction> failed = new ArrayList<Transaction>();

        for (Transaction t : this.pendingTransactions) {
            boolean result = t.performTransaction();
            if (!result)
                failed.add(t);
        }

        return failed;
    }
}
