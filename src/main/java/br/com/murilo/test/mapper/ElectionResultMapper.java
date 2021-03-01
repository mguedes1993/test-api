package br.com.murilo.test.mapper;

import org.mapstruct.Mapper;

import br.com.murilo.test.abstraction.BaseMapper;
import br.com.murilo.test.dto.ElectionResultDto;
import br.com.murilo.test.vo.ElectionResultVo;

@Mapper(componentModel = "spring")
public interface ElectionResultMapper extends BaseMapper<ElectionResultVo, ElectionResultDto> {
	
	ElectionResultVo toEntity(ElectionResultDto electionResultDto);
	
	ElectionResultDto toDto(ElectionResultVo electionResultVo);
}
