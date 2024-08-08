public class FixedDepositAccount extends BankAccount {
    private double interestRate;
    private int duration; // in months

    public FixedDepositAccount(String accountNumber, double balance, double interestRate, int duration) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
        this.duration = duration;
    }

    @Override
    public void calculateInterest() {
        // Interest is typically calculated differently for fixed deposits
        double interest = balance * interestRate / 100 * (duration / 12.0);
        deposit(interest);
        System.out.println("Interest of " + interest + " added to Fixed Deposit Account.");
    }

    @Override
    public void displayAccountDetails() {
        super.displayAccountDetails();
        System.out.println("Account Type: Fixed Deposit Account");
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Duration: " + duration + " months");
    }
}
