package pao.Customer;

import pao.Account.Account;
import pao.Account.AccountFactory;

public class Natural extends Customer {

    private String firstName;
    private String lastName;

    public Natural(String id, String firstName, String lastName){
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private String getFirstName() {
        return this.firstName;
    }

    private String getLastName() {
        return this.lastName;
    }

    public String getName() {
        return getFirstName() + getLastName();
    }

    public Account createAccount(String accId){
        AccountFactory factory = new AccountFactory();
        
        Account acc = factory.createBaseAccount(accId, 2, super.getId());

        return acc;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
}
