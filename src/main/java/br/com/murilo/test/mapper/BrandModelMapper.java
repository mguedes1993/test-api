package br.com.murilo.test.mapper;

import java.util.Objects;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.com.murilo.test.abstraction.BaseMapper;
import br.com.murilo.test.dto.ModelDto;
import br.com.murilo.test.entity.Model;

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE, disableSubMappingMethodsGeneration = true)
public interface BrandModelMapper extends BaseMapper<Model, ModelDto> {
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "created", ignore = true)
	@Mapping(target = "updated", ignore = true)
	@Mapping(target = "brand", ignore = true)
	@Mapping(target = "vehicles", ignore = true)
	Model toEntity(ModelDto modelDto);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "created", ignore = true)
	@Mapping(target = "updated", ignore = true)
	@Mapping(target = "brand", ignore = true)
	@Mapping(target = "vehicles", ignore = true)
	@Mapping(source = "name", target = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
	Model updateEntity(ModelDto modelDto, @MappingTarget Model model);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "created", ignore = true)
	@Mapping(target = "updated", ignore = true)
	@Mapping(target = "brand", ignore = true)
	@Mapping(target = "vehicles", ignore = true)
	Model fullUpdateEntity(ModelDto modelDto, @MappingTarget Model model);
	
	@InheritInverseConfiguration(name = "toEntity")
	@Mapping(source = "id", target = "id")
	@Mapping(source = "created", target = "created")
	@Mapping(source = "updated", target = "updated")
	@Mapping(target = "brand", ignore = true)
	ModelDto toDto(Model model);
	
	@Override
	default boolean equalityComparison(ModelDto modelDto, Model model) {
		if(
				modelDto != null && modelDto.getId() != null && modelDto.getId() != 0
				&& model != null && model.getId() != null && model.getId() != 0)
		{
			return Objects.equals(modelDto.getId(), model.getId());
		}
		
		return false;
	}
}
