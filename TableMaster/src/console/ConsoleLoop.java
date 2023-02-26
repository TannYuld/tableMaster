package console;

import java.util.LinkedList;
import java.util.List;

import essentials.Essentials;
import fileIO.FileStream;
import table.*;

public class ConsoleLoop {
	
	private static Table currentTable;
	private static List<Table> tableList;
	
	public static void StartLoop() {
		while(true) {
			Essentials.println("Enter your command");
			Essentials.print(ConsoleVars.prefix+"-> ");
			String ans = Essentials.askRawInput();
			
			ConsolePositions currentCommandPos = ConsolePositions.main;
			
			if(ConsoleVars.COMMAND_LIST.contains(ans)) {
				int inx = 0;
				
				if(ConsoleVars.MAIN_COMMAND_LIST.contains(ans)) {
					currentCommandPos = ConsolePositions.main;
					inx++;
				}
				if(ConsoleVars.TABLE_COMMAND_LIST.contains(ans)){
					currentCommandPos = ConsolePositions.table;
					inx++;
				}
				if(inx == 2) {
					currentCommandPos = ConsolePositions.both;
				}
			}else {
				Essentials.println();
				Essentials.warn("There are no commands such "+ans+" Try type help");
				continue;
			}
			
			if(currentCommandPos == ConsoleVars.ConsolePosition || currentCommandPos == ConsolePositions.both) {
				performCommand(ans);
			}else{
				Essentials.println();
				Essentials.warn("You can't perfrom this command here Try use exit-table or choose-table");
			}
		}
	}
	
	private static void performCommand(String ans) {
		switch(ans) {
			case "help":
				Essentials.println();
				help();
				Essentials.println();
				break;
				
			case "exit-table":
				setConsolePos(ConsolePositions.main);
				Essentials.println();
				currentTable = null;
				break;
				
			case "choose-table":
				currentTable = chooseTable();
				if(currentTable != null) {setConsolePos(ConsolePositions.table);}
				break;
				
			case "print-table":
				Essentials.println();
				Essentials.println();
				TableFactory.TableWriteConsole(currentTable);
				Essentials.println();
				Essentials.println();
				break;
			
			case "list-tables":
				listTables();
				break;
			
			case "delete-table":
				deleteTable();
				break;
				
			case "add-entry":
				addEntry();
				FileStream.SaveTable(currentTable.getName(), currentTable);
				break;
			
			case "remove-at":
				removeAt(currentTable);
				FileStream.SaveTable(currentTable.getName(), currentTable);
				break;
			
			case "change-at":
				changeAt(currentTable);
				FileStream.SaveTable(currentTable.getName(), currentTable);
				break;
				
			case "rename-table":
				renameTable(currentTable);
				FileStream.SaveTable(currentTable.getName(), currentTable);
				setConsolePos(ConsolePositions.table);
				break;
				
			case "rename-row":
				renameRow(currentTable);
				FileStream.SaveTable(currentTable.getName(), currentTable);
				break;
			
			case "find-at":
				findAt(currentTable);
				break;
			
			case "find-val":
				findVal(currentTable);
				break;
				
			case "create-table":
				createTable();
				if(currentTable != null) {
					setConsolePos(ConsolePositions.table);
					FileStream.SaveTable(currentTable.getName(), currentTable);
				}
				break;
		}
	}
	
	public static void setConsolePos(ConsolePositions newPos) {
		if(newPos == ConsolePositions.table) {
			ConsoleVars.prefix = currentTable.getName()+" ";
		}else {
			ConsoleVars.prefix = "";
		}
		ConsoleVars.ConsolePosition = newPos;
	}
	
	public static ConsolePositions getConsolePos() {
		return ConsoleVars.ConsolePosition;
	}
	
	private static void help()
	{
		Essentials.println("Command list");
		Essentials.println("------------");
		Essentials.println("create-table: Creates a new table");
		Essentials.println("choose-table: Chooses and enters a table from disk");
		Essentials.println("list-tables:  Lists the tables in the disk");
		Essentials.println("exit-table:   Leave the table that choosed");
		Essentials.println();
		Essentials.println("print-table:  Prints table in the console");
		Essentials.println("delete-table: Deletes the table in disk");
		Essentials.println("add-entry:    Add entries to the table");
		Essentials.println("remove-at:    Removes the entry of the desired index");
		Essentials.println("change-at:    Change the values of the desired index");
		Essentials.println("find-at:      Find and get the value of desired index");
		Essentials.println("find-val:     Find and get the every entry with desired value");
		Essentials.println("rename-row:   Rename desired row");
		Essentials.println("rename-table: Rename the selected table");
	}
	
	private static void listTables() {
		Essentials.println();
		Essentials.println("These are the tables in the disk");
		Essentials.makeLinesFor("These are the tables in the disk");
		Essentials.println();
		updateTableList();
		for(Table t:tableList) {
			Essentials.println("* "+t.getName()+" - "+t.getRowSize()+" rows - "+t.values.size()+" entries ");
		}
		Essentials.println();
	}
	
	private static void createTable(){
		Essentials.println();
		
		String tableName = "";
		
		while(true) {
			tableName = Essentials.askInput("Please enter name of the new table");
			
			if(checkTableName(tableName)) {
				break;
			}
			
			Essentials.warn("There are another table with same name");
		}

		Integer rowSize = Essentials.askInput("Please enter row size of the table",true);
			
		currentTable = TableFactory.CreatTable(rowSize, tableName);
	}
	
