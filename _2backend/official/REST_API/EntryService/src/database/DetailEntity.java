package database;

import javax.persistence.*;

@Entity
@Table(name = "detail")
public class DetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "resource_id", unique = true, nullable = false)
    private String resourceId;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private MetadataEntity metadata;

    public DetailEntity() {
    }

    public DetailEntity(String resourceId, String fileName) {
        this.resourceId = resourceId;
        this.fileName = fileName;
    }

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public MetadataEntity getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataEntity metadata) {
        this.metadata = metadata;
    }
}
