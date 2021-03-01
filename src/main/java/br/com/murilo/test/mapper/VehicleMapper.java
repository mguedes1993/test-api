package br.com.murilo.test.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.com.murilo.test.abstraction.BaseMapper;
import br.com.murilo.test.dto.VehicleDto;
import br.com.murilo.test.entity.Vehicle;

@Mapper(componentModel = "spring", uses = {BrandModelMapper.class, BrandMapper.class}, disableSubMappingMethodsGeneration = true)
public interface VehicleMapper extends BaseMapper<Vehicle, VehicleDto> {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "created", ignore = true)
	@Mapping(target = "updated", ignore = true)
	@Mapping(source = "model.id", target = "model.id")
	@Mapping(target = "model.brand", ignore = true)
	@Mapping(target = "model.vehicles", ignore = true)
	Vehicle toEntity(VehicleDto vehicleDto);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "created", ignore = true)
	@Mapping(target = "updated", ignore = true)
	@Mapping(source = "year", target = "year", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
	@Mapping(source = "description", target = "description", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
	@Mapping(source = "sold", target = "sold", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
	@Mapping(source = "model.id", target = "model.id")
	@Mapping(target = "model.brand", ignore = true)
	@Mapping(target = "model.vehicles", ignore = true)
	Vehicle updateEntity(VehicleDto vehicleDto, @MappingTarget Vehicle vehicle);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "created", ignore = true)
	@Mapping(target = "updated", ignore = true)
	@Mapping(source = "model.id", target = "model.id")
	@Mapping(target = "model.brand", ignore = true)
	@Mapping(target = "model.vehicles", ignore = true)
	Vehicle fullUpdateEntity(VehicleDto vehicleDto, @MappingTarget Vehicle vehicle);
	
	@InheritInverseConfiguration(name = "toEntity")
	@Mapping(source = "id", target = "id")
	@Mapping(source = "created", target = "created")
	@Mapping(source = "updated", target = "updated")
	@Mapping(source = "model", target = "model")
	@Mapping(source = "model.brand", target = "model.brand")
	VehicleDto toDto(Vehicle vehicle);
}
