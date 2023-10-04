package Tools;


import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import Models.Continent;
import Models.Country;
import Tools.Connectivity;
import Tools.MapEditor;
/**
 * The class MapEditor tests if we can add continent,if we can add country with or without continent, if we can add neighbour without country or continent.
 *
 */
class MapEditorTest {

	Connectivity d_connectivity=new Connectivity();
	
	@Test
	void addContinentTest() 
	{
		assertEquals(0,MapEditor.addContinent("Asia",10,d_connectivity));
	}
	
	@Test
	void addCountryWithContinentTest()
	{
		MapEditor.addContinent("Asia",10,d_connectivity);
		MapEditor.addContinent("Europe",10,d_connectivity);
		
		assertEquals(0,MapEditor.addCountry("India", "1", d_connectivity));
	}
	
	@Test
	void addCountryWithOutContinentTest() 
	{
		
		assertEquals(1,MapEditor.addCountry("India", "1", d_connectivity));
	}
	
	@Test
	void addNeighbourWithoutContinentAndCountryTest() 
	{
		assertEquals(1,MapEditor.addNeighbour(1, 2, d_connectivity));
	}
	
	@Test
	void addNeighbourWithContinentWithoutCountryTest()
	{
		MapEditor.addContinent("Asia", 10, d_connectivity);
		assertEquals(1,MapEditor.addNeighbour(1, 2, d_connectivity));
	}
	
	@Test
	void addNeighbourWithContinentAndCountryTest() 
	{
		MapEditor.addContinent("Asia", 10, d_connectivity);
		MapEditor.addCountry("India", "1", d_connectivity);
		assertEquals(0, MapEditor.addNeighbour(1,1, d_connectivity));
	}
	

}
