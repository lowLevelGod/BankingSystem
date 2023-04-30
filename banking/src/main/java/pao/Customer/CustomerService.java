package pao.Customer;

import pao.BankException.CustomerException;
import pao.DataLayer.CustomerDL;

public class CustomerService {

    private int id = 1;
    private CustomerDL customers;

    public CustomerService() {
        customers = new CustomerDL();
    }

    public Natural createNatural(String firstName, String lastName) {

        String id = Integer.toString(getNextId());
        Natural nat = new Natural(id, firstName, lastName);

        try {
            customers.createCustomer(nat);
        } catch (CustomerException exception) {
            System.out.println(exception.getMessage());

            return null;
        }

        return nat;
    }

    public Artificial createArtificial(String companyName) {

        String id = Integer.toString(getNextId());
        Artificial art = new Artificial(id, companyName);

        try {
            customers.createCustomer(art);
        } catch (CustomerException exception) {
            System.out.println(exception.getMessage());

            return null;
        }

        return art;
    }

    public void deleteCustomer(String id) {
        try {
            customers.deleteCustomer(id);
        } catch (CustomerException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void updateCustomer(Customer customer) {
        try {
            customers.updateCustomer(customer);
        } catch (CustomerException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public Customer readCustomer(String id) {

        Customer customer = null;
        try {
            customer = customers.readCustomer(id);
        } catch (CustomerException exception) {
            System.out.println(exception.getMessage());
        }

        return customer;
    }

    private int getNextId() {
        int id = this.id;
        this.id += 1;

        return id;
    }
}
