package Strategy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Controllers.GameEngine;
import Models.Country;
import Models.Order;
import Models.Player;
import Tools.Connectivity;
import Tools.MapLoader;
import state.Attack;
import state.Reinforcement;

public class AggressivePlayerStrategyTest {

	static GameEngine gameEngine = new GameEngine();
	static Connectivity d_connectivity=new Connectivity();
	
	@Test
	void deployTest() 
	{
		Player d_player1 = new Player();
		Country c1 = new Country();
		Country c2 = new Country();
		
		c1.setD_countryName("India");
		c2.setD_countryName("China");
		
		c1.setD_countryId(1);
		c2.setD_countryId(2);
		
		c1.setD_armyDeployedOnCountry(5);
		c2.setD_armyDeployedOnCountry(10);
		
		d_player1.addCountry(c1);
		d_player1.addCountry(c2);
		
		d_player1.setD_armyCount(10);
		
		System.out.println(d_player1.getD_Country().get(1).getD_armyDeployedOnCountry());
		
		AggressivePlayerStrategy B = new AggressivePlayerStrategy(d_player1, d_connectivity);
		
		gameEngine.setPhase(new Reinforcement(gameEngine));
		Order o = B.createOrder();
		
		String str = o.getOrderContent();
		String[] strArr = str.split(" ");
		
		assertEquals(strArr[0], "deploy");
		assertEquals(strArr[1], "2");
		assertEquals(strArr[2], "10");
	}
	
	@Test
	void advanceTest()
	{

		MapLoader.loadMap(d_connectivity, "VeryBasic");
		
		Player d_player1 = new Player();
		Country c1 = d_connectivity.getD_countryList().get(0); //canada
		Country c2 = d_connectivity.getCountryByID(c1.getD_neighbours().get(0)); //usa
		
	
		
		System.out.println(c1.getD_countryName()+" ???"+c2.getD_countryName());
		d_player1.addCountry(c1);
		d_player1.addCountry(c2);
		
		c1.setD_armyDeployedOnCountry(5);//canada
		c2.setD_armyDeployedOnCountry(12);//usa
		
		d_player1.setD_armyCount(10);
		
		AggressivePlayerStrategy B = new AggressivePlayerStrategy(d_player1, d_connectivity);
		
		gameEngine.setPhase(new Attack(gameEngine));
		Order o = B.createOrder();
		String str = o.getOrderContent();
		String[] strArr = str.split(" ");
		
		System.out.println("___"+str);
		assertEquals(strArr[0], "advance");
		assertEquals(strArr[1], "Canada");
		assertEquals(strArr[2], "USA");
		assertEquals(strArr[3], "5");
	}
	
	@Test
	void advanceAttackTest()
	{
		MapLoader.loadMap(d_connectivity, "VeryBasic");
		
		Player d_player1 = new Player();
		Country c1 = d_connectivity.getD_countryList().get(0); //canada
		Country c2 = d_connectivity.getCountryByID(c1.getD_neighbours().get(0)); //usa
		
	
		
		System.out.println(c1.getD_countryName()+" ???"+c2.getD_countryName());
		d_player1.addCountry(c1);
		//d_player1.addCountry(c2);
		
		c1.setD_armyDeployedOnCountry(5);//canada
		c2.setD_armyDeployedOnCountry(12);//usa
		
		d_player1.setD_armyCount(10);
		
		AggressivePlayerStrategy B = new AggressivePlayerStrategy(d_player1, d_connectivity);
		
		gameEngine.setPhase(new Attack(gameEngine));
		Order o = B.createOrder();
		String str = o.getOrderContent();
		String[] strArr = str.split(" ");
		
		System.out.println("___"+str);
		assertEquals(strArr[0], "advance");
		assertEquals(strArr[1], "Canada");
		assertEquals(strArr[2], "USA");
		assertEquals(strArr[3], "5");
	}
	

}
