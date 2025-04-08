
package main;

import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n-- Banking System Menu --");
            System.out.println("1. Create Account\n2. Deposit\n3. Withdraw\n4. Get Balance\n5. Transfer\n6. Get Account Details\n7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Customer ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("First Name: ");
                    String fname = sc.nextLine();
                    System.out.print("Last Name: ");
                    String lname = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Address: ");
                    String address = sc.nextLine();

                    Customer cust = new Customer(id, fname, lname, email, phone, address);
                    System.out.print("Account Type (Savings/Current): ");
                    String type = sc.nextLine();
                    System.out.print("Initial Balance: ");
                    float bal = sc.nextFloat();
                    var acc = bank.createAccount(cust, type, bal);
                    System.out.println("Account Created: " + acc.getAccountNumber());
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    long dacc = sc.nextLong();
                    System.out.print("Amount to deposit: ");
                    float damt = sc.nextFloat();
                    System.out.println("New Balance: " + bank.deposit(dacc, damt));
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    long wacc = sc.nextLong();
                    System.out.print("Amount to withdraw: ");
                    float wamt = sc.nextFloat();
                    try {
                        System.out.println("New Balance: " + bank.withdraw(wacc, wamt));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Enter Account Number: ");
                    long bacc = sc.nextLong();
                    System.out.println("Balance: " + bank.getAccountBalance(bacc));
                    break;

                case 5:
                    System.out.print("From Account: ");
                    long from = sc.nextLong();
                    System.out.print("To Account: ");
                    long to = sc.nextLong();
                    System.out.print("Amount: ");
                    float tAmt = sc.nextFloat();
                    try {
                        bank.transfer(from, to, tAmt);
                        System.out.println("Transfer successful.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    System.out.print("Enter Account Number: ");
                    long ac = sc.nextLong();
                    System.out.println(bank.getAccountDetails(ac));
                    break;

                case 7:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
