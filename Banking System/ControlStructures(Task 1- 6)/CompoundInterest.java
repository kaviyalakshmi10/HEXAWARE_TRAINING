
import java.util.Scanner;
public class CompoundInterest {
	public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Enter the number of customers: ");
	        int customerCount = scanner.nextInt();

	        for (int i = 1; i <= customerCount; i++) {
	            System.out.println("\nCustomer " + i + ":");

	            System.out.print("Enter initial balance: ");
	            double initialBalance = scanner.nextDouble();

	            System.out.print("Enter annual interest rate (%): ");
	            double interestRate = scanner.nextDouble();

	            System.out.print("Enter number of years: ");
	            int years = scanner.nextInt();

	            double futureBalance = initialBalance * Math.pow((1 + interestRate / 100), years);

	            System.out.printf("Future balance after %d years: ₹%.2f%n", years, futureBalance);
	        }

	        scanner.close();
	    }
	


}
