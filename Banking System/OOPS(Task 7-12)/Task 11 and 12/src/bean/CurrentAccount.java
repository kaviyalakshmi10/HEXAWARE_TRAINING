package bean;

import exception.OverDraftLimitExceededException;

public class CurrentAccount extends Account {
    private double overdraftLimit;

    public CurrentAccount(Customer customer, double balance, double overdraftLimit) {
        super(customer, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) throws OverDraftLimitExceededException {
        if (balance + overdraftLimit < amount) {
            throw new OverDraftLimitExceededException("Overdraft limit exceeded");
        }
        balance -= amount;
    }
}