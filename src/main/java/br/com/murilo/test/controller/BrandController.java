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

import br.com.murilo.test.dto.BrandDto;
import br.com.murilo.test.entity.Brand;
import br.com.murilo.test.mapper.BrandMapper;
import br.com.murilo.test.service.BrandService;
import br.com.murilo.test.util.SpecificationsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/brand")
@Api
@Slf4j
public class BrandController {
	@Autowired
	private BrandService brandService;

	@Autowired
	private BrandMapper brandMapper;

	@GetMapping(name = "Get All Brands")
	@ApiOperation(value = "Get All Brands")
	public @ResponseBody ResponseEntity<List<BrandDto>> getAll() {
		var vehicles = brandService.getRepository().findAll();
		return new ResponseEntity<>(brandMapper.toDto(vehicles), HttpStatus.OK);
	}
	
	@GetMapping(path = "/find", name = "Get All Brands")
	@ApiOperation(value = "Get All Brands")
	public @ResponseBody ResponseEntity<List<BrandDto>> find(@RequestParam(value = "q") String searchQuery) {
		try {
			Specification<Brand> specification = SpecificationsUtil.build(searchQuery);
			var brands = brandService.getRepository().findAll(specification);
			return new ResponseEntity<>(brandMapper.toDto(brands), HttpStatus.OK);
		} catch (Exception e) {
			log.error("", e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(path = "/{id}", name = "Get Brand")
	@ApiOperation(value = "Get Brand")
	public @ResponseBody ResponseEntity<BrandDto> get(@PathVariable("id") Long id) {
		var brand = brandService.getRepository().findById(id);
		return new ResponseEntity<>(brandMapper.toDto(brand.orElse(null)), HttpStatus.OK);
	}
	
	@PostMapping(name = "Create Brand")
	@ApiOperation(value = "Create Brand")
	@Transactional
	public @ResponseBody ResponseEntity<BrandDto> create(@RequestBody BrandDto input) {
		try {
			var brand = brandMapper.toEntity(input);
			brand = brandService.getRepository().save(brand);
			return new ResponseEntity<>(brandMapper.toDto(brand), HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("", e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(path = "/{id}", name = "Full Update Brand")
	@ApiOperation(value = "Full Update Brand")
	@Transactional
	public @ResponseBody ResponseEntity<BrandDto> fullUpdate(
			@PathVariable("id") Long id,
			@RequestBody BrandDto input)
	{
		var brandOptional = brandService.getRepository().findById(id);
		if(brandOptional.isPresent()) {
			var brand = brandOptional.get();
			brandMapper.fullUpdateEntity(input, brand);
			brand = brandService.getRepository().save(brand);
			return new ResponseEntity<>(brandMapper.toDto(brand), HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PatchMapping(path = "/{id}", name = "Update Brand")
	@ApiOperation(value = "Update Brand")
	@Transactional
	public @ResponseBody ResponseEntity<BrandDto> update(
			@PathVariable("id") Long id,
			@RequestBody BrandDto input)
	{
		var brandOptional = brandService.getRepository().findById(id);
		if(brandOptional.isPresent()) {
			var brand = brandOptional.get();
			brandMapper.updateEntity(input, brand);
			brand = brandService.getRepository().save(brand);
			return new ResponseEntity<>(brandMapper.toDto(brand), HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(path = "/{id}", name = "Delete Brand")
	@ApiOperation(value = "Delete Brand")
	@Transactional
	public @ResponseBody ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		var brandOptional = brandService.getRepository().findById(id);
		if(brandOptional.isPresent()) {
			var brand = brandOptional.get();
			brandService.getRepository().delete(brand);
			return new ResponseEntity<>(HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
