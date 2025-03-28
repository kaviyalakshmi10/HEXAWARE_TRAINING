
-- CAR_RENTAL_SYSTEM --
create database car_rental_system;
use car_rental_system;
-- schema creation--
create table vehicle (
    vehicle_id int auto_increment primary key,
    make varchar(50),
    model varchar(50),
    year int,
    daily_rate decimal(10,2),
    status enum('available', 'notavailable'),
    passenger_capacity int,
    engine_capacity int
);

create table customer (
    customer_id int auto_increment primary key,
    first_name varchar(50),
    last_name varchar(50),
    email varchar(100),
    phone_number varchar(15)
);

create table lease (
    lease_id int auto_increment primary key,
    vehicle_id int,
    customer_id int,
    start_date date,
    end_date date,
    lease_type enum('daily', 'monthly'),
    foreign key (vehicle_id) references vehicle(vehicle_id) on delete cascade,
    foreign key (customer_id) references customer(customer_id) on delete cascade
);

create table payment (
    payment_id int auto_increment primary key,
    lease_id int,
    payment_date date,
    amount decimal(10,2),
    foreign key (lease_id) references lease(lease_id) on delete cascade
);

-- inserting the given values to table 
insert into vehicle (make, model, year, daily_rate, status, passenger_capacity, engine_capacity) values
('toyota', 'camry', 2022, 50.00, 'available', 4, 1450),
('honda', 'civic', 2023, 45.00, 'available', 7, 1500),
('ford', 'focus', 2022, 48.00, 'notavailable', 4, 1400),
('nissan', 'altima', 2023, 52.00, 'available', 7, 1200),
('chevrolet', 'malibu', 2022, 47.00, 'available', 4, 1800),
('hyundai', 'sonata', 2023, 49.00, 'notavailable', 7, 1400),
('bmw', '3 series', 2023, 60.00, 'available', 7, 2499),
('mercedes', 'c-class', 2022, 58.00, 'available', 8, 2599),
('audi', 'a4', 2022, 55.00, 'notavailable', 4, 2500),
('lexus', 'es', 2023, 54.00, 'available', 4, 2500);

insert into customer (first_name, last_name, email, phone_number) values
('john', 'doe', 'johndoe@example.com', '555-555-5555'),
('jane', 'smith', 'janesmith@example.com', '555-123-4567'),
('robert', 'johnson', 'robert@example.com', '555-789-1234'),
('sarah', 'brown', 'sarah@example.com', '555-456-7890'),
('david', 'lee', 'david@example.com', '555-987-6543'),
('laura', 'hall', 'laura@example.com', '555-234-5678'),
('michael', 'davis', 'michael@example.com', '555-876-5432'),
('emma', 'wilson', 'emma@example.com', '555-432-1098'),
('william', 'taylor', 'william@example.com', '555-321-6547'),
('olivia', 'adams', 'olivia@example.com', '555-765-4321');

insert into lease (vehicle_id, customer_id, start_date, end_date, lease_type) values
(1, 1, '2023-01-01', '2023-01-05', 'daily'),
(2, 2, '2023-02-15', '2023-02-28', 'monthly'),
(3, 3, '2023-03-10', '2023-03-15', 'daily'),
(4, 4, '2023-04-20', '2023-04-30', 'monthly'),
(5, 5, '2023-05-05', '2023-05-10', 'daily'),
(4, 3, '2023-06-15', '2023-06-30', 'monthly'),
(7, 7, '2023-07-01', '2023-07-10', 'daily'),
(8, 8, '2023-08-12', '2023-08-15', 'monthly'),
(3, 3, '2023-09-07', '2023-09-10', 'daily'),
(10, 10, '2023-10-10', '2023-10-31', 'monthly');


insert into payment (lease_id, payment_date, amount) values
(1, '2023-01-03', 200.00),
(2, '2023-02-20', 1000.00),
(3, '2023-03-12', 75.00),
(4, '2023-04-25', 900.00),
(5, '2023-05-07', 60.00),
(6, '2023-06-18', 1200.00),
(7, '2023-07-03', 40.00),
(8, '2023-08-14', 1100.00),
(9, '2023-09-09', 80.00),
(10, '2023-10-25', 1500);

-- 1. Update the daily rate for a Mercedes car to 68. 
 set sql_safe_updates = 0;
 update vehicle set daily_rate = 68 where make ='mercedes';

-- 2. Delete a specific customer and all associated leases and payments.  
delete  from customer where customer_id = 3;

-- 3. Rename the "paymentDate" column in the Payment table to "transactionDate". 
alter table payment change column payment_date  transactionDate date;
select * from payment;
 
-- 4. Find a specific customer by email. 
select * from customer where email ='laura@example.com'; 

-- 5. Get active leases for a specific customer. 
-- note:to get current active customer, just update the date here.
update lease set end_date = '2025-03-31' where customer_id = 4;
select * from lease 
where customer_id = 4 and end_date >= curdate();

-- 6.Find all payments made by a customer with a specific phone number. 
select p.* from payment p
join lease l on p.lease_id = l.lease_id
join customer c on l.customer_id = c.customer_id
where c.phone_number = '555-123-4567'; 

-- 7. Calculate the average daily rate of all available cars. 
select avg(daily_rate) from vehicle where status ='available' group by status;

-- 8. Find the car with the highest daily rate. 
select * from vehicle order by daily_rate desc limit 1;

-- 9. Retrieve all cars leased by a specific customer.
select v.* from vehicle v join lease l on v.vehicle_id = l.vehicle_id
where l.customer_id = 5;

-- 10. Find the details of the most recent lease. 
select * from lease order by end_date desc limit 1;

-- 11. List all payments made in the year 2023.
select * from payment where year(transactionDate) = 2023;

-- 12. Retrieve customers who have not made any payments.
select * from customer where customer_id not in (select customer_id from lease 
where lease_id in (select lease_id from payment)); 

-- 13.Retrieve Car Details and Their Total Payments. 
select v.make, v.model, sum(p.amount) as total_payments from vehicle v
join lease l on v.vehicle_id = l.vehicle_id
join payment p on l.lease_id = p.lease_id
group by v.vehicle_id;

-- 14. Calculate Total Payments for Each Customer. 
select c.first_name, c.last_name, sum(p.amount) as total_spent
from customer c
join lease l on c.customer_id = l.customer_id
join payment p on l.lease_id = p.lease_id
group by c.customer_id;

-- 15. list car details for each lease
select l.lease_id,c.customer_id, v.make, v.model, l.start_date, l.end_date
from lease l
join customer c on l.customer_id = c.customer_id
join vehicle v on l.vehicle_id = v.vehicle_id;

-- 16. retrieve details of active leases with customer and car information.
select l.lease_id, c.first_name, c.last_name, v.make, v.model, l.start_date, l.end_date from lease l
join customer c on l.customer_id = c.customer_id
join vehicle v on l.vehicle_id = v.vehicle_id
where l.end_date >= curdate();

-- 17. find the customer who has spent the most on leases.
select c.first_name, c.last_name, sum(p.amount) as total_spent from customer c
join lease l on c.customer_id = l.customer_id
join payment p on l.lease_id = p.lease_id
group by c.customer_id
order by total_spent desc limit 1;

-- 18. list all cars with their current lease information
select v.make, v.model, l.start_date, l.end_date, c.first_name, c.last_name from vehicle v
left join lease l on v.vehicle_id = l.vehicle_id
left join customer c on l.customer_id = c.customer_id
where l.end_date >= curdate();