/********************************************************************
 * Name: Elvis Melendez
 * Date: 8/29/2026
 * Assignment: SDC330L Course Project - Week 2
 *
 * Purpose:
 * This class implements the BankOperations interface and manages the
 * collection of customers used by the application.
 ********************************************************************/

import java.util.ArrayList;

// Interface implementation: BankManager provides the operations
// required by the BankOperations interface.
public class BankManager implements BankOperations {

    private ArrayList<Customer> customers;

    public BankManager() {
        customers = new ArrayList<Customer>();
    }

    @Override
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public Customer findCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }

        return null;
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        return new ArrayList<Customer>(customers);
    }
}
