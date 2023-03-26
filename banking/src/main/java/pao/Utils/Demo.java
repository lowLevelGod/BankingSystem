package pao.Utils;

import java.util.ArrayList;

import pao.Account.Account;
import pao.Account.AccountService;
import pao.Customer.Customer;
import pao.Customer.CustomerService;
import pao.Transaction.Transaction;
import pao.Transaction.TransactionService;

public class Demo {
    public void customer(){

        System.out.println("Customer service demo...\n");

        CustomerService customerService = new CustomerService();

        System.out.println("Creating demo customers...\n");
        Customer c1 = customerService.createNatural("John", "Doe");
        Customer c2 = customerService.createNatural("Bill", "Wayne");
        Customer c3 = customerService.createArtificial("Acme Inc");
        
        System.out.println("Created " + c1.toString());
        System.out.println("Created " + c2.toString());
        System.out.println("Created " + c3.toString());

        System.out.println("Retrieving info about demo customers...\n");

        System.out.println("Retrieved by id " + c1.getId() + " " + customerService.readCustomer(c1.getId()));
        System.out.println("Retrieved by id " + c2.getId() + " " + customerService.readCustomer(c2.getId()));
        System.out.println("Retrieved by id " + c3.getId() + " " + customerService.readCustomer(c3.getId()));
        System.out.print("Failed retrieval -> ");
        customerService.readCustomer("4");

        System.out.println("Updating info about demo customers...\n");

        c3.setName("Acme Ltd");
        c1.setName("Johnny Doe");
        customerService.updateCustomer(c3);
        customerService.updateCustomer(c1);
        
        System.out.println("Updated " + " " + customerService.readCustomer(c1.getId()));
        System.out.println("Updated " + " " + customerService.readCustomer(c3.getId()));

        System.out.println("Deleting demo customers...\n");
        customerService.deleteCustomer(c1.getId());
        System.out.println("Deleted " + " " + c1.toString());
        customerService.readCustomer(c1.getId());
    }

    public void account(){
        System.out.println("Account service demo...\n");

        CustomerService customerService = new CustomerService();

        Customer c1 = customerService.createNatural("Bill", "Wayne");
        Customer c2 = customerService.createArtificial("Acme Inc");

        AccountService accountService = new AccountService();

        System.out.println("Creating demo accounts...\n");

        Account a1 = accountService.createBaseAccount(c1);
        Account a2 = accountService.createBaseAccount(c2);

        System.out.println("Created " + a1.toString());
        System.out.println("Created " + a2.toString());

        System.out.println("Retrieving info about demo accounts...\n");

        System.out.println("Retrieved by id " + a1.getId() + " " + accountService.readAccount(a1.getId()));
        System.out.println("Retrieved by id " + a2.getId() + " " + accountService.readAccount(a2.getId()));

        System.out.println("Updating info about demo accounts...\n");

        a1.setOwner(c2);
        accountService.updateAccount(a1);
        
        System.out.println("Updated " + " " + accountService.readAccount(a1.getId()));

        System.out.println("Deleting demo accounts...\n");
        accountService.deleteAccount(a1.getId());
        System.out.println("Deleted " + " " + a1.toString());
        accountService.readAccount(a1.getId());
    }

    public void transaction(){

        CustomerService customerService = new CustomerService();
        Customer c1 = customerService.createNatural("Bill", "Wayne");
        Customer c2 = customerService.createArtificial("Acme Inc");

        AccountService accountService = new AccountService();
        Account a1 = accountService.createBaseAccount(c1);
        Account a2 = accountService.createBaseAccount(c2);

        TransactionService transactionService = new TransactionService();

        Transaction t1 = transactionService.deposit(c1, a1, "deposit 300", 300);
        Transaction t2 = transactionService.deposit(c2, a2, "deposit 100000", 100000);
        Transaction t3 = transactionService.withdraw(c1, a1, "withdraw 50", 50);

        Transaction[] t4 = transactionService.transfer(c2, a2, a1, "transfer 100 to Bill", 100);

        Transaction t5 = transactionService.withdraw(c1, a1, "withdraw 10000000", 10000000);

        System.out.println("Transaction service demo...\n");

        System.out.println("Creating demo transactions...\n");

        System.out.println("Created pending " + t1.toString());
        System.out.println("Created pending " + t2.toString());
        System.out.println("Created pending " + t3.toString());
        System.out.println("Created pending " + t4[0].toString());
        System.out.println("Created pending " + t4[1].toString());
        System.out.println("Created pending " + t5.toString());

        c1.addPendingTransaction(t1);
        c1.addPendingTransaction(t3);
        c1.addPendingTransaction(t5);

        c2.addPendingTransaction(t2);
        c2.addPendingTransaction(t4[0]);
        c2.addPendingTransaction(t4[1]);

        ArrayList<Transaction> arr1 = transactionService.storePendingTransactions(c1);
        ArrayList<Transaction> arr2 = transactionService.storePendingTransactions(c2);

        System.out.println("Storing successful demo transactions...\n");

        for (Transaction t : arr1){
            System.out.println("Successfully stored " + t.toString());
        }

        for (Transaction t : arr2){
            System.out.println("Successfully stored " + t.toString());
        }

        System.out.println("Retrieving info about demo transfers...\n");

        System.out.println("Retrieved by id " + t1.getId() + " " + transactionService.readTransaction(t1.getId()));
        System.out.println("Retrieved by id " + t2.getId() + " " + transactionService.readTransaction(t2.getId()));
        System.out.println("Retrieved by id " + t3.getId() + " " + transactionService.readTransaction(t3.getId()));
        System.out.println("Retrieved by id " + t4[0].getId() + " " + transactionService.readTransaction(t4[0].getId()));
        System.out.println("Retrieved by id " + t4[1].getId() + " " + transactionService.readTransaction(t4[1].getId()));

        transactionService.readTransaction(t5.getId());

    }
}
