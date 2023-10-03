package Tools;
import java.io.File;
public class MapCheck 
{
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