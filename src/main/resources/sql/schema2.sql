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

CREATE TABLE  IF NOT EXISTS restaurant (
    rest_id integer PRIMARY KEY auto_increment,
    restname varchar(100),
    rest_address varchar(200),
    rest_contact varchar(200),
    maxSeat int default 0
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

-- 인원 분류 테이블
CREATE TABLE IF NOT EXISTS guests (
    guest_id int not null primary key,
    reserve_id INT,
    category VARCHAR(20),
    guest_count INT,
    FOREIGN KEY (reserve_id) REFERENCES reserve (reserve_id)
    );