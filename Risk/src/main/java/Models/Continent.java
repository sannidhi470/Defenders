package Models;
import java.util.*;

/**
 * The class Continent defines Continents and it's properties such as ID, Name, Control Value and list of countries it has.
 *
 */

public class Continent {

	private int d_continentId;
	private String d_continentName;
	private int d_continentArmyValue;
	private List<Country> d_countries;
	
	/**
	 * This is a default constructor.
	 *
	 */
	
	public Continent()
	{
		
	}
	
	/**
	 * It initiates a new continent.
	 * @param d_continentId refers to the continent id.
	 * @param d_continentName refers to the name of the continent.
	 * @param d_continentArmyValue refers to the number of armies deployed in the continent.
	 * @param d_countries refers to the list of countries in that aprticular continent.
	 */
	
	public Continent(int d_continentId, String d_continentName, int d_continentArmyValue, List<Country> d_countries) {
		super();
		this.d_continentId = d_continentId;
		this.d_continentName = d_continentName;
		this.d_continentArmyValue = d_continentArmyValue;
		this.d_countries = d_countries;
	}
	
	/**
	 * Gets continent ID.
	 * @return d_continent ID
	 *
	 */

	public int getD_continentId() {
		return d_continentId;
	}
	
	/**
	 * Gets the list of countries present in that continent.
	 * @return list of countries
	 *
	 */

	public List<Country> getD_countries() {
		return d_countries;
	}
	
	/**
	 * Sets list of countries to the continent.
	 *
	 */

	public void setD_countries(List<Country> d_countries) {
		this.d_countries = d_countries;
	}
	
	/**
	 * Gets the number of armies for that continent.
	 * @return the continent army value.
	 *
	 */

	public int getD_continentArmyValue() {
		return d_continentArmyValue;
	}
	
	/**
	 * Sets the continent ID
	 *
	 */

	public void setD_continentId(int d_continentId) {
		this.d_continentId = d_continentId;
	}
	
	/**
	 * Gets the continent Name.
	 * @return the continent Name
	 *
	 */

	public String getD_continentName() {
		return d_continentName;
	}
	
	/**
	 * Sets the continent Name.
	 *
	 */

	public void setD_continentName(String d_continentName) {
		this.d_continentName = d_continentName;
	}
	
	/**
	 * Gets the continent army value.
	 * @return continent army value
	 *
	 */
	
	public int getD_d_continentArmyValue() {
		return d_continentArmyValue;
	}
	
	/**
	 * Sets the continent army value.
	 *
	 */

	public void setD_continentArmyValue(int d_continentArmyValue) {
		this.d_continentArmyValue = d_continentArmyValue;
	}
	
	/**
	 * Gets the country name from continent ID.
	 * @param p_continentId refers to the continent ID.
	 * @param p_countryList refers to the list of countries present in the continent.
	 * @return countries name
	 *
	 */
	
	public ArrayList<Country> d_getCountryFromContinentId(int p_continentId,ArrayList<Country> p_countryList)
	{
		ArrayList<Country> l_countries=new ArrayList<Country>();
		for(Country i:p_countryList)
		{
			if(i.getD_continentId()==p_continentId)
				l_countries.add(i);
		}
		return l_countries;
	}
}