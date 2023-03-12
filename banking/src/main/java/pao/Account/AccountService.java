package pao.Account;

import pao.Customer.Customer;

public class AccountService {
    private int id = 0;

    public Account createBaseAccount(Customer customer)
    {
        String id = Integer.toString(getNextId());

        Account acc = customer.createAccount(id);

        return acc;
    }

    private int getNextId()
    {
        int id = this.id;
        this.id += 1; 

        return id;
    }
}
