-- DROP DATABASE vault;
CREATE DATABASE IF NOT EXISTS vault;

USE vault;

CREATE TABLE IF NOT EXISTS metadata (
    id BIGINT PRIMARY KEY unique auto_increment,
    width SMALLINT,
    height SMALLINT,
    duration BIGINT,
    resource_id CHAR(40) unique
);

CREATE TABLE IF NOT EXISTS detail (
    id BIGINT PRIMARY KEY unique auto_increment,
    resource_id CHAR(40) unique,
    file_name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS file (
    id BIGINT PRIMARY KEY AUTO_INCREMENT unique,
    resource_id CHAR(40) UNIQUE,
    date_year SMALLINT,
    date_month TINYINT,
    date_day TINYINT,
    file_type TINYINT
);
