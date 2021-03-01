package br.com.murilo.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ElectionDto {
	@JsonProperty(value = "totalVoters")
	private int totalVoters;
	@JsonProperty(value = "totalValidVotes")
	private int totalValidVotes;
	@JsonProperty(value = "totalWhiteVotes")
	private int totalWhiteVotes;
	@JsonProperty(value = "totalNullVotes")
	private int totalNullVotes;
}
