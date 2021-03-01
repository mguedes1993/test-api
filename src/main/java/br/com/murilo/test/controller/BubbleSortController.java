package br.com.murilo.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.murilo.test.service.BubbleSortService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/bubbleSort")
@Api
public class BubbleSortController {
	
	@Autowired
	private BubbleSortService bubbleSortService;
	
	@PostMapping(name = "Process Election Result")
	@ApiOperation(value = "Process Election Result")
	public @ResponseBody ResponseEntity<int[]> processElectionResult(@RequestBody int[] values) {
		
		var sortedValues = bubbleSortService.sort(values);
		
		return new ResponseEntity<>(sortedValues, HttpStatus.OK);
	}
}
