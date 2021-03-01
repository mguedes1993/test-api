package br.com.murilo.test.vo;

import java.math.BigInteger;
import java.util.List;

import lombok.Data;

@Data
public class MultipleVo {	
	private BigInteger sumMultiples = BigInteger.ZERO;
	private List<BigInteger> multiples;
}
