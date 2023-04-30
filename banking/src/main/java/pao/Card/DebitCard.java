package pao.Card;

import pao.Account.Account;
import pao.BankException.AccountException;
import pao.Customer.Customer;
import pao.Utils.Typeable;

public class DebitCard extends Card implements Typeable {

    private final Account account;

    public DebitCard(String id, Account account) {
        super(id);

        this.account = account;
    }

    public String getType() {
        return "Debit Card";
    }

    public String toString() {
        return this.getType() + " " + "'" + this.account.toString() + "'" + " amount: " + this.getAmount();
    }

    public Customer getOwner() {
        return this.account.getOwner();
    }

    public Account getAccount() {
        return this.account;
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
