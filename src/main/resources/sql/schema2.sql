SET foreign_key_checks = 0;


-- Manager 테이블 삭제 및 생성

CREATE TABLE IF NOT EXISTS Manager (
    id INT PRIMARY KEY auto_increment,
    managerId varchar(100),
    password VARCHAR(255)
    );



-- Customer 테이블 생성
CREATE TABLE IF NOT EXISTS Customer (
    customer_id INT PRIMARY KEY auto_increment,
    email VARCHAR(255),
    password VARCHAR(255),
    phone VARCHAR(20),
    point INT default 0,
    username VARCHAR(255)
    );

-- 예약 테이블 생성
CREATE TABLE IF NOT EXISTS reserve (
    reserve_id INT PRIMARY KEY auto_increment,
    customer_id INT,
    reg_date DATETIME,
    reserve_date DATETIME,
    end_date DATETIME,
    adults_count INT,
    children_count INT,
    infants_count INT,
    comment VARCHAR(4000),
    FOREIGN KEY (customer_id) REFERENCES Customer (customer_id)
    );


-- Guest 테이블 생성
CREATE TABLE IF NOT EXISTS Guest (
     Guest_id INT NOT NULL PRIMARY KEY auto_increment,
     name  VARCHAR(100) NOT NULL,
     phone VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS GReserve (
    reserve_guest_id INT PRIMARY KEY auto_increment,
    guest_id INT,
    reg_date DATETIME,
    reserve_date DATETIME,
    end_date DATETIME,
    adults_count INT,
    children_count INT,
    infants_count INT,
    comment VARCHAR(4000)
    FOREIGN KEY (guest_id) REFERENCES guest (guest_id)
    );
-- 인원 분류 테이블

CREATE TABLE IF NOT EXISTS  capacity (
    id int not null primary key,
    hour_time time not null,
    capacity int not null
);

# INSERT INTO capacity (id, hour_time,capacity)
# VALUES
#     (1, '11:00',50),
#     (2, '12:00',50),
#     (3, '13:00',50),
#     (4, '14:00',50),
#     (5, '15:00',50),
#     (6, '16:00',50),
#     (7, '17:00',50),
#     (8, '18:00',50),
#     (9, '19:00',50),
#     (10, '20:00',50);






