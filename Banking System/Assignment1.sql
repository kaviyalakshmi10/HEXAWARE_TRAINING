-- Task1-- 
-- 1.Create the database named "HMBank"--   
create database HMBank;
use HMBank;
-- 2.and 6. Define the schema and write the sql scripts-- 

CREATE TABLE Customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    DOB DATE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(15) UNIQUE NOT NULL,
    address TEXT NOT NULL
);
CREATE TABLE Accounts (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    account_type ENUM('savings', 'current', 'zero_balance') NOT NULL,
    balance DECIMAL(15,2) NOT NULL DEFAULT 0.00,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id) ON DELETE CASCADE
);
CREATE TABLE Transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT NOT NULL,
    transaction_type ENUM('deposit', 'withdrawal', 'transfer') NOT NULL,
    amount DECIMAL(15,2) NOT NULL CHECK (amount > 0),
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES Accounts(account_id) ON DELETE CASCADE
);
-- 5. Create appropriate Primary Key and Foreign Key constraints for referential integrity.--    
-- Primary Keys: customer_id, account_id, transaction_id -- 
-- Foreign Keys:customer_id in Accounts references customer_id in Customers ,account_id in Transactions references account_id in Accounts-- 

-- Task 2-- 
-- 1. Insert at least 10 sample records into each of the following tables. --
 insert into customers (first_name, last_name, dob, email, phone_number, address) values
('arun', 'kumar', '1990-05-12', 'arun.kumar@example.com', '9876543210', 'chennai'),
('divya', 'sham', '1985-07-23', 'divya.sham@example.com', '8765432109', 'chennai'),
('karthik', 'rajan', '1992-09-15', 'karthik.rajan@example.com', '7654321098', 'avadi'),
('priya', 'venkat', '1988-02-19', 'priya.venkat@example.com', '6543210987', 'chennai'),
('suresh', 'iyer', '1995-12-05', 'suresh.iyer@example.com', '5432109876', 'chennai'),
('meena', 'mohan', '1980-06-30', 'meena.mohan@example.com', '4321098765', 'avadi'),
('raj', 'patel', '1993-04-25', 'raj.patel@example.com', '3210987654', 'chennai'),
('swetha', 'rajan', '1987-10-10', 'swetharajan@example.com', '2109876543', 'avadi'),
('gopi', 'ramesh', '1994-08-20', 'gopi.ramesh@example.com', '1098765432', 'chennai'),
('lakshmi', 'bala', '1991-11-11', 'lakshmi.bala@example.com', '9988776655', 'chennai');

insert into accounts (customer_id, account_type, balance) values
(1, 'savings', 5000.00),
(2, 'current', 12000.50),
(3, 'savings', 8000.75),
(4, 'current', 15000.00),
(5, 'zero_balance', 0.00),
(6, 'savings', 20000.00),
(7, 'current', 5000.00),
(8, 'savings', 3000.25),
(9, 'zero_balance', 0.00),
(10, 'savings', 10000.00);


insert into transactions (account_id, transaction_type, amount) values
(1, 'deposit', 2000.00),
(2, 'withdrawal', 1000.50),
(3, 'deposit', 5000.25),
(4, 'withdrawal', 2000.00),
(5, 'deposit', 1500.00),
(6, 'withdrawal', 500.75),
(7, 'deposit', 3000.00),
(8, 'withdrawal', 1000.00),
(9, 'deposit', 2500.00),
(10, 'withdrawal', 1200.00);

-- 2. Write SQL queries for the following tasks: --  
-- 1. Write a SQL query to retrieve the name, account type and email of all customers. --
select first_name, last_name, account_type, email from customers 
join accounts on customers.customer_id = accounts.customer_id;

-- 2. Write a SQL query to list all transaction corresponding customer. -- 
 select c.first_name, c.last_name, t.transaction_type, t.amount, t.transaction_date 
 from customers c join accounts a on c.customer_id = a.customer_id join transactions t on a.account_id = t.account_id;

--  3. Write a SQL query to increase the balance of a specific account by a certain amount. -- 
update accounts set balance = balance + 5000 where account_id = 1;

-- 4. Write a SQL query to Combine first and last names of customers as a full_name. -- 
select concat(first_name, ' ', last_name) as full_name from customers;

-- 5. Write a SQL query to remove accounts with a balance of zero where the account type is savings.--   
set sql_safe_updates = 0;
update  accounts set balance = 0 where account_type = 'savings' and account_id = 1;
delete from accounts where balance = 0 and account_type = 'savings';
set sql_safe_updates = 1;

-- 6. Write a SQL query to Find customers living in a specific city. -- 
select * from customers where address = 'chennai';

-- 7. Write a SQL query to Get the account balance for a specific account. --  
select balance from accounts where account_id = 3;

-- 8. Write a SQL query to List all current accounts with a balance greater than $1,000. 
select * from accounts where account_type = 'current' and balance > 1000;

-- 9. Write a SQL query to Retrieve all transactions for a specific account. -- 
select * from transactions where account_id = 2;

-- 10. Write a SQL query to Calculate the interest accrued on savings accounts based on a given interest rate. --  
select account_id, balance * 0.05 as interest_accrued from accounts where account_type = 'savings';

