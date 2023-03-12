package pao.Transaction;

import java.sql.Date;

import pao.Account.Account;
import pao.Customer.Customer;

public class Withdraw extends Transaction{

    private Customer customer;
    private Account account;

    public Withdraw(String id, String details, Date date, int amount, Customer customer, Account account){
        super(id, details, date, amount);

        this.customer = customer;
        this.account = account;
    }

    public boolean performTransaction(){
        return this.account.subtractAmount(getAmount());
    }
    
}
