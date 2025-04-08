package service;

import bean.*;
import exception.*;

public class BankServiceProviderImpl implements IBankServiceProvider {
    @Override
    public Account createSavingsAccount(Customer customer, double initialAmount) {
        return new SavingsAccount(customer, initialAmount);
    }

    @Override
    public Account createCurrentAccount(Customer customer, double initialAmount, double overdraftLimit) {
        return new CurrentAccount(customer, initialAmount, overdraftLimit);
    }

    @Override
    public Account createZeroBalanceAccount(Customer customer) {
        return new ZeroBalanceAccount(customer);
    }

    @Override
    public void deposit(Account account, double amount) {
        account.deposit(amount);
    }

    @Override
    public void withdraw(Account account, double amount) throws InsufficientFundException, OverDraftLimitExceededException {
        if (account instanceof CurrentAccount) {
            ((CurrentAccount) account).withdraw(amount);
        } else {
            account.withdraw(amount);
        }
    }

    @Override
    public double calculateInterest(Account account) {
        return account.calculateInterest();
    }
}