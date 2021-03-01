package br.com.murilo.test.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.com.murilo.test.abstraction.BaseMapper;
import br.com.murilo.test.dto.BrandDto;
import br.com.murilo.test.entity.Brand;

@Mapper(componentModel = "spring", uses = BrandModelMapper.class, collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE)
public interface BrandMapper extends BaseMapper<Brand, BrandDto> {
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "created", ignore = true)
	@Mapping(target = "updated", ignore = true)
	Brand toEntity(BrandDto brandDto);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "created", ignore = true)
	@Mapping(target = "updated", ignore = true)
	@Mapping(source = "name", target = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
	@Mapping(source = "models", target = "models", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
	Brand updateEntity(BrandDto brandDto, @MappingTarget Brand brand);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "created", ignore = true)
	@Mapping(target = "updated", ignore = true)
	Brand fullUpdateEntity(BrandDto brandDto, @MappingTarget Brand brand);

	@InheritInverseConfiguration(name = "toEntity")
	@Mapping(source = "id", target = "id")
	@Mapping(source = "created", target = "created")
	@Mapping(source = "updated", target = "updated")
	BrandDto toDto(Brand brand);

	@AfterMapping
	public default void initializeModels(@MappingTarget Brand brand) {
		if (brand.getModels() != null && !brand.getModels().isEmpty()) {
			brand.getModels().forEach(model -> model.setBrand(brand));
		}
	}
}
