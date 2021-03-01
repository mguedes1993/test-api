package br.com.murilo.test.vo;

import lombok.Data;

@Data
public class ElectionVo {
	private int totalVoters;
	private int totalValidVotes;
	private int totalWhiteVotes;
	private int totalNullVotes;
}
