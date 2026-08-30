
/** ******************************************************************
 * Name: Elvis Melendez
 * Date: 8/29/2026
 * Assignment: SDC330L Course Project - Week 4
 *
 * Purpose:
 * This class connects to the SQLite banking database and creates the
 * customer and account tables when they do not already exist.
 ******************************************************************* */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDatabase {

    private final String databaseUrl;

    public SQLiteDatabase(String databaseName) {
        databaseUrl = "jdbc:sqlite:" + databaseName;
    }

    public Connection connect() throws SQLException {
        Connection connection
                = DriverManager.getConnection(databaseUrl);

        // Enables enforcement of relationships between database tables.
        try (Statement statement = connection.createStatement()) {
            statement.execute("PRAGMA foreign_keys = ON");
        }

        return connection;
    }

    public void createTables(Connection connection) throws SQLException {
        String customersSql
                = "CREATE TABLE IF NOT EXISTS Customers ("
                + "customerId INTEGER PRIMARY KEY, "
                + "firstName TEXT NOT NULL, "
                + "lastName TEXT NOT NULL, "
                + "email TEXT, "
                + "phoneNumber TEXT"
                + ")";

        String accountsSql
                = "CREATE TABLE IF NOT EXISTS Accounts ("
                + "accountNumber TEXT PRIMARY KEY, "
                + "customerId INTEGER NOT NULL, "
                + "accountType TEXT NOT NULL, "
                + "balance REAL NOT NULL, "
                + "overdraftLimit REAL, "
                + "interestRate REAL, "
                + "annualContributionLimit REAL, "
                + "FOREIGN KEY (customerId) "
                + "REFERENCES Customers(customerId) "
                + "ON DELETE CASCADE"
                + ")";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(customersSql);
            statement.executeUpdate(accountsSql);
        }
    }
}