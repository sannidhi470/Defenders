package Controllers;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GamePlayCommands {

	    public  boolean MapTypeChecker(String p_fileName) {
	        String l_path = "src/main/resources/";
	        String l_fileName = p_fileName + ".map";
	        File l_mapFile = new File(l_path + l_fileName);

	        if (!l_mapFile.exists()) {
	            return false;
	        } else {
	            Scanner l_mapReader = null;
	            try {
	                l_mapReader = new Scanner(l_mapFile);
	                while (l_mapReader.hasNextLine()) {
	                    String l_line = l_mapReader.nextLine();
	                    if (l_line.equals("[Continents]")) {
	                        l_line = l_mapReader.nextLine();
	                        if (l_line.contains("=")) {
	                            return true;
	                        } else {
	                            return false;
	                        }
	                    }
	                }
	            } catch (IOException l_e) {
	                // Handle the exception, e.g., log it
	            } finally {
	                if (l_mapReader != null) {
	                    l_mapReader.close();
	                }
	            }
	        }
	        return false;
	    }

	    public void main(String[] args) {
	        boolean result = MapTypeChecker("example");
	        if (result) {
	            System.out.println("Map is of type 2");
	        } else {
	            System.out.println("Map is of type 1");
	        }
	    }	
	
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