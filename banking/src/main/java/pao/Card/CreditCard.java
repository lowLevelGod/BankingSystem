package pao.Card;

import pao.BankException.CardException;
import pao.Customer.Customer;
import pao.Utils.Typeable;

public class CreditCard extends Card implements Typeable{

    private Customer owner;
    private int amount;

    public CreditCard(String id, Customer owner, int amount){
        super(id);

        this.owner = owner;
        this.amount = amount;
    }

    public String getType(){
        return "Credit Card";
    }

    public String toString(){
        return this.getType() + " " + "'" + this.owner.toString() + "'" + " amount: " + this.getAmount();
    }

    public Customer getOwner(){
        return this.owner;
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
