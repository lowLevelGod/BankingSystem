package pao;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import pao.BankException.CustomerException;

/**
 * Hello world!
 *
 */
public class Main {

    private static boolean end = false;

    private static final Set<String> commands = Set.of("create_natural_customer", "create_artificial_customer",
            "read_customer",
            "update_customer", "delete_customer", "create_base_account", "read_account", "update_account",
            "delete_account", "create_credit_card", "create_debit_card", "read_card", "update_card", "delete_card",
            "create_deposit_transaction", "create_withdraw_transaction", "create_transfer_transaction",
            "read_transaction", "update_transaction", "delete_transaction", "store_pending_transactions");

    private static final HashMap<String, ActionListener> actions = new HashMap<String, ActionListener>();

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/bank";
            String user = "root";
            String password = "1234";

            Connection connection = DriverManager.getConnection(url, user, password);
            // System.out.println("It works");
            return connection;
        } catch (SQLException e) {
            System.out.println(e.toString());

            return null;
        }
    }

    private static void initActions(SystemFacade systemFacade, JFrame frame, JPanel mainPanel, JTextArea messageTextField) {
        actions.put("create_natural_customer", new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.remove(mainPanel);

                try {
                    systemFacade.createNaturalCustomer(frame, mainPanel, messageTextField);
                    AuditService.getInstance().logAction("create_natural_customer");
                } catch (Exception exception) {
                    System.out.println(exception.toString());
                }
            }
        });

        actions.put("create_artificial_customer", new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.remove(mainPanel);

                try {
                    systemFacade.createArtificialCustomer(frame, mainPanel, messageTextField);
                    AuditService.getInstance().logAction("create_artificial_customer");
                } catch (Exception exception) {
                    System.out.println(exception.toString());
                }
            }
        });

        actions.put("read_customer", new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.remove(mainPanel);

                try {
                    systemFacade.readCustomer(frame, mainPanel, messageTextField);
                    AuditService.getInstance().logAction("read_customer");
                } catch (Exception exception) {
                    System.out.println(exception.toString());
                }
            }
        });

        actions.put("update_customer", new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.remove(mainPanel);

                try {
                    systemFacade.updateCustomer(frame, mainPanel, messageTextField);
                    AuditService.getInstance().logAction("update_customer");
                } catch (Exception exception) {
                    System.out.println(exception.toString());
                }
            }
        });

        actions.put("delete_customer", new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.remove(mainPanel);

                try {
                    systemFacade.deleteCustomer(frame, mainPanel, messageTextField);
                    AuditService.getInstance().logAction("delete_customer");
                } catch (Exception exception) {
                    System.out.println(exception.toString());
                }
            }
        });

        actions.put("create_base_account", new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.remove(mainPanel);

                try {
                    systemFacade.createBaseAccount(frame, mainPanel, messageTextField);
                    AuditService.getInstance().logAction("create_base_account");
                } catch (Exception exception) {
                    System.out.println(exception.toString());
                }
            }
        });

        actions.put("update_account", new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.remove(mainPanel);

                try {
                    systemFacade.updateAccount(frame, mainPanel, messageTextField);
                    AuditService.getInstance().logAction("update_account");
                } catch (Exception exception) {
                    System.out.println(exception.toString());
                }
            }
        });

        actions.put("read_account", new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.remove(mainPanel);

                try {
                    systemFacade.readAccount(frame, mainPanel, messageTextField);
                    AuditService.getInstance().logAction("read_account");
                } catch (Exception exception) {
                    System.out.println(exception.toString());
                }
            }
        });

        actions.put("delete_account", new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.remove(mainPanel);

                try {
                    systemFacade.deleteAccount(frame, mainPanel, messageTextField);
                    AuditService.getInstance().logAction("delete_account");
                } catch (Exception exception) {
                    System.out.println(exception.toString());
                }
            }
        });

        actions.put("create_credit_card", new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.remove(mainPanel);

                try {
                    systemFacade.createCreditCard(frame, mainPanel, messageTextField);
                    AuditService.getInstance().logAction("create_credit_card");
                } catch (Exception exception) {
                    System.out.println(exception.toString());
                }
            }
        });

        actions.put("create_debit_card", new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.remove(mainPanel);

                try {
                    systemFacade.createDebitCard(frame, mainPanel, messageTextField);
                    AuditService.getInstance().logAction("create_debit_card");
                } catch (Exception exception) {
                    System.out.println(exception.toString());
                }
            }
        });

        actions.put("read_card", new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.remove(mainPanel);

                try {
                    systemFacade.readCard(frame, mainPanel, messageTextField);
                    AuditService.getInstance().logAction("read_card");
                } catch (Exception exception) {
                    System.out.println(exception.toString());
                }
            }
        });

        actions.put("update_card", new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.remove(mainPanel);

                try {
                    systemFacade.updateCard(frame, mainPanel, messageTextField);
                    AuditService.getInstance().logAction("update_card");
                } catch (Exception exception) {
                    System.out.println(exception.toString());
                }
            }
        });

        actions.put("delete_card", new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.remove(mainPanel);

                try {
                    systemFacade.deleteCard(frame, mainPanel, messageTextField);
                    AuditService.getInstance().logAction("delete_card");
                } catch (Exception exception) {
                    System.out.println(exception.toString());
                }
            }
        });

        actions.put("create_deposit_transaction", new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.remove(mainPanel);

                try {
                    systemFacade.createTransactionDeposit(frame, mainPanel, messageTextField);
                    AuditService.getInstance().logAction("create_deposit_transaction");
                } catch (Exception exception) {
                    System.out.println(exception.toString());
                }
            }
        });

        actions.put("create_withdraw_transaction", new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.remove(mainPanel);

                try {
                    systemFacade.createTransactionWithDraw(frame, mainPanel, messageTextField);
                    AuditService.getInstance().logAction("create_withdraw_transaction");
                } catch (Exception exception) {
                    System.out.println(exception.toString());
                }
            }
        });

        actions.put("create_transfer_transaction", new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.remove(mainPanel);

                try {
                    systemFacade.createTransactionTransfer(frame, mainPanel, messageTextField);
                    AuditService.getInstance().logAction("create_transfer_transaction");
                } catch (Exception exception) {
                    System.out.println(exception.toString());
                }
            }
        });

        actions.put("read_transaction", new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.remove(mainPanel);

                try {
                    systemFacade.readTransaction(frame, mainPanel, messageTextField);
                    AuditService.getInstance().logAction("read_transaction");
                } catch (Exception exception) {
                    System.out.println(exception.toString());
                }
            }
        });

        actions.put("update_transaction", new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.remove(mainPanel);

                try {
                    systemFacade.updateTransaction(frame, mainPanel, messageTextField);
                    AuditService.getInstance().logAction("update_transaction");
                } catch (Exception exception) {
                    System.out.println(exception.toString());
                }
            }
        });

        actions.put("delete_transaction", new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.remove(mainPanel);

                try {
                    systemFacade.deleteTransaction(frame, mainPanel, messageTextField);
                    AuditService.getInstance().logAction("delete_transaction");
                } catch (Exception exception) {
                    System.out.println(exception.toString());
                }
            }
        });

        actions.put("store_pending_transactions", new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.remove(mainPanel);

                try {
                    systemFacade.storePendingTransactions(frame, mainPanel, messageTextField);
                    AuditService.getInstance().logAction("store_pending_transactions");
                } catch (Exception exception) {
                    System.out.println(exception.toString());
                }
            }
        });
    }

    private static void initButton(String title, String section, JPanel panel) {
        JLabel label = new JLabel(title);

        label.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setForeground(Color.BLUE);

        panel.add(label);

        for (String command : commands) {
            if (command.contains(section)) {

                JButton button = new JButton(command.replace("_", " "));
                button.addActionListener(actions.get(command));
                panel.add(button);
            }
        }
    }

    public static void main(String[] args) {
        Connection connection = getConnection();
        SystemFacade systemFacade = new SystemFacade(connection);
        // Demo demo = new Demo(connection);
        // // demo customer service
        // demo.customer();

        // // demo account service
        // demo.account();

        // // demo transaction service
        // demo.transaction();

        // // demo card service
        // demo.card();

        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.setLocation(20, 20);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        JTextArea messageTextField = new JTextArea();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        initButton("End", "random", panel);

        JButton button = new JButton("end");
        panel.add(button);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                end = true;
            }
        });

        initActions(systemFacade, frame, panel, messageTextField);

        initButton("Customers", "customer", panel);
        initButton("Accounts", "account", panel);
        initButton("Cards", "card", panel);
        initButton("Transactions", "transaction", panel);

        panel.add(messageTextField);

        frame.revalidate();
        frame.repaint();

        while (!end) {

        }

        try {
            assert connection != null;
            connection.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
