package state;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controllers.GameEngine;
import Tools.Connectivity;
import Tools.MapLoader;
import Tools.SaveMap;
import Views.ViewMap;
public class phaseValidation {
	
	static GameEngine gameEngine = new GameEngine();
	static Connectivity d_connectivity=new Connectivity();
	String[] d_commands= {"1","loadmap VeryBasic","no","savemap VeryBasic","gameplayer -add p1 -add p2","assigncountries","deploy"};
	
	@BeforeAll
	static void startGame()
	{
		gameEngine.setConnectivity(d_connectivity);
		gameEngine.setCheckIfTest(true);
	}
	@Test
	public void checkPreLoad()
	{
		
		if(d_commands[0].equals("1"))
		{
			gameEngine.setPhase(new Preload(gameEngine));
			System.out.println(gameEngine.getPhase());
			assertEquals("Preload",gameEngine.getPhaseName());
			System.out.println("Phase has been successfully changed to "+gameEngine.getPhaseName());
			String[] mapCommand = d_commands[1].split(" ") ;
			
			if(d_commands[1].equals("loadmap VeryBasic"))
			{
				gameEngine.getPhase().loadMap(gameEngine.getConnectivity(), mapCommand);
				
			}
			assertEquals("PostLoad",gameEngine.getPhaseName());
			System.out.println("Phase has been successfully changed to "+gameEngine.getPhaseName());
			if(d_commands[3].equals("savemap VeryBasic"))
			{
				gameEngine.getPhase().saveMap(d_connectivity, "VeryBasic");
			}
			assertEquals("PlaySetup",gameEngine.getPhaseName());
			System.out.println("Phase has been successfully changed to "+gameEngine.getPhaseName());
			mapCommand = d_commands[4].split(" ");
			gameEngine.getPhase().setPlayers(mapCommand);
			gameEngine.getPhase().assignCountries(gameEngine.getConnectivity());
			gameEngine.getPhase().next();
			System.out.println("Phase has been successfully changed to "+gameEngine.getPhaseName());
			for(int i=0; i<Play.getL_playersArray().size(); i++)
			{
				System.out.println("Player name ="+Play.getL_playersArray().get(i).getD_playerName());
				for(int j=0; j<Play.getL_playersArray().get(i).getD_Country().size(); j++)
				{
					System.out.println("Countries owned ="+Play.getL_playersArray().get(i).getD_Country().get(j).getD_countryId());
				}
				//creating deploy command for each player
				int l_armyCount = Play.getL_playersArray().get(i).getD_armyCount();
				//need to add skip for test case in reinforce
				gameEngine.getPhase().reinforce(d_connectivity);
			}
		}
	}
	@Test
	public void checkPostLoad()
	{

//		gameEngine.setPhase(new Preload(gameEngine));

	}
	
}
