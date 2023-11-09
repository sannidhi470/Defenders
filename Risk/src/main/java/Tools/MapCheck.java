package Tools;
import java.io.File;

/**
 * The class MapCheck performs validation of map.
 *
 */

public class MapCheck 
{
	/**
	 *
	 * This method is used to validate the Map.
	 * @param p_enteredFileName refers to the Map Name being entered.
	 * @param p_pathName refers to the path.
	 * @return returns the validation of the map.
	 */
	public static boolean validateMap(String p_enteredFileName,String p_pathName)
	{
		p_enteredFileName=p_enteredFileName+".map";		
		File l_resourcePath=new File(p_pathName);
		File l_fileList[]=l_resourcePath.listFiles();
		for(File l_file:l_fileList)
			if(p_enteredFileName.compareTo(l_file.getName())==0) return true;
		return false;
	}
} 