package br.com.murilo.test.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ElectionResultDto {
	private BigDecimal percentValidVotes;
	private BigDecimal percentWhiteVotes;
	private BigDecimal percentNullVotes;
}
