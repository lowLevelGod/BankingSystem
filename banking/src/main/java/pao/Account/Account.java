package pao.Account;

import pao.BankException.AccountException;
import pao.Customer.Customer;

public class Account {
    private final String id;
    private int interest;
    private Customer owner;
    private int amount;

    public Account(String id, int interest, Customer owner, int amount) {
        this.id = id;
        this.interest = interest;
        this.owner = owner;
        this.amount = amount;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }

    public void subtractAmount(int amount) throws AccountException {
        setAmount(this.amount - amount);
    }

    public String toString() {
        return "Account" + " " + "owned by " + owner.toString();
    }

    public String getId() {
        return this.id;
    }

    public int getInterest() {
        return this.interest;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }

    public Customer getOwner() {
        return this.owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) throws AccountException {
        if (amount >= 0) {
            this.amount = amount;
        } else {
            throw new AccountException("Insufficient funds!");
        }
    }

}
