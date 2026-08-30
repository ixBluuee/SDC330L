/********************************************************************
 * Name: Elvis Melendez
 * Date: 8/29/2026
 * Assignment: SDC330L Course Project - Week 1
 *
 * Purpose:
 * This class provides the basic user interface for the Bank Account
 * Management System and displays realistic customer and account
 * information.
 ********************************************************************/

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Customer customer = createSampleCustomer();
        boolean running = true;

        displayWelcomeMessage();

        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    displayCustomerAccounts(customer);
                    break;

                case "2":
                    System.out.println(
                            "\nThank you for using the Bank Account "
                            + "Management System.");
                    running = false;
                    break;

                default:
                    System.out.println(
                            "\nInvalid choice. Please enter 1 or 2.");
            }
        }

        input.close();
    }

    private static void displayWelcomeMessage() {
        System.out.println(
                "\n==================================================");
        System.out.println(" SDC330L Course Project - Week 1");
        System.out.println(" Bank Account Management System");
        System.out.println(" Created by: Elvis Melendez");
        System.out.println(
                "==================================================");

        System.out.println(
                "\nWelcome to the Bank Account Management System.");
        System.out.println(
                "Select an option from the menu to display sample");
        System.out.println("account information or exit the application.");
    }

    private static void displayMenu() {
        System.out.println("\n========== Main Menu ==========");
        System.out.println("1. Display Customer Accounts");
        System.out.println("2. Exit");
        System.out.println();
    }

    private static Customer createSampleCustomer() {
        Customer customer = new Customer(
                1001,
                "Maria",
                "Rodriguez",
                "maria.rodriguez@email.com",
                "757-555-0148"
        );

        customer.addAccount(new CheckingAccount(
                "CHK-10001",
                2450.75,
                500.00
        ));

        customer.addAccount(new SavingsAccount(
                "SAV-10001",
                8750.50,
                3.25
        ));

        customer.addAccount(new IRAAccount(
                "IRA-10001",
                18500.00,
                7000.00
        ));

        return customer;
    }

    private static void displayCustomerAccounts(Customer customer) {
        System.out.println("\n===== Customer Information =====");
        System.out.println(customer);

        System.out.println("\n===== Account Information =====");

        // Composition: the accounts are retrieved from the Customer object.
        for (Account account : customer.getAccounts()) {
            System.out.println(account);
            System.out.println();
        }
    }
}
