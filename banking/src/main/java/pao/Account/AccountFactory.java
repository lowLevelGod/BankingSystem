package pao.Account;

import pao.Customer.Customer;

public class AccountFactory {
    
    public Account createBaseAccount(String accId, int interest, Customer owner){
        Account acc = new Account(accId, interest, owner, 0);

        return acc;
    }
}