	private static Table chooseTable() {		
		updateTableList();
		
		if(tableList == null) {
			Essentials.warn("There are no tables  to create a one tpye create-table");
			return null;
		}
		
		Essentials.println();
		String ans = Essentials.askInput("What is the name of the table that you want to choose");
		
		for(Table table:tableList) {
			if(table.getName().equals(ans)) {
				return table;
			}
		}
		Essentials.warn("There are no tables named "+ans+"  You can type list-tables");
		return null;
	}
	
	private static void updateTableList() {
		tableList = null;
		tableList = FileStream.getTableList();
	}
	
	private static void deleteTable() {
		Essentials.println();
		if(Essentials.askInputBool("Are you sure you want to delete table")) {
			FileStream.RemoveTable(currentTable);
			currentTable = null;
			updateTableList();
			setConsolePos(ConsolePositions.main);
		}
	}
	
	private static void addEntry() {
		TableFactory.addTableEntry(currentTable);
		TableFactory.TableWriteConsole(currentTable);
		FileStream.SaveTable(currentTable.getName(), currentTable);
		Essentials.println();
		Essentials.println();
	}
	
	private static void removeAt(Table table) {
		Essentials.println();
		
		if(table.values.size() == 0) {
			Essentials.warn("There are no entries in this table");
			return;
		}
		
		int desiredIndex = Essentials.askInput("Which entry you want to remove [enter index number 1-"+table.values.size()+" ]", true);
		try {
			if(table.values.get(desiredIndex-1) != null) {
				table.values.remove(desiredIndex-1);
				table.changeLongestValues();
				Essentials.warn("Entry "+(desiredIndex)+" succesfuly removed");
			}
		}catch(Exception e) {
			Essentials.warn("Please type a valid index number");
			Essentials.println(e);
		}
	}
	
	private static void changeAt(Table table) {
		Essentials.println();
		
		if(table.values.size() == 0) {
			Essentials.warn("There are no entries in this table");
			return;
		}
		
		int desiredIndex = Essentials.askInput("Which entry you want to remove [enter index number 1-"+table.values.size()+" ]", true);
		try {
			if(table.values.get(desiredIndex-1) != null) {
				
				table.values.get(desiredIndex-1).changeValue(desiredIndex-1);
				
				table.changeLongestValues();
				Essentials.warn("Entry "+(desiredIndex)+" succesfuly changed");
			}
		}catch(Exception e) {
			Essentials.warn("Please type a valid index number");
			Essentials.println(e);
		}
	}
	
	private static void renameTable(Table table) {
		Essentials.println();
		String ans = "";
		
		while(true) {
			ans = Essentials.askInput("Type new table name");
			if(!ans.isEmpty() && !ans.isBlank()) {
				if(checkTableName(ans)) {
					break;
				}
				Essentials.warn("There are another table with same name");
				continue;
			}
			
			Essentials.warn("Invalid table name");
		}
		
		FileStream.RemoveTable(table);
		table.setName(ans);
		Essentials.warn("Table name succesfuly changed");
	}
	
	private static boolean checkTableName(String name) {
		updateTableList();
		
		List<String> nameList = new LinkedList<String>();
		
		for(Table t:tableList) {
			nameList.add(t.getName());
		}
		
		if(nameList.contains(name)) {
			return false;
		}else {
			return true;
		}
	}
	
	private static void renameRow(Table table) {
		List<String> rowNames = new LinkedList<String>();
		for(row r:table.rows) {
			rowNames.add(r.getName());
		}
		
		Essentials.println();
		Essentials.print("[  ");
		for(String s:rowNames) {
			Essentials.print(s+"  ");
		}
		Essentials.print("]");
		Essentials.println();
		Integer ansInt = Essentials.askInputWAns("Which row you want to rename", rowNames);
		
		String ans = "";
		while(true) {
			ans = Essentials.askInput("Type new name");
			
			if(!ans.isBlank() && !ans.isEmpty()) {
				if(!rowNames.contains(ans)) {
					break;
				}
				Essentials.warn("Row names can't be same");
				continue;
			}
			
			Essentials.warn("Row name can't be empty");
		}
		
		String oldName = table.rows.get(ansInt).getName();
		table.rows.get(ansInt).setName(ans);
		
		Essentials.warn("Name of row "+oldName+" succesfully changed to "+ans);
	}
	
	private static void findAt(Table table) {
		if(table.values.size() == 0) {
			Essentials.println();
			Essentials.warn("There are no entries to find");
			return;
		}
		
		int ans = 0;
		while(true) {
			ans = Essentials.askInput("Which index you want to get  [ 1-"+table.values.size()+" ]", true);
			ans--;
			if(ans >= 0 && ans < table.values.size()) {
				break;
			}
			
			Essentials.warn("Please type bewteen 1-"+table.values.size());
		}
		
		List<Integer> ansList = new LinkedList<Integer>();
		ansList.add(ans);
		TableFactory.TableWriteConsole(table, ansList);
		Essentials.println();
	}

	private static void findVal(Table table) {
		Essentials.println();
		
		List<String> rowList = new LinkedList<String>();
		List<Integer> choosedValues = new LinkedList<Integer>();
		
		for(row r:table.rows) {
			rowList.add(r.getName());
		}
		
		Essentials.print("[ ");
		for(String s:rowList) {
			Essentials.print(s+" ");
		}
		Essentials.print("]\n");
		
		int ansInt = Essentials.askInputWAns("Type row you want to search", rowList);
		
		String ans = Essentials.askInput("Type your value");
		
		int inx = 0;
		for(values val:table.values) {
			String value = val.getValue()[ansInt];
			
			if(value.equalsIgnoreCase(ans)) {
				choosedValues.add(inx);
			}
			
			inx++;
		}
		
		TableFactory.TableWriteConsole(table, choosedValues);
		Essentials.println();
	}
	
}
