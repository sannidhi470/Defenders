package Services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Models.Continent;

public class MapCreater {

	public static void createMap(String p_fileName,String p_pathName) throws IOException
	{
		
		Scanner l_input=new Scanner(System.in);
		File directory = new File(p_pathName);
		File f = new File(directory,p_fileName+".map");
		boolean success = f.createNewFile() ;
		//Need to add exception for file not found
		System.out.println(success);
		//File l_openedFile
		
		
	}
}
