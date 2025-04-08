package dao;
import java.util.List;

import entity.*;
public class CustomerServiceProviderImpl implements ICustomerServiceProvider {

    @Override
    public double getBalance(long account_id) {

        return 0;
    }

    @Override
    public double deposit(long account_id, double amount) {


        return amount;
    }
    @Override
    public double withdraw(long account_id, double amount) {
        return 0.0;
    }
    @Override
    public void transfer(long fromAccount_id, long toAccount_id, double amount) {
        System.out.println("Transferred Rs." + amount + " fromAccount " + fromAccount_id + " to account " + toAccount_id);
    }
    @Override
    public String getAccountDetails(long account_id) {
        return "Account details for account number " + account_id;
    }
    @Override
	public List<Transaction> getTransactionsBetweenDate(long account_id, String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}


    }
