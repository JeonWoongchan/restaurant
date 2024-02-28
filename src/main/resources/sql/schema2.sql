SET foreign_key_checks = 0;


-- Manager 테이블 삭제 및 생성

CREATE TABLE IF NOT EXISTS Manager (
    id INT PRIMARY KEY auto_increment,
    managerId varchar(100),
    password VARCHAR(255)
    );



CREATE TABLE IF NOT EXISTS Customer (
    customer_id INT PRIMARY KEY auto_increment,
    email VARCHAR(255),
    password VARCHAR(255),
    phone_number VARCHAR(20),
    point INT default 0,
    username VARCHAR(255)
);

-- 예약 테이블
CREATE TABLE IF NOT EXISTS reserve (
    reserve_id INT PRIMARY KEY,
    customer_id INT,
    reg_date DATE,
    reserve_date DATE,
    end_date DATE,
    comment VARCHAR(4000)
    );




CREATE TABLE IF NOT EXISTS Customer_count (
  count_id int primary key auto_increment,
  reserve_id INT,
  adults_count INT,
  children_count INT,
  infants_count INT,

  FOREIGN KEY (reserve_id) REFERENCES reserve (reserve_id)


    );




CREATE TABLE IF NOT EXISTS seat (
     seat_id integer PRIMARY KEY auto_increment,
     rest_id int(100),
     seat_number varchar(200),
     guest_count varchar(200),
     FOREIGN KEY (rest_id) REFERENCES restaurant(rest_id)
);



CREATE table IF NOT EXISTS Guest (
 Guest_id int not null primary key,
 reserve_guest_id INT,
 phoneNum VARCHAR(100) not null unique,
 FOREIGN KEY (reserve_guest_id) REFERENCES reserveGuest (reserve_guest_id)
);





CREATE TABLE IF NOT EXISTS reserveGuest (
     reserve_guest_id INT PRIMARY KEY,
     guest_id INT,
     reg_date DATE,
     reserve_date DATE,
     end_date DATE,
     comment VARCHAR(4000)
);
-- 인원 분류 테이블



CREATE TABLE IF NOT EXISTS Guest_count (
    guest_count_id int primary key,
    reserve_id INT,
    adults_count INT,
    children_count INT,
    infants_count INT,
    FOREIGN KEY (reserve_id) REFERENCES reserve (reserve_id)

    );



