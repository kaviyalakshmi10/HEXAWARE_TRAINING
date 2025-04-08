package service;

import bean.Customer;

public class CustomerServiceProviderImpl implements ICustomerServiceProvider {
    @Override
    public Customer createCustomer(int id, String name) {
        return new Customer(id, name);
    }
}