package Tools;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Controllers.GameEngine;
import state.Preload;

class MapLoaderTest {

	private static Connectivity d_connectivity=new Connectivity();
	static GameEngine gameEngine =  new GameEngine();
	
	@BeforeAll
	static void startGame()
	{
		gameEngine.setConnectivity(d_connectivity);
		gameEngine.setCheckIfTest(true);
	}
	
	@Test
	void loadMapTest1() 
	{
		gameEngine.setPhase(new Preload(gameEngine));
		
		MapLoader.loadMap(gameEngine.getConnectivity(),"continent_without_country" );
		Tools.Graph l_graph=new Tools.Graph(d_connectivity.getD_countryList().size(),d_connectivity);
		assertEquals(l_graph.continentConnection(d_connectivity, l_graph), false);
		
		
	}

	@Test
	void loadMapTest2() 
	{
		gameEngine.setPhase(new Preload(gameEngine));
		
		MapLoader.loadMap(gameEngine.getConnectivity(),"multiple_continents_same_country" );
		Tools.Graph l_graph=new Tools.Graph(d_connectivity.getD_countryList().size(),d_connectivity);
		assertEquals(l_graph.continentConnection(d_connectivity, l_graph), false);
		
		
	}
	
	@Test
	void loadMapTest3() 
	{
		gameEngine.setPhase(new Preload(gameEngine));
		
		MapLoader.loadMap(gameEngine.getConnectivity(),"multiple_continents" );
		Tools.Graph l_graph=new Tools.Graph(d_connectivity.getD_countryList().size(),d_connectivity);
		assertEquals(l_graph.continentConnection(d_connectivity, l_graph), false);
		
		
	}
}
