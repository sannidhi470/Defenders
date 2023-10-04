package Tools;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


/**
 * The class MapEditor tests if we can add continent,if we can add country with or without continent, if we can add neighbour without country or continent.
 *
 */

class MapEditorTest {

	Connectivity d_connectivity=new Connectivity();
	
	/**
	 *
	 * This method is used to test if can add continent to the user defined maps.
	 * @param p_continentId refers to the ID of the continent.
	 * @param p_continentvalue refers to the control value of the continent.
	 * @param p_connectivity refers to the object of the connectivity class.
	 *
	 */
	
	@Test
	void addContinentTest() 
	{
		assertEquals(0,MapEditor.addContinent("Asia",10,d_connectivity));
	}
	
	/**
	 *
	 * This method is to test adding country with continent.
	 * @param p_countryId refers to the ID of the country.
	 * @param p_continentId refers to the ID of the continent.
	 * @param p_connectivity refers to the object of the connectivity class.
	 *
	 */
	
	@Test
	void addCountryWithContinentTest()
	{
		MapEditor.addContinent("Asia",10,d_connectivity);
		MapEditor.addContinent("Europe",10,d_connectivity);
		
		assertEquals(0,MapEditor.addCountry("India", "1", d_connectivity));
	}
	
	/**
	 *
	 * This method is to test adding country without continent.
	 * @param p_countryId refers to the ID of the country.
	 * @param p_continentId refers to the ID of the continent.
	 * @param p_connectivity refers to the object of the connectivity class.
	 *
	 */
	
	@Test
	void addCountryWithOutContinentTest() 
	{
		
		assertEquals(1,MapEditor.addCountry("India", "1", d_connectivity));
	}
	
	/**
	 *
	 * This method is used to test adding neighbouring countries without country and continent.
	 * @param p_countryId refers to the ID of the country.
	 * @param p_neighbourcountryId refers to the ID of the neighbouring country.
	 * @param p_connectivity refers to the object of the connectivity class.
	 *
	 */
	
	@Test
	void addNeighbourWithoutContinentAndCountryTest() 
	{
		assertEquals(1,MapEditor.addNeighbour(1, 2, d_connectivity));
	}
	
	/**
	 *
	 * This method is used to test adding neighbouring countries with continent without country.
	 * @param p_countryId refers to the ID of the country.
	 * @param p_neighbourcountryId refers to the ID of the neighbouring country.
	 * @param p_connectivity refers to the object of the connectivity class.
	 *
	 */
	
	@Test
	void addNeighbourWithContinentWithoutCountryTest()
	{
		MapEditor.addContinent("Asia", 10, d_connectivity);
		assertEquals(1,MapEditor.addNeighbour(1, 2, d_connectivity));
	}
	
	/**
	 *
	 * This method is used to test adding neighbouring countries with continent and country.
	 * @param p_countryId refers to the ID of the country.
	 * @param p_neighbourcountryId refers to the ID of the neighbouring country.
	 * @param p_connectivity refers to the object of the connectivity class.
	 *
	 */
	
	@Test
	void addNeighbourWithContinentAndCountryTest() 
	{
		MapEditor.addContinent("Asia", 10, d_connectivity);
		MapEditor.addCountry("India", "1", d_connectivity);
		assertEquals(0, MapEditor.addNeighbour(1,1, d_connectivity));
	}
	

}
