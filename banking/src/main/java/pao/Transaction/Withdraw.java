package pao.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import pao.Account.Account;
import pao.BankException.AccountException;
import pao.Customer.Customer;

public class Withdraw extends Transaction {
    public Withdraw(String id, String details, Date date, int amount, Customer customer, Account account) {
        super(id, details, date, amount, customer, account);
    }

    public Withdraw(String id, Customer customer, Account account, ResultSet dbRow) throws SQLException{
        super(id, dbRow.getString("details"), dbRow.getDate("date"), dbRow.getInt("amount"), customer, account);
    }

    public String getType() {
        return "Withdraw Transaction";
    }

    public void performTransaction() throws AccountException {
        getAccount().subtractAmount(getAmount());
    }
}
