package Utilities;
import java.util.*;

import Models.Continent;
import Models.Country;

import java.io.*;
import Models.Map;
import Services.MapCheck;
import Services.MapCreater;
import Services.SaveMap;
public class MapLoader {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.print("Enter game command\n");
		Scanner l_input = new Scanner(System.in);
		String l_fileName = l_input.nextLine();
		String l_copyFileName=l_fileName;
		ArrayList<Continent> continentList=new ArrayList<Continent>();
		ArrayList<Country> l_countryList=new ArrayList<Country>();
		Connectivity l_connectivity=new Connectivity();
		Map l_map=new Map();
		//Getting file location of map
       try {	   
            // Create a file object
            File f = new File("");
  
            // Get the absolute path of file f
            String absolute = f.getAbsolutePath();
            l_connectivity.setD_pathName(absolute+ File.separator+"src\\main\\resources");
            // Display the file path of the file object
            // and also the file path of absolute file
          //  System.out.println("Absolute  path: "
          //                    + absolute);
            l_fileName = absolute + File.separator + "src\\main\\resources" + 
            		File.separator + l_fileName+".map";
            l_connectivity.setD_FilepathName(l_fileName);
            l_map.setD_mapFileName(l_fileName);
         //   System.out.print(l_fileName);
            
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
       
     if(MapCheck.validateMap(l_fileName,l_connectivity.getD_pathName() ))
     {
    	//Loading Map entered by User
    	 try {
      	   
      	   
    	   		l_input= new Scanner(new File(l_fileName));
    	   		ArrayList<String> l_fileContent=new ArrayList<String>();
    	   		while(l_input.hasNextLine())
    	   		{
    	   			l_fileContent.add(l_input.nextLine());
    	   		}
    	   		int l_parser=0;
    	   		int l_continentLength = 0;
    	   		int l_countryLength=0;
    	   		int l_borderLength=0;
    	   		for(int i=0;i<l_fileContent.size();i++)
    	   		{
    	   			String a=l_fileContent.get(i);
    	   			//System.out.println(a);
    	   			if(a.equals("[continents]"))
    	   			{
    	   				l_continentLength=i;
    	   			}
    	   			if(a.equals("[countries]"))
    	   			{
    	   				l_countryLength=i;
    	   			}
    	   			if(a.equals("[borders]"))
    	   			{
    	   				l_borderLength=i;
    	   				
    	   			}
    	   		}
    	   		
    	   		HashMap<Integer,ArrayList<Integer>> l_neighbouringCountries=new HashMap<Integer,ArrayList<Integer>>(); 
    	        for(int i=l_borderLength+1; i<l_fileContent.size(); i++) {
    	        	String a = l_fileContent.get(i);
    	        	String[] aArr=a.split(",");
    	        	String l_borderString=aArr[0];
    	        	String[] l_borderStringArr=l_borderString.split(" ");
    	        	ArrayList<Integer> l_neighbours=new ArrayList<Integer>();
    	        	for(int j=1;j<l_borderStringArr.length;j++)
    	        	{
    	        		l_neighbours.add(Integer.parseInt(l_borderStringArr[j]));
    	        			
    	        	}

    	        		l_neighbouringCountries.put(Integer.parseInt(l_borderStringArr[0]),l_neighbours );
    	        	}
    	   		
    	   		for(int i=l_countryLength+1;i<l_borderLength-1;i++)
    	   		{
    	   			//System.out.println(i);
    	   			String a=l_fileContent.get(i);
    	   			//System.out.println(a);
    	  		    String[] aArr=a.split(" ");
    	  		    Country obj=new Country();
    	  		    obj.setD_countryId(Integer.parseInt(aArr[0]));
    	  		    obj.setD_countryName(aArr[1]);
    	  		    obj.setD_continentId(Integer.parseInt(aArr[2]));
    	  		    obj.setD_neighbours(Integer.parseInt(aArr[0]), l_neighbouringCountries);
    	     		l_countryList.add(obj);
    	   			
    	   		}
    	   		
    	   		int l_continentId=1;
    	   		
    	   		for(int i=l_continentLength+1;i<l_countryLength-1;i++)
    	   		{
    	   			String a=l_fileContent.get(i);
    	   			String[] aArr=a.split(" ");
    	   			Continent l_continentObj=new Continent();
    	   			//System.out.println(aArr[0]+" "+aArr[1]);
    	   			l_continentObj.setD_continentId(l_continentId);
    	   			l_continentObj.setD_continentName(aArr[0]);
    	   			l_continentObj.setD_continentArmyValue(Integer.parseInt(aArr[1]));
    	   			l_continentObj.setD_countries(l_continentObj.d_getCountryFromContinentId(l_continentId, l_countryList));
    	   			l_continentId++;
    	   			continentList.add(l_continentObj);
    	   			
    	   		}
    	        	
    	        }
    	      
    	       catch(Exception e)
    	       {
    	    	   
    	       }
    	       
    	       try
    	       {
    	    	   for(int i=0;i<continentList.size();i++)
    	    	   {
    	    		   
    	    		   System.out.println("Continent: "+ continentList.get(i).getD_continentName()+" | "+"Army Count: "+continentList.get(i).getD_continentArmyValue());
    	    		   
    	    		   for(int j=0;j<continentList.get(i).getD_countries().size();j++)
    	    		   {
    	                   Country l_country= continentList.get(i).getD_countries().get(j);
    	                   System.out.println();
    	    			   System.out.println("Country: "+l_country.getD_countryName());
    	    			   ArrayList<Integer> l_neighbourID = l_country.getD_neighbours();
    	    			   System.out.print("Neighbour Countries: ");
    	    			   for(Integer f: l_neighbourID)
    	    			   {
    	    				   System.out.print(l_country.get_nameFromId(l_countryList,f)+" ");
    	    			   }
    	    			  System.out.println();
    	    		   }
    	    		   System.out.println("________________________________________");
    	    		   
    	    	   }
    	    	   SaveMap.saveMap(l_connectivity);
    	       } 
    	       catch (Exception e)
    	       {
    			// TODO: handle exception
    	       }
    	       
    	 
    	 
    	 
    	 
     }
	
     else
     {
    	 System.out.println("Map does not exist. Creating a map...");
    	 System.out.println(l_connectivity.getD_FilepathName());
    	 System.out.println(l_connectivity.getD_pathName());
    	MapCreater.createMap(l_copyFileName,l_connectivity.getD_pathName());
     }
      
       
       
	}

}

