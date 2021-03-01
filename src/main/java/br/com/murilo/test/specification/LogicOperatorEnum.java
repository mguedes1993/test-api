package br.com.murilo.test.specification;

public enum LogicOperatorEnum {
	AND("AND"),
	OR("OR");
	
	private String logicOperator;
	
	LogicOperatorEnum(String logicOperator) {
		this.logicOperator = logicOperator;
	}
	
	public String getLogicOperator() {
		return this.logicOperator;
	}
	
	public static LogicOperatorEnum fromValue(String value) {
		var result = LogicOperatorEnum.AND;
		
		for (var operator : LogicOperatorEnum.values()) {
			if(operator.logicOperator.equalsIgnoreCase(value)) {
				result = operator;
				break;
			}
		}
		
		return result;
	}
}
