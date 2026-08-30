/********************************************************************
 * Name: Elvis Melendez
 * Date: 8/29/2026
 * Assignment: SDC330L Course Project - Week 3
 *
 * Purpose:
 * This abstract base class defines the information and functionality
 * shared by every bank account type.
 ********************************************************************/

// Abstraction: Account provides shared functionality but cannot be
// instantiated directly. A specific derived account type is required.
public abstract class Account {

    // Private access prevents direct modification outside this class.
    private final String accountNumber;
    private double balance;

    // Protected access allows only this class and its derived classes
    // to use the Account constructor.
    protected Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }

        return false;
    }

    // Abstract method: every derived class must identify its
    // specific account type.
    public abstract String getAccountType();

    @Override
    public String toString() {
        return String.format(
                "Account Type: %s%nAccount Number: %s%nBalance: $%.2f",
                getAccountType(),
                accountNumber,
                balance
        );
    }
}
