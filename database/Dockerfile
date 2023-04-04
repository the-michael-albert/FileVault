FROM mariadb:latest

# Set environment variables
ENV MYSQL_ROOT_PASSWORD=password
ENV MYSQL_DATABASE1=file
ENV MYSQL_DATABASE2=meta

# Copy the create_tables.sql file to the container
COPY init.sql /docker-entrypoint-initdb.d/

# Install the cron package
RUN apt-get update && apt-get install -y cron

# Copy the backup script to the container
COPY backup.sh /backup.sh

# Make the backup script executable
RUN chmod +x /backup.sh

# Set up a cron job to run the backup script
# EVERY MINUTE
RUN echo "* * * * * /backup.sh" > /etc/cron.d/backup

# EVERY HOUR
# RUN echo "0 * * * * /backup.sh" > /etc/cron.d/backup

# Start cron
CMD ["cron", "-f"]
