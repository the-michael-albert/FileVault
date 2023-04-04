#!/bin/bash

# Get the current date and time
DATE=$(date +"%Y-%m-%d_%H-%M-%S")

# Create a backup file in the /backups directory
mysqldump -u root -ppassword file > /backups/file_$DATE.sql
mysqldump -u root -ppassword meta > /backups/meta_$DATE.sql

# Copy the backup file to the backup volume
cp /backups/file_$DATE.sql /backup-volume/
cp /backups/meta_$DATE.sql /backup-volume/
