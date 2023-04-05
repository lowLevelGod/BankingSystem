package pao.Card;

public abstract class Card {

    private String id;

    public String getId(){
        return this.id;
    }

    public abstract String getHolderName();
}
