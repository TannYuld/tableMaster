package essentials;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Essentials {
	
	private static final List<String> POSITIVE_ANWSERS = List.of("y","yes","1");
	private static final List<String> NEGATIVE_ANWSERS = List.of("n","no","0");
	
	public static <T> void println(T content) {
		System.out.println(content);
	}
	
	public static void println() {
		System.out.println();
	}
	
	public static <T> void print(T content) {
		System.out.print(content);
	}
	
	public static int makeLinesFor(String s) {
		int inx = 0;
		for(int i=0; i < s.length(); i++) {
			inx++;
			System.out.print("-");
		}
		return inx;
	}
	
	public static <T> void warn(T content) {
		makeLinesFor(content.toString());
		System.out.print("\n");
		System.out.println(content);
		makeLinesFor(content.toString());
		System.out.println("\n");
	}
	
	public static <T> String askInput(T question){
		System.out.println(question);
		System.out.print("-> ");
		
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		System.out.println("");
		
		return s;
	}
	
	public static <T> String askInputWithoutSpace(T question) {
		System.out.println(question);
		System.out.print("-> ");
		
		Scanner scanner = new Scanner(System.in);
		String s = scanner.next();
		System.out.println("");
		
		return s;
	}
	
	public static <T> String askInputWithoutSpaceCanEmpty(T question) {
		System.out.println(question);
		System.out.print("-> ");
		
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		
		if(s.length() != 0) {
			int lastChar = s.toCharArray().length;
			int lastValidChar = 0;
			for(int i = 0; i < s.toCharArray().length; i++) {
				if(s.toCharArray()[i] != ' ') {
					lastValidChar = i;
				}
			}
			s = s.substring(0,lastValidChar+1);
		}
		
		System.out.println("");
		return s;
	}
	
	public static String askRawInput() {
		Scanner scanner = new Scanner(System.in);
		String s = scanner.next();
		
		return s;
	}
	
	public static <T> String askInputCanEmpty(T question) {
		System.out.println(question);
		System.out.print("-> ");
		
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		System.out.println("");
		
		return s;
	}
	
	public static <T> Integer askInput(T question, boolean isInt) {
		int targetInt = 0;
		
		
		while(true) 
		{
			
			try
			{
				System.out.println(question);
				System.out.print("-> ");
				
				Scanner scanner = new Scanner(System.in);
				targetInt = scanner.nextInt();
				
				//scanner.close();
				break;
			}catch(Exception e) {
				Essentials.print("\n");
				Essentials.warn("Please enter a digit !");
			}
		}
		
		System.out.println("");
		return targetInt;
	}
	
	public static <T> boolean askInputBool(T question) {
		while(true) 
		{
			println(question);
			if(question.toString().length() >= "-  yes  - / -  no  -".length()) {
				makeLinesFor(question.toString());
			}else {
				makeLinesFor("-  yes  - / -  no  -");
			}
			println();
			print("-  yes  - / -  no  -");
			println("");
			String ans = askInput("");
			
			for(String i: POSITIVE_ANWSERS) {
				if(i.equalsIgnoreCase(ans)) {
					return true;
				}
			}
			
			for(String i: NEGATIVE_ANWSERS) {
				if(i.equalsIgnoreCase(ans)) {
					return false;
				}
			}
			
			warn("Please enter a vaild input !");
		}
	}
	
	public static <T> int askInputWAns(T question, List<String> anwserList) {
		while(true) {
			String answer = askInput(question);
			
			if(anwserList.contains(answer)) {
				return anwserList.indexOf(answer);
			}else {
				warn("Please enter a valid input !");
			}
		}
	}
	
//	public static <T> int askInputWArgs(T question) {
//		String ans = Essentials.askInput(question);
//		
//		
//	}
	
	public static void makeSpaceFor(String value) 
	{
		for(char i:value.toString().toCharArray()) {
			Essentials.print(" ");
		}
	}
	
	public static void makeSpaceFor(Integer inx) {
		for(int i = 0; i < inx; i++) {
			Essentials.print(" ");
		}
	}
	
	public static <T> void printFormat(String format, T text) {
		System.out.format(format, text.toString());
	}
}
