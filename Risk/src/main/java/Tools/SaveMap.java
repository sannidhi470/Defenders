package Tools;

import java.io.FileWriter;
import java.util.ArrayList;

import Models.LogEntryBuffer;

/**
 * The class SaveMap saves the map once user creates a new map or make changes to the existing ones.
 *
 */

public class SaveMap {
	
/**
 * 
 * This method is used to save the map once user creates a new map or make changes to the existing ones.
 * @param p_connectivity refers to the connectivity object
 * @param p_mapName refers to map name.
 * @return 0 in case of successful save of map; 1 in case of unsuccessful map save
 */

	public static int saveMap(Connectivity p_connectivity, String p_mapName) {
		LogEntryBuffer d_logEntryBuffer = new LogEntryBuffer();
		
		ArrayList<String> l_mapData=new ArrayList<String>();
		l_mapData.add("[continents]"+"\n");
		for(int i=0;i<p_connectivity.getD_continentList().size();i++)
			l_mapData.add(p_connectivity.getD_continentList().get(i).getD_continentName()+" "+p_connectivity.getD_continentList().get(i).getD_d_continentBonusValue()+"\n");
		l_mapData.add("\n");
		l_mapData.add("[countries]"+"\n");
		for(int i=0;i<p_connectivity.getD_countryList().size();i++) 
			l_mapData.add(p_connectivity.getD_countryList().get(i).getD_countryId()+" "+p_connectivity.getD_countryList().get(i).getD_countryName()+" "+p_connectivity.getD_countryList().get(i).getD_continentId()+"\n");
		l_mapData.add("\n");
		l_mapData.add("[borders]"+"\n");
		for(int i=0;i<p_connectivity.getD_countryList().size();i++)
		{	
			String l_countryInfo=Integer.toString(p_connectivity.getD_countryList().get(i).getD_countryId());
			for(int j=0;j<p_connectivity.getD_countryList().get(i).getD_neighbours().size();j++)
				 l_countryInfo=l_countryInfo+" "+p_connectivity.getD_countryList().get(i).getD_neighbours().get(j);
			l_mapData.add(l_countryInfo+"\n");
		}
		
		try 
		{
			if(p_mapName.equals(p_connectivity.getD_mapName()))
			{
				FileWriter l_input=new FileWriter(p_connectivity.getD_FilepathName());
				for(String lines:l_mapData) 
					l_input.write(lines);		
				l_input.close();	
				d_logEntryBuffer.log("Map has been successfully saved");
				System.out.println("Map has been successfully saved");
				return 0;
			}
			else
			{
				System.out.println(ColorCoding.red+"ERROR: Saving mapName '"+p_mapName+"' should be the same name as Loading mapName '"+p_connectivity.getD_mapName()+"'"+ColorCoding.blank);
				return 1;
			}

		}
        catch (Exception e)
        {
        	d_logEntryBuffer.log("Map could not be saved properly");
    	    System.out.println("Map could not be saved properly");
    	    System.err.println(e.getMessage());
    	    return 1;
        }
	}
}