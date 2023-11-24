package Models;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import Tools.Connectivity;
import Tools.ConquestMapLoader;
import Tools.MapCheck;
import Tools.MapLoader;

/**
 * The class Map defines Maps and it's properties such as File Name and checker if the file exists or not.
 *
 */

public class Map {

	private String d_mapFileName;
	private boolean d_mapExist;
	
	/**
	 * This is a default constructor.
	 *
	 */
	
	public Map()
	{
		this.d_mapExist=false;
	}
	
	/**
	 * Gets the file Name.
	 * @return file name
	 *
	 */
	
	public String getD_mapFileName() {
		return d_mapFileName;
	}
	
	/**
	 * Sets the file Name.
	 * @param p_mapFileName refers to the file name.
	 *
	 */
	
	public void setD_mapFileName(String p_mapFileName) {
		this.d_mapFileName = p_mapFileName;
	}
	
	/**
	 * Checks if the file exists for not.
	 * @return true or false accordingly
	 *
	 */
	
	public boolean isD_mapExist() {
		return d_mapExist;
	}
	
	/**
	 * Sets the checker for file existance.
	 * @param p_mapExist refers to the boolean checker.
	 *
	 */

	public void setD_mapExist(boolean p_mapExist) {
		this.d_mapExist = p_mapExist;
	}
	
	public static void checkMap(Connectivity p_connectivity,String p_mapName)
	{
		String l_fileName = p_mapName;
		String l_copyFileName=l_fileName;
		Map l_map=new Map();
		File f = new File("");
		p_connectivity.setD_mapName(p_mapName);
		String absolute = f.getAbsolutePath();
        p_connectivity.setD_pathName(absolute+ File.separator+"src/main/resources");
        l_fileName = absolute + File.separator + "src/main/resources" + File.separator + l_fileName+".map";
        p_connectivity.setD_FilepathName(l_fileName);
        l_map.setD_mapFileName(l_fileName);
        ArrayList<String> l_fileContent=new ArrayList<String>();
        if(MapCheck.validateMap(l_copyFileName,p_connectivity.getD_pathName()))
        {
       	 try 
       	 {
       	   		Scanner l_input = new Scanner(new File(l_fileName));
       	   		
       	   		while(l_input.hasNextLine())
       	   		{
       	   			l_fileContent.add(l_input.nextLine());
       	   		}
       	 }catch(Exception e)
       	 {
       		 System.out.println("File not found exception");
       	 }
        }
        if(l_fileContent.contains("[Territories]"))
        	ConquestMapLoader.loadMap(p_connectivity, p_mapName);
        if(l_fileContent.contains("[countries]"))
        	MapLoader.loadMap(p_connectivity, p_mapName);
        		
	}
	
	
}