package pao.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import pao.Account.Account;
import pao.BankException.AccountException;
import pao.Customer.Customer;
import pao.Utils.Typeable;

public abstract class Transaction implements Comparable<Transaction>, Typeable {
    private final String id;
    private String details;
    private final Date date;
    private final int amount;
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

    public Transaction(String id, Customer customer, Account account, ResultSet dbRow) throws SQLException{
        this(id, dbRow.getString("details"), dbRow.getDate("date"), dbRow.getInt("amount"), customer, account);
    }

    public abstract void performTransaction() throws AccountException;

    public String toString() {
        return "ID: " + this.getId() + " " + this.getType() + " " + "made by " + getCustomer().toString() + " to " + getAccount().toString() + "("
                + getDetails() + '/' + getDate().toString() + ")";
    }

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

    public Customer getCustomer() {
        return this.customer;
    }

    public Account getAccount() {
        return this.account;
    }

}
