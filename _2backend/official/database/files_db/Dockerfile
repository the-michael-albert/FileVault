FROM mariadb:latest

# Set environment variables
ENV MYSQL_ROOT_PASSWORD=password
ENV MYSQL_DATABASE1=file
ENV MYSQL_DATABASE2=meta

# Copy the create_tables.sql file to the container
COPY ./init.sql /docker-entrypoint-initdb.d/
