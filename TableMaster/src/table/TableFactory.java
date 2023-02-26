package table;
import java.util.List;

import essentials.Essentials;

public class TableFactory {
	
	public static Table CreatTable(int rowSize, String tableName) {
		Table myTable = new Table(rowSize, tableName);
		
		for(int i = 0; i < rowSize; i++) {
			String name = Essentials.askInput((i+1)+".row name");
			boolean canBeEmpty = Essentials.askInputBool("Can row be empty:");
			myTable.createRow(name, canBeEmpty);
		}
		
		Essentials.warn("Your table "+myTable.getName()+" succesfully created !");
		
		return myTable;
	}
	
	public static void TableWriteConsole(Table table) {
		Essentials.makeSpaceFor(4);
		Essentials.println("Table: "+table.getName());
		Essentials.makeSpaceFor(4);
		Essentials.makeLinesFor("Table: "+table.getName());
		
		Essentials.println();
		Essentials.println();
		Essentials.makeSpaceFor(4);
		
		Essentials.print("idx");
		Essentials.makeSpaceFor(4);
		
		for(int i = 0; i < table.getRowSize(); i++) {
			Essentials.print(table.rows.get(i).getName());
			if(table.getLongestValue(i).length() > table.rows.get(i).getName().length()) {Essentials.makeSpaceFor(table.getLongestValue(i).length()-table.rows.get(i).getName().length());}
			Essentials.makeSpaceFor(4);
		}
		Essentials.println();
		Essentials.makeSpaceFor(4);
		
		Essentials.makeLinesFor("idx");
		
		
		
		for(int i = 0; i < table.getRowSize(); i++) {
			Essentials.makeSpaceFor(4);
			if(table.getLongestValue(i).length() < table.rows.get(i).getName().length()) 
			{
				if(table.rows.get(i).getName().length() < 3) {
					Essentials.makeLinesFor("---");
				}else {
					Essentials.makeLinesFor(table.rows.get(i).getName());
				}
			}else {
				Essentials.makeLinesFor(table.getLongestValue(i));
			}
		}
		Essentials.println();
		if(table.values.size() != 0) {
			getTableValues(table);
		}
	}
	
	public static void TableWriteConsole(Table table, List<Integer> index) {
		Essentials.makeSpaceFor(4);
		Essentials.println("Table: "+table.getName());
		Essentials.makeSpaceFor(4);
		Essentials.makeLinesFor("Table: "+table.getName());
		
		Essentials.println();
		Essentials.println();
		Essentials.makeSpaceFor(4);
		
		Essentials.print("idx");
		Essentials.makeSpaceFor(4);
		
		for(int i = 0; i < table.getRowSize(); i++) {
			Essentials.print(table.rows.get(i).getName());
			if(table.getLongestValue(i).length() > table.rows.get(i).getName().length()) {Essentials.makeSpaceFor(table.getLongestValue(i).length()-table.rows.get(i).getName().length());}
			Essentials.makeSpaceFor(4);
		}
		Essentials.println();
		Essentials.makeSpaceFor(4);
		
		Essentials.makeLinesFor("idx");
		
		for(int i = 0; i < table.getRowSize(); i++) {
			Essentials.makeSpaceFor(4);
			if(table.getLongestValue(i).length() < table.rows.get(i).getName().length()) 
			{
				if(table.rows.get(i).getName().length() < 3) {
					Essentials.makeLinesFor("---");
				}else {
					Essentials.makeLinesFor(table.rows.get(i).getName());
				}
			}else {
				Essentials.makeLinesFor(table.getLongestValue(i));
			}
		}
		Essentials.println();
		if(table.values.size() != 0) {
			getTableValues(table,index);
		}
	} 
	
	
	private static void getTableValues(Table table) {
		int inx = 1;
		
		for(values i: table.values) {
			Essentials.makeSpaceFor(4);
			Essentials.print(inx);
			if("inx".length() > String.valueOf(table.getRowSize()).length()) {
				Essentials.makeSpaceFor(6);
			}else {
				Essentials.makeSpaceFor(String.valueOf(table.getRowSize()).length());
			}
			
			int prevValWidth = 0;
			int lineWidth = 0;
			
			int currentRow = 0;
			for(String s: i.getValue()) {
				if(table.rows.get(currentRow).getLongestColmn() > table.rows.get(currentRow).getName().length()) {
					Essentials.print(s);
					Essentials.makeSpaceFor((table.rows.get(currentRow).getLongestColmn()-s.length())+4);
				}else {
					Essentials.print(s);
					prevValWidth = s.length();
					lineWidth = table.rows.get(currentRow).getName().length();
					int x = (lineWidth-prevValWidth)+4;
					//Essentials.warn(x);
					Essentials.makeSpaceFor(x);
				}
				currentRow++;
			}
			
			Essentials.println();
			inx++;
		}
	}
	
	private static void getTableValues(Table table, List<Integer> index) {
		int inx = 1;
		int indexControler = 0;
		for(values i: table.values) {
			if(index.contains(indexControler)) 
			{
				Essentials.makeSpaceFor(4);
				Essentials.print(inx);
				if("inx".length() > String.valueOf(table.getRowSize()).length()) {
					Essentials.makeSpaceFor(6);
				}else {
					Essentials.makeSpaceFor(String.valueOf(table.getRowSize()).length());
				}
				
				int prevValWidth = 0;
				int lineWidth = 0;
				
				int currentRow = 0;
				for(String s: i.getValue()) {
					if(table.rows.get(currentRow).getLongestColmn() > table.rows.get(currentRow).getName().length()) {
						Essentials.print(s);
						Essentials.makeSpaceFor((table.rows.get(currentRow).getLongestColmn()-s.length())+4);
					}else {
						Essentials.print(s);
						prevValWidth = s.length();
						lineWidth = table.rows.get(currentRow).getName().length();
						int x = (lineWidth-prevValWidth)+4;
						//Essentials.warn(x);
						Essentials.makeSpaceFor(x);
					}
					currentRow++;
				}
				
				Essentials.println();
				inx++;
			}
			indexControler++;
		}
	}

	
	public static void addTableEntry(Table table) {
		table.addEntry();
	}
}
