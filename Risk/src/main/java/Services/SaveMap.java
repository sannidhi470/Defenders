package Services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Utilities.Connectivity;

public class SaveMap {

	public static void saveMap(Connectivity p_connectivity) throws IOException
	{
		
		ArrayList<String> l_mapData=new ArrayList<String>();
		l_mapData.add("[continents]"+"\n");
		for(int i=0;i<p_connectivity.getD_continentList().size();i++)
		{
			l_mapData.add(p_connectivity.getD_continentList().get(i).getD_continentName()+" "+p_connectivity.getD_continentList().get(i).getD_d_continentArmyValue()+"\n");
		}
		l_mapData.add("\n");
		l_mapData.add("[countries]"+"\n");
		for(int i=0;i<p_connectivity.getD_countryList().size();i++) 
		{
		l_mapData.add(p_connectivity.getD_countryList().get(i).getD_countryId()+" "+p_connectivity.getD_countryList().get(i).getD_countryName()+" "+p_connectivity.getD_countryList().get(i).getD_continentId()+"\n");
		}
		l_mapData.add("\n");
		l_mapData.add("[borders]"+"\n");
		for(int i=0;i<p_connectivity.getD_countryList().size();i++)
		{	
			String l_temp=Integer.toString(p_connectivity.getD_countryList().get(i).getD_countryId());
			for(int j=0;j<p_connectivity.getD_countryList().get(i).getD_neighbours().size();j++)
			{
				 l_temp=l_temp+" "+p_connectivity.getD_countryList().get(i).getD_neighbours().get(j);
			}
			l_mapData.add(l_temp+"\n");
		}
		
		File f = new File(p_connectivity.getD_FilepathName());
		FileWriter l_input=new FileWriter(p_connectivity.getD_FilepathName());
		for(String s:l_mapData)
		{
			l_input.write(s);
		}
		
		l_input.close();
		
		  
		  
	}
	
}