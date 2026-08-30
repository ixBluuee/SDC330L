/********************************************************************
 * Name: Elvis Melendez
 * Date: 8/29/2026
 * Assignment: SDC330L Course Project - Week 4
 *
 * Purpose:
 * This class starts the Bank Account Management System, connects to
 * the SQLite database, creates the tables, and starts the controller.
 ********************************************************************/

import java.sql.Connection;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {
        SQLiteDatabase database
                = new SQLiteDatabase("bank_accounts.db");

        try (Connection connection = database.connect()) {
            database.createTables(connection);

            // Interface polymorphism: BankManager is accessed through
            // a BankOperations reference.
            BankOperations bankManager
                    = new BankManager(connection);

            addInitialData(bankManager);

            BankController controller
                    = new BankController(bankManager);

            controller.start();

        } catch (SQLException exception) {
            System.out.println(
                    "Unable to connect to the database: "
                    + exception.getMessage());
        }
    }

    private static void addInitialData(
            BankOperations bankManager) {

        // Initial realistic records are only added when the
        // customer table is empty.
        if (!bankManager.getAllCustomers().isEmpty()) {
            return;
        }

        Customer firstCustomer = new Customer(
                1001,
                "Maria",
                "Rodriguez",
                "maria.rodriguez@email.com",
                "757-555-0148"
        );

        // Uses the overloaded Customer constructor.
        Customer secondCustomer = new Customer(
                1002,
                "James",
                "Wilson"
        );

        bankManager.addCustomer(firstCustomer);
        bankManager.addCustomer(secondCustomer);

        // Polymorphism: each derived object uses an Account reference.
        Account checkingAccount = new CheckingAccount(
                "CHK-10001",
                2450.75,
                500.00
        );

        Account savingsAccount = new SavingsAccount(
                "SAV-10001",
                8750.50,
                3.25
        );

        Account iraAccount = new IRAAccount(
                "IRA-10002",
                18500.00,
                7000.00
        );

        bankManager.addAccount(
                firstCustomer.getCustomerId(),
                checkingAccount
        );

        bankManager.addAccount(
                firstCustomer.getCustomerId(),
                savingsAccount
        );

        bankManager.addAccount(
                secondCustomer.getCustomerId(),
                iraAccount
        );
    }
}
