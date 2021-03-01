package br.com.murilo.test.service;

import java.math.BigInteger;

import org.springframework.stereotype.Service;

@Service
public class FactorialService {
	public BigInteger processFactorial(BigInteger value) {
		if(BigInteger.ZERO.equals(value)) {
			return BigInteger.ONE;
		}
		
		return value.multiply(processFactorial(value.subtract(BigInteger.ONE)));
	}
}
