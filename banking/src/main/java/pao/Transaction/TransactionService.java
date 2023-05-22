package pao.Transaction;

import java.util.Date;
import java.util.List;

import pao.Account.Account;
import pao.BankException.TransactionException;
import pao.Customer.Customer;
import pao.DataLayer.TransactionDL;

public class TransactionService {

    private int id = 1;
    private TransactionDL transactions;

    private int getNextId() {
        int id = this.id;
        this.id += 1;

        return id;
    }

    public TransactionService() {
        transactions = TransactionDL.getTransactionDL();
    }

    public Withdraw withdraw(Customer customer, Account acc, String details, int amount) {

        String id = Integer.toString(getNextId());
        Date date = new Date(System.currentTimeMillis() + this.id * 100000);

        Withdraw w = new Withdraw(id, details, date, amount, customer, acc);

        return w;
    }

    public Deposit deposit(Customer customer, Account acc, String details, int amount) {

        String id = Integer.toString(getNextId());
        Date date = new Date(System.currentTimeMillis() + this.id * 100000);

        Deposit d = new Deposit(id, details, date, amount, customer, acc);

        return d;
    }

    // transfer is made of one withdrawal from the source account
    // and a deposit into the destination account
    // returns both
    public Transaction[] transfer(Customer srcCustomer, Account srcAcc, Account destAcc, String details, int amount) {

        String srcId = Integer.toString(getNextId());
        String destId = Integer.toString(getNextId());

        Date srcDate = new Date(System.currentTimeMillis() + this.id * 100000);
        Date destDate = new Date(System.currentTimeMillis() + this.id * 100001);

        Withdraw w = new Withdraw(srcId, details, srcDate, amount, srcCustomer, srcAcc);
        Deposit d = new Deposit(destId, details, destDate, amount, srcCustomer, destAcc);

        return new Transaction[] { w, d };
    }

    public List<Transaction> storePendingTransactions(Customer customer) throws TransactionException {
        return transactions.storePendingTransactions(customer);
    }

    public Transaction readTransaction(String id) throws TransactionException {
        Transaction transaction = null;

        transaction = transactions.readTransaction(id);

        return transaction;
    }

    public void deleteTransaction(String id) throws TransactionException {

        transactions.deleteTransaction(id);

    }

    public void updateTransaction(Transaction transaction) throws TransactionException {

        transactions.updateTransaction(transaction);

    }

    public void createTransaction(Transaction transaction) throws TransactionException {

        transactions.createTransaction(transaction);

    }
}
