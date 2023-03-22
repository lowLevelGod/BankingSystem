package pao.Transaction;

import java.sql.Date;

import pao.Account.Account;
import pao.BankException.AccountException;
import pao.Customer.Customer;

public class Deposit extends Transaction{

    private Customer customer;
    private Account account;

    public Deposit(String id, String details, Date date, int amount, Customer customer, Account account){
        super(id, details, date, amount);

        this.customer = customer;
        this.account = account;
    }

    public void performTransaction() throws AccountException{
        this.account.addAmount(getAmount());
    }
    
}
