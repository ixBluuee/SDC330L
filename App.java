
/** ******************************************************************
 * Name: Elvis Melendez
 * Date: 8/29/2026
 * Assignment: SDC330L Course Project - Week 2
 *
 * Purpose:
 * This class provides the Week 2 user interface and demonstrates
 * interface implementation and polymorphism in the Bank Account
 * Management System.
 ******************************************************************* */

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Interface polymorphism: a BankManager object is accessed
        // through a BankOperations reference.
        BankOperations bankManager = new BankManager();

        addSampleCustomers(bankManager);

        boolean running = true;
        displayWelcomeMessage();

        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    displayAllCustomers(bankManager);
                    break;

                case "2":
                    findCustomer(bankManager, input);
                    break;

                case "3":
                    System.out.println(
                            "\nThank you for using the Bank Account "
                            + "Management System.");
                    running = false;
                    break;

                default:
                    System.out.println(
                            "\nInvalid choice. Please enter 1, 2, or 3.");
            }
        }

        input.close();
    }

    private static void displayWelcomeMessage() {
        System.out.println(
                "\n==================================================");
        System.out.println(" SDC330L Course Project - Week 2");
        System.out.println(" Bank Account Management System");
        System.out.println(" Created by: Elvis Melendez");
        System.out.println(
                "==================================================");

        System.out.println(
                "\nWelcome to the Bank Account Management System.");
        System.out.println(
                "Select an option to display all customers, find an");
        System.out.println("individual customer, or exit the application.");
    }

    private static void displayMenu() {
        System.out.println("\n========== Main Menu ==========");
        System.out.println("1. Display All Customer Accounts");
        System.out.println("2. Find Customer by ID");
        System.out.println("3. Exit");
        System.out.println();
    }

    private static void addSampleCustomers(
            BankOperations bankManager) {

        Customer firstCustomer = new Customer(
                1001,
                "Maria",
                "Rodriguez",
                "maria.rodriguez@email.com",
                "757-555-0148"
        );

        firstCustomer.addAccount(new CheckingAccount(
                "CHK-10001",
                2450.75,
                500.00
        ));

        firstCustomer.addAccount(new SavingsAccount(
                "SAV-10001",
                8750.50,
                3.25
        ));

        Customer secondCustomer = new Customer(
                1002,
                "James",
                "Wilson",
                "james.wilson@email.com",
                "804-555-0183"
        );

        secondCustomer.addAccount(new IRAAccount(
                "IRA-10002",
                18500.00,
                7000.00
        ));

        bankManager.addCustomer(firstCustomer);
        bankManager.addCustomer(secondCustomer);
    }

    private static void displayAllCustomers(
            BankOperations bankManager) {

        System.out.println("\n===== All Customer Accounts =====");

        for (Customer customer : bankManager.getAllCustomers()) {
            displayCustomerAccounts(customer);
        }
    }

    private static void findCustomer(
            BankOperations bankManager, Scanner input) {

        System.out.print("Enter the customer ID: ");
        String enteredId = input.nextLine();

        try {
            int customerId = Integer.parseInt(enteredId);
            Customer customer
                    = bankManager.findCustomerById(customerId);

            if (customer == null) {
                System.out.println(
                        "\nNo customer was found with ID "
                        + customerId + ".");
            } else {
                System.out.println(
                        "\n===== Customer Found =====");
                displayCustomerAccounts(customer);
            }

        } catch (NumberFormatException exception) {
            System.out.println(
                    "\nInvalid customer ID. Please enter a number.");
        }
    }

    private static void displayCustomerAccounts(Customer customer) {
        System.out.println();
        System.out.println(customer);
        System.out.println("Accounts:");

        // Polymorphism: Java calls the overridden toString() method
        // belonging to each account's actual derived class.
        for (Account account : customer.getAccounts()) {
            System.out.println();
            System.out.println(account);
        }

        System.out.println(
                "--------------------------------------------------");
    }
}
