# ATM Simulator

A Java-based ATM (Automated Teller Machine) simulator that demonstrates Object-Oriented Programming (OOP) principles. This console application allows users to perform basic banking operations like checking balance, withdrawing cash, depositing money, viewing mini statements, and changing PINs.

## Features

- **Secure Authentication**: PIN-based login system
- **Account Management**: Multiple pre-loaded accounts for simulation
- **Banking Operations**:
  - Check account balance
  - Cash withdrawal with balance validation
  - Cash deposit
  - Mini statement (last 5 transactions)
  - PIN change functionality
- **Transaction History**: Automatic recording of all transactions
- **Error Handling**: Robust input validation and exception handling
- **User-Friendly Interface**: Clear menu-driven console interface

## Technologies Used

- **Java**: Core programming language
- **OOP Concepts**: Encapsulation, Classes, Objects
- **Data Structures**: HashMap for account storage, ArrayList for transaction history
- **Input Handling**: Scanner class for user input

## How to Run

1. Ensure you have Java Development Kit (JDK) installed on your system
2. Compile the Java file:
   ```
   javac ATM.java
   ```
3. Run the application:
   ```
   java ATM
   ```

## Sample Accounts

The simulator comes pre-loaded with the following test accounts:

| Account Number | PIN  | Initial Balance |
| -------------- | ---- | --------------- |
| 1001           | 1234 | ₹5000.00        |
| 1002           | 9999 | ₹12500.50       |
| 1003           | 1111 | ₹300.00         |

## Usage

1. Run the application
2. Enter your account number (or 0 to exit)
3. Enter your 4-digit PIN
4. Choose from the available menu options:
   - 1: Check Balance
   - 2: Cash Withdrawal
   - 3: Cash Deposit
   - 4: Mini Statement
   - 5: Change PIN
   - 6: Logout

## Project Structure

- `ATM.java`: Main source file containing the Account class and AtmSimulator main class

## Learning Outcomes

This project demonstrates:

- Object-Oriented Programming principles
- Encapsulation and data hiding
- Exception handling
- Collection framework usage
- Console-based user interface design
