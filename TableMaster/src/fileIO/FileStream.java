package fileIO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import javax.xml.stream.events.Characters;

import essentials.Essentials;
import table.Table;
import table.row;

public class FileStream {
	
	public static void SaveTable(String name, Table table) {
		File file = new File(name);
		
		SaveData(file, table);
	}
	
	public static Table LoadTable(String name) {
		File file = new File(name);
		
		return LoadData(file);
	}
	
	private static void SaveData(File file, Table table) {
		try {
			java.io.File f = new java.io.File(file.getPath());
			f.getParentFile().mkdirs();
			
			if (!f.exists()) {
				f.createNewFile();
			}
			
			FileOutputStream fos = new FileOutputStream(f.getPath());
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			
			oos.writeObject(table);
			oos.close();
			
		}catch(Exception e) {
			Essentials.warn(e);
		}
	}
	
	private static Table LoadData(File file) {
		try {
			FileInputStream fis = new FileInputStream(file.getPath());
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
			
			Table table = (Table)ois.readObject();
			ois.close();
			
			return table;
		}catch(Exception e){
			Essentials.warn(e);
		}
		return null;
	}
	
	public static void RemoveTable(Table t) {
		java.io.File file = new java.io.File(new File(t.getName()).getPath());
		
		file.delete();
	}
	
	private static Table LoadData(java.io.File file) {
		try {
			FileInputStream fis = new FileInputStream(file.getPath());
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
			
			Table table = (Table)ois.readObject();
			ois.close();
			
			return table;
		}catch(Exception e){
			Essentials.warn(e);
		}
		return null;
	}
	
	public static List<Table> getTableList(){
		java.io.File folder = new java.io.File("tables/");
		java.io.File[] listOfItems = folder.listFiles();
		
		if(listOfItems == null) {
			return null;
		}
		
		List<Table> tableList = new LinkedList<Table>();
		
		for(int i = 0; i < listOfItems.length; i++) {
			if(listOfItems[i].isFile()) {
				String s = getFileExtension(listOfItems[i].getName());
				if(s.equals("table")) {
					tableList.add(LoadData(listOfItems[i]));
				}
			}
		}
		
		for(Table t: tableList) {
			if(t == null) {
				tableList.remove(t);
			}
		}
		
		return tableList;
	} 
	
	private static String getFileExtension(String filePath) {
		String reverse = new StringBuilder(new String(filePath.toCharArray())).reverse().toString();
		
		int inx = 0;
		for(char c:reverse.toCharArray()) {
			if(c == '.') {
				break;
			}
			inx++;
		}
		
		char[] newChars = new char[inx];
		
		inx = 0;
		for(char c:reverse.toCharArray()) {
			if(c == '.') {
				break;
			}
			newChars[inx] = c;
			inx++;
		}
		
		String string = new String(newChars);
		String stringReverse = new StringBuilder(string).reverse().toString();
		
		return stringReverse;
	}
}
