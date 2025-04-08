package main;
public class SavingsAccount extends BankAccount {
    private double interestRate = 4.5;

    public SavingsAccount(String accountNumber, String customerName, double balance) {
        super(accountNumber, customerName, balance);
    }

    
    public void deposit(float amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount);
    }

   
    public void withdraw(float amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

   
    public void calculateInterest() {
        double interest = (balance * interestRate) / 100;
        balance += interest;
        System.out.println("Interest added: $" + interest);
    }
}
