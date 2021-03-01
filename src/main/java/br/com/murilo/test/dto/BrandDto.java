package br.com.murilo.test.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BrandDto {
	@JsonProperty(value = "id")
    private Long id;
	@JsonProperty(value = "created")
    private Date created;
	@JsonProperty(value = "updated")
    private Date updated;
	@JsonProperty(value = "name")
    private String name;
	@JsonProperty(value = "models")
    private List<ModelDto> models;
}
