create database if not exists DB_PMS;
use DB_PMS;
create table Product(
    id int auto_increment primary key,
    name varchar(32),
    price int,
    category varchar(16),
    description TEXT
);
create table User(
    id int auto_increment primary key,
    userName varchar(64),
    email varchar(32),
    userID int,
    hashCode_password long
);
