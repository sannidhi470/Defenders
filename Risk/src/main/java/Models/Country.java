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
	
	/**
	 * This is a default constructor.
	 *
	 */
	public Country()
	{
		
	}
	
	/**
	 * It initiates a new country.
	 * @param d_countryId refers to the country ID.
	 * @param d_countryName refers to the name of the country.
	 * @param d_continentId refers to the continent ID.
	 * @param d_neighbours refers to the list of neighbours for that particular country.
	 */
	
	public Country(int d_countryId, String d_countryName, int d_continentId,ArrayList<Integer> d_neighbours) {
		this.d_countryId = d_countryId;
		this.d_countryName = d_countryName;
		this.d_continentId = d_continentId;
		this.d_neighbours=d_neighbours;
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

	public void setD_neighbours(int l_countryId,HashMap<Integer,ArrayList<Integer>> l_neighbouringCountries) {
		this.d_neighbours=l_neighbouringCountries.get(l_countryId);
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

	public void setD_continentId(int d_continentId) {
		this.d_continentId = d_continentId;
	}
	
	/**
	 * Sets the country ID.
	 * @param d_countryId refers to the country ID
	 */

	public void setD_countryId(int d_countryId) {
		this.d_countryId = d_countryId;
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

	public void setD_countryName(String d_countryName) {
		this.d_countryName = d_countryName;
	}
	
	/**
	 * Gets the country name from country ID.
	 * @param p_countryList refers to the list of countries.
	 * @param p_countryId refers to the country ID.
	 */

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