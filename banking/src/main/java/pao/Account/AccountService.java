package pao.Account;

import pao.BankException.AccountException;
import pao.Customer.Customer;
import pao.DataLayer.AccountDL;

public class AccountService {
    private int id = 1;
    private AccountDL accounts;

    public AccountService(){
        accounts = new AccountDL();
    }

    public Account createBaseAccount(Customer customer)
    {
        String id = Integer.toString(getNextId());

        Account acc = customer.createAccount(id);
        
        try {
            accounts.createAccount(acc);
        }catch(AccountException exception){
            System.out.println(exception.getMessage());

            return null;
        }

        return acc;
    }

    public void deleteAccount(String id){
        try {
            accounts.deleteAccount(id);
        }catch(AccountException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void updateAccount(Account account){
        
        try {
            accounts.updateAccount(account);
        }catch(AccountException exception){
            System.out.println(exception.getMessage());
        }
    }

    public Account readAccount(String id){

        Account account = null;
        try {
            account = accounts.readAccount(id);
        }catch(AccountException exception){
            System.out.println(exception.getMessage());
        }

        return account;
    }

    private int getNextId()
    {
        int id = this.id;
        this.id += 1; 

        return id;
    }
}
