package pao.Customer;

import java.util.Arrays;

import pao.Account.Account;
import pao.Account.AccountFactory;
import pao.Utils.Typeable;

public class Natural extends Customer implements Typeable {

    private String firstName;
    private String lastName;

    public Natural(String id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String toString() {
        return this.getType() + " " + "'" + this.getName() + "'";
    }

    public Account createAccount(String accId) {
        AccountFactory factory = new AccountFactory();

        final int naturalInterest = 5;
        Account acc = factory.createBaseAccount(accId, naturalInterest, this);

        return acc;
    }

    public String getType() {
        return "Natural customer";
    }

    private String getFirstName() {
        return this.firstName;
    }

    private String getLastName() {
        return this.lastName;
    }

    public String getName() {
        return getFirstName() + " " + getLastName();
    }

    public void setName(String name) {
        String[] arr = name.split(" ");

        this.firstName = arr[0];
        this.lastName = String.join(" ", Arrays.copyOfRange(arr, 1, arr.length));
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
