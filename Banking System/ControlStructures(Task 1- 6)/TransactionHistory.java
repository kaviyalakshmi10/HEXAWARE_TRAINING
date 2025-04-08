import java.util.ArrayList;
import java.util.Scanner;

public class TransactionHistory {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> transactions = new ArrayList<>();
        double balance = 0.0;
        boolean keepGoing = true;

        System.out.println("Welcome to the Bank Transaction System");

        while (keepGoing) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Exit and Show Transaction History");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double deposit = scanner.nextDouble();
                    balance += deposit;
                    transactions.add("Deposited: $" + deposit);
                    System.out.println("Deposit successful.");
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdraw = scanner.nextDouble();
                    if (withdraw > balance) {
                        System.out.println("Insufficient balance.");
                    } else {
                        balance -= withdraw;
                        transactions.add("Withdraw: $" + withdraw);
                        System.out.println("Withdrawal successful.");
                    }
                    break;

                case 3:
                    keepGoing = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        System.out.println("\nTransaction History:");
        for (String txn : transactions) {
            System.out.println(txn);
        }

        System.out.printf("Final Balance: $%.2f\n", balance);

        scanner.close();
    }
}



