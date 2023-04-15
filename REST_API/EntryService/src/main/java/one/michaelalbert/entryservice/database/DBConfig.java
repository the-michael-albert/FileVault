package one.michaelalbert.entryservice.database;

public class DBConfig {
    private String url = "jdbc:mariadb://localhost:3306/vault";
    private String user = "root";
    private String password = "password";

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
