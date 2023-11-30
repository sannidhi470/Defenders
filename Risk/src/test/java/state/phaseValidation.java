package state;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controllers.GameEngine;
import Tools.ColorCoding;
import Tools.Connectivity;
import Tools.MapLoader;
import Tools.SaveMap;
import Views.ViewMap;

/**
 * The class PhaseValidation tests if right phase is being executed at right time.
 *
 */
public class phaseValidation {
	/**
	 * GameEngine refers to static object GameEngine
	 * d_connectibity refers to static Connectivity Object
	 */
	
	static GameEngine gameEngine = new GameEngine();
	static Connectivity d_connectivity=new Connectivity();
	String[] d_commands= {"1","loadmap VeryBasic","no","savemap VeryBasic","gameplayer -add p1 -add p2","assigncountries","deploy"};
	
	@BeforeAll
	static void startGame()
	{
		gameEngine.setConnectivity(d_connectivity);
		gameEngine.setCheckIfTest(true);
	}
	
	/**
	 * This test checks for successful transition between states of the phase
	 */
	@Test
	public void checkState()
	{
		
		if(d_commands[0].equals("1"))
		{
			gameEngine.setPhase(new Preload(gameEngine));
			System.out.println(gameEngine.getPhase());
			assertEquals("Preload",gameEngine.getPhaseName());
			System.out.println(ColorCoding.green+"Phase has been successfully changed to "+gameEngine.getPhaseName()+ColorCoding.blank);
			String[] mapCommand = d_commands[1].split(" ") ;
			
			if(d_commands[1].equals("loadmap VeryBasic"))
			{
				gameEngine.getPhase().loadMap(gameEngine.getConnectivity(), mapCommand);
				
			}
			assertEquals("PostLoad",gameEngine.getPhaseName());
			System.out.println(ColorCoding.green+"Phase has been successfully changed to "+gameEngine.getPhaseName()+ColorCoding.blank);
			if(d_commands[3].equals("savemap VeryBasic"))
			{
				gameEngine.getPhase().saveMap(d_connectivity, "VeryBasic");
			}
			assertEquals("PlaySetup",gameEngine.getPhaseName());
			System.out.println(ColorCoding.green+"Phase has been successfully changed to "+gameEngine.getPhaseName()+ColorCoding.blank);
			mapCommand = d_commands[4].split(" ");
			gameEngine.getPhase().setPlayers(mapCommand,gameEngine.getConnectivity());
			gameEngine.getPhase().assignCountries(gameEngine.getConnectivity());
			gameEngine.getPhase().next(d_connectivity);
			assertEquals("Reinforcement",gameEngine.getPhaseName());
			System.out.println(ColorCoding.green+"Phase has been successfully changed to "+gameEngine.getPhaseName()+ColorCoding.blank);
			gameEngine.getPhase().reinforce(d_connectivity);
			assertEquals("Attack",gameEngine.getPhaseName());
			System.out.println(ColorCoding.green+"Phase has been successfully changed to "+gameEngine.getPhaseName()+ColorCoding.blank);
			gameEngine.getPhase().attack(d_connectivity);
			assertEquals("Fortify",gameEngine.getPhaseName());
			System.out.println(ColorCoding.green+"Phase has been successfully changed to "+gameEngine.getPhaseName()+ColorCoding.blank);
			gameEngine.getPhase().fortify(d_connectivity);
			assertEquals("Reinforcement",gameEngine.getPhaseName());
			System.out.println(ColorCoding.green+"Phase has been successfully changed to "+gameEngine.getPhaseName()+ColorCoding.blank);
			gameEngine.getPhase().reinforce(d_connectivity);
			assertEquals("Attack",gameEngine.getPhaseName());
			System.out.println(ColorCoding.green+"Phase has been successfully changed to "+gameEngine.getPhaseName()+ColorCoding.blank);
			gameEngine.getPhase().attack(d_connectivity);
			assertEquals("End",gameEngine.getPhaseName());
			System.out.println(ColorCoding.green+"Phase has been successfully changed to "+gameEngine.getPhaseName()+ColorCoding.blank);
		}
	}
	
}
