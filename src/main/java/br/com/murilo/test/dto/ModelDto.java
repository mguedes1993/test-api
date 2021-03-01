package br.com.murilo.test.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ModelDto {
	@JsonProperty(value = "id")
    private Long id;
	@JsonProperty(value = "created")
    private Date created;
	@JsonProperty(value = "updated")
    private Date updated;
	@JsonProperty(value = "name")
    private String name;
	@JsonProperty(value = "brand")
    private BrandDto brand;
}
