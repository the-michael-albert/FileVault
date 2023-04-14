package database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;

public class HibernateUtil {



    private static final Configuration configuration = new Configuration()
            .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
            .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/vault")
            .setProperty("hibernate.connection.username", "username")
            .setProperty("hibernate.connection.password", "password")
            .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
            .addClass(FileEntity.class);

    private static final SessionFactory sessionFactory;

    static{
        try {
             sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static void shutDown(){
        //closes caches and connections
        getSessionFactory().close();
    }
}