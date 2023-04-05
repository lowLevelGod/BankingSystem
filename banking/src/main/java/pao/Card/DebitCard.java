package pao.Card;

import pao.Account.Account;

public class DebitCard extends Card{
    
    private Account account;

    public DebitCard(String id, Account account){
        super(id);

        this.account = account;
    }

    public String getHolderName(){
        return this.account.getOwner().getName();
    }

    public int getAmount(){
        return this.account.getAmount();
    }

    public void addAmount(int amount){
        this.account.addAmount(amount);
    }

    public void substractAmount(int amount) throws Exception{
        this.account.subtractAmount(amount);
    }
}
