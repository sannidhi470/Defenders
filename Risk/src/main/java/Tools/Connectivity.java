package Tools;
import java.util.*;

import Models.Continent;
import Models.Country;

public class Connectivity {

    private ArrayList<Continent> d_continentList = new ArrayList<Continent>();
	
	private ArrayList<Country> d_countryList = new ArrayList<Country>();

	private String d_pathName;
	
	private String d_FilepathName;

	public String getD_FilepathName() {
		return d_FilepathName;
	}

	public void setD_FilepathName(String p_FilepathName) {
		this.d_FilepathName = p_FilepathName;
	}

	public String getD_pathName() {
		return d_pathName;
	}

	public void setD_pathName(String p_pathName) {
		this.d_pathName = p_pathName;
	}

	public ArrayList<Continent> getD_continentList() { 
		return d_continentList;
	}

	public void setD_continentList(ArrayList<Continent> p_continentList) {
		this.d_continentList = p_continentList;
	}

	public ArrayList<Country> getD_countryList() {
		return d_countryList;
	}

	public void setD_countryList(ArrayList<Country> p_countryList) {
		this.d_countryList = p_countryList;
	}
}