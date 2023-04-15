import database.DetailEntity;
import database.FileEntity;
import database.MetadataEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {

        // Create configuration instance
        Configuration config = new Configuration()
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/vault")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "password")
                .addAnnotatedClass(MetadataEntity.class)
                .addAnnotatedClass(DetailEntity.class)
                .addAnnotatedClass(FileEntity.class);

        // Create session factory instance
        SessionFactory sessionFactory = config.buildSessionFactory();

        // Open a new session from the session factory
        Session session = sessionFactory.openSession();

        // Create new metadata object
        MetadataEntity metadata = new MetadataEntity((short) 1920, (short) 1080, 3600L, "abc123");
        metadata.setId(1L);

        // Create new detail object
        DetailEntity detail = new DetailEntity("abc123", "example.mp4");


        // Create new file object
        FileEntity file = new FileEntity("abc123", 2023, 4, 14, 1);
        file.setId(1L);


        // Begin transaction
        Transaction tx = session.beginTransaction();

        // Save metadata object
        session.save(metadata);

        // Save detail object
        session.save(detail);

        // Save file object
        session.save(file);

        // Commit transaction
        tx.commit();

        // Close session and session factory
        session.close();
        sessionFactory.close();
    }
}
