package br.com.murilo.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.murilo.test.abstraction.BaseAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "TB_VEHICLES")
@Data
@EqualsAndHashCode(callSuper = true)
public class Vehicle extends BaseAuditedEntity {
	@Column(name = "YEAR", nullable = false)
	private Integer year;

	@Lob
	@Column(name = "DESCRIPTION", columnDefinition = "TEXT", nullable = true)
	private String description;
	
	@Column(name = "SOLD", nullable = false)
	private Boolean sold = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODEL_ID", nullable = false)
	private Model model;
}
