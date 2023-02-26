package fileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class File {
	
	private final String FileName;
	private final String FileExtension;
	private final String FilePath;
	
	public File(String name) {
		FileName = name;
		FileExtension = "table";
		FilePath = getDefaultPath();
	}
	
	public File(String name, String extension) {
		FileName = name;
		FileExtension = extension;
		FilePath = getDefaultPath();
	}
	
	public File(String name, String extension, String filePath) {
		FileName = name;
		FileExtension = extension;
		FilePath = filePath+"/"+name+"."+extension;
	}
	
	private String getDefaultPath() {
		return "tables"+java.io.File.separator+FileName+"."+FileExtension;
	}
	
	public String getPath() {
		return FilePath;
	}
	
	public String getName() {
		return FileName;
	}
}
