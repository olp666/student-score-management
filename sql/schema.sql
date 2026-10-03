CREATE DATABASE IF NOT EXISTS db01
DEFAULT CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE db01;

CREATE TABLE IF NOT EXISTS student (
                                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                       name VARCHAR(50) NOT NULL,
    score INT NOT NULL
    );