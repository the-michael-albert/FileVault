# DROP DATABASE vault;
CREATE DATABASE vault;
USE vault;

CREATE TABLE meta (
  id bigint PRIMARY KEY,
  width smallint,
  height smallint,
  hashtext char(32),
  size bigint,
  duration bigint,
  image_name varchar(255),
  thumb_path text
);

CREATE TABLE file_table (
    id BIGINT PRIMARY KEY,
    file_type SMALLINT,
    parent_id BIGINT,
    meta_id bigint,
	FOREIGN KEY (meta_id) REFERENCES meta(id)
);