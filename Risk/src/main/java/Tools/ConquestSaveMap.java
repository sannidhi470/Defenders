package Tools;

import java.io.FileWriter;
import java.util.ArrayList;

import Models.Continent;
import Models.Country;

public class ConquestSaveMap {

	public static int conquestMapSaver(Connectivity p_connectivity, String p_mapName) {
		ArrayList<String> l_mapData=new ArrayList<String>();
		l_mapData.add("[Continents]\n");
		for(int i=0;i<p_connectivity.getD_continentList().size();i++)
		{
			l_mapData.add(p_connectivity.getD_continentList().get(i).getD_continentName()+"="+p_connectivity.getD_continentList().get(i).getD_continentBonusValue()+"\n");
		}
		l_mapData.add("\n");
		l_mapData.add("[Territories]\n");
		for(int i=0;i<p_connectivity.getD_countryList().size();i++)
		{
			String l_countryInfo=p_connectivity.getD_countryList().get(i).getD_countryName()+",0,"+"0,"+Continent.getNameFromId(p_connectivity.getD_countryList().get(i).getD_continentId(), p_connectivity.getD_continentList());
			String l_neighbours="";
			for(int j=0;j<p_connectivity.getD_countryList().get(i).getD_neighbours().size()-1;j++)
				l_neighbours=l_neighbours+Country.get_nameFromId(p_connectivity.getD_countryList(), p_connectivity.getD_countryList().get(i).getD_neighbours().get(j))+"," ;
			l_neighbours=l_neighbours+Country.get_nameFromId(p_connectivity.getD_countryList(), p_connectivity.getD_countryList().get(i).getD_neighbours().get( p_connectivity.getD_countryList().get(i).getD_neighbours().size()-1));
			l_countryInfo=l_countryInfo+","+l_neighbours;
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
    	    System.out.println("Map could not be saved properly");
    	    System.err.println(e.getMessage());
    	    return 1;
        }
		
	}
}
