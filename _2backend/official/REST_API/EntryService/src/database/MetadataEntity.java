package database;

import javax.persistence.*;

@Entity
@Table(name = "metadata")
public class MetadataEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "width")
    private Short width;

    @Column(name = "height")
    private Short height;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "resource_id", unique = true, nullable = false)
    private String resourceId;

    @OneToOne(mappedBy = "metadata")
    private DetailEntity detail;

    public MetadataEntity() {
    }

    public MetadataEntity(Long id, String resourceId) {
        this.id = id;
        this.resourceId = resourceId;
    }

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

    public DetailEntity getDetail() {
        return detail;
    }

    public void setDetail(DetailEntity detail) {
        this.detail = detail;
    }
}
