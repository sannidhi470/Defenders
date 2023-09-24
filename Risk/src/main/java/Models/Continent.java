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
	
	public Continent(int d_continentId, String d_continentName, int d_continentArmyValue, List<Country> d_countries) {
		super();
		this.d_continentId = d_continentId;
		this.d_continentName = d_continentName;
		this.d_continentArmyValue = d_continentArmyValue;
		this.d_countries = d_countries;
	}

	public int getD_continentId() {
		return d_continentId;
	}

	public List<Country> getD_countries() {
		return d_countries;
	}

	public void setD_countries(List<Country> d_countries) {
		this.d_countries = d_countries;
	}

	public int getD_continentArmyValue() {
		return d_continentArmyValue;
	}

	public void setD_continentId(int d_continentId) {
		this.d_continentId = d_continentId;
	}

	public String getD_continentName() {
		return d_continentName;
	}

	public void setD_continentName(String d_continentName) {
		this.d_continentName = d_continentName;
	}
	
	public int getD_d_continentArmyValue() {
		return d_continentArmyValue;
	}

	public void setD_continentArmyValue(int d_continentArmyValue) {
		this.d_continentArmyValue = d_continentArmyValue;
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