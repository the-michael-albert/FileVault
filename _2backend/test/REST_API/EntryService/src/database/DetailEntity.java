package database;

import javax.persistence.*;

@Entity
@Table(name = "detail")
public class DetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "resource_id", unique = true, length = 40)
    private String resourceId;

    @Column(name = "file_name", length = 255)
    private String fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id", referencedColumnName = "resource_id", nullable = false, insertable = false, updatable = false)
    private MetadataEntity metadata;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private MetadataEntity metadata2;

    public DetailEntity() {}

    public DetailEntity(String resourceId, String fileName) {
        this.resourceId = resourceId;
        this.fileName = fileName;
    }

    // getters and setters

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

    public MetadataEntity getMetadata2() {
        return metadata2;
    }

    public void setMetadata2(MetadataEntity metadata2) {
        this.metadata2 = metadata2;
    }
}
