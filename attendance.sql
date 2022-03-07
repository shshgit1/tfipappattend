drop database if exists attendance;

create database attendance;

use attendance;

create table staff(
    staff_id varchar(64) not null,
    name varchar(64) not null,
    dept varchar(64) DEFAULT NULL,
    primary key(staff_id)
    
);
CREATE TABLE timelog (
    ID int NOT NULL AUTO_INCREMENT,
    staff_id varchar(64) DEFAULT NULL,
    Date date DEFAULT NULL,
    clock_in varchar(64) DEFAULT 'Nil Entry',
    clock_out varchar(64) DEFAULT 'Nil Entry',
    clock_in_loc varchar(64) DEFAULT 'Nil Entry',
    clock_out_loc varchar(64) DEFAULT 'Nil Entry',
    PRIMARY KEY (ID),
    FOREIGN KEY (staff_id) REFERENCES staff (staff_id)

);

create table adminusers(
    username varchar(64) not NULL,
    password varchar(64) not null,
    primary key (username)
);
