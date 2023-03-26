package pao.Transaction;

import java.util.Date;

import pao.BankException.AccountException;

public abstract class Transaction implements Comparable<Transaction>{
    private String id;
    private String details;
    private Date date;
    private int amount;

    public int compareTo(Transaction other){
        return this.date.compareTo(other.getDate());
    }

    public Transaction(String id, String details, Date date, int amount){
        this.id = id;
        this.details = details;
        this.date = date;
        this.amount = amount;
    }

    public abstract void performTransaction() throws AccountException;

    public String getId(){
        return this.id;
    }

    public String getDetails(){
        return this.details;
    }

    public Date getDate(){
        return this.date;
    }

    public int getAmount(){
        return this.amount;
    }

}
