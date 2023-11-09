package Models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Tools.Connectivity;
import Tools.MapLoader;

/**
 * This class tests the components of Country Model
 */
public class CountryTest {
	
	private Connectivity d_connectivity=new Connectivity();


	/**
	*
	* This method is used to loadmap.
	*
	*/
	
	@BeforeEach
	public void addPlayersIfNotThereTest() 
	{
		MapLoader.loadMap(d_connectivity, "VeryBasic");
	}
			

	/**
	 * 
	 * The method tests if the function getCountryFromID returns correct Country .
	 *
	 */
	
	@Test
	void getCountryFromIDTest() {
		Country c=Country.getCountryFromID(d_connectivity.getD_countryList(),11);
		assertEquals(c.getD_countryName(),"India");
	}
	

	/**
	 * 
	 * The method tests if the function getCountryFromIName returns correct Country .
	 *
	 */
	@Test
	void getCountryFromNameTest() {
		Country c= new Country();
		Country result	=c.getCountryFromName(d_connectivity.getD_countryList(),"India");
		assertEquals(result.getD_countryId(),11);
	}

}
