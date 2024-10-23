package common.base;import jakarta.persistence.*;import java.util.Date;@MappedSuperclasspublic class BaseModel {    @Id    @GeneratedValue(strategy = GenerationType.AUTO)    @Column(name = "id")    protected Long id;    @Column(name = "created_at")    @Temporal(TemporalType.TIMESTAMP)    protected Date createdAt;    @Column(name = "created_by")    protected Date createdBy;    @Column(name = "updated_at")    @Temporal(TemporalType.TIMESTAMP)    protected Date updatedAt;    @Column(name = "updated_by")    protected Date updatedBy;    public Long getId() {        return id;    }    public void setId(Long id) {        this.id = id;    }    public Date getCreatedAt() {        return createdAt;    }    public void setCreatedAt(Date createdAt) {        this.createdAt = createdAt;    }    public Date getCreatedBy() {        return createdBy;    }    public void setCreatedBy(Date createdBy) {        this.createdBy = createdBy;    }    public Date getUpdatedAt() {        return updatedAt;    }    public void setUpdatedAt(Date updatedAt) {        this.updatedAt = updatedAt;    }    public Date getUpdatedBy() {        return updatedBy;    }    public void setUpdatedBy(Date updatedBy) {        this.updatedBy = updatedBy;    }    @PrePersist    public void setCreatedAt(){        this.createdAt = new Date();    }    @PreUpdate    public void setUpdatedAt(){        this.updatedAt = new Date();    }}