package database;

import javax.persistence.*;

@Entity
@Table(name = "metadata")
public class MetadataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "width")
    private Short width;

    @Column(name = "height")
    private Short height;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "resource_id", unique = true, length = 40)
    private String resourceId;

    public MetadataEntity() {}

    public MetadataEntity(Short width, Short height, Long duration, String resourceId) {
        this.width = width;
        this.height = height;
        this.duration = duration;
        this.resourceId = resourceId;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getWidth() {
        return width;
    }

    public void setWidth(Short width) {
        this.width = width;
    }

    public Short getHeight() {
        return height;
    }

    public void setHeight(Short height) {
        this.height = height;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
