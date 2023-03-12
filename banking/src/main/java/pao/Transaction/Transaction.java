package pao.Transaction;

import java.sql.Date;

public abstract class Transaction {
    private String id;
    private String details;
    private Date date;
    private int amount;

    public Transaction(String id, String details, Date date, int amount){
        this.id = id;
        this.details = details;
        this.date = date;
        this.amount = amount;
    }

    public abstract boolean performTransaction();

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
