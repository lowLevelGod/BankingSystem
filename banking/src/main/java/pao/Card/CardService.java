package pao.Card;

import java.sql.Connection;

import pao.Account.Account;
import pao.BankException.CardException;
import pao.Customer.Customer;
import pao.DataLayer.CardDL;

public class CardService {
    private int id = 1;
    private CardDL cards;

    private int getNextId() {
        int id = this.id;
        this.id += 1;

        return id;
    }

    public CardService(Connection connection) {
        cards = new CardDL(connection);
    }

    public CreditCard createCredit(Customer owner, int amount) throws CardException {

        String id = Integer.toString(getNextId());
        CreditCard credit = new CreditCard(id, owner, amount);

        cards.createCard(credit);

        return credit;
    }

    public DebitCard createDebit(Account account) throws CardException {

        String id = Integer.toString(getNextId());
        DebitCard debit = new DebitCard(id, account);

        cards.createCard(debit);

        return debit;
    }

    public void deleteCard(String id) throws CardException {

        cards.deleteCard(id);
    }

    public void updateCard(Card card) throws CardException {

        cards.updateCard(card);

    }

    public Card readCard(String id) throws CardException {

        Card card = null;

        card = cards.readCard(id);

        return card;
    }

}
