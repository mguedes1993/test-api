package br.com.murilo.test.dto;

import java.math.BigInteger;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MultipleDto {
	@JsonProperty(value = "sumMultiples")
	private BigInteger sumMultiples;
	@JsonProperty(value = "multiples")
	private List<BigInteger> multiples;
}
