package br.com.murilo.test.util;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.domain.Specification;

import br.com.murilo.test.specification.GenericSpecification;
import br.com.murilo.test.specification.SearchCriteria;

public class SpecificationsUtil {

	public static <T> Specification<T> build(String searchQuery) {
		var searchCriterias = SearchCriteriaUtil.process(searchQuery);
		return build(searchCriterias);
	}

	public static <T> Specification<T> build(List<SearchCriteria> searchCriterias) {
		Specification<T> specification = null;

		if (CollectionUtils.isNotEmpty(searchCriterias)) {
			for (var searchCriteria : searchCriterias) {
				if(specification == null) {
					specification = new GenericSpecification<>(searchCriteria);
				} else {
					specification = 
							searchCriteria.isAndLogicOperator() ?
									specification.and(new GenericSpecification<>(searchCriteria))
									: specification.or(new GenericSpecification<>(searchCriteria));
				} 
			}
		}

		return specification;
	}
}
