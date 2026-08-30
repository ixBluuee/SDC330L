/********************************************************************
 * Name: Elvis Melendez
 * Date: 8/29/2026
 * Assignment: SDC330L Course Project - Week 4
 *
 * Purpose:
 * This interface defines the customer, account, and transaction
 * operations required by the Bank Account Management System.
 ********************************************************************/

import java.util.ArrayList;

// Interface creation: defines the database operations that the
// BankManager implementation must provide.
public interface BankOperations {

    boolean addCustomer(Customer customer);
    Customer findCustomerById(int customerId);
    ArrayList<Customer> getAllCustomers();
    boolean addAccount(int customerId, Account account);
    Account findAccount(String accountNumber);
    boolean removeAccount(String accountNumber);
    boolean deposit(String accountNumber, double amount);
    boolean withdraw(String accountNumber, double amount);
    boolean updateCustomer(Customer customer);
}
