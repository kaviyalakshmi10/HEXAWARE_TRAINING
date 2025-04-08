package service;

import bean.*;
import exception.*;

public interface IBankServiceProvider {
    Account createSavingsAccount(Customer customer, double initialAmount);
    Account createCurrentAccount(Customer customer, double initialAmount, double overdraftLimit);
    Account createZeroBalanceAccount(Customer customer);
    void deposit(Account account, double amount);
    void withdraw(Account account, double amount) throws InsufficientFundException, OverDraftLimitExceededException;
    double calculateInterest(Account account);
}