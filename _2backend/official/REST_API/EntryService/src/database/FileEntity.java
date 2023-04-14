package database;


import javax.persistence.*;
import org.hibernate.*;


@Entity
@Table(name = "file")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "resource_id", unique = true)
    private String resourceId;

    @Column(name = "date_year")
    private Short dateYear;

    @Column(name = "date_month")
    private Byte dateMonth;

    @Column(name = "date_day")
    private Byte dateDay;

    @Column(name = "file_type")
    private Byte fileType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private MetadataEntity metadata;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private DetailEntity detail;

    // constructor, getters, and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Short getDateYear() {
        return dateYear;
    }

    public void setDateYear(Short dateYear) {
        this.dateYear = dateYear;
    }

    public Byte getDateMonth() {
        return dateMonth;
    }

    public void setDateMonth(Byte dateMonth) {
        this.dateMonth = dateMonth;
    }

    public Byte getDateDay() {
        return dateDay;
    }

    public void setDateDay(Byte dateDay) {
        this.dateDay = dateDay;
    }

    public Byte getFileType() {
        return fileType;
    }

    public void setFileType(Byte fileType) {
        this.fileType = fileType;
    }

    public MetadataEntity getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataEntity metadata) {
        this.metadata = metadata;
    }

    public DetailEntity getDetail() {
        return detail;
    }

    public void setDetail(DetailEntity detail) {
        this.detail = detail;
    }

    public void insertIntoTables() {
        // create new entities for each table
        MetadataEntity metadata = new MetadataEntity();
        DetailEntity detail = new DetailEntity();

        // set values for metadata entity
        metadata.setWidth((short) 1080);
        metadata.setHeight((short) 1080);
        metadata.setDuration(4200L);
        metadata.setResourceId(this.resourceId);

        // set values for detail entity
        detail.setResourceId(this.resourceId);
        detail.setFileName("bigfile.jpg");

        // set values for file entity
        this.metadata = metadata;
        this.detail = detail;

        // save all entities to the database
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            session.save(metadata);
            session.save(detail);
            session.save(this);

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
