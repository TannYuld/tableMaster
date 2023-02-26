import java.io.IOException;
import java.util.Scanner;

import console.ConsoleLoop;
import essentials.Essentials;
import fileIO.*;
import table.Table;

public class Main {
	
	public static void main(String[] args){
		
		String asciiArt = """ 
				
.%%%%%%...%%%%...%%%%%...%%......%%%%%%..%%...%%...%%%%....%%%%...%%%%%%..%%%%%%..%%%%%..
...%%....%%..%%..%%..%%..%%......%%......%%%.%%%..%%..%%..%%........%%....%%......%%..%%.
...%%....%%%%%%..%%%%%...%%......%%%%....%%.%.%%..%%%%%%...%%%%.....%%....%%%%....%%%%%..
...%%....%%..%%..%%..%%..%%......%%......%%...%%..%%..%%......%%....%%....%%......%%..%%.
...%%....%%..%%..%%%%%...%%%%%%..%%%%%%..%%...%%..%%..%%...%%%%.....%%....%%%%%%..%%..%%.
.........................................................................................
                                                                   
                 Welcome to tableMaster [version 0.8v]                                                
                         Created by TannYuld
                            au79stone.nl
                            
                                                                                                        
				"""; 
		
		Essentials.print(asciiArt);
		
		ConsoleLoop.StartLoop();
	}

}

