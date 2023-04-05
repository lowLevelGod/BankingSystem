package pao.Card;

import pao.BankException.CardException;

public class CreditCard extends Card{

    private String name;
    private int amount;

    public String getHolderName(){
        return this.name;
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
