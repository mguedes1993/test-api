package br.com.murilo.test.mapper;

import org.mapstruct.Mapper;

import br.com.murilo.test.abstraction.BaseMapper;
import br.com.murilo.test.dto.ElectionDto;
import br.com.murilo.test.vo.ElectionVo;

@Mapper(componentModel = "spring")
public interface ElectionMapper extends BaseMapper<ElectionVo, ElectionDto> {
	
	ElectionVo toEntity(ElectionDto electionDto);
	
	ElectionDto toDto(ElectionVo electionVo);
}
