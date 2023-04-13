DROP DATABASE vault;
CREATE DATABASE IF NOT EXISTS vault;

USE vault;

CREATE TABLE IF NOT EXISTS file (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    resource_id CHAR(40) UNIQUE,
    date_year SMALLINT,
    date_month TINYINT,
    date_day TINYINT,
    file_type TINYINT
);

CREATE TABLE IF NOT EXISTS metadata (
    id BIGINT PRIMARY KEY,
    width SMALLINT,
    height SMALLINT,
    duration BIGINT,
    resource_id CHAR(40) UNIQUE,
    FOREIGN KEY (id) REFERENCES file(id),
    FOREIGN KEY (resource_id) REFERENCES file(resource_id)
);


CREATE TABLE IF NOT EXISTS detail (
    id BIGINT PRIMARY KEY,
    resource_id CHAR(40) UNIQUE,
    file_name VARCHAR(255),
    FOREIGN KEY (resource_id) REFERENCES file(resource_id)
);
