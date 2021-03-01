package br.com.murilo.test.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.murilo.test.dto.MultipleDto;
import br.com.murilo.test.mapper.MultipleMapper;
import br.com.murilo.test.service.MultipleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/multiple")
@Api
public class MultipleController {
	@Autowired
	private MultipleService multipleService;
	
	@Autowired
	private MultipleMapper multipleMapper;
	
	@GetMapping(name = "Process 3 and 5 multiples")
	@ApiOperation(value = "Process 3 and 5 multiples")
	public @ResponseBody ResponseEntity<MultipleDto> processMultiples3And5(@RequestParam(name = "value") BigInteger value) {
		if(value != null && value.longValue() >= 0) {
			var multiple = multipleService.process3And5Multiple(value);
			return new ResponseEntity<>(multipleMapper.toDto(multiple), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
