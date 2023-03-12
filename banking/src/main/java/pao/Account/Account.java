package pao.Account;

public class Account {
    private String id;
    private int interest;
    private String ownerId;
    private int amount;

    public Account(String id, int interest, String ownerId, int amount) {
        this.id = id;
        this.interest = interest;
        this.ownerId = ownerId;
        this.amount = amount;
    }

    public boolean addAmount(int amount) {
        this.amount += amount;

        return true;
    }

    public boolean subtractAmount(int amount) {
        if (this.amount >= 0) {
            this.amount -= amount;
            return true;
        }

        return false;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public int getInterest() {
        return this.interest;
    }

    public int getAmount() {
        return this.amount;
    }
}
