/********************************************************************
 * Name: Elvis Melendez
 * Date: 8/29/2026
 * Assignment: SDC330L Course Project - Week 1
 *
 * Purpose:
 * This class represents a savings account and adds an interest rate
 * to the information inherited from the Account class.
 ********************************************************************/

// Inheritance: SavingsAccount is a derived class of Account.
public class SavingsAccount extends Account {

    private double interestRate;

    public SavingsAccount(String accountNumber, double balance,
            double interestRate) {

        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public String toString() {
        return String.format(
                "Account Type: Savings%n%s%nInterest Rate: %.2f%%",
                super.toString(),
                interestRate
        );
    }
}
