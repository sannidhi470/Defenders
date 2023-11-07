package Views;
import java.util.ArrayList;

import Models.Continent;
import Models.Country;
import Models.LogEntryBuffer;
import Models.Player;
import dnl.utils.text.table.TextTable;

/**
 * The class ViewMap displays the map in an tabular manner.
 *
 */

public class ViewMap {
	
	/**
	 *
	 * This method is used to display the map in an tabular manner.
	 * @param p_continentList refers to the list of continents.
	 * @param p_countryList refers to the list of countries.
	 * @param p_playerList refers to the list of players.
	 *
	 */

	public static void viewMap(ArrayList<Continent> p_continentList, ArrayList<Country> p_countryList, ArrayList<Player> p_playerList) {
		LogEntryBuffer d_logEntryBuffer = new LogEntryBuffer();
		
	 	   String[] l_columnNames = {"Continent", "Country", "Neighbouring Country", "Continent Bonus", "Armies deployed", "Country Owner"};
	 	   if(p_continentList.size() == 0)
	 	   {
	 		   d_logEntryBuffer.log("No continents or countries exist in the map");
	 		   System.out.println("No continents or countries exist in the map");
	 		   return;
	 	   }
	 	   
	 	   int l_lineCount=0;
	 	   for(int i=0; i<p_continentList.size();i++) {
	 		   if(p_continentList.get(i).getD_countries().size()!=0)
	 			  l_lineCount = l_lineCount + p_continentList.get(i).getD_countries().size();
	 		   else
	 			  l_lineCount = l_lineCount+1;
	 	   }
	 	   
	 	   String[][] l_map = new String[l_lineCount][6];
			   for(int l_lineIterator=0; l_lineIterator<l_lineCount; l_lineIterator++) 
			   {
				   for(int l_continentIterator=0; l_continentIterator<p_continentList.size(); l_continentIterator++) 
				   {
					   if(p_continentList.get(l_continentIterator).getD_countries().size() != 0) 
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
						   	   l_map[l_lineIterator][3] = Integer.toString(p_continentList.get(l_continentIterator).getD_continentBonusValue());
						   	   l_map[l_lineIterator][4] = Integer.toString(l_country.getD_armyDeployedOnCountry());
						   	   l_map[l_lineIterator][5] = "Neutral";
						   	   for(int l_playerIterator=0; l_playerIterator<p_playerList.size(); l_playerIterator++)
						   	   {
						   		   for(int l_countryOwned=0; l_countryOwned<p_playerList.get(l_playerIterator).getD_Country().size(); l_countryOwned++)
						   		   {
						   			   if(p_playerList.get(l_playerIterator).getD_Country().get(l_countryOwned).getD_countryId() == l_country.getD_countryId())
						   			   {
						   				  l_map[l_lineIterator][5] = p_playerList.get(l_playerIterator).getD_playerName();
						   			   }
						   		   }
						   	   }
						   	l_lineIterator=l_lineIterator+1;
			    		   }
					   } else 
					   {
						   l_map[l_lineIterator][0] = p_continentList.get(l_continentIterator).getD_continentName();
						   l_map[l_lineIterator][3] = Integer.toString(p_continentList.get(l_continentIterator).getD_continentBonusValue());
						   l_lineIterator=l_lineIterator+1;
					   }
				   }
			   }

			   TextTable l_tableview = new TextTable(l_columnNames, l_map);
			   l_tableview.printTable();
			   System.out.println();
		}
}
