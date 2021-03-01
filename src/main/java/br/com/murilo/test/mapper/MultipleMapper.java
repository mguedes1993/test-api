package br.com.murilo.test.mapper;

import org.mapstruct.Mapper;

import br.com.murilo.test.abstraction.BaseMapper;
import br.com.murilo.test.dto.MultipleDto;
import br.com.murilo.test.vo.MultipleVo;

@Mapper(componentModel = "spring")
public interface MultipleMapper extends BaseMapper<MultipleVo, MultipleDto> {
	MultipleVo toEntity(MultipleDto multipleDto);
	
	MultipleDto toDto(MultipleVo multipleVo);
}
