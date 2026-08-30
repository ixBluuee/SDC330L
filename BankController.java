/********************************************************************
 * Name: Elvis Melendez
 * Date: 8/29/2026
 * Assignment: SDC330L Course Project - Week 4
 *
 * Purpose:
 * This class controls the console interface for the Bank Account
 * Management System and processes customer and account operations.
 ********************************************************************/

import java.util.ArrayList;
import java.util.Scanner;

public class BankController {

    private final Scanner input;
    private final BankOperations bankManager;

    public BankController(BankOperations bankManager) {
        this.bankManager = bankManager;
        input = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        displayWelcomeMessage();

        while (running) {
            displayMenu();
            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    displayAllCustomers();
                    break;

                case "2":
                    displayOneCustomer();
                    break;

                case "3":
                    addAccount();
                    break;

                case "4":
                    removeAccount();
                    break;

                case "5":
                    makeDeposit();
                    break;

                case "6":
                    makeWithdrawal();
                    break;

                case "7":
                    updateCustomer();
                    break;

                case "8":
                    System.out.println(
                            "\nThank you for using the Bank Account "
                            + "Management System.");
                    running = false;
                    break;

                default:
                    System.out.println(
                            "\nInvalid choice. Please enter 1 through 8.");
            }
        }

        input.close();
    }

    private void displayWelcomeMessage() {
        System.out.println(
                "\n==================================================");
        System.out.println(" SDC330L Course Project - Week 4");
        System.out.println(" Bank Account Management System");
        System.out.println(" Created by: Elvis Melendez");
        System.out.println(
                "==================================================");

        System.out.println(
                "\nWelcome to the Bank Account Management System.");
        System.out.println(
                "Select a menu option to view customers, manage");
        System.out.println(
                "accounts, complete transactions, or exit.");
    }

    private void displayMenu() {
        System.out.println("\n========== Main Menu ==========");
        System.out.println("1. Display All Customer Accounts");
        System.out.println("2. Display Accounts for One Customer");
        System.out.println("3. Add an Account");
        System.out.println("4. Remove an Account");
        System.out.println("5. Make a Deposit");
        System.out.println("6. Make a Withdrawal");
        System.out.println("7. Update Customer Information");
        System.out.println("8. Exit");
        System.out.print("\nEnter your choice: ");
    }

    private void displayAllCustomers() {
        ArrayList<Customer> customers
                = bankManager.getAllCustomers();

        System.out.println("\n===== All Customer Accounts =====");

        if (customers.isEmpty()) {
            System.out.println("No customers are stored.");
            return;
        }

        for (Customer customer : customers) {
            displayCustomerAccounts(customer);
        }
    }

    private void displayOneCustomer() {
        int customerId = readInt("Enter the customer ID: ");
        Customer customer
                = bankManager.findCustomerById(customerId);

        if (customer == null) {
            System.out.println(
                    "No customer was found with ID "
                    + customerId + ".");
            return;
        }

        System.out.println("\n===== Customer Accounts =====");
        displayCustomerAccounts(customer);
    }

    private void addAccount() {
        int customerId = readInt("Enter the customer ID: ");

        if (bankManager.findCustomerById(customerId) == null) {
            System.out.println(
                    "No customer was found with ID "
                    + customerId + ".");
            return;
        }

        System.out.print("Enter the new account number: ");
        String accountNumber = input.nextLine();

        if (bankManager.findAccount(accountNumber) != null) {
            System.out.println(
                    "That account number is already in use.");
            return;
        }

        double balance = readDouble("Enter the starting balance: ");

        if (balance < 0) {
            System.out.println(
                    "The starting balance cannot be negative.");
            return;
        }

        System.out.println("\nSelect the account type:");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        System.out.println("3. IRA");
        System.out.print("Enter your choice: ");
        String accountType = input.nextLine();

        // Polymorphism: each derived object uses an Account reference.
        Account account;

        switch (accountType) {
            case "1":
                double overdraftLimit
                        = readDouble("Enter the overdraft limit: ");

                account = new CheckingAccount(
                        accountNumber,
                        balance,
                        overdraftLimit
                );
                break;

            case "2":
                double interestRate
                        = readDouble("Enter the interest rate: ");

                account = new SavingsAccount(
                        accountNumber,
                        balance,
                        interestRate
                );
                break;

            case "3":
                double contributionLimit
                        = readDouble(
                                "Enter the annual contribution limit: ");

                account = new IRAAccount(
                        accountNumber,
                        balance,
                        contributionLimit
                );
                break;

            default:
                System.out.println("Invalid account type.");
                return;
        }

        if (bankManager.addAccount(customerId, account)) {
            System.out.println("\nAccount added successfully.");
            System.out.println(account);
        } else {
            System.out.println("The account could not be added.");
        }
    }

    private void removeAccount() {
        System.out.print("Enter the account number to remove: ");
        String accountNumber = input.nextLine();

        Account account = bankManager.findAccount(accountNumber);

        if (account == null) {
            System.out.println("No matching account was found.");
            return;
        }

        System.out.println("\nAccount selected:");
        System.out.println(account);
        System.out.print("Remove this account? Enter Y to confirm: ");
        String confirmation = input.nextLine();

        if (!confirmation.equalsIgnoreCase("Y")) {
            System.out.println("Account removal canceled.");
            return;
        }

        if (bankManager.removeAccount(accountNumber)) {
            System.out.println("Account removed successfully.");
        } else {
            System.out.println("The account could not be removed.");
        }
    }

    private void makeDeposit() {
        System.out.print("Enter the account number: ");
        String accountNumber = input.nextLine();

        if (bankManager.findAccount(accountNumber) == null) {
            System.out.println("No matching account was found.");
            return;
        }

        double amount = readDouble("Enter the deposit amount: ");

        if (bankManager.deposit(accountNumber, amount)) {
            System.out.println("\nDeposit completed successfully.");
            System.out.println(bankManager.findAccount(accountNumber));
        } else {
            System.out.println(
                    "The deposit amount must be greater than zero.");
        }
    }

    private void makeWithdrawal() {
        System.out.print("Enter the account number: ");
        String accountNumber = input.nextLine();

        if (bankManager.findAccount(accountNumber) == null) {
            System.out.println("No matching account was found.");
            return;
        }

        double amount = readDouble("Enter the withdrawal amount: ");

        if (bankManager.withdraw(accountNumber, amount)) {
            System.out.println("\nWithdrawal completed successfully.");
            System.out.println(bankManager.findAccount(accountNumber));
        } else {
            System.out.println(
                    "The withdrawal could not be completed. "
                    + "Check the amount and available balance.");
        }
    }

    private void updateCustomer() {
        int customerId = readInt("Enter the customer ID: ");
        Customer customer
                = bankManager.findCustomerById(customerId);

        if (customer == null) {
            System.out.println(
                    "No customer was found with ID "
                    + customerId + ".");
            return;
        }

        System.out.print("Enter the new first name: ");
        customer.setFirstName(input.nextLine());

        System.out.print("Enter the new last name: ");
        customer.setLastName(input.nextLine());

        System.out.print("Enter the new email address: ");
        customer.setEmail(input.nextLine());

        System.out.print("Enter the new phone number: ");
        customer.setPhoneNumber(input.nextLine());

        if (bankManager.updateCustomer(customer)) {
            System.out.println("\nCustomer updated successfully.");
            System.out.println(customer);
        } else {
            System.out.println("The customer could not be updated.");
        }
    }

    private void displayCustomerAccounts(Customer customer) {
        System.out.println();
        System.out.println(customer);
        System.out.println("Accounts:");

        if (customer.getAccounts().isEmpty()) {
            System.out.println("No accounts are associated with this customer.");
        }

        // Polymorphism calls the correct overridden toString() method.
        for (Account account : customer.getAccounts()) {
            System.out.println();
            System.out.println(account);
        }

        System.out.println(
                "--------------------------------------------------");
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException exception) {
                System.out.println(
                        "Invalid entry. Please enter a whole number.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                return Double.parseDouble(input.nextLine());
            } catch (NumberFormatException exception) {
                System.out.println(
                        "Invalid entry. Please enter a number.");
            }
        }
    }
}