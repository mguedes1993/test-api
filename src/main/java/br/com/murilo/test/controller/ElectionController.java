package br.com.murilo.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.murilo.test.dto.ElectionDto;
import br.com.murilo.test.dto.ElectionResultDto;
import br.com.murilo.test.mapper.ElectionMapper;
import br.com.murilo.test.mapper.ElectionResultMapper;
import br.com.murilo.test.service.ElectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/election")
@Api
public class ElectionController {
	
	@Autowired
	private ElectionService electionService;
	
	@Autowired
	private ElectionMapper electionMapper;
	
	@Autowired
	private ElectionResultMapper electionResultMapper;
	
	@PostMapping(name = "Process Election Result")
	@ApiOperation(value = "Process Election Result")
	public @ResponseBody ResponseEntity<ElectionResultDto> processElectionResult(@RequestBody ElectionDto electionDto) {
		
		if(electionDto.getTotalVoters() != 0) {
			var electionResult = electionService.processElection(electionMapper.toEntity(electionDto));
			return new ResponseEntity<>(electionResultMapper.toDto(electionResult), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
