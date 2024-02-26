import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FinanceManager {

    private List<Record> records;
    private Scanner scanner;

    public FinanceManager() {
        this.records = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void addRecord(double amount, String type, String category) {
        records.add(new Record(amount, type, category));
    }

    private void addIncome() {
        System.out.print("Enter income amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); 
        addRecord(amount, "Income", "Salary"); // Assuming all income falls under 'Salary' category
        System.out.println("Income added.");
    }

    private void addExpense() {
        System.out.println("Select a category for the expense:");
        System.out.println("1) Food");
        System.out.println("2) Housing");
        System.out.println("3) Entertainment");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); 

        String category = "";
        switch (choice) {
            case 1:
                category = "Food";
                break;
            case 2:
                category = "Housing";
                break;
            case 3:
                category = "Entertainment";
                break;
            default:
                System.out.println("Invalid choice. Expense not added.");
                return;
        }

        System.out.print("Enter expense amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); 

        addRecord(amount, "Expense", category);
        System.out.println("Expense added to " + category + " category.");
    }


    public double getTotalIncome() {
        return records.stream().filter(r -> r.type.equals("Income")).mapToDouble(r -> r.amount).sum();
    }

    public double getTotalExpenses() {
        return records.stream().filter(r -> r.type.equals("Expense")).mapToDouble(r -> r.amount).sum();
    }

    public double getCategoryTotal(String category) {
        return records.stream().filter(r -> r.category.equals(category)).mapToDouble(r -> r.amount).sum();
    }

    public void resetData() {
        records.clear();
    }

    private static class Record {
        double amount;
        String type; // "Income" or "Expense"
        String category; // "Food", "Housing", "Entertainment"

        public Record(double amount, String type, String category) {
            this.amount = amount;
            this.type = type;
            this.category = category;
        }
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nChoose a function:");
            System.out.println("1) Total Income and Expenses");
            System.out.println("2) Income and Expenses in 'Food' category");
            System.out.println("3) Income and Expenses in 'Housing' category");
            System.out.println("4) Income and Expenses in 'Entertainment' category");
            System.out.println("5) Add Income");
            System.out.println("6) Add Expense");
            System.out.println("7) Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Total Income: " + getTotalIncome());
                    System.out.println("Total Expenses: " + getTotalExpenses());
                    System.out.println("Total amount of money: " + (getTotalIncome() - getTotalExpenses()));
                    break;
                case 2:
                    System.out.println("Total in 'Food' category: " + getCategoryTotal("Food"));
                    break;
                case 3:
                    System.out.println("Total in 'Housing' category: " + getCategoryTotal("Housing"));
                    break;
                case 4:
                    System.out.println("Total in 'Entertainment' category: " + getCategoryTotal("Entertainment"));
                    break;
                case 5:
                    addIncome();
                    break;
                case 6:
                    addExpense();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

   public static void main(String[] args) {
        FinanceManager manager = new FinanceManager();

        // Fill data
        manager.addRecord(5000, "Income", "Salary");
        manager.addRecord(1200, "Expense", "Housing");
        manager.addRecord(300, "Expense", "Food");
        manager.addRecord(200, "Expense", "Entertainment");

        manager.showMenu();
    }

}