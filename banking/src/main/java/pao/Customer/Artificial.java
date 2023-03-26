package pao.Customer;

import pao.Account.Account;
import pao.Account.AccountFactory;
import pao.Utils.Typeable;

public class Artificial extends Customer implements Typeable {
    private String companyName;

    public Artificial(String id, String companyName){
        super(id);
        this.companyName = companyName;
    }

    public String getType(){
        return "Artificial customer";
    }

    private String getCompanyName() {
        return this.companyName;
    }

    public String toString(){
        return this.getType() + " " + "'" + this.getCompanyName() + "'";
    }

    public void setName(String companyName) {
        this.companyName = companyName;
    }

    public String getName() {
        return getCompanyName();
    }

    public Account createAccount(String accId){
        AccountFactory factory = new AccountFactory();
        
        final int artificialInterest = 5;
        Account acc = factory.createBaseAccount(accId, artificialInterest, this);

        return acc;
    }
}
