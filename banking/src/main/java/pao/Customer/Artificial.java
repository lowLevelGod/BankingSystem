package pao.Customer;

import pao.Account.Account;
import pao.Account.AccountFactory;

public class Artificial extends Customer {
    private String companyName;

    public Artificial(String id, String companyName){
        super(id);
        this.companyName = companyName;
    }

    private String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getName() {
        return getCompanyName();
    }

    public Account createAccount(String accId){
        AccountFactory factory = new AccountFactory();
        
        Account acc = factory.createBaseAccount(accId, 5, this);

        return acc;
    }
}
