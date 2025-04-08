package main;
public class CurrentAccount extends BankAccount {
    private static final double OVERDRAFT_LIMIT = 1000.0;

    public CurrentAccount(String accountNumber, String customerName, double balance) {
        super(accountNumber, customerName, balance);
    }

    
    public void deposit(float amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount);
    }

     public void withdraw(float amount) {
        if (balance + OVERDRAFT_LIMIT >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Withdrawal failed. Overdraft limit exceeded.");
        }
    }

    public void calculateInterest() {
        System.out.println("No interest for current accounts.");
    }
}
