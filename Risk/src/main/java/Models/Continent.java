package Models;
import java.util.*;

/**
 * The class Continent defines Continents and it's properties such as ID, Name, Control Value and list of countries it has.
 *
 */

public class Continent {

	private int d_continentId;
	private String d_continentName;
	private int d_continentBonusValue;
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
	 * @param p_continentId refers to the continent id.
	 * @param p_continentName refers to the name of the continent.
	 * @param p_continentArmyValue refers to the number of armies deployed in the continent.
	 * @param p_countries refers to the list of countries in that particular continent.
	 */
	
	public Continent(int p_continentId, String p_continentName, int p_continentBonusValue, List<Country> p_countries) {
		super();
		this.d_continentId = p_continentId;
		this.d_continentName = p_continentName;
		this.d_continentBonusValue = p_continentBonusValue;
		this.d_countries = p_countries;
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
	 * @param list of countries
	 */

	public void setD_countries(List<Country> p_countries) {
		this.d_countries = p_countries;
	}
	
	/**
	 * Gets the number of armies for that continent.
	 * @return the continent army value.
	 * 
	 */

	public int getD_continentBonusValue() {
		return d_continentBonusValue;
	}
	
	/**
	 * Sets the continent ID
	 * @param list of continent
	 */

	public void setD_continentId(int p_continentId) {
		this.d_continentId = p_continentId;
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
	 * @param continent name
	 */

	public void setD_continentName(String p_continentName) {
		this.d_continentName = p_continentName;
	}
	
	/**
	 * Gets the continent army value.
	 * @return continent army value
	 *
	 */
	
	public int getD_d_continentArmyValue() {
		return d_continentBonusValue;
	}
	
	/**
	 * Sets the continent army value.
	 * @param army value
	 */

	public void setD_continentBonusValue(int p_continentArmyValue) {
		this.d_continentBonusValue = p_continentArmyValue;
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