package Models;

import java.util.ArrayList;

import dnl.utils.text.table.TextTable;

public class Map {

	private String d_mapFileName;
	private boolean d_mapExist;
	
	public Map()
	{
		this.d_mapExist=false;
	}
	public String getD_mapFileName() {
		return d_mapFileName;
	}
	public void setD_mapFileName(String d_mapFileName) {
		this.d_mapFileName = d_mapFileName;
	}
	public boolean isD_mapExist() {
		return d_mapExist;
	}
	public void setD_mapExist(boolean d_mapExist) {
		this.d_mapExist = d_mapExist;
	}
	
	public static void viewMap(ArrayList<Continent> p_continentList, ArrayList<Country> p_countryList) {
		
 	   String[] l_columnNames = {"Continent", "Country", "Neighbouring Country", "Army Count"};
 	   if(p_continentList.size() == 0)
 	   {
 		   System.out.println("No continents or countries exist in the map");
 		   return;
 	   }
 	   String[][] l_map = new String[p_countryList.size()][4];
		   for(int l_lineIterator=0; l_lineIterator<p_countryList.size(); l_lineIterator++) 
		   {
			   for(int l_continentIterator=0; l_continentIterator<p_continentList.size(); l_continentIterator++) 
			   {
				   for(int l_countryIterator=0;l_countryIterator<p_continentList.get(l_continentIterator).getD_countries().size();l_countryIterator++)
	    		   {
					   if(l_countryIterator==0) 
					   {
						   l_map[l_lineIterator][0] = p_continentList.get(l_continentIterator).getD_continentName();
					   }
				   	   Country l_country= p_continentList.get(l_continentIterator).getD_countries().get(l_countryIterator);

				   	   l_map[l_lineIterator][1] = l_country.getD_countryName();
				   	   ArrayList<Integer> l_neighbourID = l_country.getD_neighbours();
				   	   String l_tempNeighbours="";
				   	   for(Integer f: l_neighbourID)
	    			   {
				   		l_tempNeighbours = l_tempNeighbours + " "+l_country.get_nameFromId(p_countryList,f)+",";
	    			   }
				   	   l_map[l_lineIterator][2] = l_tempNeighbours;
				   	   l_map[l_lineIterator][3] = Integer.toString(p_continentList.get(l_continentIterator).getD_continentArmyValue());
				   	l_lineIterator=l_lineIterator+1;
	    		   }
			   }
		   }
		   
		   if(p_countryList.size() == 0 && p_continentList.size()!= 0)
	 	   {
	 		  l_map = new String[p_continentList.size()][4];
	 		  l_map[0][0] = p_continentList.get(0).getD_continentName();
	 		  l_map[0][3] = Integer.toString(p_continentList.get(0).getD_continentArmyValue());
	 	   }

		   TextTable l_tableview = new TextTable(l_columnNames, l_map);
		   l_tableview.printTable();
		   System.out.println();
	}
	
	
}