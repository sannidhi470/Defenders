package state;

import Controllers.GameEngine;
import Tools.Connectivity;
import Tools.SaveMap;

public class PostLoad extends Edit {

	public PostLoad(GameEngine p_ge) {
		super(p_ge);
	}
	
	public void showMap() {
		printInvalidCommandMessage(); 
	}

	public void loadMap() {
		printInvalidCommandMessage(); 
	}

	public void editCountry() {
		printInvalidCommandMessage(); 
	}

	public void saveMap(Connectivity l_connectivity, String p_mapName) {
		int l_saveMapResult = SaveMap.saveMap(l_connectivity, p_mapName);
		if(l_saveMapResult == 0) ge.setPhase(new PlaySetup(ge));
	}

	public void next() {
		System.out.println("must save map");
	}



	@Override
	public void editCountry(String[] l_commands, Connectivity l_connectivity) {
		// TODO Auto-generated method stub
		printInvalidCommandMessage(); 
		
	}

	@Override
	public void editContinent(String[] l_commands, Connectivity l_connectivity) {
		// TODO Auto-generated method stub
		printInvalidCommandMessage(); 
		
	}

	@Override
	public void editNeighbor(String[] l_commands, Connectivity l_connectivity) {
		// TODO Auto-generated method stub
		printInvalidCommandMessage(); 
		
	}

	@Override
	public void loadMap(Connectivity l_connectivity, String[] l_commands) {
		// TODO Auto-generated method stub
		printInvalidCommandMessage(); 
		
	}

	@Override
	public void setPlayers(String[] l_commands) {
		// TODO Auto-generated method stub
		printInvalidCommandMessage(); 
		
	}

	@Override
	public boolean assignCountries(Connectivity l_connectivity) {
		printInvalidCommandMessage(); 
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reinforce(Connectivity l_connectivity) {
		// TODO Auto-generated method stub
		printInvalidCommandMessage(); 
	}

	@Override
	public void attack(Connectivity l_connectivity) {
		// TODO Auto-generated method stub
		printInvalidCommandMessage(); 
	}

	@Override
	public void fortify() {
		// TODO Auto-generated method stub
		printInvalidCommandMessage(); 
	}

	@Override
	public void validateMap(Connectivity l_connectivity) {
		
		// TODO Auto-generated method stub
		printInvalidCommandMessage(); 
	}

	@Override
	public void endGame() {
		// TODO Auto-generated method stub
		System.out.println("Thank you for Playing the Game");
		System.exit(0);
	}
	
	
}
