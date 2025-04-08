package bean;

public class SavingsAccount extends Account {
    public SavingsAccount(Customer customer, double balance) {
        super(customer, balance);
    }

    @Override
    public double calculateInterest() {
        return balance * 0.04;
    }
}