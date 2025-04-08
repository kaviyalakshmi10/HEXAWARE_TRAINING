package main;


public class Account {
    private static long nextAccNumber = 1001;
    private long accountNumber;
    private String accountType;
    private float balance;
    private Customer customer;

    public Account() {}

    public Account(Customer customer, String accountType, float balance) {
        this.accountNumber = nextAccNumber++;
        this.customer = customer;
        this.accountType = accountType;
        this.balance = balance;
    }

    // Getters and setters
    public long getAccountNumber() {
        return accountNumber;
    }

    public float getBalance() {
        return balance;
    }

    public void deposit(float amount) {
        balance += amount;
    }

    public boolean withdraw(float amount) {
        if (amount > balance) return false;
        balance -= amount;
        return true;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "Account No: " + accountNumber + ", Type: " + accountType + ", Balance: " + balance + "\n" + customer.toString();
    }
}
