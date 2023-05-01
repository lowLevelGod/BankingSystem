package pao;

import java.sql.Connection;
import java.util.Scanner;

import pao.Account.Account;
import pao.Account.AccountService;
import pao.BankException.AccountException;
import pao.BankException.CardException;
import pao.BankException.CustomerException;
import pao.BankException.TransactionException;
import pao.Card.Card;
import pao.Card.CardService;
import pao.Card.CreditCard;
import pao.Card.DebitCard;
import pao.Customer.Artificial;
import pao.Customer.Customer;
import pao.Customer.CustomerService;
import pao.Customer.Natural;
import pao.Transaction.Transaction;
import pao.Transaction.TransactionService;

public class SystemFacade {
    private final CustomerService customerService;
    private final CardService cardService;
    private final AccountService accountService;
    private final TransactionService transactionService;

    public SystemFacade(Connection connection) {
        customerService = new CustomerService(connection);
        cardService = new CardService(connection);
        accountService = new AccountService(connection);
        transactionService = new TransactionService(connection);
    }

    public void createNaturalCustomer(Scanner in) throws CustomerException {
        System.out.println("First name: ");
        String firstName = in.nextLine().strip();
        System.out.println("Last name: ");
        String lastName = in.nextLine().strip();

        Customer c = customerService.createNatural(firstName, lastName);
        System.out.println(c.toString());
    }

    public void createArtificialCustomer(Scanner in) throws CustomerException {
        System.out.println("Company name: ");
        String name = in.nextLine().strip();

        Customer c = customerService.createArtificial(name);
        System.out.println(c.toString());
    }

    public void readCustomer(Scanner in) throws CustomerException {
        System.out.println("Customer ID: ");
        String id = in.nextLine().strip();

        Customer c = customerService.readCustomer(id);

        System.out.println(c.toString());
    }

    public void updateCustomer(Scanner in) throws CustomerException {
        System.out.println("Customer type: ");
        String type = in.nextLine().strip();
        System.out.println("Customer ID: ");
        String id = in.nextLine().strip();

        Customer c = null;
        if (type.toLowerCase().equals("natural")){
            System.out.println("First name: ");
            String firstName = in.nextLine().strip();
            System.out.println("Last name: ");
            String lastName = in.nextLine().strip();

            c = new Natural(id, firstName, lastName);

        }else{
            System.out.println("Company name: ");
            String name = in.nextLine().strip();

            c = new Artificial(id, name);
        }

        customerService.updateCustomer(c);
        System.out.println(c.toString());
    }
    
    public void deleteCustomer(Scanner in) throws CustomerException {
        System.out.println("Customer ID: ");
        String id = in.nextLine().strip();

        customerService.deleteCustomer(id);

        System.out.println("Deleted!");
    }

    public void createBaseAccount(Scanner in) throws AccountException, CustomerException {
        System.out.println("Owner ID: ");
        String id = in.nextLine().strip();

        Customer c = customerService.readCustomer(id);

        Account a = accountService.createBaseAccount(c);
        System.out.println(a.toString());
    }

    public void deleteAccount(Scanner in) throws AccountException {
        System.out.println("Account ID: ");
        String id = in.nextLine().strip();

        accountService.deleteAccount(id);
        System.out.println("Deleted");
    }

    public void updateAccount(Scanner in) throws AccountException, CustomerException {

        System.out.println("Account ID: ");
        String id = in.nextLine().strip();

        System.out.println("Interest: ");
        int interest = Integer.parseInt(in.nextLine().strip());

        System.out.println("Owner ID: ");
        String ownerId = in.nextLine().strip();

        System.out.println("Amount: ");
        int amount = Integer.parseInt(in.nextLine().strip());

        Customer c = customerService.readCustomer(ownerId);

        Account a = new Account(id, interest, c, amount);

        accountService.updateAccount(a);
        System.out.println(a.toString());
    }

    public void readAccount(Scanner in) throws AccountException {

        System.out.println("Account ID: ");
        String id = in.nextLine().strip();

        Account a = accountService.readAccount(id);

        System.out.println(a.toString());
    }

    public void createCreditCard(Scanner in) throws CardException, CustomerException {

        System.out.println("Owner ID: ");
        String id = in.nextLine().strip();

        System.out.println("Amount: ");
        int amount = Integer.parseInt(in.nextLine().strip());

        Customer owner = customerService.readCustomer(id);

        Card card = cardService.createCredit(owner, amount);

        System.out.println(card.toString());
    }

    public void createDebitCard(Scanner in) throws CardException, AccountException {

        System.out.println("Account ID: ");
        String id = in.nextLine().strip();

        Account account = accountService.readAccount(id);

        Card card = cardService.createDebit(account);

        System.out.println(card.toString());
    }

