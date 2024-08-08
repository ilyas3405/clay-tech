public class BankSystem {
    public static void main(String[] args) {
        BankAccount savings = new SavingsAccount("SA123", 1000.0, 3.5);
        BankAccount current = new CurrentAccount("CA456", 500.0, 1000.0);
        BankAccount fixedDeposit = new FixedDepositAccount("FD789", 5000.0, 6.0, 12);

        savings.deposit(200);
        savings.withdraw(100);
        savings.calculateInterest();
        savings.displayAccountDetails();

        System.out.println();

        current.deposit(300);
        current.withdraw(1000);
        current.calculateInterest();
        current.displayAccountDetails();

        System.out.println();

        fixedDeposit.calculateInterest();
        fixedDeposit.displayAccountDetails();
    }
}
