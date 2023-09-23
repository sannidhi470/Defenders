package Models;

public class Country {

	private int d_countryId;
	private String d_countryName;
	private int d_continentId;
	
	public Country(int d_countryId, String d_countryName, int d_continentId) {
		this.d_countryId = d_countryId;
		this.d_countryName = d_countryName;
		this.d_continentId = d_continentId;
	}

	public int getD_countryId() {
		return d_countryId;
	}

	public void setD_countryId(int d_countryId) {
		this.d_countryId = d_countryId;
	}

	public String getD_countryName() {
		return d_countryName;
	}

	public void setD_countryName(String d_countryName) {
		this.d_countryName = d_countryName;
	}
}
