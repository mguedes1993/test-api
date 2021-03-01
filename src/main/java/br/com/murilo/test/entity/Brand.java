package br.com.murilo.test.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.StringUtils;

import br.com.murilo.test.abstraction.BaseAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "TB_BRANDS", uniqueConstraints = { @UniqueConstraint(columnNames = { "NAME" }) })
@Data
@EqualsAndHashCode(callSuper = true)
public class Brand extends BaseAuditedEntity {
    @Column(name = "NAME", nullable = false, length = 200)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "brand")
    private List<Model> models;

    @PrePersist
    @PreUpdate
    private void prepare() {
        setName(StringUtils.upperCase(getName()));
    }
}
