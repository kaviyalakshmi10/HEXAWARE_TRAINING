

import java.util.Scanner;

public class AccountBalanceCheck {

    static int[] accountNumbers = {1001, 1002, 1003, 1004, 1005};
    static double[] balances = {25000.0, 15200.5, 30800.0, 12000.75, 500.0};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean found = false;

        System.out.println("Welcome to HM Bank Balance Checker");

        while (!found) {
            System.out.print("Enter your account number: ");

            if (scanner.hasNextInt()) {
                int inputAcc = scanner.nextInt();

                for (int i = 0; i < accountNumbers.length; i++) {
                    if (accountNumbers[i] == inputAcc) {
                        System.out.println("Account Found!");
                        System.out.printf("Your Balance: ₹%.2f\n", balances[i]);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Invalid account number. Please try again.");
                }
            } else {
                System.out.println("Please enter a valid numeric account number.");
                scanner.next(); 
            }
        }

        scanner.close();
    }
}



