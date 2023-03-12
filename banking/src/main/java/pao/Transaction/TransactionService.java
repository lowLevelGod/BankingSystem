package pao.Transaction;

import java.sql.Date;

import pao.Account.Account;
import pao.Customer.Customer;

public class TransactionService {
    
    private int id = 0;

    private int getNextId()
    {
        int id = this.id;
        this.id += 1; 

        return id;
    }

    public Withdraw withdraw(Customer customer, Account acc, String details, int amount){

        String id = Integer.toString(getNextId());
        Date date = new Date(System.currentTimeMillis());

        Withdraw w = new Withdraw(id, details, date, amount, customer, acc);

        return w;
    }

    public Deposit deposit(Customer customer, Account acc, String details, int amount){

        String id = Integer.toString(getNextId());
        Date date = new Date(System.currentTimeMillis());

        Deposit d = new Deposit(id, details, date, amount, customer, acc);

        return d;
    }

    public Transaction[] transfer(Customer srcCustomer, Account srcAcc, Account destAcc, String details, int amount){

        String srcId = Integer.toString(getNextId());
        String destId = Integer.toString(getNextId());

        Date srcDate = new Date(System.currentTimeMillis());
        Date destDate = new Date(System.currentTimeMillis());

        Withdraw w = new Withdraw(srcId, details, srcDate, amount, srcCustomer, srcAcc);
        Deposit d = new Deposit(destId, details, destDate, amount, srcCustomer, destAcc);

        return new Transaction[] {w, d};
    }
}
