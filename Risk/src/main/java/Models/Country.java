package Models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The class Country defines Countries and it's properties such as ID, Name, Continent ID and list of neighbours.
 *
 */

public class Country {

	private int d_countryId;
	private String d_countryName;
	private int d_continentId;
	private ArrayList<Integer> d_neighbours=new ArrayList<>();
	private int d_armyDeployedOnCountry=0;
	
	/**
	 * This is a default constructor.
	 *
	 */
	public Country()
	{
		
	}
	

	/**
	 * It initiates a new country.
	 * @param p_countryId refers to the country ID.
	 * @param p_countryName refers to the name of the country.
	 * @param p_continentId refers to the continent ID.
	 * @param p_neighbours refers to the list of neighbours for that particular country.
	 */

	public Country(int p_countryId, String p_countryName, int p_continentId,ArrayList<Integer> p_neighbours) {
		this.d_countryId = p_countryId;
		this.d_countryName = p_countryName;
		this.d_continentId = p_continentId;
		this.d_neighbours=p_neighbours;
	}
	
	/**
	 * Gets the list of neighbours.
	 * @return neighbours.
	 */

	public ArrayList<Integer> getD_neighbours() {
		return d_neighbours;
		
	}
	
	/**
	 * Sets the list of neighbours.
	 * @param l_countryId refers to the country ID.
	 * @param l_neighbouringCountries refers to the neighbouring countries.
	 */

	public void setD_neighbours(int p_countryId,HashMap<Integer,ArrayList<Integer>> p_neighbouringCountries) {
		this.d_neighbours=p_neighbouringCountries.get(p_countryId);
	}
	
	/**
	 * Gets the country ID.
	 * @return country ID
	 */

	public int getD_countryId() {
		return d_countryId;
	}
	
	/**
	 * Gets the continent ID.4
	 * @return continent ID
	 */

	public int getD_continentId() {
		return d_continentId;
	}
	
	/**
	 * Sets the continent ID.
	 * @param d_continentId refers to the continent ID
	 */

	public void setD_continentId(int p_continentId) {
		this.d_continentId = p_continentId;
	}
	
	/**
	 * Sets the country ID.
	 * @param d_countryId refers to the country ID
	 */

	public void setD_countryId(int p_countryId) {
		this.d_countryId = p_countryId;
	}
	
	/**
	 * Gets the country Name.
	 * @return country Name
	 */

	public String getD_countryName() {
		return d_countryName;
	}
	
	/**
	 * Sets the country Name.
	 * @param d_countryName refers to the country Name
	 */

	public void setD_countryName(String p_countryName) {
		this.d_countryName = p_countryName;
	}
	
	/**
	 * Gets the country name from country ID.
	 * @param p_countryList refers to the list of countries.
	 * @param p_countryId refers to the country ID.
	 */

	public String get_nameFromId(ArrayList<Country> p_countryList,int p_countryId)  
	{
		for(Country i:p_countryList)
		{
			if(i.getD_countryId()==p_countryId)
			{
				return i.getD_countryName();
			}
			
		}
		return " ";
		
	}
	public int getD_armyDeployedOnCountry() {
		return d_armyDeployedOnCountry;
	}

	public void setD_armyDeployedOnCountry(int d_armyDeployedOnCountry) {
		this.d_armyDeployedOnCountry = d_armyDeployedOnCountry;
	}
	
	public Country getCountryFromName(ArrayList<Country> p_countryList,String p_countryName)
	{
		for(Country c:p_countryList)
		{
			if(c.getD_countryName().equals(p_countryName))
				return c;
		}
		return null;
		
	}
}