package pao.Account;

public class AccountFactory {
    
    public Account createBaseAccount(String accId, int interest, String ownerId){
        Account acc = new Account(accId, interest, ownerId, 0);

        return acc;
    }
}
