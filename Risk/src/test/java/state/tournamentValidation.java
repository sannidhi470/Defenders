package state;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controllers.GameEngine;
import Models.Continent;
import Models.Country;
import Tools.Connectivity;

public class tournamentValidation {
	
	Connectivity l_connectivity=new Connectivity();
	ArrayList<String> l_mapList = new ArrayList<>();
	HashSet<String> l_behaviourList = new HashSet<String>();
	int l_gameCount = 0;
	int l_roundCount = 0;
	GameEngine gameEngine = new GameEngine();
	ArrayList<String> d_gameResult = new ArrayList<>();
	String d_gameplayerCommand = "";
	
	@Test
	public void testTournament() {
		l_connectivity.setD_continentList(new ArrayList<Continent>());
		l_connectivity.setD_countryList(new ArrayList<Country>());
		String l_userCommand = "tournament -M VeryBasic canada -P Random Aggressive Cheater Benevolent -G 3 -D 10";
		gameEngine.setPhase(new PlayGame(gameEngine));
		gameEngine.getPhase().enableTournament(l_userCommand);
	}
}
