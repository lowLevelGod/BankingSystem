package pao.Transaction;

import java.util.Date;

import pao.Account.Account;
import pao.BankException.AccountException;
import pao.Customer.Customer;

public abstract class Transaction implements Comparable<Transaction> {
    private final String id;
    private String details;
    private final Date date;
    private int amount;
    private final Customer customer;
    private final Account account;

    public int compareTo(Transaction other) {
        return this.date.compareTo(other.getDate());
    }

    public Transaction(String id, String details, Date date, int amount, Customer customer, Account account) {
        this.id = id;
        this.details = details;
        this.date = date;
        this.amount = amount;
        this.customer = customer;
        this.account = account;
    }

    public abstract void performTransaction() throws AccountException;

    public String getId() {
        return this.id;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getDate() {
        return this.date;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Account getAccount() {
        return this.account;
    }

}
