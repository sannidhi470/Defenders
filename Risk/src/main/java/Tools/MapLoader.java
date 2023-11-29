package Tools;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Models.Continent;
import Models.Country;
import Models.LogEntryBuffer;

import java.io.*;
import Models.Map;

/**
 * The class MapLoader will load the map selected by the user.
 *
 */

public class MapLoader {
	
	/**
 	 * 
	 * This method is used to load the Map selected by the user whether pre-defined or user made.
	 * @param p_connectivity refers to connectivity object
	 * @param p_mapName refers to the Name of the map being loaded.
	 * @return 0 in-case of successful loading of map; 1 in-case unsuccessful loading of map
 */
	
	public static int loadMap(Connectivity p_connectivity,String p_mapName) {
		LogEntryBuffer d_logEntryBuffer = new LogEntryBuffer();
		Scanner l_input = new Scanner(System.in);
		String l_fileName = p_mapName;
		String l_copyFileName=l_fileName;
		ArrayList<Continent> continentList=new ArrayList<Continent>();
		ArrayList<Country> l_countryList=new ArrayList<Country>();
		Map l_map=new Map();
		try 
		{	   
			File f = new File("");
			p_connectivity.setD_mapName(p_mapName);
			String absolute = f.getAbsolutePath();
            p_connectivity.setD_pathName(absolute+ File.separator+"src/main/resources");
            l_fileName = absolute + File.separator + "src/main/resources" + File.separator + l_fileName+".map";
            p_connectivity.setD_FilepathName(l_fileName);
            l_map.setD_mapFileName(l_fileName);
        }
        catch (Exception e)  
		{
            System.err.println(e.getMessage());
        }
       
     if(MapCheck.validateMap(l_copyFileName,p_connectivity.getD_pathName()))
     {
    	 try 
    	 {
    	   		l_input= new Scanner(new File(l_fileName));
    	   		ArrayList<String> l_fileContent=new ArrayList<String>();
    	   		while(l_input.hasNextLine())
    	   		{
    	   			l_fileContent.add(l_input.nextLine());
    	   		}
    	   		int l_continentLength = 0;
    	   		int l_countryLength=0;
    	   		int l_borderLength=0;
    	   		for(int i=0;i<l_fileContent.size();i++)
    	   		{
    	   			String a=l_fileContent.get(i);
    	   			if(a.equals("[continents]")) l_continentLength=i;	
    	   			if(a.equals("[countries]")) l_countryLength=i;
    	   			if(a.equals("[borders]")) l_borderLength=i;
    	   			
    	   		}
    	   		HashMap<Integer,ArrayList<Integer>> l_neighbouringCountries=new HashMap<Integer,ArrayList<Integer>>(); 
    	        for(int i=l_borderLength+1; i<l_fileContent.size(); i++) 
    	        {
    	        	String a = l_fileContent.get(i);
    	        	String[] aArr=a.split(",");
    	        	String l_borderString=aArr[0];
    	        	String[] l_borderStringArr=l_borderString.split(" ");
    	        	ArrayList<Integer> l_neighbours=new ArrayList<Integer>();
    	        	for(int j=1;j<l_borderStringArr.length;j++) l_neighbours.add(Integer.parseInt(l_borderStringArr[j]));
    	        	l_neighbouringCountries.put(Integer.parseInt(l_borderStringArr[0]),l_neighbours );
    	        }
    	   		for(int i=l_countryLength+1;i<l_borderLength-1;i++)
    	   		{
    	   			String a=l_fileContent.get(i);
    	   			
    	  		    String[] aArr=a.split(" ");
    	  		    Country obj=new Country();
    	  		    obj.setD_continentId(Integer.parseInt(aArr[aArr.length-1]));
    	  		  Pattern pattern=Pattern.compile("(([A-Za-z._&-]{1,})([\\s][A-Za-z._&-]{1,}){0,})");
    	      	  Matcher matcher = pattern.matcher(a);
    	        
    	          if(matcher.find())
    	        	  aArr[1]=matcher.group(0);
    	  		    obj.setD_countryId(Integer.parseInt(aArr[0]));
    	  		    obj.setD_countryName(aArr[1]);
    	  		    
    	  		    obj.setD_neighbours(Integer.parseInt(aArr[0]), l_neighbouringCountries);
    	     		l_countryList.add(obj);	
    	   		}
    	   		int l_continentId=1;
    	   		for(int i=l_continentLength+1;i<l_countryLength-1;i++)
    	   		{
    	   			String a=l_fileContent.get(i);
    	   			String[] aArr=a.split(" ");
    	   			Continent l_continentObj=new Continent();
    	   			l_continentObj.setD_continentId(l_continentId);
    	   		 Pattern pattern=Pattern.compile("(([A-Za-z._&-]{1,})([\\s][A-Za-z._&-]{1,}){0,})");
    	   		 Matcher matcher = pattern.matcher(a);
    	   		 
    	   		 if(matcher.find())
    	   			 aArr[0]=matcher.group(0);
    	   			l_continentObj.setD_continentName(aArr[0]);
    	   			l_continentObj.setD_continentBonusValue(Integer.parseInt(aArr[aArr.length-1]));
    	   			l_continentObj.setD_countries(l_continentObj.d_getCountryFromContinentId(l_continentId, l_countryList));
    	   			l_continentId++;
    	   			continentList.add(l_continentObj);	
    	   		}
    	   		p_connectivity.setD_continentList(continentList);
    	        p_connectivity.setD_countryList(l_countryList);
    	        }
    	      catch(Exception e)
    	       {
    	    	  System.err.println(e.getMessage());
    	       }
    	      try
    	       {
    	    	  d_logEntryBuffer.log("Map "+p_mapName+".map"+" has been successfully loaded...");
    	    	  System.out.println(ColorCoding.green+"Map "+p_mapName+".map"+" has been successfully loaded..."+ColorCoding.blank);
    	       } 
    	       catch (Exception e)
    	       {
    	    	   System.out.println("Map could not be loaded properly");
    	    	   return -1;
    	       }
    	      return 0;
    	 	 
     }
     else
     {
    	 p_connectivity.setD_continentList(new ArrayList<Continent>());
    	 p_connectivity.setD_countryList(new ArrayList<Country>());
    	 d_logEntryBuffer.log("Map does not exist. Creating a map...");
    	 System.out.println(ColorCoding.green+"Map does not exist. Creating a map..."+ColorCoding.blank);
    	 MapCreater.createMap(l_copyFileName,p_connectivity.getD_pathName());
    	 SaveMap.saveMap(p_connectivity, l_copyFileName);
    	 return 1;
  	 }
       
	}
}

