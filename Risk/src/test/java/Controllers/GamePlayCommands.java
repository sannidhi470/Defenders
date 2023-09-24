package Controllers;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GamePlayCommands {

	public static void LoadMap(List<String> p_argsToken)
	{
	    if (p_argsToken.size() != 1) {
	        System.out.println("Invalid number of arguments. The 'LoadMap' command requires one argument.");        
	    }
	}

	public static void ShowMap(List<String> p_argsToken) {
	  
	}

	private static void HandleExceptionAndPrintErrorMessage(Exception e){
		System.out.println("Map Validation Failed.\n Use 'loadmap' to load a map again.");
		System.out.println(e.getMessage());
	}

}