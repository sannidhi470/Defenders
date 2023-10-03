package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Country {

	private int d_countryId;
	private String d_countryName;
	private int d_continentId;
	private ArrayList<Integer> d_neighbours=new ArrayList<>();
	public Country()
	{
		
	}
	
	public Country(int p_countryId, String p_countryName, int p_continentId,ArrayList<Integer> p_neighbours) {
		this.d_countryId = p_countryId;
		this.d_countryName = p_countryName;
		this.d_continentId = p_continentId;
		this.d_neighbours=p_neighbours;
	}

	public ArrayList<Integer> getD_neighbours() {
		return d_neighbours;
		
	}


	public void setD_neighbours(int p_countryId,HashMap<Integer,ArrayList<Integer>> p_neighbouringCountries) {
		this.d_neighbours=p_neighbouringCountries.get(p_countryId);
	}

	public int getD_countryId() {
		return d_countryId;
	}

	public int getD_continentId() {
		return d_continentId;
	}

	public void setD_continentId(int p_continentId) {
		this.d_continentId = p_continentId;
	}

	public void setD_countryId(int p_countryId) {
		this.d_countryId = p_countryId;
	}

	public String getD_countryName() {
		return d_countryName;
	}

	public void setD_countryName(String p_countryName) {
		this.d_countryName = p_countryName;
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