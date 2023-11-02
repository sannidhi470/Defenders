package state;

import Controllers.GameEngine;
import Tools.Connectivity;

public class Fortify extends MainPlay {

	Fortify(GameEngine p_ge) {
		super(p_ge);
	}
	public void reinforce(Connectivity l_connectivity) {
		printInvalidCommandMessage(); 
	}

	public void attack(Connectivity l_connectivity) {
		printInvalidCommandMessage(); 
	}

	public void fortify() {
		System.out.println("fortification done");
		ge.setPhase(new Reinforcement(ge));
	}

	public void next() {
		ge.setPhase(new Reinforcement(ge));
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
	@Override
	public void setPlayers(String[] l_commands) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean assignCountries(Connectivity l_connectivity) {
		return false;
		// TODO Auto-generated method stub
		
	}
	@Override
	public void endGame() {
		// TODO Auto-generated method stub
		System.out.println("Thank you for Playing the Game");
		System.exit(0);
	}
}
