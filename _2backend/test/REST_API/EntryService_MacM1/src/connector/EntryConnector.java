package connector;

import database.DetailEntity;
import database.FileEntity;
import database.MetadataEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Random;

public class EntryConnector {

    public static void send(){
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
        long id = 2L;
        String resourceID = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < 40; i++) {
            resourceID += alphabet.charAt(new Random().nextInt(alphabet.length()));
        }

        // Create new metadata object
        MetadataEntity metadata = new MetadataEntity((short) 1920, (short) 1080, 3600L, resourceID);
        // Create new detail object
        DetailEntity detail = new DetailEntity(metadata.getResourceId(), "example.mp4");
        detail.setId(metadata.getId());


        // Create new file object
        FileEntity file = new FileEntity(detail.getResourceId(), 2023, 4, 14, 1);
        file.setId(detail.getId());


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
