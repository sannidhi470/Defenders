package Tools;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


/**
 * The class MapEditor tests if we can add continent,if we can add country with or without continent, if we can add neighbour without country or continent.
 *
 */

public class MapEditorTest {

	Connectivity d_connectivity=new Connectivity();
	
	/**
	 *
	 * This method is used to test if can add continent to the user defined maps.
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
	 *
	 */
	
	@Test
	void addNeighbourWithContinentAndCountryTest() 
	{
		MapEditor.addContinent("Asia", 10, d_connectivity);
		MapEditor.addCountry("India", "1", d_connectivity);
		assertEquals(0, MapEditor.addNeighbour(1,1, d_connectivity));
	}
	
	/**
	 *
	 * This method is used to test removing neighbour countries without country and neighbours.
	 *
	 */
	
	@Test
	void removeNeighbourWithoutCountryAndNeighbours()
	{
		MapLoader.loadMap(d_connectivity, "VeryBasic");
		assertEquals(1,MapEditor.removeNeighbour(30, 31, d_connectivity,1));
	}
	
	/**
	 *
	 * This method is used to test removing neighbour with country and without neighbours.
	 *
	 */
	
	@Test
	void removeNeighbourWithCountryWithoutNeighbours()
	{
		MapLoader.loadMap(d_connectivity, "VeryBasic");
		assertEquals(1,MapEditor.removeNeighbour(1, 31, d_connectivity,1));
	}
	
	/**
	 *
	 * This method is used to test removing neighbour with wrong country neighbour.
	 *
	 */
	
	@Test
	void removeNeighbourWrongCountryNeighbour()
	{
		MapLoader.loadMap(d_connectivity, "VeryBasic");
		assertEquals(1,MapEditor.removeNeighbour(1, 6, d_connectivity,1));
	}
	
	/**
	 *
	 * This method is used to test removing neighbour with correct country and neighbour.
	 *
	 */
	
	@Test 
	void removeNeighbourWithCorrectCountryAndNeighbour()
	{
		MapLoader.loadMap(d_connectivity, "VeryBasic");
		assertEquals(0,MapEditor.removeNeighbour(1, 2, d_connectivity,1));
	}
	
	/**
	 *
	 * This method is used to test removing country with wrong country.
	 *
	 */
	
	@Test
	void removeCountryWrongCountryTest()
	{
		MapLoader.loadMap(d_connectivity, "VeryBasic");
		assertEquals(1,MapEditor.removeCountry("abc", d_connectivity));
	}
	
	/**
	 *
	 * This method is used to test removing country with correct country.
	 *
	 */
	
	@Test
	void removeCountryCorrectCountryTest()
	{
		MapLoader.loadMap(d_connectivity, "VeryBasic");
		assertEquals(0,MapEditor.removeCountry("Canada", d_connectivity));
	}
	
	/**
	 *
	 * This method is used to test removing continent with wrong continent.
	 *
	 */
	
	@Test
	void removeContinentWrongContinentTest()
	{
		MapLoader.loadMap(d_connectivity, "VeryBasic");
		assertEquals(1,MapEditor.removeContinent("india", d_connectivity));
	}
	
	/**
	 *
	 * This method is used to test removing continent with correct continent.
	 *
	 */
	
	@Test
	void removeContinentCorrectContinentTest()
	{
		MapLoader.loadMap(d_connectivity, "VeryBasic");
		assertEquals(0,MapEditor.removeContinent("Africa", d_connectivity));
	}

}
