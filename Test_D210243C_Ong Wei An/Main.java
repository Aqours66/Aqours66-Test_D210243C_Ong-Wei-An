import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<String> accountNumbers = new ArrayList<>();
    private static List<BankAccount> bankAccounts = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Banking System!");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Create a Bank Account");
            System.out.println("2. Create a Savings Account");
            System.out.println("3. Create a Current Account");
            System.out.println("4. Exit");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (option) {
                case 1:
                    createBankAccount(scanner);
                    break;
                case 2:
                    createSavingsAccount(scanner);
                    break;
                case 3:
                    createCurrentAccount(scanner);
                    break;
                case 4:
                    System.out.println("Thank you for using the Banking System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void createBankAccount(Scanner scanner) {
        System.out.println("\nCreating a Bank Account...");

        try {
            System.out.print("Enter account number: ");
            String bankAccountNumber = scanner.nextLine();

            if (accountNumbers.contains(bankAccountNumber)) {
                throw new Exception("An account with the same account number already exists.");
            }

            System.out.print("Enter account holder name: ");
            String bankAccountHolderName = scanner.nextLine();

            BankAccount bankAccount = new BankAccount(bankAccountNumber, bankAccountHolderName);
            accountNumbers.add(bankAccountNumber);
            bankAccounts.add(bankAccount);

            System.out.println("Bank Account created successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void createSavingsAccount(Scanner scanner) {
        System.out.println("\nCreating a Savings Account...");

        try {
            System.out.print("Enter account number: ");
            String savingsAccountNumber = scanner.nextLine();

            if (accountNumbers.contains(savingsAccountNumber)) {
                throw new Exception("An account with the same account number already exists.");
            }

            System.out.print("Enter account holder name: ");
            String savingsAccountHolderName = scanner.nextLine();

            System.out.print("Enter interest rate: ");
            double savingsAccountInterestRate = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character

            SavingsAccount savingsAccount = new SavingsAccount(
                    savingsAccountNumber,
                    savingsAccountHolderName,
                    savingsAccountInterestRate);

            accountNumbers.add(savingsAccountNumber);
            bankAccounts.add(savingsAccount);

            System.out.println("Savings Account created successfully.");

            performAccountOperations(scanner, savingsAccount);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void createCurrentAccount(Scanner scanner) {
        System.out.println("\nCreating a Current Account...");

        try {
            BankAccount bankAccount = selectBankAccount(scanner); // Select an existing BankAccount

            if (bankAccount == null) {
                throw new Exception("Failed to create a Current Account.");
            }

            System.out.print("Enter overdraft limit: ");
            double currentAccountOverdraftLimit = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character

            CurrentAccount currentAccount = new CurrentAccount(
                    bankAccount.getAccountNumber(),
                    bankAccount.getAccountHolderName(),
                    currentAccountOverdraftLimit);

            System.out.println("Current Account created successfully.");

            performAccountOperations(scanner, currentAccount);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static BankAccount selectBankAccount(Scanner scanner) {
        System.out.print("Enter the account number of the existing Bank Account: ");
        String accountNumber = scanner.nextLine();

        for (BankAccount account : bankAccounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                // Bank Account found, return it
                return account;
            }
        }

        // Bank Account not found, return null
        System.out.println("Bank Account not found.");
        return null;
    }

    private static void performAccountOperations(Scanner scanner, BankAccount account) {
        boolean exitLoop = false;

        while (!exitLoop) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Display Account Information");
            System.out.println("4. Go Back");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (option) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Deposit successful.");
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    account.withdraw(withdrawalAmount);
                    break;
                case 3:
                    System.out.println("Account Information:");
                    account.displayAccountInfo();
                    break;
                case 4:
                    exitLoop = true; // Set the flag to exit the loop
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