-- 11. Write a SQL query to Identify accounts where the balance is less than a specified overdraft limit. --  
select * from accounts where balance < 1000;

-- 12. Write a SQL query to Find customers not living in a specific city. --  
select * from customers where address != 'chennai';

-- Task 3--  
-- 1. Write a SQL query to Find the average account balance for all customers. --   
select avg(balance) as average_balance from accounts;

-- 2. Write a SQL query to Retrieve the top 10 highest account balances.  -- 
select * from accounts order by balance desc limit 10;

-- 3. Write a SQL query to Calculate Total Deposits for All Customers in specific date.--  
select sum(amount) as total_deposits from transactions 
where transaction_type = 'deposit' and date(transaction_date) = '2025-03-19';
-- 4. Write a SQL query to Find the Oldest and Newest Customers. -- 
select * from customers order by dob asc limit 1;
select * from customers order by dob desc limit 1;

-- 5. Write a SQL query to Retrieve transaction details along with the account type.-- 
select t.*,a.account_type from transactions t join accounts a on t.transaction_id = t.account_id ;

-- 6. Write a SQL query to Get a list of customers along with their account details. -- 
select c.*, a.account_id, a.account_type, a.balance 
from customers c join accounts a on c.customer_id = a.customer_id;

-- 7. Write a SQL query to Retrieve transaction details along with customer information for a specific account.--
  select t.*, c.* from transactions t join accounts a on t.account_id = a.account_id 
join customers c on a.customer_id = c.customer_id where t.account_id = 4;

-- 8. Write a SQL query to Identify customers who have more than one account. -- 
insert into accounts(customer_id, account_type, balance) values(2 , 'savings', 12000.50);

select c.customer_id, c.first_name, c.last_name, count(a.account_id) as account_count 
from customers c join accounts a on c.customer_id = a.customer_id
group by c.customer_id having count(a.account_id) > 1;

-- 9. Write a SQL query to Calculate the difference in transaction amounts between deposits and withdrawals. -- 
select a.account_id, sum(case when t.transaction_type = 'deposit' then t.amount else 0 end) 
- sum(case when t.transaction_type = 'withdrawal' then t.amount else 0 end) as balance_difference 
from accounts a join transactions t on a.account_id = t.account_id
group by a.account_id;

-- 10. Write a SQL query to Calculate the average daily balance for each account over a specified period.--  
select account_id, round(avg(amount),2) as avg_daily_balance from transactions 
where transaction_date between '2025-03-19 22:10:06' and '2025-03-19 22:10:08' 
group by account_id;

-- 11. Calculate the total balance for each account type. -- 
select account_type, sum(balance) as total_bal
from accounts group by account_type;

-- 12. Identify accounts with the highest number of transactions order by descending order.-- 
 select account_id, count(transaction_id) as transact_count 
from transactions group by account_id order by transact_count desc;

-- 13. List customers with high aggregate account balances, along with their account types. -- 

select c.customer_id, a.account_type, a.balance  as totalbalance from customers c join accounts a on c.customer_id = a.customer_id
where a.balance > 5000 order by a.balance desc;

select c.customer_id, group_concat(a.account_type separator ', ') as account_types, 
sum(a.balance) as tot_bal from customers c join accounts a on c.customer_id = a.customer_id
group by c.customer_id having tot_bal > 5000 order by tot_bal desc ;

-- 14. Identify and list duplicate transactions based on transaction amount, date, and account.--  
 select account_id, amount, transaction_date, count(*) as duplicate_count 
from transactions group by account_id, amount, transaction_date 
having count(*) > 1;

-- TASK 4--  
-- 1. Retrieve the customer(s) with the highest account balance.-- 
select * from customers where customer_id in (select customer_id from accounts 
where balance = (select max(balance) from accounts));

-- 2. Calculate the average account balance for customers who have more than one account.-- 
select customer_id, avg(balance) as avg_bal from accounts group by customer_id 
having count(account_id) >1;
 
-- 3. Retrieve accounts with transactions whose amounts exceed the average transaction amount.-- 
select * from transactions where amount > (select avg(amount) from transactions);

-- 4. Identify customers who have no recorded transactions.-- 
select * from customers where customer_id not in (select distinct customer_id from accounts 
where account_id in (select distinct account_id from transactions));

-- 5. Calculate the total balance of accounts with no recorded transactions.-- 
select  account_id,sum(balance) as tot_bal from accounts 
where account_id not in (select distinct account_id from transactions) group by account_id;

-- 6. Retrieve transactions for accounts with the lowest balance.-- 
select * from transactions where account_id in (select account_id from accounts 
where balance = (select min(balance) from accounts));

-- 7. Identify customers who have accounts of multiple types.-- 
select customer_id from accounts group by customer_id having count(distinct account_type) > 1;

-- 8. Calculate the percentage of each account type out of the total number of accounts.-- 
select account_type,  concat(floor(count(*) * 100 / (select count(*) from accounts)), '%') as percent 
from accounts group by account_type;

-- 9. Retrieve all transactions for a customer with a given customer_id.-- 
select * from transactions  
where account_id in (select account_id from accounts where customer_id = 3);

-- 10. Calculate the total balance for each account type, including a subquery within the SELECT clause.-- 
select c.* from customers c
join accounts a on c.customer_id = a.customer_id
where a.balance = (select max(balance) from accounts);






