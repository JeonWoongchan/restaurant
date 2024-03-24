SET foreign_key_checks = 0;

use restaurant;

DROP TABLE IF EXISTS Customer;
CREATE TABLE Customer (
    customer_id INT PRIMARY KEY auto_increment,
    email VARCHAR(255),
    password VARCHAR(255),
    phone_number VARCHAR(20),
    point INT default 0,
    username VARCHAR(255)
);

-- Manager 테이블 삭제 및 생성
DROP TABLE IF EXISTS Manager;
CREATE TABLE Manager (
    id INT PRIMARY KEY auto_increment,
    managerId varchar(100),
    password VARCHAR(255)
);
DROP TABLE IF EXISTS restaurant;
CREATE TABLE restaurant (
    rest_id integer PRIMARY KEY auto_increment,
    restname varchar(100),
    rest_address varchar(200),
    rest_contact varchar(200),
    maxSeat int default 0
);


DROP TABLE IF EXISTS seat;
CREATE TABLE seat (
     seat_id integer PRIMARY KEY auto_increment,
     rest_id int(100),
     seat_number varchar(200),
     guest_count varchar(200),
     FOREIGN KEY (rest_id) REFERENCES restaurant(rest_id)
);