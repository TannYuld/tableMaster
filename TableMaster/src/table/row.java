package table;

import java.io.Serializable;

public class row implements Serializable{
	
	public final boolean CanBeEmpty;
	
	private String RowName = "";
	private String value = null;
	
	private int longestColmnInRow;
	
	public row(String rowName, boolean canBeEmpty) {
		CanBeEmpty = canBeEmpty;
		RowName = rowName;
		longestColmnInRow = RowName.length();
	}
	
	public void setName(String newName) {
		RowName = newName;
	}
	
	public String getName() {
		return RowName;
	}
	
	public void setValue(String val) {
		value = val;
	}
	
	public String getValue() {
		return value;
	}
	
	protected void setLongestColmn(String val) {
		this.longestColmnInRow = val.length();
	}
	
	protected void setLongestColmn(int val) {
		this.longestColmnInRow = val;
	}
	
	public int getLongestColmn() {
		return this.longestColmnInRow;
	}
}
