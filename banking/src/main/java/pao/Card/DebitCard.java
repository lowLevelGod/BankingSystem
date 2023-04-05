package pao.Card;

import pao.Account.Account;

public class DebitCard extends Card{
    
    private Account account;

    public String getHolderName(){
        return this.account.getOwner().getName();
    }
}
