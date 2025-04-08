package main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = null;

        System.out.println("Welcome to the Bank!");
        System.out.println("Choose Account Type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();
        System.out.print("Enter Customer Name: ");
        String custName = scanner.nextLine();
        System.out.print("Enter Initial Balance: ");
        double balance = scanner.nextDouble();

        switch (choice) {
            case 1:
                account = new SavingsAccount(accNum, custName, balance);
                break;
            case 2:
                account = new CurrentAccount(accNum, custName, balance);
                break;
            default:
                System.out.println("Invalid option.");
                System.exit(0);
        }

        int option;
        do {
            System.out.println("\nSelect Operation:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Calculate Interest");
            System.out.println("4. Display Details");
            System.out.println("5. Exit");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    account.deposit(scanner.nextFloat());
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    account.withdraw(scanner.nextFloat());
                    break;
                case 3:
                    account.calculateInterest();
                    break;
                case 4:
                    account.displayDetails();
                    break;
                case 5:
                    System.out.println("Thank you for using the Bank!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (option != 5);

        scanner.close();
    }
}
