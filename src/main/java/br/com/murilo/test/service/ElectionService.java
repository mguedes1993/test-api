package br.com.murilo.test.service;

import java.math.BigDecimal;
import java.math.MathContext;

import org.springframework.stereotype.Service;

import br.com.murilo.test.vo.ElectionResultVo;
import br.com.murilo.test.vo.ElectionVo;

@Service
public class ElectionService {
	public ElectionResultVo processElection(ElectionVo election) {
		var electionResult = new ElectionResultVo();
		
		var percentValidVotes =
				new BigDecimal(election.getTotalValidVotes()).divide(new BigDecimal(election.getTotalVoters()), MathContext.DECIMAL128);
		var percentWhiteVotes =
				new BigDecimal(election.getTotalWhiteVotes()).divide(new BigDecimal(election.getTotalVoters()), MathContext.DECIMAL128);
		var percentNullVotes =
				new BigDecimal(election.getTotalNullVotes()).divide(new BigDecimal(election.getTotalVoters()), MathContext.DECIMAL128);
		
		electionResult.setPercentValidVotes(percentValidVotes);
		electionResult.setPercentWhiteVotes(percentWhiteVotes);
		electionResult.setPercentNullVotes(percentNullVotes);
		
		return electionResult;
	}
}
