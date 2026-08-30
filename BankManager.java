/********************************************************************
 * Name: Elvis Melendez
 * Date: 8/29/2026
 * Assignment: Bank Account Management System - Final Project
 *
 * Purpose:
 * This class implements BankOperations and performs customer,
 * account, and transaction operations using the SQLite database.
 ********************************************************************/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

// Interface implementation: this class provides every operation
// required by the BankOperations interface.
public class BankManager implements BankOperations {

    // Private access protects the database connection.
    private final Connection connection;

    private static final String ACCOUNT_SELECT
            = "SELECT accountNumber, accountType, balance, "
            + "overdraftLimit, interestRate, annualContributionLimit "
            + "FROM Accounts ";

    public BankManager(Connection connection) {
        this.connection = connection;
    }

    // CREATE: adds a customer record to the database.
    @Override
    public boolean addCustomer(Customer customer) {
        String sql
                = "INSERT INTO Customers "
                + "(customerId, firstName, lastName, email, phoneNumber) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement
                = connection.prepareStatement(sql)) {

            statement.setInt(1, customer.getCustomerId());
            statement.setString(2, customer.getFirstName());
            statement.setString(3, customer.getLastName());
            statement.setString(4, customer.getEmail());
            statement.setString(5, customer.getPhoneNumber());

            return statement.executeUpdate() > 0;

        } catch (SQLException exception) {
            System.out.println(
                    "Unable to add customer: "
                    + exception.getMessage());
            return false;
        }
    }

    // READ: retrieves one customer and that customer's accounts.
    @Override
    public Customer findCustomerById(int customerId) {
        String sql
                = "SELECT customerId, firstName, lastName, "
                + "email, phoneNumber "
                + "FROM Customers WHERE customerId = ?";

        try (PreparedStatement statement
                = connection.prepareStatement(sql)) {

            statement.setInt(1, customerId);

            try (ResultSet results = statement.executeQuery()) {
                if (results.next()) {
                    Customer customer = createCustomer(results);
                    loadAccounts(customer);
                    return customer;
                }
            }

        } catch (SQLException exception) {
            System.out.println(
                    "Unable to find customer: "
                    + exception.getMessage());
        }

        return null;
    }

    // READ: retrieves every customer and account from the database.
    @Override
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<Customer>();

        String sql
                = "SELECT customerId, firstName, lastName, "
                + "email, phoneNumber "
                + "FROM Customers ORDER BY customerId";

        try (PreparedStatement statement
                = connection.prepareStatement(sql); ResultSet results = statement.executeQuery()) {

            while (results.next()) {
                customers.add(createCustomer(results));
            }

        } catch (SQLException exception) {
            System.out.println(
                    "Unable to read customers: "
                    + exception.getMessage());
            return customers;
        }

        for (Customer customer : customers) {
            loadAccounts(customer);
        }

        return customers;
    }

    // CREATE: adds an account for an existing customer.
    @Override
    public boolean addAccount(int customerId, Account account) {
        String sql
                = "INSERT INTO Accounts "
                + "(accountNumber, customerId, accountType, balance, "
                + "overdraftLimit, interestRate, "
                + "annualContributionLimit) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement
                = connection.prepareStatement(sql)) {

            statement.setString(1, account.getAccountNumber());
            statement.setInt(2, customerId);
            statement.setString(3, account.getAccountType());
            statement.setDouble(4, account.getBalance());

            statement.setNull(5, Types.REAL);
            statement.setNull(6, Types.REAL);
            statement.setNull(7, Types.REAL);

            if (account instanceof CheckingAccount) {
                CheckingAccount checking
                        = (CheckingAccount) account;

                statement.setDouble(
                        5, checking.getOverdraftLimit());

            } else if (account instanceof SavingsAccount) {
                SavingsAccount savings
                        = (SavingsAccount) account;

                statement.setDouble(
                        6, savings.getInterestRate());

            } else if (account instanceof IRAAccount) {
                IRAAccount ira = (IRAAccount) account;

                statement.setDouble(
                        7, ira.getAnnualContributionLimit());

            } else {
                return false;
            }

            return statement.executeUpdate() > 0;

        } catch (SQLException exception) {
            System.out.println(
                    "Unable to add account: "
                    + exception.getMessage());
            return false;
        }
    }

    // READ: retrieves one account by its account number.
    @Override
    public Account findAccount(String accountNumber) {
        String sql
                = ACCOUNT_SELECT + "WHERE accountNumber = ?";

        try (PreparedStatement statement
                = connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);

            try (ResultSet results = statement.executeQuery()) {
                if (results.next()) {
                    return createAccount(results);
                }
            }

        } catch (SQLException exception) {
            System.out.println(
                    "Unable to find account: "
                    + exception.getMessage());
        }

        return null;
    }

    // DELETE: removes an account from the database.
    @Override
    public boolean removeAccount(String accountNumber) {
        String sql
                = "DELETE FROM Accounts WHERE accountNumber = ?";

        try (PreparedStatement statement
                = connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);
            return statement.executeUpdate() > 0;

        } catch (SQLException exception) {
            System.out.println(
                    "Unable to remove account: "
                    + exception.getMessage());
            return false;
        }
    }

    // UPDATE: deposits money and saves the new balance.
    @Override
    public boolean deposit(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);

        if (account == null || amount <= 0) {
            return false;
        }

        account.deposit(amount);
        return updateBalance(account);
    }

    // UPDATE: withdraws money and saves the new balance.
    @Override
    public boolean withdraw(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);

        if (account == null || !account.withdraw(amount)) {
            return false;
        }

        return updateBalance(account);
    }

    // UPDATE: saves modified customer information.
    @Override
    public boolean updateCustomer(Customer customer) {
        String sql
                = "UPDATE Customers SET "
                + "firstName = ?, lastName = ?, email = ?, "
                + "phoneNumber = ? WHERE customerId = ?";

        try (PreparedStatement statement
                = connection.prepareStatement(sql)) {

            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhoneNumber());
            statement.setInt(5, customer.getCustomerId());

            return statement.executeUpdate() > 0;

        } catch (SQLException exception) {
            System.out.println(
                    "Unable to update customer: "
                    + exception.getMessage());
            return false;
        }
    }

    private boolean updateBalance(Account account) {
        String sql
                = "UPDATE Accounts SET balance = ? "
                + "WHERE accountNumber = ?";

        try (PreparedStatement statement
                = connection.prepareStatement(sql)) {

            statement.setDouble(1, account.getBalance());
            statement.setString(2, account.getAccountNumber());

            return statement.executeUpdate() > 0;

        } catch (SQLException exception) {
            System.out.println(
                    "Unable to update account balance: "
                    + exception.getMessage());
            return false;
        }
    }

    private Customer createCustomer(ResultSet results)
            throws SQLException {

        return new Customer(
                results.getInt("customerId"),
                results.getString("firstName"),
                results.getString("lastName"),
                results.getString("email"),
                results.getString("phoneNumber")
        );
    }

    private void loadAccounts(Customer customer) {
        String sql
                = ACCOUNT_SELECT
                + "WHERE customerId = ? ORDER BY accountNumber";

        try (PreparedStatement statement
                = connection.prepareStatement(sql)) {

            statement.setInt(1, customer.getCustomerId());

            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    customer.addAccount(createAccount(results));
                }
            }

        } catch (SQLException exception) {
            System.out.println(
                    "Unable to read customer accounts: "
                    + exception.getMessage());
        }
    }

    private Account createAccount(ResultSet results)
            throws SQLException {

        String accountNumber
                = results.getString("accountNumber");
        String accountType
                = results.getString("accountType");
        double balance = results.getDouble("balance");

        if ("Checking".equals(accountType)) {
            return new CheckingAccount(
                    accountNumber,
                    balance,
                    results.getDouble("overdraftLimit")
            );
        }

        if ("Savings".equals(accountType)) {
            return new SavingsAccount(
                    accountNumber,
                    balance,
                    results.getDouble("interestRate")
            );
        }

        if ("IRA".equals(accountType)) {
            return new IRAAccount(
                    accountNumber,
                    balance,
                    results.getDouble("annualContributionLimit")
            );
        }

        throw new SQLException(
                "Unknown account type: " + accountType);
    }
}
