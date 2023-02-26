package table;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import essentials.Essentials;

public class Table implements Serializable{
	
	private int rowSize;
	private String tableName;
	public List<row> rows = new LinkedList<row>();
	public List<values> values = new LinkedList<values>();
	
	public Table(int rowSize, String name) {
		this.rowSize = rowSize;
		tableName = name;
	}
	
	public void createRow(String rowName, boolean canBeBool) {
		row newRow = new row(rowName, canBeBool);
		rows.add(newRow);
	}
	
	public int getRowSize() {
		return rowSize;
	}
	
	public String getName() {
		return tableName;
	}
	
	public <T> void setName(T newName) {
		this.tableName = newName.toString();
	} 
	
	public void addEntry() {
		values newEntry = new values(this);  
		values.add(newEntry);
		
		newEntry.addEntry();
		Essentials.warn("New Entry Added !");
	}
	
	public String getLongestValue(int row) {
		String currentString = "";
		String longestString = "";
		for(values i:values) {
			currentString = i.getValue()[row];
			if(currentString.length() > longestString.length()) {
				longestString = currentString;
			}
		}
		return longestString;
	}
	
	public void changeLongestValues() {
		for(int i = 0; i < rows.size(); i++) {
			int longest = getLongestValue(i).length();
			if(longest > rows.get(i).getName().length()) {
				rows.get(i).setLongestColmn(longest);
			}else {
				rows.get(i).setLongestColmn(rows.get(i).getName());
			}
		}
	}
}
