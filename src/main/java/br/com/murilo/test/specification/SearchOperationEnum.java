package br.com.murilo.test.specification;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum SearchOperationEnum {
	EQUAL(":"),
	NOT_EQUAL("!"),
	GREATER_THAN(">"),
	LESS_THAN("<"),
	LIKE("~"),
	IS_NULL(null),
	IS_NOT_NULL(null);
	
	private String operation;
	
	SearchOperationEnum(String operation) {
		this.operation = operation;
	}
	
	public String getOperation() {
		return this.operation;
	}
	
	public static List<String> stringValues(){
		return Arrays.stream(SearchOperationEnum.values())
				.filter(o -> o.getOperation() != null)
				.map(SearchOperationEnum::getOperation)
				.collect(Collectors.toList());
	}
	
	public static SearchOperationEnum fromValue(String value) {
		var result = SearchOperationEnum.EQUAL;
		
		for (var o : SearchOperationEnum.values()) {
			if(o.getOperation().equalsIgnoreCase(value)) {
				result = o;
				break;
			}
		}
		
		return result;
	}
}
