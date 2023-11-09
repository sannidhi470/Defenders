package Tools;

import java.util.LinkedList;

/**
 * Graph class is used to recreate .map file in the graph format
 * 
 */
public class Graph extends validateGraph{
	 
	/**
	 * This method creates a graph.
	 * 
	 * @param d_noOfCountries refers to the total number of countries in the map
	 * @param p_connectivity refers to the connectivity object
	 */
	public Graph(int d_noOfCountries,Connectivity p_connectivity)
	{
		this.d_noOfCountries=d_noOfCountries;
		this.d_adjacentCountriesList=new LinkedList[d_noOfCountries+1];
		for(int i=1;i<=d_noOfCountries;i++)
		{
			d_adjacentCountriesList[i]=new LinkedList();
		}
		for(int i=0;i<p_connectivity.getD_countryList().size();i++)
		{
			for(int j=0;j<p_connectivity.getD_countryList().get(i).getD_neighbours().size();j++)
				this.addCountries(p_connectivity.getD_countryList().get(i).getD_countryId(), p_connectivity.getD_countryList().get(i).getD_neighbours().get(j));
		}

	}
	/**
	 * This method add countries.
	 * 
	 * @param d_noOfCountries refers to the total number of countries
	 * @param p_countryId refers to the countryID of a particular country
	 */
	public void addCountries(int d_noOfCountries,int p_countryId)
	{
		d_adjacentCountriesList[d_noOfCountries].add(p_countryId);
		
	}
	
}