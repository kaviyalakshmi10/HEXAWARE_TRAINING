create database cars;
use cars;
create table victims (
    victim_id int auto_increment primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    date_of_birth date,
    gender enum('male', 'female', 'other'),
    victim_address text,
    victim_phonenumber varchar(15)
);

create table suspects (
    suspect_id int auto_increment primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    date_of_birth date,
    gender enum('male', 'female', 'other'),
    suspects_address text,
    suspects_phonenumber varchar(15)
);

create table incidents (
    incident_id int auto_increment primary key,
    incident_type varchar(50) not null,
    incident_date date not null,
    description text,
    status enum('open', 'closed', 'under investigation') not null,
    victim_id int,
    suspect_id int,
    latitude decimal(9,6),
    longitude decimal(9,6),
    foreign key (victim_id) references victims(victim_id) ,
    foreign key (suspect_id) references suspects(suspect_id) 
);

create table law_enforcement_agencies (
    agency_id int auto_increment primary key,
    agency_name varchar(100) not null,
    jurisdiction varchar(100),
    agency_address text,
    agency_phonenumber varchar(15)
);

create table officers (
    officer_id int auto_increment primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    badge_number int,
    officers_rank varchar(50),
    officer_address text,
    officer_phonenumber varchar(15),
    agency_id int,
    foreign key (agency_id) references law_enforcement_agencies(agency_id) 
);

create table evidence (
    evidence_id int auto_increment primary key,
    description text,
    location_found text,
    incident_id int,
    foreign key (incident_id) references incidents(incident_id) 
);

create table reports (
    report_id int auto_increment primary key,
    incident_id int,
    reporting_officer int,
    report_date date not null,
    report_details text,
    status enum('draft', 'finalized') not null,
    foreign key (incident_id) references incidents(incident_id) on delete cascade,
    foreign key (reporting_officer) references officers(officer_id) 
);

insert into victims (first_name, last_name, date_of_birth, gender, victim_address, victim_phonenumber) values
('Ravi', 'Kumar', '1990-05-14', 'male', 'Bangalore, Karnataka', 9876543210),
('Sita', 'Iyer', '1985-08-22', 'female', 'Chennai, Tamil Nadu', 9823456789),
('Arjun', 'Singh', '1992-12-11', 'male', 'Delhi', 9845123678),
('Pooja', 'Sharma', '1988-07-19', 'female', 'Mumbai, Maharashtra', 9867452310),
('Rohan', 'Das', '1995-03-25', 'male', 'Kolkata, West Bengal', 9988776655),
('Meera', 'Patel', '1983-11-02', 'female', 'Ahmedabad, Gujarat', 9776655443),
('Vikram', 'Joshi', '1991-06-17', 'male', 'Hyderabad, Telangana', 9556677889),
('Ananya', 'Reddy', '1997-09-28', 'female', 'Pune, Maharashtra', 9445566778),
('Suresh', 'Naik', '1994-04-05', 'male', 'Goa', 9334455667),
('Neha', 'Verma', '1990-01-30', 'female', 'Lucknow, Uttar Pradesh', 9123344556);

insert into suspects (first_name, last_name, date_of_birth, gender, suspects_address, suspects_phonenumber) values
('Rahul', 'Mishra', '1985-09-21', 'male', 'Patna, Bihar', 9811122334),
('Kavita', 'Shah', '1990-02-15', 'female', 'Indore, Madhya Pradesh', 9844332211),
('Amit', 'Bose', '1987-06-10', 'male', 'Howrah, West Bengal', 9765544321),
('Sunita', 'Pillai', '1984-03-08', 'female', 'Thiruvananthapuram, Kerala', 9899776655),
('Rajesh', 'Gupta', '1981-12-02', 'male', 'Jaipur, Rajasthan', 9873322110),
('Manish', 'Yadav', '1989-07-23', 'male', 'Varanasi, Uttar Pradesh', 9955443322),
('Swati', 'Chopra', '1993-10-29', 'female', 'Shimla, Himachal Pradesh', 9899556677),
('Arvind', 'Thakur', '1996-05-14', 'male', 'Bhopal, Madhya Pradesh', 9445566778),
('Prakash', 'Shetty', '1982-08-30', 'male', 'Mangalore, Karnataka', 9776655443),
('Neeraj', 'Kapoor', '1990-11-11', 'male', 'Dehradun, Uttarakhand', 9112233445);

insert into law_enforcement_agencies (agency_name, jurisdiction, agency_address, agency_phonenumber) values
('Bangalore Police Department', 'Bangalore', 'MG Road, Bangalore', 9876543210),
('Mumbai Crime Branch', 'Mumbai', 'Colaba, Mumbai', 9823456789),
('Delhi Special Task Force', 'Delhi', 'Connaught Place, Delhi', 9845123678),
('Chennai Cyber Cell', 'Chennai', 'T. Nagar, Chennai', 9867452310),
('Kolkata Traffic Police', 'Kolkata', 'Park Street, Kolkata', 9988776655);

insert into officers (first_name, last_name, badge_number, officers_rank, officer_address, officer_phonenumber, agency_id) values
('Vikas', 'Raj', 101, 'Inspector', 'Koramangala, Bangalore', 9876543211, 1),
('Akhil', 'Pawar', 102, 'Sub-Inspector', 'Bandra, Mumbai', 9823456799, 2),
('Anand', 'Singh', 103, 'DSP', 'Rohini, Delhi', 9845123688, 3),
('Shankar', 'Nair', 104, 'ACP', 'Anna Nagar, Chennai', 9867452320, 4),
('Rajeev', 'Sen', 105, 'Constable', 'Salt Lake, Kolkata', 9988776677, 5);

insert into incidents (incident_type, incident_date, description, status, victim_id, suspect_id, latitude, longitude) values
('Robbery', '2024-03-01', 'Robbery at a jewelry store', 'open', 1, 2, 12.971599, 77.594566), 
('Assault', '2024-02-15', 'Physical assault near metro station', 'under investigation', 3, 4, 28.704060, 77.102493),  
('Fraud', '2024-01-10', 'Bank fraud reported', 'closed', 5, 6, 19.076090, 72.877426),  
('Burglary', '2024-03-12', 'House burglary at midnight', 'open', 7, 8, 13.082680, 80.270721), 
('Hit and Run', '2024-02-28', 'Hit and run case near bypass road', 'under investigation', 9, 10, 22.572645, 88.363892), 
('Cybercrime', '2024-01-25', 'Online scam reported', 'closed', 2, 3, 17.385044, 78.486671),  
('Kidnapping', '2024-03-20', 'Child missing from school', 'open', 4, 5, 23.259933, 77.412613), 
('Murder', '2024-03-05', 'Homicide case under investigation', 'under investigation', 6, 7, 26.912434, 75.787270), 
('Vandalism', '2024-02-10', 'Public property vandalized', 'closed', 8, 9, 25.317645, 82.973915), 
('Drug Trafficking', '2024-01-30', 'Illegal drug trade busted', 'open', 10, 1, 15.299326, 74.123993);  


insert into evidence (description, location_found, incident_id) values
('Blood-stained knife', 'Near victim\'s residence', 1),
('CCTV footage', 'Bank ATM', 2);

insert into reports (incident_id, reporting_officer, report_date, report_details, status) values
(1, 1, '2024-02-16', 'Initial report filed, awaiting forensic analysis', 'draft'),
(2, 2, '2024-03-06', 'Case handed over to crime branch', 'finalized');


