package br.com.murilo.test.vo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ElectionResultVo {
	private BigDecimal percentValidVotes;
	private BigDecimal percentWhiteVotes;
	private BigDecimal percentNullVotes;
}
