package pao.Card;

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

    public CardService() {
        cards = new CardDL();
    }

    public CreditCard createCredit(Customer owner, int amount) {

        String id = Integer.toString(getNextId());
        CreditCard credit = new CreditCard(id, owner, amount);

        try {
            cards.createCard(credit);
        } catch (CardException exception) {
            System.out.println(exception.getMessage());

            return null;
        }

        return credit;
    }

    public DebitCard createDebit(Account account) {

        String id = Integer.toString(getNextId());
        DebitCard debit = new DebitCard(id, account);

        try {
            cards.createCard(debit);
        } catch (CardException exception) {
            System.out.println(exception.getMessage());

            return null;
        }

        return debit;
    }

    public void deleteCard(String id) {
        try {
            cards.deleteCard(id);
        } catch (CardException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void updateCard(Card card){
        try {
            cards.updateCard(card);
        } catch (CardException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public Card readCard(String id){

        Card card = null;
        try {
            card = cards.readCard(id);
        } catch (CardException exception) {
            System.out.println(exception.getMessage());
        }

        return card;
    }

}
