
import java.util.Scanner;

public class Password_Validator {
	public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        String password;

	        while (true) {
	            System.out.print("Create your bank account password: ");
	            password = scanner.nextLine();

	            if (isValidPassword(password)) {
	                System.out.println("Password is valid. Account created successfully.");
	                break;
	            } else {
	                System.out.println("Password is invalid. Please follow the password rules:");
	                System.out.println("- At least 8 characters long");
	                System.out.println("- At least one uppercase letter");
	                System.out.println("- At least one digit");
	                System.out.println("Try again.\n");
	            }
	        }

	        scanner.close();
	    }

	    public static boolean isValidPassword(String password) {
	        if (password.length() < 8) {
	            return false;
	        }

	        boolean hasUpperCase = false;
	        boolean hasDigit = false;

	        for (char ch : password.toCharArray()) {
	            if (Character.isUpperCase(ch)) {
	                hasUpperCase = true;
	            }
	            if (Character.isDigit(ch)) {
	                hasDigit = true;
	            }
	        }

	        return hasUpperCase && hasDigit;
	    }
	}


