/********************************************************************
 * Name: Elvis Melendez
 * Date: 8/29/2026
 * Assignment: Bank Account Management System - Final Project
 *
 * Purpose:
 * This class represents an IRA account and adds an annual
 * contribution limit to the shared Account functionality.
 ********************************************************************/

public class IRAAccount extends Account {

    private final double annualContributionLimit;

    public IRAAccount(String accountNumber, double balance,
            double annualContributionLimit) {

        // Constructor chaining: initializes inherited account data.
        super(accountNumber, balance);
        this.annualContributionLimit = annualContributionLimit;
    }

    public double getAnnualContributionLimit() {
        return annualContributionLimit;
    }

    // Abstraction: provides the account type required by Account.
    @Override
    public String getAccountType() {
        return "IRA";
    }

    @Override
    public String toString() {
        return String.format(
                "%s%nAnnual Contribution Limit: $%.2f",
                super.toString(),
                annualContributionLimit
        );
    }
}
