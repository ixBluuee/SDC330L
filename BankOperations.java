/********************************************************************
 * Name: Elvis Melendez
 * Date: 8/29/2026
 * Assignment: SDC330L Course Project - Week 2
 *
 * Purpose:
 * This interface defines the basic customer operations provided by
 * the Bank Account Management System.
 ********************************************************************/

import java.util.ArrayList;

// Interface creation: defines operations that a bank manager must provide.
public interface BankOperations {

    void addCustomer(Customer customer);

    Customer findCustomerById(int customerId);

    ArrayList<Customer> getAllCustomers();
}