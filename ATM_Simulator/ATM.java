import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// ==========================================
// ACCOUNT CLASS (Encapsulation & OOP)
// ==========================================
class Account {
    private int accountNumber;
    private int pin;
    private double balance;
    private List<String> transactionHistory;

    public Account(int accountNumber, int pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        recordTransaction("Initial Deposit: ₹" + initialBalance);
    }

    public boolean validatePin(int enteredPin) {
        return this.pin == enteredPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            recordTransaction("Deposited: +₹" + amount + " | Balance: ₹" + balance);
            System.out.println("\n✅ Successfully deposited ₹" + amount);
        } else {
            System.out.println("\n❌ Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            recordTransaction("Withdrew: -₹" + amount + " | Balance: ₹" + balance);
            System.out.println("\n✅ Successfully withdrew ₹" + amount);
            System.out.println("💵 Please collect your cash.");
        } else if (amount > balance) {
            System.out.println("\n❌ Insufficient funds. Current balance: ₹" + balance);
        } else {
            System.out.println("\n❌ Invalid withdrawal amount.");
        }
    }

    public void changePin(int oldPin, int newPin) {
        if (validatePin(oldPin)) {
            this.pin = newPin;
            System.out.println("\n✅ PIN successfully changed.");
        } else {
            System.out.println("\n❌ Old PIN is incorrect.");
        }
    }

    public void printMiniStatement() {
        System.out.println("\n--- 📜 Mini Statement ---");
        if (transactionHistory.isEmpty()) {
            System.out.println("No recent transactions.");
        } else {
            // Print the last 5 transactions
            int start = Math.max(0, transactionHistory.size() - 5);
            for (int i = start; i < transactionHistory.size(); i++) {
                System.out.println(transactionHistory.get(i));
            }
        }
        System.out.println("Available Balance: ₹" + balance);
        System.out.println("-------------------------");
    }

    private void recordTransaction(String details) {
        transactionHistory.add(details);
    }
}

// ==========================================
// MAIN ATM SYSTEM
// ==========================================
public class ATM {
    // Simulating a database of accounts using a HashMap
    private static Map<Integer, Account> bankDatabase = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Pre-loading some dummy accounts for the simulator
        bankDatabase.put(1001, new Account(1001, 1234, 5000.0));
        bankDatabase.put(1002, new Account(1002, 9999, 12500.50));
        bankDatabase.put(1003, new Account(1003, 1111, 300.0));

        System.out.println("=========================================");
        System.out.println("🏦 WELCOME TO THE SECURE JAVA ATM 🏦");
        System.out.println("=========================================");

        while (true) {
            try {
                System.out.print("\nPlease enter your Account Number (or 0 to exit): ");
                int accNum = Integer.parseInt(scanner.nextLine());

                if (accNum == 0) {
                    System.out.println("Thank you for using the Secure Java ATM. Goodbye!");
                    break;
                }

                if (bankDatabase.containsKey(accNum)) {
                    Account currentAccount = bankDatabase.get(accNum);
                    
                    System.out.print("Please enter your 4-digit PIN: ");
                    int enteredPin = Integer.parseInt(scanner.nextLine());

                    if (currentAccount.validatePin(enteredPin)) {
                        showAtmMenu(currentAccount);
                    } else {
                        System.out.println("\n❌ Incorrect PIN. Access Denied.");
                    }
                } else {
                    System.out.println("\n❌ Account not found. Please try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("\n❌ Invalid input. Please enter numerical values only.");
            }
        }
        scanner.close();
    }

    private static void showAtmMenu(Account account) {
        boolean sessionActive = true;

        while (sessionActive) {
            System.out.println("\n================ MAIN MENU ================");
            System.out.println("1. Check Balance");
            System.out.println("2. Cash Withdrawal");
            System.out.println("3. Cash Deposit");
            System.out.println("4. Mini Statement");
            System.out.println("5. Change PIN");
            System.out.println("6. Logout / Return Card");
            System.out.println("===========================================");
            System.out.print("Enter your choice (1-6): ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("\n💰 Available Balance: ₹" + account.getBalance());
                        break;
                    case 2:
                        System.out.print("\nEnter amount to withdraw: ₹");
                        double withdrawAmount = Double.parseDouble(scanner.nextLine());
                        account.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("\nEnter amount to deposit: ₹");
                        double depositAmount = Double.parseDouble(scanner.nextLine());
                        account.deposit(depositAmount);
                        break;
                    case 4:
                        account.printMiniStatement();
                        break;
                    case 5:
                        System.out.print("\nEnter your CURRENT PIN: ");
                        int oldPin = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter your NEW 4-digit PIN: ");
                        int newPin = Integer.parseInt(scanner.nextLine());
                        account.changePin(oldPin, newPin);
                        break;
                    case 6:
                        System.out.println("\n🔒 Logging out... Please take your card.");
                        sessionActive = false;
                        break;
                    default:
                        System.out.println("\n❌ Invalid choice. Please select from 1-6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n❌ Invalid input. Please enter numerical values only.");
            }
        }
    }
}