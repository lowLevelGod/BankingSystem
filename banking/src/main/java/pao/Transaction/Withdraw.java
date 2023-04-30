package pao.Transaction;

import java.util.Date;

import pao.Account.Account;
import pao.BankException.AccountException;
import pao.Customer.Customer;
import pao.Utils.Typeable;

public class Withdraw extends Transaction implements Typeable {
    public Withdraw(String id, String details, Date date, int amount, Customer customer, Account account) {
        super(id, details, date, amount, customer, account);
    }

    public String getType() {
        return "Withdraw Transaction";
    }

    public String toString() {
        return this.getType() + " " + "made by " + getCustomer().toString() + " to " + getAccount().toString() + "("
                + getDetails() + '/' + getDate().toString() + ")";
    }

    public void performTransaction() throws AccountException {
        getAccount().subtractAmount(getAmount());
    }
}
