DROP DATABASE vault;
CREATE DATABASE IF NOT EXISTS vault;

USE vault;

CREATE TABLE IF NOT EXISTS metadata (
    id BIGINT PRIMARY KEY unique,
    width SMALLINT,
    height SMALLINT,
    duration BIGINT,
    resource_id CHAR(40) unique
);

CREATE TABLE IF NOT EXISTS detail (
    id BIGINT PRIMARY KEY unique,
    resource_id CHAR(40) unique,
    file_name VARCHAR(255),
    FOREIGN KEY (resource_id) REFERENCES metadata(resource_id),
    FOREIGN KEY (id) REFERENCES metadata(id)
);

CREATE TABLE IF NOT EXISTS file (
    id BIGINT PRIMARY KEY AUTO_INCREMENT unique,
    resource_id CHAR(40) UNIQUE,
    date_year SMALLINT,
    date_month TINYINT,
    date_day TINYINT,
    file_type TINYINT,
    FOREIGN KEY (id) REFERENCES metadata(id),
    FOREIGN KEY (id) REFERENCES detail(id),
    FOREIGN KEY (resource_id) REFERENCES metadata(resource_id),
    FOREIGN KEY (resource_id) REFERENCES detail(resource_id)
);
