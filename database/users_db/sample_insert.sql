INSERT INTO detail (id, resource_id, file_name, thumb_path, permissions)
VALUES (NULL, 'file_resource_id', 'file_name.txt', '/path/to/thumbnail', '{"read": "true", "write": "false"}');

SET @detail_id = LAST_INSERT_ID();

INSERT INTO metadata (id, width, height, duration, hashtext, detail_id, resource_id)
VALUES (@detail_id, 800, 600, 120, 'abc123', @detail_id, 'file_resource_id');

INSERT INTO file (id, parent, type, detail_id, meta_id, resource_id)
VALUES (@detail_id, 1, 2, @detail_id, LAST_INSERT_ID(), 'file_resource_id');
