package br.com.murilo.test.abstraction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.mapstruct.MappingTarget;

public interface BaseMapper<E, D> {
	
	E toEntity(D dto);
    
    D toDto(E entity);
	
	E updateEntity(D dto, @MappingTarget E entity);
	
	E fullUpdateEntity(D dto, @MappingTarget E entity);
	
	List<E> toEntity(List<D> dto);
    
    List<D> toDto(List<E> entity);
    
    default boolean equalityComparison(D dto, E entity) {
    	if(dto == null || entity == null) {
    		return false;
    	}
    	
    	return entity.equals(toEntity(dto));
    }
    
    default Collection<E> updateEntity(Collection<D> dtos, @MappingTarget Collection<E> entities){
    	var entitiesToAddUpdate = new ArrayList<E>();
    	
    	for (D dto : dtos) {
			var entity = entities.stream().filter(e -> equalityComparison(dto, e)).findFirst();
			if(entity.isPresent()) {
				entitiesToAddUpdate.add(updateEntity(dto, entity.get()));
			} else {
				entity = entitiesToAddUpdate.stream().filter(e -> equalityComparison(dto, e)).findFirst();
				if(!entity.isPresent()) {
					entitiesToAddUpdate.add(toEntity(dto));
				}
			}
		}
    	
    	entities.clear();
    	entities.addAll(entitiesToAddUpdate);
    	
    	return entities;
    }
    
    default List<E> updateEntity(List<D> dtos, @MappingTarget List<E> entities){
    	return (List<E>) updateEntity((Collection<D>) dtos, (Collection<E>) entities);
    }
    
    default Set<E> updateEntity(List<D> dtos, @MappingTarget Set<E> entities){
    	return (Set<E>) updateEntity((Collection<D>) dtos, (Collection<E>) entities);
    }
}
