/********************************************************************
 * Name: Elvis Melendez
 * Date: 8/29/2026
 * Assignment: SDC330L Course Project - Week 1
 *
 * Purpose:
 * This class represents an individual bank customer and contains the
 * bank accounts associated with that customer.
 ********************************************************************/

import java.util.ArrayList;

public class Customer {

    private final int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    // Composition: a Customer contains a collection of Account objects.
    private ArrayList<Account> accounts;

    public Customer(int customerId, String firstName, String lastName,
            String email, String phoneNumber) {

        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        accounts = new ArrayList<Account>();
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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
