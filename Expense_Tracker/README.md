# Expense Tracker CLI

A simple command-line interface (CLI) application for tracking personal expenses, built with Java.

## Features

- Add expenses with description, amount, category, and date
- View all expenses
- Filter expenses by category
- View financial summary (total spent and number of transactions)
- Delete expenses by ID
- Pre-loaded with sample data for testing

## Prerequisites

- Java Development Kit (JDK) 8 or higher installed on your system
- Command-line interface (Terminal, Command Prompt, or PowerShell)

## How to Compile and Run

1. **Compile the program:**

   ```
   javac ExpenseTrackerCLI.java
   ```

2. **Run the program:**
   ```
   java ExpenseTrackerCLI
   ```

## Usage

The application presents a menu with the following options:

1. **Add an Expense**: Enter description, amount (in ₹), category, and date (YYYY-MM-DD or press Enter for today)
2. **View All Expenses**: Displays all recorded expenses
3. **Filter by Category**: View expenses for a specific category (FOOD, TRANSPORT, ENTERTAINMENT, UTILITIES, SHOPPING, OTHER)
4. **View Total Summary**: Shows total amount spent and number of transactions
5. **Delete an Expense**: Remove an expense by entering its ID
6. **Exit**: Close the application

## Categories

Available expense categories:

- FOOD
- TRANSPORT
- ENTERTAINMENT
- UTILITIES
- SHOPPING
- OTHER

## Example Usage

```
=========================================
💳 SMART EXPENSE TRACKER 💳
=========================================

1. Add an Expense
2. View All Expenses
3. Filter by Category
4. View Total Summary
5. Delete an Expense
6. Exit
Choose an option (1-6): 1

Enter description: Lunch at restaurant
Enter amount: ₹250.50
Categories: FOOD, TRANSPORT, ENTERTAINMENT, UTILITIES, SHOPPING, OTHER
Enter category: FOOD
Enter date (YYYY-MM-DD) or press Enter for today:

✅ Expense added successfully!
```

## Error Handling

The application handles invalid inputs gracefully:

- Invalid numbers for amounts or IDs
- Invalid category names
- Invalid date formats

## Technologies Used

- Java 8+ (Streams, Lambdas, LocalDate)
- Command-line interface

## License

This project is open-source and available under the MIT License.
