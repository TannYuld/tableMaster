package console;

import java.util.List;

public class ConsoleVars {
	
	public static String prefix = "";
	public static String suffix = "";
	
	public static ConsolePositions ConsolePosition = ConsolePositions.main;
	
	public final static List<String> COMMAND_LIST = List.of(
			"help",          //*
			"create-table",  //*
			"choose-table",  //*
			"list-tables",   //*
			"exit-table",    //*
			
			"delete-table",  //*
			"print-table",   //*
			"add-entry",	 //*
			"remove-at",	 //*
			"change-at",	 //*
			"find-at",		 //*
			"find-val",      //*
			"rename-row",	 //*
			"rename-table"   //*
	);
	
	public final static List<String> MAIN_COMMAND_LIST = List.of(
			"help",
			"create-table",
			"list-tables",
			"choose-table"
	);
	
	public final static List<String> TABLE_COMMAND_LIST = List.of(
			"help",          
			"exit-table",
			"delete-table",
			"add-entry",
			"print-table",
			"remove-at",
			"change-at",
			"change-on",
			"find-at",
			"find-val",
			"rename-row",
			"rename-table"
	);
}
