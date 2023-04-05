package pao.Card;

public abstract class Card {

    private String id;

    public String getId(){
        return this.id;
    }

    public abstract String getHolderName();
    public abstract int getAmount();
    public abstract void addAmount(int amount);
    public abstract void substractAmount(int amount) throws Exception;
}
