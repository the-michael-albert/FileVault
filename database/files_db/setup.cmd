docker build -t fileserver .
docker run -p 3306:3306 --name my-mariadb-container -e MYSQL_ROOT_PASSWORD=password -d fileserver