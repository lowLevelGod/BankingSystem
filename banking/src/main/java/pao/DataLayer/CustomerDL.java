package pao.DataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pao.BankException.CustomerException;
import pao.Customer.Artificial;
import pao.Customer.Customer;
import pao.Customer.Natural;

public class CustomerDL {

    // placeholder for database service
    // private HashMap<String, Customer> customers;

    private Connection connection;

    public CustomerDL(Connection connection) {
        // customers = new HashMap<String, Customer>();
        this.connection = connection;
    }

    public void createCustomer(Customer customer) throws CustomerException {
        try {

            PreparedStatement preparedStmt = null;
            if (customer.getType().equals("Natural customer")) {
                String[] name = customer.getName().split(" ");
                String firstName = name[0];
                String lastName = "";
                for (int i = 1; i < name.length; ++i)
                    lastName += name[i];

                String query = "INSERT INTO Customer (id, first_name, last_name, type) VALUES (?, ?, ?, ?)";
                preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, customer.getId());
                preparedStmt.setString(2, firstName);
                preparedStmt.setString(3, lastName);
                preparedStmt.setString(4, customer.getType());
            } else {
                String query = "INSERT INTO Customer (id, company_name, type) VALUES (?, ?, ?)";
                preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, customer.getId());
                preparedStmt.setString(2, customer.getName());
                preparedStmt.setString(3, customer.getType());
            }
            preparedStmt.execute();
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new CustomerException("Failed to add new customer.");
        }
    }

    public void deleteCustomer(String id) throws CustomerException {

        int res = 0;
        try {
            String query = "DELETE FROM Customer WHERE id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, id);
            res = preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new CustomerException("Failed to delete customer. ID " + id + " does not exist!");
        }

        if (res == 0) {
            throw new CustomerException("Failed to delete customer. ID " + id + " does not exist!");
        }
    }

    public void updateCustomer(Customer customer) throws CustomerException {
    
        int res = 0;
        try {
            PreparedStatement preparedStmt = null;
            if (customer.getType().equals("Natural customer")) {
                String[] name = customer.getName().split(" ");
                String firstName = name[0];
                String lastName = "";
                for (int i = 1; i < name.length; ++i)
                    lastName += name[i];

                String query = "UPDATE Customer SET first_name = ?, last_name = ? WHERE id = ?";
                preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, firstName);
                preparedStmt.setString(2, lastName);
                preparedStmt.setString(3, customer.getId());
            } else {
                String query = "UPDATE Customer SET company_name = ? WHERE id = ?";
                preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, customer.getName());
                preparedStmt.setString(2, customer.getId());
            }
            res = preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (Exception e) {
            // System.out.println(e.toString());
            // throw new CustomerException("Failed to update customer data. ID " + customer.getId() + " does not exist!");
        }

        if (res == 0) {
            throw new CustomerException("Failed to update customer data. ID " + customer.getId() + " does not exist!");
        }
    }

    public Customer readCustomer(String id) throws CustomerException {

        Customer customer = null;
        try {

            String query = "SELECT * FROM Customer WHERE id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, id);

            ResultSet result = preparedStmt.executeQuery();
            result.next();

            if (result.getString("type").equals("Natural customer")) {
                customer = new Natural(id, result);
            } else {
                customer = new Artificial(id, result);
            }
            preparedStmt.close();

        } catch (Exception e) {
            System.out.println(e.toString());
            throw new CustomerException("Failed to retrieve customer data. ID " + id + " does not exist!");
        }

        return customer;
    }
}
