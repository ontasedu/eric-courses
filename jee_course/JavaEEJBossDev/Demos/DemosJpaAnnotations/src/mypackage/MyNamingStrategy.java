package mypackage;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.util.StringHelper;

public class MyNamingStrategy extends ImprovedNamingStrategy {

	@Override
	public String tableName(String tableName) {
		return "TAB_" + tableName;
	}

	@Override
	public String classToTableName(String className) {
		return StringHelper.unqualify(className);
	}

	@Override
	public String columnName(String columnName) {
		return columnName;
	}

	@Override
	public String propertyToColumnName(String propertyName) {
		return "COL_" + propertyName;
	}
}
