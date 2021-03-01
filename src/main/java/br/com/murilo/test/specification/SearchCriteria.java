package br.com.murilo.test.specification;

import java.io.Serializable;

import lombok.Data;

@Data
public class SearchCriteria implements Serializable {
	
	private static final long serialVersionUID = 4304312182365931951L;

	public SearchCriteria(LogicOperatorEnum logicOperator, String key, SearchOperationEnum operation, String valuePrefix, String value, String valueSuffix) {
		this.logicOperator = logicOperator;
		this.key = key;
		this.operation = operation;
		this.valuePrefix = valuePrefix;
		this.value = value;
		this.valueSuffix = valueSuffix;
	}
	
	private LogicOperatorEnum logicOperator;
	private String key;
	private SearchOperationEnum operation;
	private String valuePrefix;
	private String value;
	private String valueSuffix;
	
	public boolean isAndLogicOperator() {
		return LogicOperatorEnum.AND.equals(logicOperator);
	}
	
	public boolean isOrLogicOperator() {
		return LogicOperatorEnum.OR.equals(logicOperator);
	}
}
