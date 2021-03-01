package br.com.murilo.test.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class VehicleDto {
	@JsonProperty(value = "id")
	private Long id;
	@JsonProperty(value = "created")
    private Date created;
	@JsonProperty(value = "updated")
    private Date updated;
	@JsonProperty(value = "year")
	private Integer year;
	@JsonProperty(value = "description")
	private String description;
	@JsonProperty(value = "sold")
	private Boolean sold;
	@JsonProperty(value = "model")
	private ModelDto model;
}
