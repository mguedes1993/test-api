package br.com.murilo.test.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import br.com.murilo.test.vo.MultipleVo;

@Service
public class MultipleService {
	
	private static final BigInteger[] DIVIDERS_3_5 = {BigInteger.valueOf(3l), BigInteger.valueOf(5l)};
	
	public MultipleVo process3And5Multiple(BigInteger value) {
		return processMultiple(value, DIVIDERS_3_5);
	}
	
	private MultipleVo processMultiple(BigInteger value, BigInteger... dividers) {
		if(value != null && value.longValue() >= 0 && dividers != null && dividers.length > 0) {
			var localDividers = Arrays.copyOf(dividers, dividers.length);
			Arrays.sort(localDividers);
			var multiple = new MultipleVo();
			multiple.setMultiples(new ArrayList<>());
			for (var i = localDividers[0].longValue(); i <= value.longValue(); i++) {
				var isMultiple = false;
				var bigI = BigInteger.valueOf(i);
				for (var localDivider : localDividers) {					
					if(isMultiple(bigI, localDivider)) {
						isMultiple = true;
						break;
					}
				}
				if(isMultiple) {
					multiple.setSumMultiples(multiple.getSumMultiples().add(bigI));
					multiple.getMultiples().add(bigI);
				}
			}
			
			return multiple;
		}
		
		return null;
	}
	
	private boolean isMultiple(BigInteger value, BigInteger divisor) {		
		return value.mod(divisor).equals(BigInteger.ZERO);
	}
}
