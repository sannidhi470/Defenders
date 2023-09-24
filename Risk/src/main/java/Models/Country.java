package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Country {

	private int d_countryId;
	private String d_countryName;
	private int d_continentId;
	private ArrayList<Integer> d_neighbours;
	public Country()
	{
		
	}
	
	public Country(int d_countryId, String d_countryName, int d_continentId,ArrayList<Integer> d_neighbours) {
		this.d_countryId = d_countryId;
		this.d_countryName = d_countryName;
		this.d_continentId = d_continentId;
		this.d_neighbours=d_neighbours;
	}

	public ArrayList<Integer> getD_neighbours() {
		return d_neighbours;
		
	}


	public void setD_neighbours(int l_countryId,HashMap<Integer,ArrayList<Integer>> l_neighbouringCountries) {
		this.d_neighbours=l_neighbouringCountries.get(l_countryId);
	}

	public int getD_countryId() {
		return d_countryId;
	}

	public int getD_continentId() {
		return d_continentId;
	}

	public void setD_continentId(int d_continentId) {
		this.d_continentId = d_continentId;
	}

	public void setD_countryId(int d_countryId) {
		this.d_countryId = d_countryId;
	}

	public String getD_countryName() {
		return d_countryName;
	}

	public void setD_countryName(String d_countryName) {
		this.d_countryName = d_countryName;
	}
	public String get_nameFromId(ArrayList<Country> p_countryList,int p_countryId)  
	{
		//need to add exception
		for(Country i:p_countryList)
		{
			if(i.getD_countryId()==p_countryId)
			{
				return i.getD_countryName();
			}
			
		}
		return " ";
		
	}
}