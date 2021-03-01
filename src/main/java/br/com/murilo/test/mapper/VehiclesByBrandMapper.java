package br.com.murilo.test.mapper;

import org.mapstruct.Mapper;

import br.com.murilo.test.abstraction.BaseMapper;
import br.com.murilo.test.dto.VehiclesByBrandDto;
import br.com.murilo.test.vo.VehiclesByBrandVo;

@Mapper(componentModel = "spring")
public interface VehiclesByBrandMapper extends BaseMapper<VehiclesByBrandVo, VehiclesByBrandDto> {
	
	VehiclesByBrandVo toEntity(VehiclesByBrandDto vehiclesByBrandDto);
	
	VehiclesByBrandDto toDto(VehiclesByBrandVo vehiclesByBrandVo);
}
