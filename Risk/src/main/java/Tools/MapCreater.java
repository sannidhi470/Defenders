package Tools;

import java.io.File;
import java.io.IOException;

/**
 * The class MapCreator will write in the file whenever user creates a map.
 *
 */

public class MapCreater {
	
	/**
	 *
	 * This method is used to create the map.
	 * @param p_enteredFileName refers to the Map Name being entered.
	 * @param p_pathName refers to the path.
	 *
	 */

	public static void createMap(String p_fileName,String p_pathName) 
	{
		
		File directory = new File(p_pathName);
		File f = new File(directory,p_fileName+".map");
		boolean success;
		try {
			success = f.createNewFile();
			if(success) 
				System.out.println("File Created successfully");
			else
				System.out.println("Unable to create new file");
			
		} catch (IOException e) {
			System.out.println("Unable to create a new file");
			e.printStackTrace();
		}
	}
}
