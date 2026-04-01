import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// ==========================================
// ENUMS & DATA MODELS
// ==========================================
enum Category {
    FOOD, TRANSPORT, ENTERTAINMENT, UTILITIES, SHOPPING, OTHER
}

class Expense {
    private static int idCounter = 1; // Auto-incrementing ID
    private int id;
    private String description;
    private double amount;
    private Category category;
    private LocalDate date;

    public Expense(String description, double amount, Category category, LocalDate date) {
        this.id = idCounter++;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public int getId() { return id; }
    public double getAmount() { return amount; }
    public Category getCategory() { return category; }
    public LocalDate getDate() { return date; }

    @Override
    public String toString() {
        return String.format("ID: %-3d | Date: %-10s | Category: %-13s | Amount: ₹%-8.2f | Desc: %s", 
                              id, date, category, amount, description);
    }
}

// ==========================================
// CORE BUSINESS LOGIC (Using Java Streams)
// ==========================================
class ExpenseManager {
    private List<Expense> expenses = new ArrayList<>();

    public void addExpense(Expense expense) {
        expenses.add(expense);
        System.out.println("\n✅ Expense added successfully!");
    }

    public void viewAllExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("\n📭 No expenses recorded yet.");
            return;
        }
        System.out.println("\n--- 📜 All Expenses ---");
        // Using Java 8 forEach and method reference
        expenses.forEach(System.out::println);
    }

    public void viewExpensesByCategory(Category category) {
        // Using Java Streams and Lambdas to filter data
        List<Expense> filtered = expenses.stream()
                .filter(e -> e.getCategory() == category)
                .collect(Collectors.toList());

        System.out.println("\n--- 📊 Expenses for " + category + " ---");
        if (filtered.isEmpty()) {
            System.out.println("No expenses found in this category.");
        } else {
            filtered.forEach(System.out::println);
            double categoryTotal = filtered.stream().mapToDouble(Expense::getAmount).sum();
            System.out.println("-------------------------------------------------");
            System.out.printf("Total for %s: ₹%.2f\n", category, categoryTotal);
        }
    }

    public void viewSummary() {
        if (expenses.isEmpty()) {
            System.out.println("\n📭 No data to summarize.");
            return;
        }
        // Using Streams to calculate sum
        double total = expenses.stream().mapToDouble(Expense::getAmount).sum();
        System.out.println("\n--- 💰 Financial Summary ---");
        System.out.printf("Total Spent: ₹%.2f\n", total);
        System.out.println("Total Transactions: " + expenses.size());
    }

    public void deleteExpense(int id) {
        // Using removeIf with a lambda expression
        boolean removed = expenses.removeIf(e -> e.getId() == id);
        if (removed) {
            System.out.println("\n✅ Expense deleted successfully.");
        } else {
            System.out.println("\n❌ Expense ID not found.");
        }
    }
}

// ==========================================
// MAIN CLI APPLICATION
// ==========================================
public class ExpenseTrackerCLI {
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();

        // Pre-load dummy data for testing
        manager.addExpense(new Expense("Groceries", 1500.50, Category.FOOD, LocalDate.now().minusDays(2)));
        manager.addExpense(new Expense("Uber to work", 350.0, Category.TRANSPORT, LocalDate.now().minusDays(1)));
        manager.addExpense(new Expense("Movie tickets", 600.0, Category.ENTERTAINMENT, LocalDate.now()));

        System.out.println("\n=========================================");
        System.out.println("💳 SMART EXPENSE TRACKER 💳");
        System.out.println("=========================================");

        while (true) {
            System.out.println("\n1. Add an Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Filter by Category");
            System.out.println("4. View Total Summary");
            System.out.println("5. Delete an Expense");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addExpensePrompt(manager);
                        break;
                    case 2:
                        manager.viewAllExpenses();
                        break;
                    case 3:
                        filterByCategoryPrompt(manager);
                        break;
                    case 4:
                        manager.viewSummary();
                        break;
                    case 5:
                        System.out.print("\nEnter the Expense ID to delete: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        manager.deleteExpense(id);
                        break;
                    case 6:
                        System.out.println("\nClosing tracker. Have a great day!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("\n❌ Invalid choice. Please select 1-6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n❌ Invalid input. Please enter a number.");
            }
        }
    }

    private static void addExpensePrompt(ExpenseManager manager) {
        try {
            System.out.print("\nEnter description: ");
            String desc = scanner.nextLine();

            System.out.print("Enter amount: ₹");
            double amount = Double.parseDouble(scanner.nextLine());

            System.out.println("Categories: FOOD, TRANSPORT, ENTERTAINMENT, UTILITIES, SHOPPING, OTHER");
            System.out.print("Enter category: ");
            Category cat = Category.valueOf(scanner.nextLine().toUpperCase());

            System.out.print("Enter date (YYYY-MM-DD) or press Enter for today: ");
            String dateInput = scanner.nextLine();
            LocalDate date = dateInput.isEmpty() ? LocalDate.now() : LocalDate.parse(dateInput, dateFormatter);

            manager.addExpense(new Expense(desc, amount, cat, date));

        } catch (NumberFormatException e) {
            System.out.println("\n❌ Invalid amount. Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ Invalid category. Please use one of the listed categories.");
        } catch (DateTimeParseException e) {
            System.out.println("\n❌ Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    private static void filterByCategoryPrompt(ExpenseManager manager) {
        try {
            System.out.println("\nCategories: FOOD, TRANSPORT, ENTERTAINMENT, UTILITIES, SHOPPING, OTHER");
            System.out.print("Enter category to filter by: ");
            Category cat = Category.valueOf(scanner.nextLine().toUpperCase());
            manager.viewExpensesByCategory(cat);
        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ Invalid category.");
        }
    }
}
