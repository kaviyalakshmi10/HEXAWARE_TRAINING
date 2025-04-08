public class Account {
    protected int accountNumber;
    protected String accountType;
    protected double balance;

    public Account() {}

    public Account(int accountNumber, String accountType, double balance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
    }

    public void deposit(float amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    public void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    public void withdraw(float amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void withdraw(int amount) {
        withdraw((float) amount);
    }

    public void withdraw(double amount) {
        withdraw((float) amount);
    }

    public void calculateInterest() {
        System.out.println("No interest for base Account.");
    }

    public void showBalance() {
        System.out.println("Account Balance: $" + balance);
    }
}
