package pao.DataLayer;

import java.util.HashMap;

import pao.Account.Account;
import pao.BankException.AccountException;

public class AccountDL {

    // placeholder for database service
    private HashMap<String, Account> accounts;

    public AccountDL(){
        accounts = new HashMap<String, Account>();
    }

    public void createAccount(Account account) throws AccountException {

        String id = account.getId();
        if (!accounts.containsKey(id)) {
            accounts.put(id, account);
        }else{
            throw new AccountException("Failed to add new account. ID " + id + " already exists!");
        }
    }

    public void deleteAccount(String id) throws AccountException{

        if (accounts.remove(id) == null){
            throw new AccountException("Failed to delete account. ID " + id + " does not exist!");
        }
    }

    public void updateAccount(Account account) throws AccountException{

        String id = account.getId();
        if (accounts.containsKey(id)) {
            accounts.put(id, account);
        }else{
            throw new AccountException("Failed to update account data. ID " + id + " does not exist!");
        }
    }

    public Account readAccount(String id) throws AccountException{

        Account account = accounts.get(id);
        if (account == null){
            throw new AccountException("Failed to retrieve account data. ID " + id + " does not exist!");
        }

        return account;
    }
}
