/********************************************************************
 * Name: Elvis Melendez
 * Date: 8/29/2026
 * Assignment: SDC330L Course Project - Week 1
 *
 * Purpose:
 * This class represents a checking account and adds an overdraft
 * limit to the information inherited from the Account class.
 ********************************************************************/

// Inheritance: CheckingAccount is a derived class of Account.
public class CheckingAccount extends Account {

    private double overdraftLimit;

    public CheckingAccount(String accountNumber, double balance,
            double overdraftLimit) {

        super(accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    @Override
    public String toString() {
        return String.format(
                "Account Type: Checking%n%s%nOverdraft Limit: $%.2f",
                super.toString(),
                overdraftLimit
        );
    }
}
