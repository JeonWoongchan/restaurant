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