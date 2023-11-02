package Tools;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Controllers.GameEngine;
import Models.Country;
import Models.Player;
import state.Play;

/**
 * The class PlayerGameplay tests all the player perspective.
 *
 */

class PlayersGameplayTest {
	
	private ArrayList<Player> d_playersArray = Play.getL_playersArray();
	private Connectivity d_connectivity=new Connectivity();
	
	/**
	*
	* This method is used to test addition of players if not there.
	*
	*/
	
	@BeforeEach
	public void addPlayersIfNotThereTest() 
	{
		
		if(d_playersArray.size() == 0)
		{
			MapLoader.loadMap(d_connectivity, "VeryBasic");
			
			Player l_player = new Player();
			l_player.setD_playerName("player1");
			d_playersArray.add(l_player);
			
			PlayersGameplay.assigncountries(d_playersArray, d_connectivity.getD_countryList(), d_connectivity.getD_continentList());
			
			PlayersGameplay.assignArmiesToPlayers(d_playersArray);
		}
		
	}
	
	/**
	 * 
	 * The method tests if countries are assigned without players.
	 *
	 */
	
	@Test
	void assigncountriesWithoutPlayersTest() {
		d_playersArray.clear();
		assertEquals(1, PlayersGameplay.assigncountries(d_playersArray, d_connectivity.getD_countryList(),
				d_connectivity.getD_continentList()));
	}
	
	/**
	 * 
	 * The method tests if players are not assigned with countries.
	 *
	 */

	@Test
	void assigncountriesWithplayersWithoutCountriesTest() {
		Player l_player = new Player();
		l_player.setD_playerName("player2");
		d_playersArray.add(l_player);

		ArrayList<Country> l_countryList = new ArrayList<Country>();

		assertEquals(1,
				PlayersGameplay.assigncountries(d_playersArray, l_countryList, d_connectivity.getD_continentList()));
	}
	
	/**
	 * 
	 * The method tests if countries are more than the number of players.
	 *
	 */

	@Test
	void assigncountriesWithPlayersAndWithLessCountriesTest() {
		Player l_player = new Player();
		l_player.setD_playerName("player2");
		d_playersArray.add(l_player);
		Country India = new Country();

		ArrayList<Country> l_countryList = new ArrayList<Country>();
		l_countryList.add(India);

	}

	/**
	 * 
	 * The method tests if armies are available to attack or not.
	 *
	 */
	
	@Test
	void checkArmyAvailableSendMoreTroopsTest() 
	{
		
		boolean l_truthValue = PlayersGameplay.checkArmyAvailable((d_playersArray.get(0).getD_armyCount()+1), d_playersArray.get(0));
		assertEquals(false, l_truthValue);
		
	}
	
	/**
	 * 
	 * The method tests if assignment of armies is correct.  
	 * 
	 */
	
	@Test
	void assignArmiesToPlayersManualCorrectTest()
	{
		Player l_player= d_playersArray.get(0);
		Player l_playerTest = new Player(l_player);
		
		ArrayList<Player> l_playerArrayTest = new ArrayList<Player>();
		l_playerArrayTest.add(l_playerTest);
		
		int l_countryListSize=l_playerArrayTest.get(0).getD_Country().size()/3;
		int l_armyCount=Math.max(3, l_countryListSize);
		int l_tempContinentCount=0;
		if(l_playerArrayTest.get(0).getD_playerContinent().size()!=0)
		{
			for(int j=0;j<l_playerArrayTest.get(0).getD_playerContinent().size();j++)
				l_tempContinentCount=l_tempContinentCount+ l_playerArrayTest.get(0).getD_playerContinent().get(j).getD_continentBonusValue();
		}
		l_armyCount+=l_tempContinentCount;
		l_playerArrayTest.get(0).setD_armyCount(l_armyCount);
		System.out.println(l_armyCount);
		
		PlayersGameplay.assignArmiesToPlayers(d_playersArray);
		
		assertEquals(d_playersArray.get(0).getD_armyCount(), l_playerArrayTest.get(0).getD_armyCount());
	}
	
	/**
	 * 
	 * The method tests if assignment of armies is wrong.  
	 * 
	 */
	
	@Test
	void assignArmiesToPlayersManualWrongTest()
	{
		Player l_player= d_playersArray.get(0);
		Player l_playerTest = new Player(l_player);
		
		
		ArrayList<Player> l_playerArrayTest = new ArrayList<Player>();
		l_playerArrayTest.add(l_playerTest);
		
		int l_countryListSize=l_playerArrayTest.get(0).getD_Country().size()/3;
		int l_armyCount=Math.max(3, l_countryListSize);
		int l_tempContinentCount=0;
		if(l_playerArrayTest.get(0).getD_playerContinent().size()!=0)
		{
			for(int j=0;j<l_playerArrayTest.get(0).getD_playerContinent().size();j++)
				l_tempContinentCount=l_tempContinentCount+ l_playerArrayTest.get(0).getD_playerContinent().get(j).getD_continentBonusValue();
		}
		l_armyCount+=l_tempContinentCount;
		
		l_playerArrayTest.get(0).setD_armyCount(l_armyCount+1);
		System.out.println(l_armyCount);
		
		PlayersGameplay.assignArmiesToPlayers(d_playersArray);
		
		assertNotEquals(d_playersArray.get(0).getD_armyCount(), l_playerArrayTest.get(0).getD_armyCount());
	}
}
