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
	
	public static void viewMap(ArrayList<Continent> continentList, ArrayList<Country> l_countryList) {
 	   String[] columnNames = {"Continent", "Country", "Neighbouring Country", "Army Count"};
 	   String[][] string2DArray = new String[l_countryList.size()][4];
		   for(int i=0; i<l_countryList.size(); i++) 
		   {
			   for(int j=0; j<continentList.size(); j++) 
			   {
				   for(int k=0;k<continentList.get(j).getD_countries().size();k++)
	    		   {
					   if(k==0) 
					   {
						   string2DArray[i][0] = continentList.get(j).getD_continentName();
					   }
				   	   Country l_country= continentList.get(j).getD_countries().get(k);

				   	   string2DArray[i][1] = l_country.getD_countryName();
				   	   ArrayList<Integer> l_neighbourID = l_country.getD_neighbours();
				   	   String temp="";
				   	   for(Integer f: l_neighbourID)
	    			   {
	    				    temp = temp + " "+l_country.get_nameFromId(l_countryList,f)+",";
	    			   }
				   	   string2DArray[i][2] = temp;
				   	   string2DArray[i][3] = Integer.toString(continentList.get(j).getD_continentArmyValue());
				   	   i=i+1;
	    		   }
			   }
		   }

		   TextTable tt = new TextTable(columnNames, string2DArray);
		   tt.printTable();	
	}
	
	
}