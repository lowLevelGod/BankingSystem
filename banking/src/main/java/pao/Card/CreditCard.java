package pao.Card;

import java.sql.ResultSet;
import java.sql.SQLException;

import pao.BankException.CardException;
import pao.Customer.Customer;

public class CreditCard extends Card {

    private final Customer owner;
    private int amount;

    public CreditCard(String id, Customer owner, int amount) {
        super(id);

        this.owner = owner;
        this.amount = amount;
    }

    public CreditCard(String id, Customer owner, ResultSet dbRow) throws SQLException{
        this(id, owner, dbRow.getInt("amount"));
    }

    public String getType() {
        return "Credit Card";
    }

    public String toString() {
        return this.getType() + " " + "'" + this.owner.toString() + "'" + " amount: " + this.getAmount();
    }

    public Customer getOwner() {
        return this.owner;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) throws CardException {
        if (amount >= 0) {
            this.amount = amount;
        } else {
            throw new CardException("Insufficient funds!");
        }
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }

    public void substractAmount(int amount) throws CardException {
        setAmount(this.amount - amount);
    }
}