    public void deleteCard(Scanner in) throws CardException {

        System.out.println("Card ID: ");
        String id = in.nextLine().strip();

        cardService.deleteCard(id);
        System.out.println("Deleted");
    }

    public void updateCard(Scanner in) throws CardException, AccountException {

        System.out.println("Card type: ");
        String type = in.nextLine().strip();
        System.out.println("Card ID: ");
        String id = in.nextLine().strip();

        Card c = null;
        if (type.toLowerCase().equals("debit")){
            System.out.println("Account ID: ");
            String accountId = in.nextLine().strip();

            Account account = accountService.readAccount(accountId);

            c = new DebitCard(id, account);

        }else{
            System.out.println("Amount: ");
            int amount = Integer.parseInt(in.nextLine().strip());

            Card oldCard = cardService.readCard(id);

            c = new CreditCard(id, oldCard.getOwner(), amount);
        }

        cardService.updateCard(c);
        System.out.println(c.toString());

    }

    public void readCard(Scanner in) throws CardException {

        System.out.println("Card ID: ");
        String id = in.nextLine().strip();

        Card c = cardService.readCard(id);

        System.out.println(c.toString());
    }

    public void readTransaction(Scanner in) throws TransactionException {
        System.out.println("Transaction ID: ");
        String id = in.nextLine().strip();

        Transaction t = transactionService.readTransaction(id);

        System.out.println(t.toString());
    }

    public void deleteTransaction(Scanner in) throws TransactionException {

        System.out.println("Transaction ID: ");
        String id = in.nextLine().strip();

        transactionService.deleteTransaction(id);
        System.out.println("Deleted");

    }

    public void updateTransaction(Scanner in) throws TransactionException {

        System.out.println("Transaction ID: ");
        String id = in.nextLine().strip();

        System.out.println("Details: ");
        String details = in.nextLine().strip();

        Transaction t = transactionService.readTransaction(id);
        t.setDetails(details);

        transactionService.updateTransaction(t);

        System.out.println(t.toString());
    }

    public void createTransactionDeposit(Scanner in) throws TransactionException, CustomerException, AccountException {

        System.out.println("Details: ");
        String details = in.nextLine().strip();

        System.out.println("Amount: ");
        int amount = Integer.parseInt(in.nextLine().strip());

        System.out.println("Customer ID: ");
        String ownerId = in.nextLine().strip();

        System.out.println("Account ID: ");
        String accountId = in.nextLine().strip();


        Customer customer = customerService.readCustomer(ownerId);
        Account account = accountService.readAccount(accountId);
        
        Transaction t = transactionService.deposit(customer, account, details, amount);

        account.addAmount(amount);
        accountService.updateAccount(account);

        transactionService.createTransaction(t);

        System.out.println(t.toString());

    }

    public void createTransactionWithDraw(Scanner in) throws TransactionException, CustomerException, AccountException {

        System.out.println("Details: ");
        String details = in.nextLine().strip();

        System.out.println("Amount: ");
        int amount = Integer.parseInt(in.nextLine().strip());

        System.out.println("Customer ID: ");
        String ownerId = in.nextLine().strip();

        System.out.println("Account ID: ");
        String accountId = in.nextLine().strip();


        Customer customer = customerService.readCustomer(ownerId);
        Account account = accountService.readAccount(accountId);
        
        Transaction t = transactionService.withdraw(customer, account, details, amount);

        account.subtractAmount(amount);
        accountService.updateAccount(account);

        transactionService.createTransaction(t);

        System.out.println(t.toString());

    }

    public void createTransactionTransfer(Scanner in) throws TransactionException, CustomerException, AccountException {

        System.out.println("Details: ");
        String details = in.nextLine().strip();

        System.out.println("Amount: ");
        int amount = Integer.parseInt(in.nextLine().strip());

        System.out.println("Customer ID: ");
        String ownerId = in.nextLine().strip();

        System.out.println("Source Account ID: ");
        String srcAccountId = in.nextLine().strip();

        System.out.println("Destination Account ID: ");
        String dstAccountId = in.nextLine().strip();

        Customer customer = customerService.readCustomer(ownerId);
        Account srcAccount = accountService.readAccount(srcAccountId);
        Account dstAccount = accountService.readAccount(dstAccountId);
        
        Transaction[] t = transactionService.transfer(customer, srcAccount, dstAccount, details, amount);
    
        srcAccount.subtractAmount(amount);
        accountService.updateAccount(srcAccount);
        dstAccount.addAmount(amount);
        accountService.updateAccount(dstAccount);

        transactionService.createTransaction(t[0]);
        transactionService.createTransaction(t[1]);

        System.out.println(t[0].toString());
        System.out.println(t[1].toString());
    }

}
