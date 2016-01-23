package repositories;

import java.util.List;

import domain.EnumerationValue;
import domain.User;
import domain.UserRoles;

public interface IEnumerationValueRepository extends IRepository<EnumerationValue> {
	
	public List<EnumerationValue> withName(String name);
	public List<EnumerationValue> withValue(String value);
	public List<EnumerationValue> withStringKey(String key);

}