package pao.Card;

import pao.Customer.Customer;
import pao.Utils.Typeable;

public abstract class Card implements Typeable {

    private final String id;

    public Card(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public abstract Customer getOwner();

    public abstract int getAmount();

    public abstract void addAmount(int amount);

    public abstract void substractAmount(int amount) throws Exception;
}
