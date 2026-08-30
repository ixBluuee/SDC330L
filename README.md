# Bank Account Management System

## Project Description

The Bank Account Management System is a Java console application. It allows users to manage individual customers and their checking, savings, and IRA accounts. Customer and account information is stored in an SQLite database.

## Features

The application allows users to:

- Display all accounts for all customers
- Display accounts for an individual customer
- Add checking, savings, and IRA accounts
- Remove an account
- Make deposits
- Make withdrawals
- Update customer information
- Store and retrieve persistent SQLite records
- Continue using the menu until Exit is selected

## Object-Oriented Programming Concepts

The project demonstrates the following concepts:

- **Abstraction:** `Account` is an abstract base class.
- **Inheritance:** `CheckingAccount`, `SavingsAccount`, and `IRAAccount` extend `Account`.
- **Interface:** `BankOperations` defines the required banking operations.
- **Polymorphism:** Derived account objects are stored and processed using `Account` references. `BankManager` is accessed through a `BankOperations` reference.
- **Composition:** Each `Customer` contains a collection of `Account` objects.
- **Constructors:** Constructors and constructor overloading initialize realistic customer and account objects.
- **Encapsulation:** Private properties and appropriate access specifiers protect application data.

## Database

The application uses SQLite and the SQLite JDBC driver. It creates the following tables automatically:

- `Customers` – Stores customer names and contact information
- `Accounts` – Stores account information and connects each account to a customer

The application demonstrates all CRUD operations:

- **Create:** Add customers and accounts
- **Read:** Display all accounts or accounts for one customer
- **Update:** Make deposits and withdrawals or update customer information
- **Delete:** Remove an account

## Main Classes

- `App` – Starts the application and database connection
- `BankController` – Controls the terminal menu and user input
- `BankOperations` – Defines required banking operations
- `BankManager` – Performs SQLite customer and account operations
- `SQLiteDatabase` – Connects to SQLite and creates the tables
- `Customer` – Represents an individual customer
- `Account` – Abstract base class for all account types
- `CheckingAccount` – Represents a checking account
- `SavingsAccount` – Represents a savings account
- `IRAAccount` – Represents an IRA account

## Technologies Used

- Java
- SQLite
- SQLite JDBC
- Visual Studio Code
- GitHub

## Project Structure

```text
Bank-Account-Management-System
├── .vscode
│   └── settings.json
├── lib
│   └── sqlite-jdbc-3.36.0.3.jar
├── src
│   ├── Account.java
│   ├── App.java
│   ├── BankController.java
│   ├── BankManager.java
│   ├── BankOperations.java
│   ├── CheckingAccount.java
│   ├── Customer.java
│   ├── IRAAccount.java
│   ├── SavingsAccount.java
│   └── SQLiteDatabase.java
├── bank_accounts.db
└── README.md
```

## Project Summary

This project produced a functional Bank Account Management System using Java and SQLite. The application allows users to manage customers and checking, savings, and IRA accounts through a console menu. Users can add and remove accounts, display account information, make deposits and withdrawals, and update customer information. The project demonstrates inheritance, composition, abstraction, interfaces, polymorphism, constructors, access specifiers, SQLite CRUD operations, input validation, and persistent data storage.
