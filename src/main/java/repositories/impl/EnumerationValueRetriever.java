package repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import domain.EnumerationValue;

public class EnumerationValueRetriever implements IEntityRetriever<EnumerationValue> {

	@Override
	public EnumerationValue build(ResultSet rs) throws SQLException {
		
		EnumerationValue result = new EnumerationValue();
		result.setId(rs.getInt("id"));
		result.setIntKey(rs.getInt("intkey"));
		result.setStringKey(rs.getString("stringkey"));
		result.setValue(rs.getString("value"));
		result.setEnumerationName(rs.getString("enumerationName"));
		return result;
	}

}