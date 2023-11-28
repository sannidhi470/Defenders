package Tools;
import java.util.*;

import Models.Continent;
import Models.Country;
import Models.Player;

/**
 * The class Connectivity manages the transfer of data from skeleton to other classes where the map objects are used.
 *
 */

public class Connectivity {

    private ArrayList<Continent> d_continentList = new ArrayList<Continent>();
	
	private ArrayList<Country> d_countryList = new ArrayList<Country>();

	private String d_pathName;
	
	private String d_FilepathName;
	
	private String d_mapName;
	
	private Player d_winnerPlayer = new Player();
	
	public Player getD_winnerPlayer() {
		return d_winnerPlayer;
	}

	public void setD_winnerPlayer(Player d_winnerPlayer) {
		this.d_winnerPlayer = d_winnerPlayer;
	}

	/**
	 * Function is used to get the name of current map
	 * @return name of map
	 */	
	public String getD_mapName() {
		return d_mapName;
	}
	
	/**
	 * Used to set the name of current map
	 * @param d_mapName name of map to be set
	 */
	public void setD_mapName(String d_mapName) {
		this.d_mapName = d_mapName;
	}

	/**
	 * Gets the File path address.
	 * @return file path
	 */

	public String getD_FilepathName() {
		return d_FilepathName;
	}
	
	/**
	 * Sets the File path address
	 * @param p_FilepathName refers to the address of the file.
	 */

	public void setD_FilepathName(String p_FilepathName) {
		this.d_FilepathName = p_FilepathName;
	}
	
	/**
	 * Gets the path name.
	 * @return path name
	 */

	public String getD_pathName() {
		return d_pathName;
	}
	
	/**
	 * Sets the path name
	 * @param p_pathName refers to the file name.
	 */

	public void setD_pathName(String p_pathName) {
		this.d_pathName = p_pathName;
	}
	
	/**
	 * Gets the continent list.
	 * @return the continent list
	 */

	public ArrayList<Continent> getD_continentList() { 
		return d_continentList;
	}
	
	/**
	 * Sets the continent list.
	 * @param p_continentList refers to the list of continents.
	 */

	public void setD_continentList(ArrayList<Continent> p_continentList) {
		this.d_continentList = p_continentList;
	}
	
	/**
	 * Gets the list of countries.
	 * @return the country list
	 */

	public ArrayList<Country> getD_countryList() {
		return d_countryList;
	}
	
	public Country getCountryByID(int ID)
	{
		for(Country c : d_countryList)
		{
			if(c.getD_countryId()==ID)
			{
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Sets the country list.
	 * @param p_countryList refers to the list of the countries.
	 */

	public void setD_countryList(ArrayList<Country> p_countryList) {
		this.d_countryList = p_countryList;
	}
}