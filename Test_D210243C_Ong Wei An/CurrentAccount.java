public class CurrentAccount extends BankAccount {
    private double overdraftLimit;

    public CurrentAccount(String accountNumber, String accountHolderName, double overdraftLimit) {
        super(accountNumber, accountHolderName);
        this.overdraftLimit = overdraftLimit;
    }

    public void withdraw(double amount) {
        if (accountBalance >= amount) {
            accountBalance -= amount;
        } else if (amount <= (accountBalance + overdraftLimit)) {
            double remainingAmount = amount - accountBalance;
            accountBalance = 0;
            overdraftLimit -= remainingAmount + (remainingAmount * 0.03);
        } else {
            System.out.println("Exceeded overdraft limit.");
        }
    }

    public void displayAccountInfo() {
        super.displayAccountInfo();
        System.out.println("Overdraft Limit: " + overdraftLimit);
    }
}