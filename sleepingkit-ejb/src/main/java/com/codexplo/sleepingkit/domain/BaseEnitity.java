package com.codexplo.sleepingkit.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Users: Bazlur Rahman Rokon
 * Date: 2/7/13
 * Time: 3:53 PM
 */
@MappedSuperclass
public abstract class BaseEnitity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLasteUpdated;

    @Version
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateLasteUpdated() {
        return dateLasteUpdated;
    }

    public void setDateLasteUpdated(Date dateLasteUpdated) {
        this.dateLasteUpdated = dateLasteUpdated;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @PreUpdate
    void preUpdate() {
        setDateLasteUpdated(new Date());
    }

    @PrePersist
    void prePersist() {
        setDateCreated(new Date());
    }
}
