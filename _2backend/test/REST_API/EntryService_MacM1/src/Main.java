import connector.EntryConnector;
import database.DetailEntity;
import database.FileEntity;
import database.MetadataEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {
        EntryConnector.send();

    }
//    public static void

}
