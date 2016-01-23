package domain;

public class EnumerationValue extends Entity {

	private int intKey;
	private String stringKey;
	private String value;
	private String enumerationName;
	
	
	public int getIntKey() {
		return intKey;
	}
	public void setIntKey(int intKey) {
		this.intKey = intKey;
	}
	public String getStringKey() {
		return stringKey;
	}
	public void setStringKey(String stringKey) {
		this.stringKey = stringKey;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getEnumerationName() {
		return enumerationName;
	}
	public void setEnumerationName(String enumerationName) {
		this.enumerationName = enumerationName;
	}
	
	
}