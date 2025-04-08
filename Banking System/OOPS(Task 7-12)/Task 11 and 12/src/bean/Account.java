package bean;

import exception.InsufficientFundException;
import exception.OverDraftLimitExceededException;

public class Account {
    private static int nextAccountId = 1001;
    protected int accountId;
    protected Customer customer;
    protected double balance;

    public Account(Customer customer, double balance) {
        this.accountId = nextAccountId++;
        this.customer = customer;
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundException, OverDraftLimitExceededException {
        if (balance < amount) {
            throw new InsufficientFundException("Insufficient balance");
        }
        this.balance -= amount;
    }

    public double calculateInterest() {
        return 0;
    }
}