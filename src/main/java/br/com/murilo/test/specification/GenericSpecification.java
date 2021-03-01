package br.com.murilo.test.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class GenericSpecification<T> implements Specification<T> {

    private static final long serialVersionUID = 1408214346069554712L;

    private SearchCriteria searchCriteria;

    public GenericSpecification(SearchCriteria searchCriteria) {
    	this.searchCriteria = searchCriteria;
    }
    
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    	Predicate predicate = null;
    	
    	switch (searchCriteria.getOperation()) {
		case EQUAL:
			predicate = criteriaBuilder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue());
			break;
		case NOT_EQUAL:
			predicate = criteriaBuilder.notEqual(root.get(searchCriteria.getKey()), searchCriteria.getValue());
			break;
		case GREATER_THAN:
			predicate = criteriaBuilder.greaterThan(root.get(searchCriteria.getKey()), searchCriteria.getValue());
			break;
		case LESS_THAN:
			predicate = criteriaBuilder.lessThan(root.get(searchCriteria.getKey()), searchCriteria.getValue());
			break;
		case LIKE:
			var value = new StringBuilder();
			value.append(searchCriteria.getValuePrefix());
			value.append(searchCriteria.getValue());
			value.append(searchCriteria.getValueSuffix());
			predicate = criteriaBuilder.like(root.get(searchCriteria.getKey()), value.toString());
			break;
		case IS_NULL:
			predicate = criteriaBuilder.isNull(root.get(searchCriteria.getKey()));
			break;
		case IS_NOT_NULL:
			predicate = criteriaBuilder.isNotNull(root.get(searchCriteria.getKey()));
			break;
		default:
			predicate = null;
			break;
		}
		
		return predicate;
    }
}
