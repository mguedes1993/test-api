package br.com.murilo.test.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import br.com.murilo.test.specification.LogicOperatorEnum;
import br.com.murilo.test.specification.SearchCriteria;
import br.com.murilo.test.specification.SearchOperationEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SearchCriteriaUtil {
	private static final int LOGIC_OPERATOR = 1;
	private static final int KEY = 2;
	private static final int OPERATION = 3;
	private static final int PREFIX = 4;
	private static final int VALUE = 5;
	private static final int SUFFIX = 6;
	private static final String NULL = "NULL";
	private static final String ASTERISK = "*";
	private static final String PERCENT = "%";
	
	private static final Pattern PATTERN = Pattern.compile("(|AND|OR)\\((\\w+?)(" + String.join("|", SearchOperationEnum.stringValues()) + ")(\\*?)([a-zA-Z0-9_ \\p{Punct}]*?)(\\*?)\\)", Pattern.UNICODE_CHARACTER_CLASS);
	
	public static List<SearchCriteria> process(String searchQuery) {
		var criterias = new ArrayList<SearchCriteria>();
		
		try {
			var matcher = PATTERN.matcher(searchQuery);
			while (matcher.find()) {
				log.info(matcher.group(0) + " | " + matcher.group(LOGIC_OPERATOR) + " | " + matcher.group(KEY) + " | " + matcher.group(OPERATION) + " | " + matcher.group(PREFIX) + " | " + matcher.group(VALUE) + " | " + matcher.group(SUFFIX));
				
				var operation = SearchOperationEnum.fromValue(matcher.group(OPERATION));
				var value = matcher.group(VALUE);
				if(NULL.equalsIgnoreCase(value) && (operation.equals(SearchOperationEnum.EQUAL) || operation.equals(SearchOperationEnum.NOT_EQUAL))) {
					if(operation.equals(SearchOperationEnum.EQUAL)) {
						operation = SearchOperationEnum.IS_NULL;
					} else {
						operation = SearchOperationEnum.IS_NOT_NULL;
					}
				}
				
				var prefix = StringUtils.replace(matcher.group(PREFIX), ASTERISK, PERCENT);
				var suffix = StringUtils.replace(matcher.group(SUFFIX), ASTERISK, PERCENT);
				
				var criteria = new SearchCriteria(
						LogicOperatorEnum.fromValue(matcher.group(LOGIC_OPERATOR)),
						matcher.group(KEY),
						operation,
						prefix,
						value,
						suffix);
				
				criterias.add(criteria);
	        }
		} catch (Exception e) {
			log.error("", e);
		}
		
		return criterias;
	}
}
