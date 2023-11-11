package Tools;
import java.util.*;
import Models.Continent;
import Models.Country;
import Models.LogEntryBuffer;

import java.io.*;
import Models.Map;

/**
 * The class MapLoader will load the map selected by the user.
 *
 */

public class ConquestMapLoader {
	
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
    	   		int l_territoryLength=0;
    	   		for(int i=0;i<l_fileContent.size();i++)
    	   		{
    	   			String a=l_fileContent.get(i);
    	   			if(a.equals("[Continents]")) l_continentLength=i;
    	   			if(a.equals("[Territories]")) l_territoryLength=i;
    	   			
    	   		}
    	   		HashMap<Integer,ArrayList<Integer>> l_neighbouringCountries=new HashMap<Integer,ArrayList<Integer>>();
    	   		int l_countryID =1;
    	   		int l_continentID =1;
    	   		for(int i=l_continentLength+1; i<l_territoryLength-1; i++)
    	   		{
    	   			String a=l_fileContent.get(i);
    	   			String[] aArr=a.split("=");
    	   			Continent l_continentObj=new Continent();
    	   			l_continentObj.setD_continentId(l_continentID);
    	   			l_continentObj.setD_continentName(aArr[0]);
    	   			l_continentObj.setD_continentBonusValue(Integer.parseInt(aArr[1]));
    	   			System.out.println("setting continentID"+l_continentID+" continentName "+aArr[0]+" continentBonus"+Integer.parseInt(aArr[1]));
    	   			l_continentObj.setD_countries(l_countryList);
    	   			l_continentID++;
    	   			continentList.add(l_continentObj);

    	   		}
    	   		//Only Alaska Countries are duplicated in countryList
    	   		for(int i=l_territoryLength+1; i<l_fileContent.size(); i++)
    	   		{
    	   			int l_currentCountryID = l_countryID;
	   				Country l_currentCountry = new Country();
	   				boolean l_countryAlreadyExists = false;
    	   			String a = l_fileContent.get(i);
    	   			System.out.println(a);
    	   			String[] aArr = a.split(",");
    	   			
    	   			//Checking if aArr[0] country already exists or not, if it does, set it to l_currentCountry
    	   			//else create a new country object and assign it to l_currentCountry
    	   			if(checkIfcountryExists(l_countryList,aArr[0]))
   					{
    	   				for(int j=0; j<l_countryList.size(); j++)
    	   					if(l_countryList.get(j).getD_countryName().equals(aArr[0]))
    	   						l_currentCountry = l_countryList.get(j);
   					}
    	   			else
    	   			{
    	   				l_currentCountry.setD_countryId(l_countryID);
    	   				l_currentCountry.setD_countryName(aArr[0]);
        	   			l_countryList.add(l_currentCountry);
        	   			l_countryID++;
        	   			for(int j=0; j<continentList.size();j++)
        	   			{
        	   				if(continentList.get(j).getD_continentName().equals(aArr[3]))
        	   				{
        	   					continentList.get(j).getD_countries().add(l_currentCountry);
        	   				}
        	   			}
        	   			System.out.println("added to list"+l_currentCountry.getD_countryName());
    	   			}
    	   			System.out.println("CurrentCountry ="+l_currentCountry.getD_countryName());
    	   			   
    	   			//Setting neighbors for l_currentCountry according to loaded map
    	   			ArrayList<Integer> l_neighbours=new ArrayList<Integer>();
    	   			for(int j=4; j<aArr.length; j++)
    	   			{
    	   				Country l_neighborCountry=new Country();
    	   				if(checkIfcountryExists(l_countryList, aArr[j]))
    	   				{
    	   					l_neighborCountry = Country.getCountryFromName(l_countryList, aArr[j]);
    	   					System.out.println("country already exists"+l_neighborCountry.getD_countryName());
    	   				}

    	   				else
    	   				{
        	   				l_neighborCountry.setD_countryId(l_countryID);
        	   				l_neighborCountry.setD_countryName(aArr[j]);
            	   			l_countryList.add(l_neighborCountry);
            	   			System.out.println("country newly created"+l_neighborCountry.getD_countryName()+" ID="+l_neighborCountry.getD_countryId());
            	   			l_countryID++;   					   				
    	   				}
    	   				l_neighbours.add(l_neighborCountry.getD_countryId());
    	   			}
    	   			l_neighbouringCountries.put(l_currentCountryID,l_neighbours);
    	   			l_currentCountry.setD_neighbours(l_currentCountry.getD_countryId(), l_neighbouringCountries);
    	   			System.out.println("\n\n");
    	   			for(Country x: l_countryList)
    	   			{
    	   				System.out.println(x.getD_countryName());
    	   			}
//    	   			
    	   			//Setting continents and their respective bonus values
    	   		}
//    	        for(int i=l_borderLength+1; i<l_fileContent.size(); i++) 
//    	        {
//    	        	String a = l_fileContent.get(i);
//    	        	String[] aArr=a.split(",");
//    	        	String l_borderString=aArr[0];
//    	        	String[] l_borderStringArr=l_borderString.split(" ");
//    	        	ArrayList<Integer> l_neighbours=new ArrayList<Integer>();
//    	        	for(int j=1;j<l_borderStringArr.length;j++) l_neighbours.add(Integer.parseInt(l_borderStringArr[j]));
//    	        	l_neighbouringCountries.put(Integer.parseInt(l_borderStringArr[0]),l_neighbours );
//    	        }
//    	   		for(int i=l_countryLength+1;i<l_borderLength-1;i++)
//    	   		{
//    	   			String a=l_fileContent.get(i);
//    	  		    String[] aArr=a.split(" ");
//    	  		    Country obj=new Country();
//    	  		    obj.setD_countryId(Integer.parseInt(aArr[0]));
//    	  		    obj.setD_countryName(aArr[1]);
//    	  		    obj.setD_continentId(Integer.parseInt(aArr[2]));
//    	  		    obj.setD_neighbours(Integer.parseInt(aArr[0]), l_neighbouringCountries);
//    	     		l_countryList.add(obj);	
//    	   		}
//    	   		int l_continentId=1;
//    	   		for(int i=l_continentLength+1;i<l_countryLength-1;i++)
//    	   		{
//    	   			String a=l_fileContent.get(i);
//    	   			String[] aArr=a.split(" ");
//    	   			Continent l_continentObj=new Continent();
//    	   			l_continentObj.setD_continentId(l_continentId);
//    	   			l_continentObj.setD_continentName(aArr[0]);
//    	   			l_continentObj.setD_continentBonusValue(Integer.parseInt(aArr[1]));
//    	   			l_continentObj.setD_countries(l_continentObj.d_getCountryFromContinentId(l_continentId, l_countryList));
//    	   			l_continentId++;
//    	   			continentList.add(l_continentObj);	
//    	   		}
    	   		
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
	
	public static boolean checkIfcountryExists(ArrayList<Country> p_countryList, String p_country)
	{
		boolean l_countryExists = false;
		for(int i=0; i<p_countryList.size(); i++)
		{
			if(p_countryList.get(i).getD_countryName().equals(p_country))
				l_countryExists = true;
		}
		return l_countryExists;
	}
}


