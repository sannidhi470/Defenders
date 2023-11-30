package state;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Controllers.GameEngine;
import Models.Continent;
import Models.Country;
import Tools.Connectivity;

public class loadSaveTest {

	GameEngine gameEngine = new GameEngine();
	Connectivity l_connectivity=new Connectivity();
	
	@Test
	public void testLoadGame() {
		gameEngine.setCheckIfTest(true);
		l_connectivity.setD_continentList(new ArrayList<Continent>());
		l_connectivity.setD_countryList(new ArrayList<Country>());
		String l_userCommand = "loadgame gg";
		String[] l_spiltCommand = l_userCommand.split(" ");
		gameEngine.setPhase(new PlayGame(gameEngine));
		try {
			gameEngine.getPhase().loadgame(l_spiltCommand, l_connectivity, gameEngine);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("Attack",gameEngine.getPhaseName());
		System.out.println("Game has been loaded to the previous saved state successfully");
		gameEngine.setPhase(new End(gameEngine));
		gameEngine.getPhase().endGame(l_connectivity);
	}
}
