create database hospitalmanagement;
use hospitalmanagement;
create table patient (
    patient_id int primary key auto_increment,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    date_of_birth date not null,
    gender varchar(10),
    contact_number varchar(15) unique,
    address varchar(255)
);
create table doctor (
    doctor_id int primary key auto_increment,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    specialization varchar(100),
    contact_number varchar(15) unique
);
create table appointment (
    appointment_id int primary key auto_increment,
    patient_id int,
    doctor_id int,
    appointment_date datetime not null,
    description text,
    foreign key (patient_id) references patient(patient_id) on delete cascade,
    foreign key (doctor_id) references doctor(doctor_id) on delete set null
);
insert into patient (first_name, last_name, date_of_birth, gender, contact_number, address) values
('Aarav', 'Sharma', '1985-03-15', 'Male', '9876543210', '123 Main St, Mumbai'),
('Sanya', 'Mehra', '1990-07-22', 'Female', '9123456789', '456 Hill Rd, Delhi'),
('Rohan', 'Kumar', '1978-11-02', 'Male', '9988776655', '789 Sea View, Chennai'),
('Neha', 'Verma', '1983-06-30', 'Female', '8877665544', '321 MG Road, Bangalore'),
('Vivaan', 'Patel', '2000-01-01', 'Male', '8765432109', '654 JP Nagar, Pune');

insert into doctor (first_name, last_name, specialization, contact_number) values
('Dr. Arjun', 'Saxena', 'Cardiologist', '9812345678'),
('Dr. Kavya', 'Rao', 'Dermatologist', '9823456789'),
('Dr. Siddharth', 'Nair', 'Pediatrician', '9834567890'),
('Dr. Meera', 'Joshi', 'Neurologist', '9845678901'),
('Dr. Rajeev', 'Gupta', 'Orthopedic', '9856789012');
insert into appointment (patient_id, doctor_id, appointment_date, description) values
(1, 1, '2025-04-16 10:00:00', 'Routine cardiac check-up'),
(2, 2, '2025-04-17 11:30:00', 'Skin allergy consultation'),
(3, 3, '2025-04-18 09:00:00', 'Fever and cold - child'),
(4, 4, '2025-04-19 14:00:00', 'Migraine and headache issue'),
(5, 5, '2025-04-20 16:00:00', 'Knee joint pain'),
(1, 3, '2025-04-21 12:00:00', 'Follow-up for flu'),
(2, 1, '2025-04-22 10:30:00', 'Irregular heartbeat'),
(3, 4, '2025-04-23 15:00:00', 'Neurological consultation'),
(4, 5, '2025-04-24 13:30:00', 'Orthopedic review'),
(5, 2, '2025-04-25 17:00:00', 'Acne and skin treatment');

select *from appointment;