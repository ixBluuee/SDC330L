/********************************************************************
 * Name: Elvis Melendez
 * Date: 8/29/2026
 * Assignment: SDC330L Course Project - Week 4
 *
 * Purpose:
 * This class represents an individual bank customer and contains the
 * bank accounts associated with that customer.
 ********************************************************************/

import java.util.ArrayList;

public class Customer {

    // Private access protects customer data from direct modification.
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    // Composition: a Customer contains a collection of Account objects.
    private ArrayList<Account> accounts;

    // Full constructor initializes all customer properties.
    public Customer(int customerId, String firstName, String lastName,
            String email, String phoneNumber) {

        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        accounts = new ArrayList<Account>();
    }

    // Constructor overloading: creates a customer when contact
    // information is not available.
    public Customer(int customerId, String firstName, String lastName) {
        this(
                customerId,
                firstName,
                lastName,
                "Not provided",
                "Not provided"
        );
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    @Override
    public String toString() {
        return String.format(
                "Customer ID: %d%nName: %s %s%nEmail: %s%nPhone: %s",
                customerId,
                firstName,
                lastName,
                email,
                phoneNumber
        );
    }
}
