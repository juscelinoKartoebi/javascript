create database hoteladmin;
use hoteladmin;
create table clients(
                        client_id int not null auto_increment primary key,
                        first_name varchar(40),
                        last_name varchar(40),
                        dob date,
                        gender varchar (3),
                        email varchar (80),
                        phone_number varchar(30)
);

create table adds(
                     id int not null auto_increment primary key,
                     add_name varchar(30)
);

create table accounts(
                         accountNumber int not null auto_increment primary key,
                         client_id int not null,
                         type varchar(30),
                         currency varchar(10),
                         balance double,
                         FOREIGN KEY (client_id) REFERENCES clients(client_id)
);

create table account_add(
                            account_id int not null,
                            add_id int not null,
                            FOREIGN KEY (account_id) REFERENCES accounts(accountNumber),
                            FOREIGN KEY (add_id) REFERENCES adds(id)
);

#Add the following adds to avoid issues(you can add more)
# insert into adds (id, add_name)
# values
# (1,"Electricity"),
# (2, "Water");


select * from clients;