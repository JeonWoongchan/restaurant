SET foreign_key_checks = 0;


CREATE TABLE IF NOT EXISTS Customer (
    customer_id INT PRIMARY KEY auto_increment,
    email VARCHAR(255),
    password VARCHAR(255),
    phone_number VARCHAR(20),
    point INT default 0,
    username VARCHAR(255)
);

-- Manager 테이블 삭제 및 생성

CREATE TABLE IF NOT EXISTS Manager (
    id INT PRIMARY KEY auto_increment,
    managerId varchar(100),
    password VARCHAR(255)
);




CREATE TABLE IF NOT EXISTS seat (
     seat_id integer PRIMARY KEY auto_increment,
     rest_id int(100),
     seat_number varchar(200),
     guest_count varchar(200),
     FOREIGN KEY (rest_id) REFERENCES restaurant(rest_id)
);

-- 예약 테이블
CREATE TABLE IF NOT EXISTS reserve (
    reserve_id INT PRIMARY KEY,
    customer_id INT,
    reserve_date DATE,
    start_date DATE,
    end_date DATE
);
CREATE TABLE IF NOT EXISTS reserveGuest (
     reserve_guest_id INT PRIMARY KEY,
     guest_id INT,
     reserve_date DATE,
     start_date DATE,
     end_date DATE
);
-- 인원 분류 테이블
CREATE TABLE IF NOT EXISTS Customer_count (
    count_id int not null primary key,
    reserve_guest_id INT,
    category_id INT,
    customer_count INT,
    FOREIGN KEY (reserve_guest_id) REFERENCES reserveGuest (reserve_guest_id),
    FOREIGN KEY (category_id) REFERENCES category (category_id)

);


CREATE TABLE IF NOT EXISTS Guest_count (
    guest_count_id int not null primary key,
    reserve_id INT,
    category_id INT,
    customer_count INT,
    FOREIGN KEY (reserve_id) REFERENCES reserve (reserve_id),
    FOREIGN KEY (category_id) REFERENCES category (category_id)

    );

CREATE table IF NOT EXISTS Guest (
    Guest_id int not null primary key,
    reserve_id INT,

    FOREIGN KEY (reserve_id) REFERENCES reserve (reserve_id)
);

create table  IF NOT EXISTS humancategory(
    category_id int not null primary key ,
    name varchar(30) not null

);


INSERT INTO humancategory
SELECT *
FROM (SELECT 1, '성인') AS tmp
WHERE NOT EXISTS (SELECT * FROM humancategory);

INSERT INTO humancategory
SELECT *
FROM (SELECT 2, '어린이') AS tmp
WHERE NOT EXISTS (SELECT * FROM humancategory);

INSERT INTO humancategory
SELECT *
FROM (SELECT 3, '유아') AS tmp
WHERE NOT EXISTS (SELECT * FROM humancategory);