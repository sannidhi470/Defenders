package Utilities;
import java.util.*;

import Models.Continent;
import Models.Country;

public class Connectivity {

    private ArrayList<Continent> d_continentList;
	
	private ArrayList<Country> d_countryList;

	private String d_pathName;
	
	private String d_FilepathName;

	public String getD_FilepathName() {
		return d_FilepathName;
	}

	public void setD_FilepathName(String d_FilepathName) {
		this.d_FilepathName = d_FilepathName;
	}

	public String getD_pathName() {
		return d_pathName;
	}

	public void setD_pathName(String d_pathName) {
		this.d_pathName = d_pathName;
	}

	public ArrayList<Continent> getD_continentList() { 
		return d_continentList;
	}

	public void setD_continentList(ArrayList<Continent> d_continentList) {
		this.d_continentList = d_continentList;
	}

	public ArrayList<Country> getD_countryList() {
		return d_countryList;
	}

	public void setD_countryList(ArrayList<Country> d_countryList) {
		this.d_countryList = d_countryList;
	}
}