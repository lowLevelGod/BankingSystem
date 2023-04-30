package pao.DataLayer;

import java.util.HashMap;

import pao.BankException.CardException;
import pao.Card.Card;

public class CardDL {

    // placeholder for database service
    private HashMap<String, Card> cards;

    public CardDL() {
        cards = new HashMap<String, Card>();
    }

    public void createCard(Card card) throws CardException {

        String id = card.getId();
        if (!cards.containsKey(id)) {
            cards.put(id, card);
        } else {
            throw new CardException("Failed to add new card. ID " + id + " already exists!");
        }
    }

    public void deleteCard(String id) throws CardException {

        if (cards.remove(id) == null) {
            throw new CardException("Failed to delete card. ID " + id + " does not exist!");
        }
    }

    public void updateCard(Card card) throws CardException {

        String id = card.getId();
        if (cards.containsKey(id)) {
            cards.put(id, card);
        } else {
            throw new CardException("Failed to update card data. ID " + id + " does not exist!");
        }
    }

    public Card readCard(String id) throws CardException {

        Card card = cards.get(id);
        if (card == null) {
            throw new CardException("Failed to retrieve card data. ID " + id + " does not exist!");
        }

        return card;
    }
}
