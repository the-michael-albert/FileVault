docker build -t fileserver .
docker run -p 3306:3306 --name entry-service -e MYSQL_ROOT_PASSWORD=password -d fileserver