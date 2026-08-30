/********************************************************************
 * Name: Elvis Melendez
 * Date: 8/29/2026
 * Assignment: SDC330L Course Project - Week 1
 *
 * Purpose:
 * This class represents an IRA account and adds an annual
 * contribution limit to the information inherited from Account.
 ********************************************************************/

// Inheritance: IRAAccount is a derived class of Account.
public class IRAAccount extends Account {

    private double annualContributionLimit;

    public IRAAccount(String accountNumber, double balance,
            double annualContributionLimit) {

        super(accountNumber, balance);
        this.annualContributionLimit = annualContributionLimit;
    }

    public double getAnnualContributionLimit() {
        return annualContributionLimit;
    }

    @Override
    public String toString() {
        return String.format(
                "Account Type: IRA%n%s%nAnnual Contribution Limit: $%.2f",
                super.toString(),
                annualContributionLimit
        );
    }
}
