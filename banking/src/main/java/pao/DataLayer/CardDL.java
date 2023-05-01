package pao.DataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pao.Account.Account;
import pao.BankException.CardException;
import pao.Card.Card;
import pao.Card.CreditCard;
import pao.Card.DebitCard;
import pao.Customer.Customer;

public class CardDL {

    // placeholder for database service
    // private HashMap<String, Card> cards;
    private final Connection connection;

    public CardDL(Connection connection) {
        // cards = new HashMap<String, Card>();
        this.connection = connection;
    }

    public void createCard(Card card) throws CardException {

        try {

            PreparedStatement preparedStmt = null;
            if (card.getType().equals("Debit Card")) {
                String query = "INSERT INTO Card (id, account, type) VALUES (?, ?, ?)";
                preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, card.getId());
                preparedStmt.setString(2, ((DebitCard) card).getAccount().getId());
                preparedStmt.setString(3, card.getType());
            } else {
                String query = "INSERT INTO Card (id, owner, amount, type) VALUES (?, ?, ?, ?)";
                preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, card.getId());
                preparedStmt.setString(2, card.getOwner().getId());
                preparedStmt.setInt(3, card.getAmount());
                preparedStmt.setString(4, card.getType());
            }
            preparedStmt.execute();
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new CardException("Failed to add new card.");
        }
    }

    public void deleteCard(String id) throws CardException {

        int res = 0;
        try {
            String query = "DELETE FROM Card WHERE id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, id);
            res = preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new CardException("Failed to delete card. ID " + id + " does not exist!");
        }

        if (res == 0) {
            throw new CardException("Failed to delete card. ID " + id + " does not exist!");
        }
    }

    public void updateCard(Card card) throws CardException {

        int res = 0;
        try {

            PreparedStatement preparedStmt = null;
            if (card.getType().equals("Debit Card")) {
                String query = "UPDATE Card SET account = ? WHERE id = ?";
                preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, ((DebitCard) card).getAccount().getId());
                preparedStmt.setString(2, card.getId());
            } else {
                String query = "UPDATE Card SET amount = ? WHERE id = ?";
                preparedStmt = connection.prepareStatement(query);
                preparedStmt.setInt(1, card.getAmount());
                preparedStmt.setString(2, card.getId());
            }
            res = preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new CardException("Failed to update card data. ID " + card.getId() + " does not exist!");
        }

        if (res == 0) {
            throw new CardException("Failed to update card data. ID " + card.getId() + " does not exist!");
        }
    }

    public Card readCard(String id) throws CardException {

        Card card = null;
        try {

            String query = "SELECT * FROM Transaction WHERE id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, id);

            ResultSet result = preparedStmt.executeQuery();
            result.next();

            if (result.getString("type").equals("Debit Card")) {
                AccountDL aDl = new AccountDL(connection);
                Account account = aDl.readAccount(result.getString("account"));
                card = new DebitCard(id, account);
            } else {
                CustomerDL cDl = new CustomerDL(connection);
                Customer customer = cDl.readCustomer(result.getString("customer"));
                card = new CreditCard(id, customer, result);
            }
            preparedStmt.close();

        } catch (Exception e) {
            System.out.println(e.toString());
            throw new CardException("Failed to retrieve card data. ID " + id + " does not exist!");
        }

        return card;
    }
}
