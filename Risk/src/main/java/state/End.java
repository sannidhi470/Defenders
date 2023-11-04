package state;

import Controllers.GameEngine;
import Tools.Connectivity;

public class End extends Phase {

	End(GameEngine p_ge) {
		super(p_ge);
	}

	public void loadMap() {
		printInvalidCommandMessage(); 
	}

	public void showMap() {
		printInvalidCommandMessage(); 
	}

	public void editCountry() {
		printInvalidCommandMessage(); 
	}

	public void saveMap() {
		printInvalidCommandMessage(); 
	}

	public void setPlayers(String[] l_commands) {
		printInvalidCommandMessage(); 
	}

	public boolean assignCountries(Connectivity l_connectivity) {
		printInvalidCommandMessage();
		return false; 
	}

	public void reinforce(Connectivity l_connectivity) {
		printInvalidCommandMessage(); 
	}

	public void attack(Connectivity l_connectivity) {
		printInvalidCommandMessage(); 
	}

	public void fortify(Connectivity l_connectivity) {
		printInvalidCommandMessage(); 
	}

	public void endGame() {
		//d_logEntryBuffer.log("The Game is Ended");
		System.out.println("Thank you for Playing the Game");
		System.exit(0);
//		printInvalidCommandMessage(); 
	}

	public void next() {
		printInvalidCommandMessage(); 
	}

	@Override
	public void loadMap(Connectivity l_connectivity, String[] l_commands) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateMap(Connectivity l_connectivity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editCountry(String[] l_commands, Connectivity l_connectivity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editContinent(String[] l_commands, Connectivity l_connectivity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editNeighbor(String[] l_commands, Connectivity l_connectivity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveMap(Connectivity l_connectivity, String p_mapName) {
		// TODO Auto-generated method stub
		
	}
}
