package Utilities;


import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import Models.Continent;
import Models.Country;
import Tools.Connectivity;
import Tools.MapEditor;

class MapEditorTest {

	Connectivity d_connectivity=new Connectivity();
	
	
	@BeforeClass
	public static void beforeClass()
	{
		
	}
	@Test
	void addCountryWithContinentTest() throws IOException
	{
		MapEditor.addContinent("Asia",10,d_connectivity);
		MapEditor.addContinent("Europe",10,d_connectivity);
		
		System.out.println(MapEditor.addCountry("India", "1", d_connectivity));
	}
	@Test
	void addCountryWithOutContinentTest() throws IOException
	{
		
		assertEquals(1,MapEditor.addCountry("India", "Asia", d_connectivity));
	}
	@Test
	void addContinentTest() throws IOException 
	{
		assertEquals(0,MapEditor.addContinent("Asia",10,d_connectivity));
	}

}
