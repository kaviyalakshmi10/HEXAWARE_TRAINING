public class SavingsAccount extends Account {
    private final double interestRate = 4.5;

    public SavingsAccount(int accountNumber, double balance) {
        super(accountNumber, "Savings", balance);
    }

    public void calculateInterest() {
        double interest = balance * interestRate / 100;
        balance += interest;
        System.out.println("Interest added: " + interest);
    }
}
