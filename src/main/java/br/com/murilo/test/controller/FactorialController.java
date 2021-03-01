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

import br.com.murilo.test.service.FactorialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/factorial")
@Api
public class FactorialController {
	@Autowired
	private FactorialService factorialService;
	
	@GetMapping(name = "Process Factorial")
	@ApiOperation(value = "Process Factorial")
	public @ResponseBody ResponseEntity<BigInteger> processFactorial(@RequestParam(name = "value") BigInteger value) {
		if(value != null && value.longValue() >= 0) {
			return new ResponseEntity<>(factorialService.processFactorial(value), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
