package br.com.murilo.test.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TB_MODELS", uniqueConstraints = { @UniqueConstraint(columnNames = { "NAME" }) })
@Data
@EqualsAndHashCode(callSuper = true)
public class Model extends BaseAuditedEntity {
    @Column(name = "NAME", nullable = false, length = 200)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRAND_ID", nullable = false)
    private Brand brand;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "model")
    private List<Vehicle> vehicles;

    @PrePersist
    @PreUpdate
    private void prepare() {
        setName(StringUtils.upperCase(getName()));
    }
}
