package Tools;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Models.Continent;
import Models.Country;
import Models.Player;
import state.Play;

/**
 * The class PlayerGameplay tests all the player perspective.
 *
 */

class PlayersGameplayTest {
	
	private ArrayList<Player> d_playersArray = new ArrayList<Player>();
			//Play.getL_playersArray();
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
			
			Player l_player1 = new Player();
			l_player1.setD_playerName("player1");
			
			Player l_player2 = new Player();
			l_player2.setD_playerName("player2");
			
			d_playersArray.add(l_player1);
			d_playersArray.add(l_player2);
			
			
			//PlayersGameplay.assigncountries(d_playersArray, d_connectivity.getD_countryList(), d_connectivity.getD_continentList());
			
			//PlayersGameplay.assignArmiesToPlayers(d_playersArray);
		}
		
	}
	
	/**
	 * 
	 * The method tests if countries are assigned without players.
	 *
	 */
	
	@Test
	void assigncountriesWithoutPlayersTest() 
	{
		
		
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
	void assigncountriesWithplayersWithoutCountriesTest() 
	{
		
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
	void assigncountriesWithPlayersAndWithLessCountriesTest() 
	{
		
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
	
	
	
	@Test //Advance from country that doesn't belong to players.	
	void advanceFromCountryNotBelongToPlayerTest()
	{	
		Player l_player1 = d_playersArray.get(0);
		
		
		Country country1 = d_connectivity.getD_countryList().get(0);
		Country country2 = d_connectivity.getD_countryList().get(1);
		
		PlayersGameplay.clearNutrealCountry();
		PlayersGameplay.addNutrealCountry(country1);
		PlayersGameplay.addNutrealCountry(country2);
		country1.setD_armyDeployedOnCountry(10);
		
		ArrayList<Continent> continentList = d_connectivity.getD_continentList();
		
		int result = PlayersGameplay.advance(l_player1, d_playersArray, country2, country1, 4, continentList,d_connectivity);
		
		
		//System.out.println("Resulr: "+result);
		assertEquals(result, 1);
		
	}
	
	@Test //Advance from country that belong to players, to country doesn't belong to player.That also means attack on neutral country	
	void advanceToCountryNotBelongToPlayerTest()
	{	
		Player l_player1 = d_playersArray.get(0);
		Player l_player2 = d_playersArray.get(1);
		
		Country country1 = d_connectivity.getD_countryList().get(0);
		Country country2 = d_connectivity.getD_countryList().get(1);
		
		
		country1.setD_armyDeployedOnCountry(10);
		ArrayList<Continent> continentList = d_connectivity.getD_continentList();
		
		l_player1.addCountry(country1);
		PlayersGameplay.clearNutrealCountry();
		PlayersGameplay.addNutrealCountry(country2);
		
		
		int result = PlayersGameplay.advance(l_player1, d_playersArray, country1, country2, 4, continentList,d_connectivity);
		assertEquals(result, 0);
		
	}
	
	@Test
	void advanceWithInsufficientTroops()
	{
		
		Player l_player1 = d_playersArray.get(0);
		
		//System.out.println("XXXXX1"+d_connectivity.getD_countryList());
		
		
		Country country1 = d_connectivity.getD_countryList().get(0);
		Country country2 = d_connectivity.getD_countryList().get(1);
		
		
		
		ArrayList<Continent> continentList = d_connectivity.getD_continentList();
		
		l_player1.addCountry(country1);
		l_player1.addCountry(country2);
		
		//System.out.println("Player1 Country:zzzzzzzz "+l_player1.getD_Country());
		
		country1.setD_armyDeployedOnCountry(2);
		
		
		
		int result = PlayersGameplay.advance(l_player1, d_playersArray, country1, country2, 4, continentList,d_connectivity);
		//System.out.println("Player1 Country: "+l_player1.getD_Country());
		//System.out.println("Resulr: "+result);
		//System.out.println("army:"+country1.getD_armyDeployedOnCountry());
		assertEquals(result, 1);
	}
	
	@Test
	void advanceNegativeNumberTroopsTest()
	{
		Player l_player1 = d_playersArray.get(0);
		
		Country country1 = d_connectivity.getD_countryList().get(0);
		Country country2 = d_connectivity.getD_countryList().get(1);
			
		ArrayList<Continent> continentList = d_connectivity.getD_continentList();
		
		l_player1.addCountry(country1);
		l_player1.addCountry(country2);
		
		country1.setD_armyDeployedOnCountry(2);
		
		int result = PlayersGameplay.advance(l_player1, d_playersArray, country1, country2, -2, continentList,d_connectivity);
		
		assertEquals(result, 1);
	}
	
	@Test
	void advanceToNonNeighbourTest()
	{
		Player l_player1 = d_playersArray.get(0);
		
		Country country1 = d_connectivity.getD_countryList().get(0);
		Country country2 = d_connectivity.getD_countryList().get(2);
			
		ArrayList<Continent> continentList = d_connectivity.getD_continentList();
		
		l_player1.addCountry(country1);
		l_player1.addCountry(country2);
		
		country1.setD_armyDeployedOnCountry(5);
		
		int result = PlayersGameplay.advance(l_player1, d_playersArray, country1, country2, 2, continentList,d_connectivity);
		
		assertEquals(result, 1);
		
	}
	
	@Test
	void advanceCorrectTest()
	{
		Player l_player1 = d_playersArray.get(0);
		
		Country country1 = d_connectivity.getD_countryList().get(0);
		Country country2 = d_connectivity.getD_countryList().get(1);
			
		ArrayList<Continent> continentList = d_connectivity.getD_continentList();
		
		l_player1.addCountry(country1);
		l_player1.addCountry(country2);
		
		country1.setD_armyDeployedOnCountry(5);
		
		int result = PlayersGameplay.advance(l_player1, d_playersArray, country1, country2, 2, continentList,d_connectivity);
		
		assertEquals(result, 0);
		assertEquals(country1.getD_armyDeployedOnCountry(), 3);
		assertEquals(country2.getD_armyDeployedOnCountry(),2);
	}
	
	@Test
	void attackFromCountryNotBelongToPlayerTest()
	{
		Player l_player1 = d_playersArray.get(0);
		
		Country country1 = d_connectivity.getD_countryList().get(0);
		Country country2 = d_connectivity.getD_countryList().get(1);
			
		ArrayList<Continent> continentList = d_connectivity.getD_continentList();
		
		PlayersGameplay.clearNutrealCountry();
		PlayersGameplay.addNutrealCountry(country1);
		PlayersGameplay.addNutrealCountry(country2);
		country1.setD_armyDeployedOnCountry(10);
		
		int result = PlayersGameplay.attack(l_player1, d_playersArray, country1, country2, 2, continentList);
		//System.out.println("result:"+result);
		assertEquals(result, 1);
	}
	
	@Test
	void attackToOwnCountryTest()
	{
		Player l_player1 = d_playersArray.get(0);
		
		Country country1 = d_connectivity.getD_countryList().get(0);
		Country country2 = d_connectivity.getD_countryList().get(1);
			
		ArrayList<Continent> continentList = d_connectivity.getD_continentList();
		
		l_player1.addCountry(country1);
		l_player1.addCountry(country2);
		country1.setD_armyDeployedOnCountry(10);
		
		int result = PlayersGameplay.attack(l_player1, d_playersArray, country1, country2, 2, continentList);
		
		assertEquals(result, 1);
	}
	
	@Test
	void attackWithMoreTroopsTest()
	{
		Player l_player1 = d_playersArray.get(0);
		Player l_player2 = d_playersArray.get(1);
		
		Country country1 = d_connectivity.getD_countryList().get(0);
		Country country2 = d_connectivity.getD_countryList().get(1);
			
		ArrayList<Continent> continentList = d_connectivity.getD_continentList();
		
		l_player1.addCountry(country1);
		l_player2.addCountry(country2);
		
		country1.setD_armyDeployedOnCountry(3);
		country2.setD_armyDeployedOnCountry(1);
		
		int result = PlayersGameplay.attack(l_player1, d_playersArray, country1, country2, 10, continentList);
		
		assertEquals(result, 1);
	}
	
	@Test
	void attackWithNegativeNumberOfTroopsTest()
	{
		Player l_player1 = d_playersArray.get(0);
		Player l_player2 = d_playersArray.get(1);
		
		Country country1 = d_connectivity.getD_countryList().get(0);
		Country country2 = d_connectivity.getD_countryList().get(1);
			
		ArrayList<Continent> continentList = d_connectivity.getD_continentList();
		
		l_player1.addCountry(country1);
		l_player2.addCountry(country2);
		
		country1.setD_armyDeployedOnCountry(3);
		country2.setD_armyDeployedOnCountry(1);
		
		int result = PlayersGameplay.attack(l_player1, d_playersArray, country1, country2, -2, continentList);
		//System.out.println("Resutl:"+result);
		assertEquals(result, 1);
	}
	
	@Test
	void attackToNonNeighbourTest()
	{
		Player l_player1 = d_playersArray.get(0);
		Player l_player2 = d_playersArray.get(1);
		
		Country country1 = d_connectivity.getD_countryList().get(0);
		Country country2 = d_connectivity.getD_countryList().get(2);
			
		ArrayList<Continent> continentList = d_connectivity.getD_continentList();
		
		l_player1.addCountry(country1);
		l_player2.addCountry(country2);
		
		country1.setD_armyDeployedOnCountry(4);
		country2.setD_armyDeployedOnCountry(1);
		
		int result = PlayersGameplay.attack(l_player1, d_playersArray, country1, country2, 2, continentList);
		assertEquals(result, 1);
	}
	
	@Test
	void attackSuccessfulTest()
	{
		Player l_player1 = d_playersArray.get(0);
		Player l_player2 = d_playersArray.get(1);
		
		Country country1 = d_connectivity.getD_countryList().get(0);
		Country country2 = d_connectivity.getD_countryList().get(1);
		Country country3 = d_connectivity.getD_countryList().get(2);
			
		ArrayList<Continent> continentList = d_connectivity.getD_continentList();
		
		l_player1.addCountry(country1);
		l_player1.addCountry(country3);
		l_player2.addCountry(country2);
		
		country1.setD_armyDeployedOnCountry(4);
		country2.setD_armyDeployedOnCountry(0);
		
		int result = PlayersGameplay.attack(l_player1, d_playersArray, country1, country2, 3, continentList);
		
		
		System.out.println("p1 COntinent: ");
//		for(Continent c:l_player1.getD_playerContinent())
//		{
//			System.out.println(c.getD_continentName());
//		}
//		
//		System.out.println("p2 Countrys: ");
//		for(Country c:l_player2.getD_Country())
//		{
//			System.out.println(c.getD_countryName());
//		}
		
		assertEquals(result, 0);
		assertEquals(l_player1.getCards().size(),1);
		assertEquals(l_player1.getD_Country().size(),3);
		assertEquals(l_player1.getD_playerContinent().size(), 1);
		assertEquals(l_player2.getD_Country().size(),0);
	}
	
	@Test
	void attackCountryNotInMap()
	{
		Player l_player1 = d_playersArray.get(0);
		
		Country country1 = d_connectivity.getD_countryList().get(0);
		Country country2 = new Country();
		country2.setD_countryName("aaa");
	
		l_player1.addCountry(country1);
		country1.setD_armyDeployedOnCountry(10);
		
		PlayersGameplay.clearNutrealCountry();
			
		ArrayList<Continent> continentList = d_connectivity.getD_continentList();
		
	
		int result = PlayersGameplay.attack(l_player1, d_playersArray, country1, country2, 3, continentList);
	
		assertEquals(result, 1);
	//	assertEquals(l_player1.getCards().size(),1);
	//	assertEquals(l_player1.getD_Country().size(),2);
		
	}
	
	@Test
	void attackNutrealCountry()
	{
		Player l_player1 = d_playersArray.get(0);
		
		Country country1 = d_connectivity.getD_countryList().get(0);
		Country country2 = d_connectivity.getD_countryList().get(1);
	
		l_player1.addCountry(country1);
		country1.setD_armyDeployedOnCountry(10);
		
		PlayersGameplay.clearNutrealCountry();
		PlayersGameplay.addNutrealCountry(country2);
			
		ArrayList<Continent> continentList = d_connectivity.getD_continentList();
	
		int result = PlayersGameplay.attack(l_player1, d_playersArray, country1, country2, 3, continentList);
		assertEquals(result, 0);
		assertEquals(l_player1.getCards().size(),1);
		assertEquals(l_player1.getD_Country().size(),2);
		assertEquals(PlayersGameplay.getNutrealCountry().size(),0);
	}
}
