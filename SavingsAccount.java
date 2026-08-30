/********************************************************************
 * Name: Elvis Melendez
 * Date: 8/29/2026
 * Assignment: SDC330L Course Project - Week 3
 *
 * Purpose:
 * This class represents a savings account and adds an interest rate
 * to the shared Account functionality.
 ********************************************************************/

public class SavingsAccount extends Account {

    private double interestRate;

    public SavingsAccount(String accountNumber, double balance,
            double interestRate) {

        // Constructor chaining: initializes inherited account data.
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    // Abstraction: provides the account type required by Account.
    @Override
    public String getAccountType() {
        return "Savings";
    }

    @Override
    public String toString() {
        return String.format(
                "%s%nInterest Rate: %.2f%%",
                super.toString(),
                interestRate
        );
    }
}
