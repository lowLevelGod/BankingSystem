package pao.DataLayer;

import java.util.HashMap;

import pao.BankException.CustomerException;
import pao.Customer.Customer;

public class CustomerDL {

    // placeholder for database service
    private HashMap<String, Customer> customers;

    public CustomerDL() {
        customers = new HashMap<String, Customer>();
    }

    public void createCustomer(Customer customer) throws CustomerException {

        String id = customer.getId();
        if (!customers.containsKey(id)) {
            customers.put(id, customer);
        } else {
            throw new CustomerException("Failed to add new customer. ID " + id + " already exists!");
        }
    }

    public void deleteCustomer(String id) throws CustomerException {

        if (customers.remove(id) == null) {
            throw new CustomerException("Failed to delete customer. ID " + id + " does not exist!");
        }
    }

    public void updateCustomer(Customer customer) throws CustomerException {

        String id = customer.getId();
        if (customers.containsKey(id)) {
            customers.put(id, customer);
        } else {
            throw new CustomerException("Failed to update customer data. ID " + id + " does not exist!");
        }
    }

    public Customer readCustomer(String id) throws CustomerException {

        Customer customer = customers.get(id);
        if (customer == null) {
            throw new CustomerException("Failed to retrieve customer data. ID " + id + " does not exist!");
        }

        return customer;
    }
}
