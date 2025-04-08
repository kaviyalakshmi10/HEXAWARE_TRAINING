package service;

import bean.Customer;

public interface ICustomerServiceProvider {
    Customer createCustomer(int id, String name);
}