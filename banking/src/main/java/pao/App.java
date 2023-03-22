package pao;

import pao.Account.Account;
import pao.Account.AccountService;
import pao.Customer.Customer;
import pao.Customer.CustomerService;
import pao.Transaction.Transaction;
import pao.Transaction.TransactionService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CustomerService customerService = new CustomerService();

        Customer c1 = customerService.createNatural("John", "Doe");
        Customer c2 = customerService.createNatural("Bill", "Wayne");
        Customer c3 = customerService.createArtificial("Acme Inc");

        AccountService accountService = new AccountService();

        Account a1 = accountService.createBaseAccount(c1);
        Account a2 = accountService.createBaseAccount(c2);
        Account a3 = accountService.createBaseAccount(c3);

        TransactionService transactionService = new TransactionService();

        Transaction t1 = transactionService.deposit(c1, a1, "deposit 300", 300);
        Transaction t2 = transactionService.deposit(c3, a3, "deposit 100000", 100000);
        Transaction t3 = transactionService.withdraw(c1, a1, "withdraw 50", 50);

        Transaction[] t4 = transactionService.transfer(c3, a3, a1, "transfer 100 to John", 100);

        c1.addPendingTransaction(t1);
        c3.addPendingTransaction(t2);
        c1.addPendingTransaction(t3);

        c3.addPendingTransaction(t4[0]);
        c3.addPendingTransaction(t4[1]);

        // store in database only successful transactions
        c1.performPendingTransactions();
        c3.performPendingTransactions();

        System.out.println("Account 1 balance: " + a1.getAmount());
        System.out.println("Account 2 balance: " + a2.getAmount());
        System.out.println("Account 3 balance: " + a3.getAmount());
    }
}
