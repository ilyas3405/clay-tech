public class CurrentAccount extends BankAccount {
    private double overdraftLimit;

    public CurrentAccount(String accountNumber, double balance, double overdraftLimit) {
        super(accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && balance + overdraftLimit >= amount) {
            balance -= amount;
            System.out.println("Withdrew " + amount + ". New balance: " + balance);
        } else {
            System.out.println("Exceeds overdraft limit or invalid amount.");
        }
    }

    @Override
    public void calculateInterest() {
        // Current accounts typically do not earn interest
        System.out.println("No interest for Current Account.");
    }

    @Override
    public void displayAccountDetails() {
        super.displayAccountDetails();
        System.out.println("Account Type: Current Account");
        System.out.println("Overdraft Limit: " + overdraftLimit);
    }
}
