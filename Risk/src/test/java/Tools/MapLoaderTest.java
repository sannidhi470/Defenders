package Tools;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Controllers.GameEngine;
import state.Preload;

/**
 * The class MapLoader tests if we can load the valid map or not.
 *
 */
public class MapLoaderTest {

	private static Connectivity d_connectivity=new Connectivity();
	static GameEngine gameEngine =  new GameEngine();
	
	@BeforeAll
	static void startGame()
	{
		gameEngine.setConnectivity(d_connectivity);
		gameEngine.setCheckIfTest(true);
	}
	
	
	/**
	 * This test checks whether a map containing continent without country could be loaded for not
	 */
	@Test
	void loadMapTest1() 
	{
		gameEngine.setPhase(new Preload(gameEngine));
		
		MapLoader.loadMap(gameEngine.getConnectivity(),"continent_without_country" );
		Tools.Graph l_graph=new Tools.Graph(d_connectivity.getD_countryList().size(),d_connectivity);
		assertEquals(l_graph.continentConnection(d_connectivity, l_graph), false);
		
		
	}

	/**
	 * This test checks whether a map containing same country assigned to multiple continents could be loaded or not.
	 */
	@Test
	void loadMapTest2() 
	{
		gameEngine.setPhase(new Preload(gameEngine));
		
		MapLoader.loadMap(gameEngine.getConnectivity(),"multiple_continents_same_country" );
		Tools.Graph l_graph=new Tools.Graph(d_connectivity.getD_countryList().size(),d_connectivity);
		assertEquals(l_graph.continentConnection(d_connectivity, l_graph), false);
		
		
	}
	
	/**
	 * This test checks whether a map containing multiple continents could be loaded or not.
	 */
	@Test
	void loadMapTest3() 
	{
		gameEngine.setPhase(new Preload(gameEngine));
		
		MapLoader.loadMap(gameEngine.getConnectivity(),"multiple_continents" );
		Tools.Graph l_graph=new Tools.Graph(d_connectivity.getD_countryList().size(),d_connectivity);
		assertEquals(l_graph.continentConnection(d_connectivity, l_graph), false);
		
		
	}
}
