package pao.Customer;

import pao.BankException.CustomerException;
import pao.DataLayer.CustomerDL;

public class CustomerService {

    private int id = 1;
    private CustomerDL customers;

    public CustomerService() {
        customers = CustomerDL.getCustomerDL();
    }

    public Natural createNatural(String firstName, String lastName) throws CustomerException {

        String id = Integer.toString(getNextId());
        Natural nat = new Natural(id, firstName, lastName);
        
        customers.createCustomer(nat);

        return nat;
    }

    public Artificial createArtificial(String companyName) throws CustomerException {

        String id = Integer.toString(getNextId());
        Artificial art = new Artificial(id, companyName);

        customers.createCustomer(art);

        return art;
    }

    public void deleteCustomer(String id) throws CustomerException {
        customers.deleteCustomer(id);
    }

    public void updateCustomer(Customer customer) throws CustomerException {

        customers.updateCustomer(customer);

    }

    public Customer readCustomer(String id) throws CustomerException {

        Customer customer = null;

        customer = customers.readCustomer(id);
   
        return customer;
    }

    private int getNextId() {
        int id = this.id;
        this.id += 1;

        return id;
    }
}
