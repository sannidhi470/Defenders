package Tools;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Controllers.GameEngine;
import Models.Player;

class PlayersGameplayTest {
	
	private ArrayList<Player> d_playersArray = GameEngine.getL_playersArray();
	private Connectivity d_connectivity=new Connectivity();
	
	@BeforeEach
	public void addPlayersIfNotThere() 
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

	
	@Test
	void checkArmyAvailableSendMoreTroopsTest() 
	{
		
		boolean l_truthValue = PlayersGameplay.checkArmyAvailable((d_playersArray.get(0).getD_armyCount()+1), d_playersArray.get(0));
		assertEquals(false, l_truthValue);
		
	}
	
	@Test
	void assignArmiesToPlayersManualCorrectTest()
	{
		Player l_player= d_playersArray.get(0);
		Player l_playerTest = new Player(l_player);
		
		ArrayList<Player> l_playerArrayTest = new ArrayList<Player>();
		l_playerArrayTest.add(l_playerTest);
		//System.out.println(l_playerArrayTest.size());
		
		int l_countryListSize=l_playerArrayTest.get(0).getD_Country().size()/3;
		int l_armyCount=Math.max(3, l_countryListSize);
		int l_tempContinentCount=0;
		if(l_playerArrayTest.get(0).getD_playerContinent().size()!=0)
		{
			for(int j=0;j<l_playerArrayTest.get(0).getD_playerContinent().size();j++)
				l_tempContinentCount=l_tempContinentCount+ l_playerArrayTest.get(0).getD_playerContinent().get(j).getD_continentArmyValue();
		}
		l_armyCount+=l_tempContinentCount;
		l_playerArrayTest.get(0).setD_armyCount(l_armyCount);
		System.out.println(l_armyCount);
		
		PlayersGameplay.assignArmiesToPlayers(d_playersArray);
		
		assertEquals(d_playersArray.get(0).getD_armyCount(), l_playerArrayTest.get(0).getD_armyCount());
	}
	
	@Test
	void assignArmiesToPlayersManualWrongTest()
	{
		Player l_player= d_playersArray.get(0);
		Player l_playerTest = new Player(l_player);
		
		
		ArrayList<Player> l_playerArrayTest = new ArrayList<Player>();
		l_playerArrayTest.add(l_playerTest);
		//System.out.println(l_playerArrayTest.size());
		
		int l_countryListSize=l_playerArrayTest.get(0).getD_Country().size()/3;
		int l_armyCount=Math.max(3, l_countryListSize);
		int l_tempContinentCount=0;
		if(l_playerArrayTest.get(0).getD_playerContinent().size()!=0)
		{
			for(int j=0;j<l_playerArrayTest.get(0).getD_playerContinent().size();j++)
				l_tempContinentCount=l_tempContinentCount+ l_playerArrayTest.get(0).getD_playerContinent().get(j).getD_continentArmyValue();
		}
		l_armyCount+=l_tempContinentCount;
		
		//Increasing the army count to +1
		l_playerArrayTest.get(0).setD_armyCount(l_armyCount+1);
		System.out.println(l_armyCount);
		
		PlayersGameplay.assignArmiesToPlayers(d_playersArray);
		
		assertNotEquals(d_playersArray.get(0).getD_armyCount(), l_playerArrayTest.get(0).getD_armyCount());
	}
	
	
	
	

}
