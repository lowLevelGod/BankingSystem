package pao.Account;

import pao.BankException.AccountException;
import pao.Customer.Customer;

public class Account {
    private String id;
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
        if (this.amount >= 0) {
            this.amount -= amount;
        }else{
            throw new AccountException("Insufficient funds!");
        }
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public String getId(){
        return this.id;
    }

    public String getOwnerId() {
        return this.owner.getId();
    }

    public int getInterest() {
        return this.interest;
    }

    public int getAmount() {
        return this.amount;
    }
}
