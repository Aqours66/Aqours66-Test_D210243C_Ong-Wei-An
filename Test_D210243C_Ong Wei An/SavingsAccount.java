public class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accountNumber, String accountHolderName, double interestRate) {
        super(accountNumber, accountHolderName);
        this.interestRate = interestRate;
    }

    public double calculateInterest() {
        return getAccountBalance() * interestRate;
    }

    public void addInterest() {
        double interest = calculateInterest();
        deposit(interest);
    }

    public void displayAccountInfo() {
        super.displayAccountInfo();
        System.out.println("Interest Rate: " + interestRate);
    }
}
