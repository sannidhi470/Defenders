package Tools;

import java.io.File;
import java.io.IOException;

public class MapCreater {

	public static void createMap(String p_fileName,String p_pathName) 
	{
		
		File directory = new File(p_pathName);
		File f = new File(directory,p_fileName+".map");
		boolean success;
		//Need to add exception for file not found
		try {
			success = f.createNewFile();
			if(success) 
				System.out.println("File Created successfully");
			else
				System.out.println("Unable to create new file");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to create a new file");
			e.printStackTrace();
		}
	}
}
