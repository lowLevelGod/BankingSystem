package pao.Transaction;

import java.util.Date;

import pao.Account.Account;
import pao.BankException.AccountException;
import pao.Customer.Customer;
import pao.Utils.Typeable;

public class Withdraw extends Transaction implements Typeable{

    private Customer customer;
    private Account account;

    public Withdraw(String id, String details, Date date, int amount, Customer customer, Account account){
        super(id, details, date, amount);

        this.customer = customer;
        this.account = account;
    }

    public String getType(){
        return "Withdraw Transaction";
    }

    public String toString(){
        return this.getType() + " " + "made by " + customer.toString() + " to " + account.toString() + "(" + getDetails() + '/' + getDate().toString() + ")";
    }

    public void performTransaction() throws AccountException{
        this.account.subtractAmount(getAmount());
    }

    public Customer getCustomer(){
        return this.customer;
    }

    public Account getAccount(){
        return this.account;
    }
    
}
