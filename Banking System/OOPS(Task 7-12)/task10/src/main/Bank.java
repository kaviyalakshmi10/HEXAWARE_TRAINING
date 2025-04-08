package main;

import java.util.*;

public class Bank {
    private Map<Long, Account> accounts = new HashMap<>();

    public Account createAccount(Customer customer, String accType, float balance) {
        Account account = new Account(customer, accType, balance);
        accounts.put(account.getAccountNumber(), account);
        return account;
    }

    public float getAccountBalance(long accNo) {
        if (accounts.containsKey(accNo))
            return accounts.get(accNo).getBalance();
        throw new IllegalArgumentException("Account not found");
    }

    public float deposit(long accNo, float amount) {
        Account acc = accounts.get(accNo);
        acc.deposit(amount);
        return acc.getBalance();
    }

    public float withdraw(long accNo, float amount) {
        Account acc = accounts.get(accNo);
        if (!acc.withdraw(amount)) throw new IllegalArgumentException("Insufficient balance");
        return acc.getBalance();
    }

    public void transfer(long fromAcc, long toAcc, float amount) {
        if (!accounts.containsKey(fromAcc) || !accounts.containsKey(toAcc))
            throw new IllegalArgumentException("One or both accounts not found");
        if (!accounts.get(fromAcc).withdraw(amount))
            throw new IllegalArgumentException("Insufficient balance to transfer");
        accounts.get(toAcc).deposit(amount);
    }

    public String getAccountDetails(long accNo) {
        if (!accounts.containsKey(accNo)) throw new IllegalArgumentException("Account not found");
        return accounts.get(accNo).toString();
    }
}
