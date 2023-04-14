import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;


import database.DetailEntity;
import database.FileEntity;
import database.HibernateUtil;
import database.MetadataEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UploadFile {

    public static void main(String[] args) {
        String resourceID = "";
        int id = 0;
        DetailEntity detailEntity = new DetailEntity();
        MetadataEntity metadataEntity = new MetadataEntity();
        FileEntity fileEntity = new FileEntity();


        detailEntity.setId((long) id);
        metadataEntity.setId((long) id);
        fileEntity.setId((long) id);


        metadataEntity.setResourceId(resourceID);
        detailEntity.setResourceId(resourceID);
        fileEntity.setResourceId(resourceID);


        // create a new instance of FileEntity and set its properties
        fileEntity.setDateYear((short) Calendar.getInstance().get(Calendar.YEAR));
        fileEntity.setDateMonth((byte) (Calendar.getInstance().get(Calendar.MONTH) + 1));
        fileEntity.setDateDay((byte) Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        fileEntity.setFileType((byte) 12);

        // create a new instance of MetadataEntity and set its properties
        metadataEntity.setWidth((short) 800);
        metadataEntity.setHeight((short) 600);
        metadataEntity.setDuration((long)(60));

        detailEntity.setFileName("testFile");


        // call the insertIntoTables() method to insert the entities into the database
        insertIntoTables(fileEntity, metadataEntity, detailEntity);
    }

    private static byte[] readFileContents(File file) {
        byte[] data = null;
        try (FileInputStream fis = new FileInputStream(file)) {
            data = new byte[(int) file.length()];
            fis.read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static void insertIntoTables(FileEntity fileEntity, MetadataEntity metadataEntity, DetailEntity detailEntity) {

        // save all entities to the database
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(metadataEntity);
            session.save(detailEntity);
            fileEntity.setId(metadataEntity.getId());
            fileEntity.setResourceId(metadataEntity.getResourceId());
            session.save(fileEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
