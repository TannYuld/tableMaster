package table;
import java.io.Serializable;
import java.util.List;

import essentials.Essentials;

public class values implements Serializable {
	
	private final int ROW_SIZE;
	private String[] valueList;
	private Table table;
	private static int valueIndex;
	
	public values(Table table) {
		ROW_SIZE = table.rows.size();
		this.table = table;
		valueList = new String[ROW_SIZE];
		valueIndex = table.values.size()+1;
	}
	
	public void addEntry() {
		valueList[0] = ""+valueIndex;
		int i = 0;
		String ans;
		Essentials.println();
		for(row r:table.rows) {
			while(true) {
				ans = Essentials.askInputWithoutSpaceCanEmpty(r.getName()+" :");
				if(ans.isBlank() && !r.CanBeEmpty) {
					Essentials.warn("These row has to be value !");
				}else {
					break;
				}
			}
			valueList[i] = ans;
			i++;
			
			if(ans.length() > r.getLongestColmn()) {
				r.setLongestColmn(ans.length());
			}
		}
	}
	
	public void changeValue(int index) {
		for(int i = 0; i < table.rows.size(); i++){
			while(true) {
				String s = Essentials.askInputWithoutSpaceCanEmpty(
							table.rows.get(i).getName()+"  old["+valueList[i]+"]"); 
				
				if(!table.rows.get(i).CanBeEmpty && (s.isBlank() || s.isEmpty())) {
					Essentials.warn("These value can't be empty");
				}else {
					if(s.isBlank() || s.isEmpty()) {
						valueList[i] = "";
						Essentials.println("GOOD");
						break;
					}else {
						valueList[i] = s;
						Essentials.println("GOOD");
						break;
					}
				}
			}
		}
	}
	
	public String[] getValue() {
		return valueList;
	}
}
