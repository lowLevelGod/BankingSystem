package pao.Card;

import pao.BankException.CardException;
import pao.Customer.Customer;

public class CreditCard extends Card{

    private Customer owner;
    private int amount;

    public CreditCard(String id, Customer owner, int amount){
        super(id);

        this.owner = owner;
        this.amount = amount;
    }

    public String getHolderName(){
        return this.owner.getName();
    }

    public int getAmount(){
        return this.amount;
    }

    public void addAmount(int amount){
        this.amount += amount;
    }

    public void substractAmount(int amount) throws Exception{
        if (this.amount >= amount) {
            this.amount -= amount;
        }else{
            throw new CardException("Insufficient funds!");
        }
    }
}
