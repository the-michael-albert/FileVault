CREATE DATABASE IF NOT EXISTS vault;

USE vault;

CREATE TABLE IF NOT EXISTS detail (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    resource_id CHAR(40),
    file_name VARCHAR(255),
    thumb_path TEXT,
    permissions JSON
);

CREATE TABLE IF NOT EXISTS metadata (
    id BIGINT PRIMARY KEY,
    width SMALLINT,
    height SMALLINT,
    duration BIGINT,
    hashtext CHAR(32),
    detail_id BIGINT,
    resource_id CHAR(40),
    FOREIGN KEY (detail_id) REFERENCES detail(id)
);


CREATE TABLE IF NOT EXISTS file (
    id BIGINT PRIMARY KEY,
    parent BIGINT,
    type TINYINT,
    detail_id BIGINT,
    meta_id BIGINT,
    resource_id CHAR(40),
    FOREIGN KEY (detail_id) REFERENCES detail(id),
    FOREIGN KEY (meta_id) REFERENCES metadata(id)
);
