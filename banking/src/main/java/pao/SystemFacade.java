package pao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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

    private final HashMap<String, Customer> sessionCustomers = new HashMap<String, Customer>();

    public SystemFacade(Connection connection) {
        customerService = new CustomerService(connection);
        cardService = new CardService(connection);
        accountService = new AccountService(connection);
        transactionService = new TransactionService(connection);
    }

    public void createNaturalCustomer(JFrame frame, JPanel mainPanel, JTextArea messageTextField)
            throws CustomerException {

        JTextField firstNameTextField = new JTextField();
        firstNameTextField.setColumns(50);

        JTextField lastNameTextField = new JTextField();
        lastNameTextField.setColumns(50);

        JButton button = new JButton("SUBMIT");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();

                Customer c;
                String text = null;
                try {
                    c = customerService.createNatural(firstName, lastName);
                    text = "Succesful: " + c.toString() + "\n";
                    // System.out.println(c.toString());
                } catch (Exception exception) {
                    text = "Error: " + exception.getMessage() + "\n";
                    // System.out.println(exception.getMessage());
                }

                messageTextField.setText(text);

                frame.getContentPane().remove(panel);

                frame.getContentPane().add(mainPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        JLabel firstNameLabel = new JLabel("First name: ");
        JLabel lastNameLabel = new JLabel("Last name: ");

        JPanel firstNamePanel = new JPanel();

        firstNamePanel.add(firstNameLabel);
        firstNamePanel.add(firstNameTextField);

        JPanel lastNamePanel = new JPanel();

        lastNamePanel.add(lastNameLabel);
        lastNamePanel.add(lastNameTextField);

        panel.add(firstNamePanel);
        panel.add(lastNamePanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        panel.add(buttonPanel);

        frame.revalidate();
        frame.repaint();

    }

    public void createArtificialCustomer(JFrame frame, JPanel mainPanel, JTextArea messageTextField)
            throws CustomerException {

        JTextField nameTextField = new JTextField();
        nameTextField.setColumns(50);

        JButton button = new JButton("SUBMIT");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();

                String text = null;
                Customer c;
                try {
                    c = customerService.createArtificial(name);
                    text = "Succesful: " + c.toString() + "\n";
                    // System.out.println(c.toString());
                } catch (Exception exception) {
                    text = "Error: " + exception.getMessage() + "\n";
                    // System.out.println(exception.getMessage());
                }

                messageTextField.setText(text);

                frame.getContentPane().remove(panel);

                frame.getContentPane().add(mainPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        JLabel nameLabel = new JLabel("Company name: ");

        JPanel namePanel = new JPanel();

        namePanel.add(nameLabel);
        namePanel.add(nameTextField);

        panel.add(namePanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        panel.add(buttonPanel);

        frame.revalidate();
        frame.repaint();

    }

    public void readCustomer(JFrame frame, JPanel mainPanel, JTextArea messageTextField) throws CustomerException {
        JTextField idTextField = new JTextField();
        idTextField.setColumns(50);

        JButton button = new JButton("SUBMIT");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idTextField.getText();

                String text = null;
                Customer c;
                try {
                    c = customerService.readCustomer(id);
                    text = "Succesful: " + c.toString() + "\n";
                    // System.out.println(c.toString());
                } catch (Exception exception) {
                    text = "Error: " + exception.getMessage() + "\n";
                    // System.out.println(exception.getMessage());
                }

                messageTextField.setText(text);

                frame.getContentPane().remove(panel);

                frame.getContentPane().add(mainPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        JLabel idLabel = new JLabel("Customer ID: ");

        JPanel idPanel = new JPanel();

        idPanel.add(idLabel);
        idPanel.add(idTextField);

        panel.add(idPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        panel.add(buttonPanel);

        frame.revalidate();
        frame.repaint();
    }

    public void updateCustomer(JFrame frame, JPanel mainPanel, JTextArea messageTextField) throws CustomerException {

        JComboBox<String> typeList = new JComboBox<String>(new String[] { "natural", "artificial" });

        JPanel panel = new JPanel();
        frame.add(panel);

        panel.add(typeList);

        JPanel panelForms = new JPanel();
        panelForms.setLayout(new BoxLayout(panelForms, BoxLayout.Y_AXIS));

        panel.add(panelForms);

        typeList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> cb = (JComboBox<String>) e.getSource();
                String type = (String) cb.getSelectedItem();

                JLabel idLabel = new JLabel("Customer ID: ");
                JTextField idTextField = new JTextField();
                idTextField.setColumns(50);
                JPanel idPanel = new JPanel();
                idPanel.add(idLabel);
                idPanel.add(idTextField);

                if (type.toLowerCase().equals("natural")) {
                    JLabel firstNameLabel = new JLabel("First name: ");
                    JTextField firstNameTextField = new JTextField();
                    firstNameTextField.setColumns(50);
                    JPanel firstNamePanel = new JPanel();
                    firstNamePanel.add(firstNameLabel);
                    firstNamePanel.add(firstNameTextField);

                    JLabel lastNameLabel = new JLabel("Last name: ");
                    JTextField lastNameTextField = new JTextField();
                    lastNameTextField.setColumns(50);
                    JPanel lastNamePanel = new JPanel();
                    lastNamePanel.add(lastNameLabel);
                    lastNamePanel.add(lastNameTextField);

                    JButton button = new JButton("SUBMIT");

                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String id = idTextField.getText().strip();
                            String firstName = firstNameTextField.getText().strip();
                            String lastName = lastNameTextField.getText().strip();

                            String text = null;
                            Customer c = new Natural(id, firstName, lastName);
                            try {
                                customerService.updateCustomer(c);
                                text = "Succesful: " + c.toString() + "\n";
                                // System.out.println(c.toString());
                            } catch (Exception exception) {
                                text = "Error: " + exception.getMessage() + "\n";
                                // System.out.println(exception.getMessage());
                            }

                            messageTextField.setText(text);

                            frame.getContentPane().remove(panel);

                            frame.getContentPane().add(mainPanel);
                            frame.revalidate();
                            frame.repaint();
                        }
                    });

                    panelForms.removeAll();

                    panelForms.add(idPanel);
                    panelForms.add(firstNamePanel);
                    panelForms.add(lastNamePanel);
                    panelForms.add(button);

                    panelForms.revalidate();
                    panelForms.repaint();

                    frame.revalidate();
                    frame.repaint();
                } else {
                    JLabel nameLabel = new JLabel("Company name: ");
                    JTextField nameTextField = new JTextField();
                    nameTextField.setColumns(50);
                    JPanel namePanel = new JPanel();
                    namePanel.add(nameLabel);
                    namePanel.add(nameTextField);

                    JButton button = new JButton("SUBMIT");

                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String id = idTextField.getText().strip();
                            String name = nameTextField.getText().strip();

                            String text = null;
                            Customer c = new Artificial(id, name);
                            try {
                                customerService.updateCustomer(c);
                                text = "Succesful: " + c.toString() + "\n";
                                // System.out.println(c.toString());
                            } catch (Exception exception) {
                                text = "Error: " + exception.getMessage() + "\n";
                                // System.out.println(exception.getMessage());
                            }

                            messageTextField.setText(text);

                            frame.getContentPane().remove(panel);

                            frame.getContentPane().add(mainPanel);
                            frame.revalidate();
                            frame.repaint();
                        }
                    });

                    panelForms.removeAll();

                    panelForms.add(idPanel);
                    panelForms.add(namePanel);
                    panelForms.add(button);

                    panelForms.revalidate();
                    panelForms.repaint();

                    frame.revalidate();
                    frame.repaint();
                }
            }
        });

        typeList.setSelectedItem("natural");

        frame.revalidate();
        frame.repaint();

    }

    public void deleteCustomer(JFrame frame, JPanel mainPanel, JTextArea messageTextField) throws CustomerException {

        JTextField idTextField = new JTextField();
        idTextField.setColumns(50);

        JButton button = new JButton("SUBMIT");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idTextField.getText();

                String text = null;
                try {
                    customerService.deleteCustomer(id);

                    text = "Succesful: " + "Deleted" + "\n";
                    // System.out.println(c.toString());
                } catch (Exception exception) {
                    text = "Error: " + exception.getMessage() + "\n";
                    // System.out.println(exception.getMessage());
                }

                messageTextField.setText(text);

                frame.getContentPane().remove(panel);

                frame.getContentPane().add(mainPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        JLabel idLabel = new JLabel("Customer ID: ");

        JPanel idPanel = new JPanel();

        idPanel.add(idLabel);
        idPanel.add(idTextField);

        panel.add(idPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        panel.add(buttonPanel);

        frame.revalidate();
        frame.repaint();
    }

    public void createBaseAccount(JFrame frame, JPanel mainPanel, JTextArea messageTextField)
            throws AccountException, CustomerException {

        JTextField idTextField = new JTextField();
        idTextField.setColumns(50);

        JButton button = new JButton("SUBMIT");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idTextField.getText();

                String text = null;
                try {
                    Customer c = customerService.readCustomer(id);

                    Account a = accountService.createBaseAccount(c);
                    text = "Succesful: " + a.toString() + "\n";
                    // System.out.println(a.toString());
                } catch (Exception exception) {
                    text = "Error: " + exception.getMessage() + "\n";
                    // System.out.println(exception.getMessage());
                }

                messageTextField.setText(text);

                frame.getContentPane().remove(panel);

                frame.getContentPane().add(mainPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        JLabel idLabel = new JLabel("Owner ID: ");

        JPanel idPanel = new JPanel();

        idPanel.add(idLabel);
        idPanel.add(idTextField);

        panel.add(idPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        panel.add(buttonPanel);

        frame.revalidate();
        frame.repaint();
    }

    public void deleteAccount(JFrame frame, JPanel mainPanel, JTextArea messageTextField) throws AccountException {
        JTextField idTextField = new JTextField();
        idTextField.setColumns(50);

        JButton button = new JButton("SUBMIT");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idTextField.getText();

                String text = null;
                try {
                    accountService.deleteAccount(id);

                    text = "Succesful: " + "Deleted" + "\n";
                    // System.out.println(a.toString());
                } catch (Exception exception) {
                    text = "Error: " + exception.getMessage() + "\n";
                    // System.out.println(exception.getMessage());
                }

                messageTextField.setText(text);

                frame.getContentPane().remove(panel);

                frame.getContentPane().add(mainPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        JLabel idLabel = new JLabel("Account ID: ");

        JPanel idPanel = new JPanel();

        idPanel.add(idLabel);
        idPanel.add(idTextField);

        panel.add(idPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        panel.add(buttonPanel);

        frame.revalidate();
        frame.repaint();
    }

    public void updateAccount(JFrame frame, JPanel mainPanel, JTextArea messageTextField)
            throws AccountException, CustomerException {

        JTextField idTextField = new JTextField();
        idTextField.setColumns(50);

        JTextField interestTextField = new JTextField();
        interestTextField.setColumns(50);

        JTextField ownerTextField = new JTextField();
        ownerTextField.setColumns(50);

        JTextField amountTextField = new JTextField();
        amountTextField.setColumns(50);

        JButton button = new JButton("SUBMIT");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idTextField.getText();
                int interest = Integer.parseInt(interestTextField.getText().strip());
                String ownerId = ownerTextField.getText();
                int amount = Integer.parseInt(amountTextField.getText().strip());

                String text = null;
                try {
                    Customer c = customerService.readCustomer(ownerId);

                    Account a = new Account(id, interest, c, amount);

                    accountService.updateAccount(a);
                    text = "Succesful: " + a.toString() + "\n";
                    // System.out.println(a.toString());
                } catch (Exception exception) {
                    text = "Error: " + exception.getMessage() + "\n";
                    // System.out.println(exception.getMessage());
                }

                messageTextField.setText(text);

                frame.getContentPane().remove(panel);

                frame.getContentPane().add(mainPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        JLabel idLabel = new JLabel("Account ID: ");

        JPanel idPanel = new JPanel();

        idPanel.add(idLabel);
        idPanel.add(idTextField);

        panel.add(idPanel);

        JLabel interestLabel = new JLabel("Interest: ");

        JPanel interestPanel = new JPanel();

        interestPanel.add(interestLabel);
        interestPanel.add(interestTextField);

        panel.add(interestPanel);

        JLabel ownerLabel = new JLabel("Owner ID: ");

        JPanel ownerPanel = new JPanel();

        ownerPanel.add(ownerLabel);
        ownerPanel.add(ownerTextField);

        panel.add(ownerPanel);

        JLabel amountLabel = new JLabel("Amount: ");

        JPanel amountPanel = new JPanel();

        amountPanel.add(amountLabel);
        amountPanel.add(amountTextField);

        panel.add(amountPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        panel.add(buttonPanel);

        frame.revalidate();
        frame.repaint();

    }

    public void readAccount(JFrame frame, JPanel mainPanel, JTextArea messageTextField) throws AccountException {
        JTextField idTextField = new JTextField();
        idTextField.setColumns(50);

        JButton button = new JButton("SUBMIT");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idTextField.getText();

                String text = null;
                try {
                    Account a = accountService.readAccount(id);

                    text = "Succesful: " + a.toString() + "\n";
                    // System.out.println(a.toString());
                } catch (Exception exception) {
                    text = "Error: " + exception.getMessage() + "\n";
                    // System.out.println(exception.getMessage());
                }

                messageTextField.setText(text);

                frame.getContentPane().remove(panel);

                frame.getContentPane().add(mainPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        JLabel idLabel = new JLabel("Account ID: ");

        JPanel idPanel = new JPanel();

        idPanel.add(idLabel);
        idPanel.add(idTextField);

        panel.add(idPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        panel.add(buttonPanel);

        frame.revalidate();
        frame.repaint();
    }

    public void createCreditCard(JFrame frame, JPanel mainPanel, JTextArea messageTextField)
            throws CardException, CustomerException {

        JTextField ownerTextField = new JTextField();
        ownerTextField.setColumns(50);

        JTextField amountTextField = new JTextField();
        amountTextField.setColumns(50);

        JButton button = new JButton("SUBMIT");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = ownerTextField.getText();
                int amount = Integer.parseInt(amountTextField.getText().strip());


                String text = null;
                try {
                    Customer owner = customerService.readCustomer(id);

                    Card card = cardService.createCredit(owner, amount);

                    text = "Succesful: " + card.toString() + "\n";
                    // System.out.println(a.toString());
                } catch (Exception exception) {
                    text = "Error: " + exception.getMessage() + "\n";
                    // System.out.println(exception.getMessage());
                }

                messageTextField.setText(text);

                frame.getContentPane().remove(panel);

                frame.getContentPane().add(mainPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        JLabel ownerLabel = new JLabel("Owner ID: ");
        JLabel amountLabel = new JLabel("Amount: ");

        JPanel ownerPanel = new JPanel();

        ownerPanel.add(ownerLabel);
        ownerPanel.add(ownerTextField);

        JPanel amountPanel = new JPanel();

        amountPanel.add(amountLabel);
        amountPanel.add(amountTextField);

        panel.add(ownerPanel);
        panel.add(amountPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        panel.add(buttonPanel);

        frame.revalidate();
        frame.repaint();
    }

    public void createDebitCard(JFrame frame, JPanel mainPanel, JTextArea messageTextField)
            throws CardException, AccountException {

        JTextField accountTextField = new JTextField();
        accountTextField.setColumns(50);

        JButton button = new JButton("SUBMIT");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = accountTextField.getText();

                
                String text = null;
                try {
                    Account account = accountService.readAccount(id);

                    Card card = cardService.createDebit(account);

                    text = "Succesful: " + card.toString() + "\n";
                    // System.out.println(a.toString());
                } catch (Exception exception) {
                    text = "Error: " + exception.getMessage() + "\n";
                    // System.out.println(exception.getMessage());
                }   
                
                messageTextField.setText(text);

                frame.getContentPane().remove(panel);

                frame.getContentPane().add(mainPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        JLabel accountLabel = new JLabel("Account ID: ");

        JPanel accountPanel = new JPanel();

        accountPanel.add(accountLabel);
        accountPanel.add(accountTextField);

        panel.add(accountPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        panel.add(buttonPanel);

        frame.revalidate();
        frame.repaint();

    }

    public void deleteCard(JFrame frame, JPanel mainPanel, JTextArea messageTextField) throws CardException {

        JTextField idTextField = new JTextField();
        idTextField.setColumns(50);

        JButton button = new JButton("SUBMIT");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idTextField.getText();

                String text = null;
                try {
                    cardService.deleteCard(id);

                    text = "Succesful: " + "Deleted" + "\n";
                    // System.out.println(a.toString());
                } catch (Exception exception) {
                    text = "Error: " + exception.getMessage() + "\n";
                    // System.out.println(exception.getMessage());
                }   

                messageTextField.setText(text);

                frame.getContentPane().remove(panel);

                frame.getContentPane().add(mainPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        JLabel idLabel = new JLabel("Card ID: ");

        JPanel idPanel = new JPanel();

        idPanel.add(idLabel);
        idPanel.add(idTextField);

        panel.add(idPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        panel.add(buttonPanel);

        frame.revalidate();
        frame.repaint();
    }

    public void updateCard(JFrame frame, JPanel mainPanel, JTextArea messageTextField)
            throws CardException, AccountException {

        JComboBox<String> typeList = new JComboBox<String>(new String[] { "debit", "credit" });

        JPanel panel = new JPanel();
        frame.add(panel);

        panel.add(typeList);

        JPanel panelForms = new JPanel();
        panelForms.setLayout(new BoxLayout(panelForms, BoxLayout.Y_AXIS));

        panel.add(panelForms);

        typeList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> cb = (JComboBox<String>) e.getSource();
                String type = (String) cb.getSelectedItem();

                JLabel idLabel = new JLabel("Card ID: ");
                JTextField idTextField = new JTextField();
                idTextField.setColumns(50);
                JPanel idPanel = new JPanel();
                idPanel.add(idLabel);
                idPanel.add(idTextField);

                if (type.toLowerCase().equals("debit")) {
                    JLabel accountLabel = new JLabel("Account ID: ");
                    JTextField accountTextField = new JTextField();
                    accountTextField.setColumns(50);
                    JPanel accountPanel = new JPanel();
                    accountPanel.add(accountLabel);
                    accountPanel.add(accountTextField);

                    JButton button = new JButton("SUBMIT");

                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String id = idTextField.getText().strip();
                            String accountId = accountTextField.getText().strip();

                            String text = null;
                            try {

                                Account account = accountService.readAccount(accountId);

                                Card c = new DebitCard(id, account);

                                cardService.updateCard(c);

                                text = "Succesful: " + c.toString() + "\n";
                                // System.out.println(a.toString());
                            } catch (Exception exception) {
                                text = "Error: " + exception.getMessage() + "\n";
                                // System.out.println(exception.getMessage());
                            }

                            messageTextField.setText(text);

                            frame.getContentPane().remove(panel);

                            frame.getContentPane().add(mainPanel);
                            frame.revalidate();
                            frame.repaint();
                        }
                    });

                    panelForms.removeAll();

                    panelForms.add(idPanel);
                    panelForms.add(accountPanel);
                    panelForms.add(button);

                    panelForms.revalidate();
                    panelForms.repaint();

                    frame.revalidate();
                    frame.repaint();
                } else {
                    JLabel amountLabel = new JLabel("Amount: ");
                    JTextField amountTextField = new JTextField();
                    amountTextField.setColumns(50);
                    JPanel amountPanel = new JPanel();
                    amountPanel.add(amountLabel);
                    amountPanel.add(amountTextField);

                    JButton button = new JButton("SUBMIT");

                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String id = idTextField.getText();
                            int amount = Integer.parseInt(amountTextField.getText());

                            String text = null;
                            try {

                                Card oldCard = cardService.readCard(id);

                                Card c = new CreditCard(id, oldCard.getOwner(), amount);

                                cardService.updateCard(c);

                                text = "Succesful: " + c.toString() + "\n";
                                // System.out.println(a.toString());
                            } catch (Exception exception) {
                                text = "Error: " + exception.getMessage() + "\n";
                                // System.out.println(exception.getMessage());
                            }

                            messageTextField.setText(text);

                            frame.getContentPane().remove(panel);

                            frame.getContentPane().add(mainPanel);
                            frame.revalidate();
                            frame.repaint();
                        }
                    });

                    panelForms.removeAll();

                    panelForms.add(idPanel);
                    panelForms.add(amountPanel);
                    panelForms.add(button);

                    panelForms.revalidate();
                    panelForms.repaint();

                    frame.revalidate();
                    frame.repaint();
                }
            }
        });

        typeList.setSelectedItem("debit");

        frame.revalidate();
        frame.repaint();

    }

    public void readCard(JFrame frame, JPanel mainPanel, JTextArea messageTextField) throws CardException {

        JTextField idTextField = new JTextField();
        idTextField.setColumns(50);

        JButton button = new JButton("SUBMIT");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idTextField.getText();

                String text = null;
                Card c;
                try {
                    c = cardService.readCard(id);

                    text = "Succesful: " + c.toString() + "\n";
                    // System.out.println(a.toString());
                } catch (Exception exception) {
                    text = "Error: " + exception.getMessage() + "\n";
                    // System.out.println(exception.getMessage());
                }

                messageTextField.setText(text);

                frame.getContentPane().remove(panel);

                frame.getContentPane().add(mainPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        JLabel idLabel = new JLabel("Card ID: ");

        JPanel idPanel = new JPanel();

        idPanel.add(idLabel);
        idPanel.add(idTextField);

        panel.add(idPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        panel.add(buttonPanel);

        frame.revalidate();
        frame.repaint();
    }

    public void readTransaction(JFrame frame, JPanel mainPanel, JTextArea messageTextField)
            throws TransactionException {

        JTextField idTextField = new JTextField();
        idTextField.setColumns(50);

        JButton button = new JButton("SUBMIT");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idTextField.getText();

                String text = null;
                try {
                    Transaction t = transactionService.readTransaction(id);

                    text = "Succesful: " + t.toString() + "\n";
                    // System.out.println(a.toString());
                } catch (Exception exception) {
                    text = "Error: " + exception.getMessage() + "\n";
                    // System.out.println(exception.getMessage());
                }

                messageTextField.setText(text);

                frame.getContentPane().remove(panel);

                frame.getContentPane().add(mainPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        JLabel idLabel = new JLabel("Transaction ID: ");

        JPanel idPanel = new JPanel();

        idPanel.add(idLabel);
        idPanel.add(idTextField);

        panel.add(idPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        panel.add(buttonPanel);

        frame.revalidate();
        frame.repaint();

    }

    public void deleteTransaction(JFrame frame, JPanel mainPanel, JTextArea messageTextField)
            throws TransactionException {

        JTextField idTextField = new JTextField();
        idTextField.setColumns(50);

        JButton button = new JButton("SUBMIT");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idTextField.getText();

                String text = null;
                try {

                    transactionService.deleteTransaction(id);

                    text = "Succesful: " + "Deleted" + "\n";
                    // System.out.println(a.toString());
                } catch (Exception exception) {
                    text = "Error: " + exception.getMessage() + "\n";
                    // System.out.println(exception.getMessage());
                }

                messageTextField.setText(text);

                frame.getContentPane().remove(panel);

                frame.getContentPane().add(mainPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        JLabel idLabel = new JLabel("Transaction ID: ");

        JPanel idPanel = new JPanel();

        idPanel.add(idLabel);
        idPanel.add(idTextField);

        panel.add(idPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        panel.add(buttonPanel);

        frame.revalidate();
        frame.repaint();
    }

    public void updateTransaction(JFrame frame, JPanel mainPanel, JTextArea messageTextField)
            throws TransactionException {

        JTextField idTextField = new JTextField();
        idTextField.setColumns(50);

        JTextField detailsTextField = new JTextField();
        detailsTextField.setColumns(50);

        JButton button = new JButton("SUBMIT");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idTextField.getText();
                String details = detailsTextField.getText();

                String text = null;
                try {

                    Transaction t = transactionService.readTransaction(id);
                    t.setDetails(details);

                    transactionService.updateTransaction(t);

                    text = "Succesful: " + t.toString() + "\n";
                    // System.out.println(a.toString());
                } catch (Exception exception) {
                    text = "Error: " + exception.getMessage() + "\n";
                    // System.out.println(exception.getMessage());
                }

                messageTextField.setText(text);

                frame.getContentPane().remove(panel);

                frame.getContentPane().add(mainPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        JLabel idLabel = new JLabel("Transaction ID: ");

        JPanel idPanel = new JPanel();

        idPanel.add(idLabel);
        idPanel.add(idTextField);

        panel.add(idPanel);

        JLabel detailsLabel = new JLabel("Details: ");

        JPanel detailsPanel = new JPanel();

        detailsPanel.add(detailsLabel);
        detailsPanel.add(detailsTextField);

        panel.add(detailsPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        panel.add(buttonPanel);

        frame.revalidate();
        frame.repaint();

    }

    public void createTransactionDeposit(JFrame frame, JPanel mainPanel, JTextArea messageTextField)
            throws TransactionException, CustomerException, AccountException {

        JTextField detailsTextField = new JTextField();
        detailsTextField.setColumns(50);

        JTextField amountTextField = new JTextField();
        amountTextField.setColumns(50);

        JTextField ownerTextField = new JTextField();
        ownerTextField.setColumns(50);

        JTextField accountTextField = new JTextField();
        accountTextField.setColumns(50);

        JButton button = new JButton("SUBMIT");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String details = detailsTextField.getText();
                int amount = Integer.parseInt(amountTextField.getText().strip());
                String ownerId = ownerTextField.getText();
                String accountId = accountTextField.getText();

                String text = null;
                try {

                    Customer customer = null;
                    if (sessionCustomers.containsKey(ownerId)) {
                        customer = sessionCustomers.get(ownerId);
                    } else {

                        customer = customerService.readCustomer(ownerId);
                        sessionCustomers.put(ownerId, customer);
                    }

                    Account account = accountService.readAccount(accountId);

                    Transaction t = transactionService.deposit(customer, account, details,
                            amount);

                    customer.addPendingTransaction(t);

                    text = "Succesful: " + t.toString() + "\n";
                    // System.out.println(a.toString());
                } catch (Exception exception) {
                    text = "Error: " + exception.getMessage() + "\n";
                    // System.out.println(exception.getMessage());
                }

                messageTextField.setText(text);

                frame.getContentPane().remove(panel);

                frame.getContentPane().add(mainPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        JLabel detailsLabel = new JLabel("Details: ");

        JPanel detailsPanel = new JPanel();

        detailsPanel.add(detailsLabel);
        detailsPanel.add(detailsTextField);

        panel.add(detailsPanel);

        JLabel amountLabel = new JLabel("Amount: ");

        JPanel amountPanel = new JPanel();

        amountPanel.add(amountLabel);
        amountPanel.add(amountTextField);

        panel.add(amountPanel);

        JLabel ownerLabel = new JLabel("Customer ID: ");

        JPanel ownerPanel = new JPanel();

        ownerPanel.add(ownerLabel);
        ownerPanel.add(ownerTextField);

        panel.add(ownerPanel);

        JLabel accountLabel = new JLabel("Account ID: ");

        JPanel accountPanel = new JPanel();

        accountPanel.add(accountLabel);
        accountPanel.add(accountTextField);

        panel.add(accountPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        panel.add(buttonPanel);

        frame.revalidate();
        frame.repaint();

    }

    public void createTransactionWithDraw(JFrame frame, JPanel mainPanel, JTextArea messageTextField)
            throws TransactionException, CustomerException, AccountException {

        JTextField detailsTextField = new JTextField();
        detailsTextField.setColumns(50);

        JTextField amountTextField = new JTextField();
        amountTextField.setColumns(50);

        JTextField ownerTextField = new JTextField();
        ownerTextField.setColumns(50);

        JTextField accountTextField = new JTextField();
        accountTextField.setColumns(50);

        JButton button = new JButton("SUBMIT");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String details = detailsTextField.getText();
                int amount = Integer.parseInt(amountTextField.getText().strip());
                String ownerId = ownerTextField.getText();
                String accountId = accountTextField.getText();

                String text = null;
                try {

                    Customer customer = null;
                    if (sessionCustomers.containsKey(ownerId)) {
                        customer = sessionCustomers.get(ownerId);
                    } else {

                        customer = customerService.readCustomer(ownerId);
                        sessionCustomers.put(ownerId, customer);
                    }

                    Account account = accountService.readAccount(accountId);

                    Transaction t = transactionService.withdraw(customer, account, details,
                            amount);

                    customer.addPendingTransaction(t);

                    text = "Succesful: " + t.toString() + "\n";
                    // System.out.println(a.toString());
                } catch (Exception exception) {
                    text = "Error: " + exception.getMessage() + "\n";
                    // System.out.println(exception.getMessage());
                }

                messageTextField.setText(text);

                frame.getContentPane().remove(panel);

                frame.getContentPane().add(mainPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        JLabel detailsLabel = new JLabel("Details: ");

        JPanel detailsPanel = new JPanel();

        detailsPanel.add(detailsLabel);
        detailsPanel.add(detailsTextField);

        panel.add(detailsPanel);

        JLabel amountLabel = new JLabel("Amount: ");

        JPanel amountPanel = new JPanel();

        amountPanel.add(amountLabel);
        amountPanel.add(amountTextField);

        panel.add(amountPanel);

        JLabel ownerLabel = new JLabel("Customer ID: ");

        JPanel ownerPanel = new JPanel();

        ownerPanel.add(ownerLabel);
        ownerPanel.add(ownerTextField);

        panel.add(ownerPanel);

        JLabel accountLabel = new JLabel("Account ID: ");

        JPanel accountPanel = new JPanel();

        accountPanel.add(accountLabel);
        accountPanel.add(accountTextField);

        panel.add(accountPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        panel.add(buttonPanel);

        frame.revalidate();
        frame.repaint();
    }

    public void createTransactionTransfer(JFrame frame, JPanel mainPanel, JTextArea messageTextField)
            throws TransactionException, CustomerException, AccountException {

        JTextField detailsTextField = new JTextField();
        detailsTextField.setColumns(50);

        JTextField amountTextField = new JTextField();
        amountTextField.setColumns(50);

        JTextField ownerTextField = new JTextField();
        ownerTextField.setColumns(50);

        JTextField srcAccountTextField = new JTextField();
        srcAccountTextField.setColumns(50);

        JTextField dstAccountTextField = new JTextField();
        dstAccountTextField.setColumns(50);

        JButton button = new JButton("SUBMIT");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String details = detailsTextField.getText();
                int amount = Integer.parseInt(amountTextField.getText().strip());
                String ownerId = ownerTextField.getText();
                String srcAccountId = srcAccountTextField.getText();
                String dstAccountId = dstAccountTextField.getText();


                String text = null;
                try {

                    Customer customer = null;
                    if (sessionCustomers.containsKey(ownerId)) {
                        customer = sessionCustomers.get(ownerId);
                    } else {

                        customer = customerService.readCustomer(ownerId);
                        sessionCustomers.put(ownerId, customer);
                    }

                    Account srcAccount = accountService.readAccount(srcAccountId);
                    Account dstAccount = accountService.readAccount(dstAccountId);

                    Transaction[] t = transactionService.transfer(customer, srcAccount,
                            dstAccount, details, amount);

                    customer.addPendingTransaction(t[0]);
                    customer.addPendingTransaction(t[1]);

                    // System.out.println(t[0].toString());
                    // System.out.println(t[1].toString());
                    
                    text = "Succesful: " + t[0].toString() + "\n";
                    text += "Succesful: " + t[1].toString() + "\n";
                    // System.out.println(a.toString());
                } catch (Exception exception) {
                    text = "Error: " + exception.getMessage() + "\n";
                    // System.out.println(exception.getMessage());
                }

                messageTextField.setText(text);

                frame.getContentPane().remove(panel);

                frame.getContentPane().add(mainPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        JLabel detailsLabel = new JLabel("Details: ");

        JPanel detailsPanel = new JPanel();

        detailsPanel.add(detailsLabel);
        detailsPanel.add(detailsTextField);

        panel.add(detailsPanel);

        JLabel amountLabel = new JLabel("Amount: ");

        JPanel amountPanel = new JPanel();

        amountPanel.add(amountLabel);
        amountPanel.add(amountTextField);

        panel.add(amountPanel);

        JLabel ownerLabel = new JLabel("Customer ID: ");

        JPanel ownerPanel = new JPanel();

        ownerPanel.add(ownerLabel);
        ownerPanel.add(ownerTextField);

        panel.add(ownerPanel);

        JLabel srcAccountLabel = new JLabel("Source Account ID: ");

        JPanel srcAccountPanel = new JPanel();

        srcAccountPanel.add(srcAccountLabel);
        srcAccountPanel.add(srcAccountTextField);

        panel.add(srcAccountPanel);

        JLabel dstAccountLabel = new JLabel("Destination Account ID: ");

        JPanel dstAccountPanel = new JPanel();

        dstAccountPanel.add(dstAccountLabel);
        dstAccountPanel.add(dstAccountTextField);

        panel.add(dstAccountPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        panel.add(buttonPanel);

        frame.revalidate();
        frame.repaint();

    }

    // only store successful transactions
    public void storePendingTransactions(JFrame frame, JPanel mainPanel, JTextArea messageTextField)
            throws CustomerException, AccountException, TransactionException {

        JTextField idTextField = new JTextField();
        idTextField.setColumns(50);

        JButton button = new JButton("SUBMIT");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ownerId = idTextField.getText();


                String text = null;
                try {

                    Customer customer = null;
                    if (sessionCustomers.containsKey(ownerId)) {
                        customer = sessionCustomers.get(ownerId);
                    } else {

                        customer = customerService.readCustomer(ownerId);
                        sessionCustomers.put(ownerId, customer);
                    }

                    List<Transaction> successful = customer.performPendingTransactions();

                    text = "Successful transactions below" + "\n";
                    // System.out.println("Successful transactions below");

                    for (Transaction t : successful) {
                        accountService.updateAccount(t.getAccount());
                        transactionService.createTransaction(t);
                        // System.out.println(t.toString());
                        text += t.toString() + "\n";
                    }
                } catch (Exception exception) {
                    text = "Error: " + exception.getMessage() + "\n";
                    // System.out.println(exception.getMessage());
                }

                messageTextField.setText(text);

                frame.getContentPane().remove(panel);

                frame.getContentPane().add(mainPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        JLabel idLabel = new JLabel("Customer ID: ");

        JPanel idPanel = new JPanel();

        idPanel.add(idLabel);
        idPanel.add(idTextField);

        panel.add(idPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        panel.add(buttonPanel);

        frame.revalidate();
        frame.repaint();
    }

}
