package Models;
import java.util.*;

public class Continent {

	private int d_continentId;
	private String d_continentName;
	private int d_continentArmyValue;
	private List<Country> d_countries;
	
	public Continent()
	{
		
	}
	
	public Continent(int p_continentId, String p_continentName, int p_continentArmyValue, List<Country> p_countries) {
		super();
		this.d_continentId = p_continentId;
		this.d_continentName = p_continentName;
		this.d_continentArmyValue = p_continentArmyValue;
		this.d_countries = p_countries;
	}

	public int getD_continentId() {
		return d_continentId;
	}

	public List<Country> getD_countries() {
		return d_countries;
	}

	public void setD_countries(List<Country> p_countries) {
		this.d_countries = p_countries;
	}

	public int getD_continentArmyValue() {
		return d_continentArmyValue;
	}

	public void setD_continentId(int p_continentId) {
		this.d_continentId = p_continentId;
	}

	public String getD_continentName() {
		return d_continentName;
	}

	public void setD_continentName(String p_continentName) {
		this.d_continentName = p_continentName;
	}
	
	public int getD_d_continentArmyValue() {
		return d_continentArmyValue;
	}

	public void setD_continentArmyValue(int p_continentArmyValue) {
		this.d_continentArmyValue = p_continentArmyValue;
	}
	
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