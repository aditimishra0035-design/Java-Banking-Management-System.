
import java.util.ArrayList;
import java.util.Scanner;

// Interface
interface BankOperations {
    void deposit(double amount);
    void withdraw(double amount);
    void checkBalance();
}

// Parent class
class BankAccount implements BankOperations {
    private String accountHolder;
    private double balance;
    private ArrayList<String> transactions;

    public BankAccount(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
        transactions = new ArrayList<>();
        transactions.add("Account opened with balance: Rs. " + balance);
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getAccountType() {
        return "Regular Account";
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add("Deposited: Rs. " + amount);
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Enter a valid amount.");
        }
    }

    // Method overloading
    public void deposit(double amount, String note) {
        if (amount > 0) {
            balance += amount;
            transactions.add("Deposited Rs. " + amount + " - " + note);
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Enter a valid amount.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Enter a valid amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance -= amount;
            transactions.add("Withdrawn: Rs. " + amount);
            System.out.println("Withdrawal successful!");
        }
    }

    @Override
    public void checkBalance() {
        System.out.println("Current balance: Rs. " + balance);
    }

    public void showTransactions() {
        System.out.println("\n--- Transaction History ---");
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }
}

// Inheritance and method overriding
class SavingsAccount extends BankAccount {
    public SavingsAccount(String name, double balance) {
        super(name, balance);
    }

    @Override
    public String getAccountType() {
        return "Savings Account";
    }
}

// Main class
public class BankingManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        BankOperations account = new SavingsAccount(name, 1000);
        BankAccount bankAccount = (BankAccount) account;

        System.out.println("\nWelcome, " + bankAccount.getAccountHolder());
        System.out.println("Account type: " + bankAccount.getAccountType());

        while (true) {
            System.out.println("\n===== BANKING SYSTEM =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter amount: ");
                        double depositAmount =
                            Double.parseDouble(sc.nextLine());
                        account.deposit(depositAmount);
                        break;

                    case 2:
                        System.out.print("Enter amount: ");
                        double withdrawAmount =
                            Double.parseDouble(sc.nextLine());
                        account.withdraw(withdrawAmount);
                        break;

                    case 3:
                        account.checkBalance();
                        break;

                    case 4:
                        bankAccount.showTransactions();
                        break;

                    case 5:
                        System.out.println("Thank you for using our bank!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
