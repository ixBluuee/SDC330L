/********************************************************************
 * Name: Elvis Melendez
 * Date: 8/29/2026
 * Assignment: SDC330L Course Project - Week 3
 *
 * Purpose:
 * This class represents a checking account and adds an overdraft
 * limit to the shared Account functionality.
 ********************************************************************/

public class CheckingAccount extends Account {

    // Private access limits direct access to this class.
    private final double overdraftLimit;

    public CheckingAccount(String accountNumber, double balance,
            double overdraftLimit) {

        // Constructor chaining: initializes inherited account data.
        super(accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    // Abstraction: provides the account type required by Account.
    @Override
    public String getAccountType() {
        return "Checking";
    }

    @Override
    public String toString() {
        return String.format(
                "%s%nOverdraft Limit: $%.2f",
                super.toString(),
                overdraftLimit
        );
    }
}
