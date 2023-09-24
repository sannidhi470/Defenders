package Utilities;
import java.util.*;

import Models.Continent;
import Models.Country;

public class Connectivity {

    private List<Continent> d_continentList;
	
	private List<Country> d_countryList;


	public List<Continent> getD_continentList() { 
		return d_continentList;
	}

	public void setD_continentList(List<Continent> d_continentList) {
		this.d_continentList = d_continentList;
	}

	public List<Country> getD_countryList() {
		return d_countryList;
	}

	public void setD_countryList(List<Country> d_countryList) {
		this.d_countryList = d_countryList;
	}
}
