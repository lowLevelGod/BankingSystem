package pao.Card;

import pao.Account.Account;
import pao.BankException.AccountException;
import pao.Customer.Customer;

public class DebitCard extends Card {

    private Account account;

    public DebitCard(String id, Account account) {
        super(id);

        this.account = account;
    }

    public String getType() {
        return "Debit Card";
    }

    public String toString() {
        return super.toString() + this.getType() + " " + "'" + this.account.toString() + "'";
    }

    public Customer getOwner() {
        return this.account.getOwner();
    }

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getAmount() {
        return this.account.getAmount();
    }

    public void addAmount(int amount) {
        this.account.addAmount(amount);
    }

    public void substractAmount(int amount) throws AccountException {
        this.account.subtractAmount(amount);
    }
}
