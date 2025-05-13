CREATE DATABASE IF NOT EXISTS `test-db`
DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE `test-db`;

CREATE TABLE IF NOT EXISTS `restaurant` (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    address VARCHAR(255),
    phone_number VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS `menu` (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    price DOUBLE,
    description VARCHAR(255),
    restaurant_id BIGINT,
    
    FOREIGN KEY (restaurant_id) REFERENCES `restaurant`(id)
);

SELECT * FROM restaurant;
SELECT * FROM menu;