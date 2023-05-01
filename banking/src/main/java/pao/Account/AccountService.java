package pao.Account;

import java.sql.Connection;

import pao.BankException.AccountException;
import pao.Customer.Customer;
import pao.DataLayer.AccountDL;

public class AccountService {
    private int id = 1;
    private AccountDL accounts;

    public AccountService(Connection connection) {
        accounts = new AccountDL(connection);
    }

    public Account createBaseAccount(Customer customer) throws AccountException {
        String id = Integer.toString(getNextId());

        Account acc = customer.createAccount(id);

        accounts.createAccount(acc);

        return acc;
    }

    public void deleteAccount(String id) throws AccountException {
        accounts.deleteAccount(id);
    }

    public void updateAccount(Account account) throws AccountException {

        accounts.updateAccount(account);
    }

    public Account readAccount(String id) throws AccountException {

        Account account = null;

        account = accounts.readAccount(id);

        return account;
    }

    private int getNextId() {
        int id = this.id;
        this.id += 1;

        return id;
    }
}
