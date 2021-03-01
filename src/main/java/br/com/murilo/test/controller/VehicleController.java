package br.com.murilo.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.murilo.test.dto.VehicleDto;
import br.com.murilo.test.dto.VehiclesByBrandDto;
import br.com.murilo.test.entity.Vehicle;
import br.com.murilo.test.mapper.VehicleMapper;
import br.com.murilo.test.mapper.VehiclesByBrandMapper;
import br.com.murilo.test.service.VehicleService;
import br.com.murilo.test.util.SpecificationsUtil;
import br.com.murilo.test.vo.VehiclesByDecadeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/vehicle")
@Api
@Slf4j
public class VehicleController {
	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private VehicleMapper vehicleMapper;
	
	@Autowired
	private VehiclesByBrandMapper vehiclesByBrandMapper;
	
	@GetMapping(name = "Get All Vehicles")
	@ApiOperation(value = "Get All Vehicles")
	public @ResponseBody ResponseEntity<List<VehicleDto>> getAll() {
		var vehicles = vehicleService.getRepository().findAll();
		return new ResponseEntity<>(vehicleMapper.toDto(vehicles), HttpStatus.OK);
	}
	
	@GetMapping(path = "/find", name = "Get All Vehicles")
	@ApiOperation(value = "Get All Groups")
	public @ResponseBody ResponseEntity<List<VehicleDto>> find(@RequestParam(value = "q") String searchQuery) {
		try {
			Specification<Vehicle> specification = SpecificationsUtil.build(searchQuery);
			var vehicles = vehicleService.getRepository().findAll(specification);
			return new ResponseEntity<>(vehicleMapper.toDto(vehicles), HttpStatus.OK);
		} catch (Exception e) {
			log.error("", e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(path = "/{id}", name = "Get Vehicle")
	@ApiOperation(value = "Get Vehicle")
	public @ResponseBody ResponseEntity<VehicleDto> get(@PathVariable("id") Long id) {
		var vehicle = vehicleService.getRepository().findById(id);
		return new ResponseEntity<>(vehicleMapper.toDto(vehicle.orElse(null)), HttpStatus.OK);
	}
	
	@PostMapping(name = "Create Vehicle")
	@ApiOperation(value = "Create Vehicle")
	@Transactional
	public @ResponseBody ResponseEntity<VehicleDto> create(@RequestBody VehicleDto input) {
		var vehicle = vehicleMapper.toEntity(input);
		vehicle = vehicleService.getRepository().save(vehicle);
		return new ResponseEntity<>(vehicleMapper.toDto(vehicle), HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}", name = "Full Update Vehicle")
	@ApiOperation(value = "Full Update Vehicle")
	@Transactional
	public @ResponseBody ResponseEntity<VehicleDto> fullUpdate(
			@PathVariable("id") Long id,
			@RequestBody VehicleDto input)
	{
		var vehicleOptional = vehicleService.getRepository().findById(id);
		if(vehicleOptional.isPresent()) {
			var vehicle = vehicleOptional.get();
			vehicleMapper.fullUpdateEntity(input, vehicle);
			vehicle = vehicleService.getRepository().save(vehicle);
			return new ResponseEntity<>(vehicleMapper.toDto(vehicle), HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PatchMapping(path = "/{id}", name = "Update Vehicle")
	@ApiOperation(value = "Update Vehicle")
	@Transactional
	public @ResponseBody ResponseEntity<VehicleDto> update(
			@PathVariable("id") Long id,
			@RequestBody VehicleDto input)
	{
		var vehicleOptional = vehicleService.getRepository().findById(id);
		if(vehicleOptional.isPresent()) {
			var vehicle = vehicleOptional.get();
			vehicleMapper.updateEntity(input, vehicle);
			vehicle = vehicleService.getRepository().save(vehicle);
			return new ResponseEntity<>(vehicleMapper.toDto(vehicle), HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(path = "/{id}", name = "Delete Vehicle")
	@ApiOperation(value = "Delete Vehicle")
	@Transactional
	public @ResponseBody ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		var vehicleOptional = vehicleService.getRepository().findById(id);
		if(vehicleOptional.isPresent()) {
			var vehicle = vehicleOptional.get();
			vehicleService.getRepository().delete(vehicle);
			return new ResponseEntity<>(HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(path = "/vehiclesByBrand", name = "Get Total Vehicles by Brand")
	@ApiOperation(value = "Get Total Vehicles by Brand")
	public @ResponseBody ResponseEntity<List<VehiclesByBrandDto>> getVehiclesByBrand() {
		var vehiclesByBrand = vehicleService.vehiclesByBrand();
		return new ResponseEntity<>(vehiclesByBrandMapper.toDto(vehiclesByBrand), HttpStatus.OK);
	}
	
	@GetMapping(path = "/vehiclesByDecade", name = "Get Total Vehicles by Decade")
	@ApiOperation(value = "Get Total Vehicles by Brand")
	public @ResponseBody ResponseEntity<List<VehiclesByDecadeVo>> getVehiclesByDecade() {
		var vehiclesByDecade = vehicleService.vehiclesByDecade();
		return new ResponseEntity<>(vehiclesByDecade, HttpStatus.OK);
	}
	
	@GetMapping(path = "/totalVehiclesLastWeek", name = "Get Total Vehicles Last Week")
	@ApiOperation(value = "Get Total Vehicles by Brand")
	public @ResponseBody ResponseEntity<Long> getTotalVehiclesLastWeek() {
		var totalVehiclesLastWeek = vehicleService.totalVehiclesLastWeek();
		return new ResponseEntity<>(totalVehiclesLastWeek, HttpStatus.OK);
	}
}
