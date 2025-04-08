package app;

import bean.*;
import service.*;
import exception.*;
import java.util.*;

public class BankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ICustomerServiceProvider customerService = new CustomerServiceProviderImpl();
        IBankServiceProvider bankService = new BankServiceProviderImpl();
        Map<Integer, Account> accountMap = new HashMap<>();

        while (true) {
            System.out.println("\nMenu: 1.create_account | 2.deposit | 3.withdraw | 4.get_balance | 5.transfer | 6.getAccountDetails | 7.ListAccounts | 8.exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter customer ID: ");
                        int id = sc.nextInt();
                        System.out.print("Enter name: ");
                        String name = sc.next();
                        Customer customer = customerService.createCustomer(id, name);

                        System.out.println("1. Savings 2. Current 3. ZeroBalance");
                        int type = sc.nextInt();
                        Account acc = null;
                        if (type == 1) {
                            System.out.print("Enter initial deposit: ");
                            acc = bankService.createSavingsAccount(customer, sc.nextDouble());
                        } else if (type == 2) {
                            System.out.print("Enter initial deposit: ");
                            double bal = sc.nextDouble();
                            System.out.print("Enter overdraft limit: ");
                            double limit = sc.nextDouble();
                            acc = bankService.createCurrentAccount(customer, bal, limit);
                        } else if (type == 3) {
                            acc = bankService.createZeroBalanceAccount(customer);
                        } else {
                            throw new InvalidAccountException("Invalid account type");
                        }
                        accountMap.put(acc.getAccountId(), acc);
                        System.out.println("Account created: ID " + acc.getAccountId());
                        break;

                    case 2:
                        System.out.print("Enter account ID: ");
                        int did = sc.nextInt();
                        System.out.print("Enter amount: ");
                        bankService.deposit(accountMap.get(did), sc.nextDouble());
                        break;

                    case 3:
                        System.out.print("Enter account ID: ");
                        int wid = sc.nextInt();
                        System.out.print("Enter amount: ");
                        bankService.withdraw(accountMap.get(wid), sc.nextDouble());
                        break;

                    case 4:
                        System.out.print("Enter account ID: ");
                        System.out.println("Balance: " + accountMap.get(sc.nextInt()).getBalance());
                        break;

                    case 5:
                    try {
                        System.out.print("From account ID: ");
                        int fromId = sc.nextInt();
                        if (!accountMap.containsKey(fromId)) {
                            throw new InvalidAccountException("Invalid FROM account ID.");
                        }
                        Account from = accountMap.get(fromId);
                
                        System.out.print("To account ID: ");
                        int toId = sc.nextInt();
                        if (!accountMap.containsKey(toId)) {
                            throw new InvalidAccountException("Invalid TO account ID.");
                        }
                        Account to = accountMap.get(toId);
                
                        System.out.print("Enter amount: ");
                        double amt = sc.nextDouble();
                
                        bankService.withdraw(from, amt);
                        bankService.deposit(to, amt);
                        System.out.println("Transfer complete.");
                
                    } catch (InvalidAccountException e) {
                        System.err.println("Account error: " + e.getMessage());
                    } catch (InsufficientFundException | OverDraftLimitExceededException e) {
                        System.err.println("Transaction failed: " + e.getMessage());
                    } catch (NullPointerException e) {
                        System.err.println("Null account encountered: " + e.getMessage());
                    }
                    break;
                

                    case 6:
                        System.out.print("Enter account ID: ");
                        Account a = accountMap.get(sc.nextInt());
                        System.out.println("Account ID: " + a.getAccountId());
                        System.out.println("Customer: " + a.getCustomer().getName());
                        System.out.println("Balance: " + a.getBalance());
                        break;

                    case 7:
                        for (Account ac : accountMap.values()) {
                            System.out.println("ID: " + ac.getAccountId() + ", Customer: " + ac.getCustomer().getName());
                        }
                        break;

                    case 8:
                        System.out.println("Exiting...");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}