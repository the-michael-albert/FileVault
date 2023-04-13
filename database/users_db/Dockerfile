FROM mariadb:latest

# Set environment variables
ENV MYSQL_ROOT_PASSWORD=password

# Copy the create_tables.sql file to the container
COPY init.sql /docker-entrypoint-initdb.d/
