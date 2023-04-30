package pao.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

import pao.Account.Account;
import pao.Account.AccountFactory;

public class Artificial extends Customer{
    private String companyName;

    public Artificial(String id, String companyName) {
        super(id);
        this.companyName = companyName;
    }

    public Artificial(String id, ResultSet dbRow) throws SQLException{
        this(id, dbRow.getString("company_name"));
    }

    public String getType() {
        return "Artificial customer";
    }

    public String toString() {
        return this.getType() + " " + "'" + this.getCompanyName() + "'";
    }

    public Account createAccount(String accId) {
        AccountFactory factory = new AccountFactory();

        final int artificialInterest = 5;
        Account acc = factory.createBaseAccount(accId, artificialInterest, this);

        return acc;
    }

    private String getCompanyName() {
        return this.companyName;
    }

    public void setName(String companyName) {
        this.companyName = companyName;
    }

    public String getName() {
        return getCompanyName();
    }

}
