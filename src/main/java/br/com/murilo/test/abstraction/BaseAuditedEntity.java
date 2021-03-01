package br.com.murilo.test.abstraction;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseAuditedEntity extends BaseEntity {
    @Column(name = "CREATED", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "UPDATED", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @PrePersist
    protected void onPrePersist() {
        setCreated(new Date());
        setUpdated(new Date());
    }

    @PreUpdate
    protected void onPreUpdate() {
        setUpdated(new Date());
    }
}
