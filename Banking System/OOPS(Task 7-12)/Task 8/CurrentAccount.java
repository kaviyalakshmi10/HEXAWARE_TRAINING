public class CurrentAccount extends Account {
    private final double overdraftLimit = 5000.0;

    public CurrentAccount(int accountNumber, double balance) {
        super(accountNumber, "Current", balance);
    }

   
    public void withdraw(float amount) {
        if (amount <= balance + overdraftLimit) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Overdraft limit exceeded.");
        }
    }

  
    public void calculateInterest() {
        System.out.println("No interest for current account.");
    }
}
