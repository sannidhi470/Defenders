package state;

import java.util.ArrayList;

import Controllers.GameEngine;
import Models.Continent;
import Models.Country;
import Models.LogEntryBuffer;
import Models.Player;
import Tools.Connectivity;
import Views.ViewMap;

/**
 *	State of the State pattern. Here implemented as a abstract class. 
 *  
 *	In this example, the states represent states in the board game Risk. 
 *  There are many states, and even a hierarchy of states: 
 *
 *		Phase 
 *        Edit phase (abstract)
 *          Preload
 *          Postload
 *        Play (abstract)
 *          PlaySetup
 *          MainPlay 
 *          Reinforcement <--
 *          Attack          | 
 *          Fortify ---------
 *        End
 *        
 *      In each state, nextState() is defined so that it goes down in 
 *      the above list, except for Fortify, which goes back to 
 *      Reinforcement state. 
 */
public abstract class Phase {

	/**
	 *  Contains a reference to the State of the GameEngine 
	 *  so that the state object can change the state of 
	 *  the GameEngine to transition between states. 
	 */
	GameEngine ge;

	Phase(GameEngine p_ge) {
		ge = p_ge;
	}

	// common commands
	abstract public void loadMap(Connectivity l_connectivity, String[] l_commands);

	public void viewMap(ArrayList<Continent> p_continentList, ArrayList<Country> p_countryList, ArrayList<Player> p_players) {
		ViewMap.viewMap(p_continentList, p_countryList,p_players);
		}

	// Edit map commands
	abstract public void editCountry(String[] l_commands,Connectivity l_connectivity);
	abstract public void editContinent(String[] l_commands,Connectivity l_connectivity);
	abstract public void editNeighbor(String[] l_commands,Connectivity l_connectivity);
	abstract public void saveMap(Connectivity l_connectivity, String p_mapName);

	// Play commands
	// game setup commands
	abstract public void setPlayers(String[] l_commands);
	abstract public boolean assignCountries(Connectivity l_connectivity);

	// reinforcement commands
	abstract public void reinforce(Connectivity l_connectivity);

	// attack commands
	abstract public void attack(Connectivity l_connectivity);

	// fortify commands
	abstract public void fortify();

	// end command
	abstract public void endGame();

	//help command
	public void help()
	{
		//d_logEntryBuffer.log("Help is being used by Player");
		System.out.println("\nloadmap filename: \n	Game starts by user selection of a user-saved map file, which loads the map as a connected directed graph\n__________________________________________________________\n"
				+ "validatemap: \n	Verification of map correctness\n__________________________________________________________\n"
				+ "editmap filename: \n	User-driven creation/deletion of map elements: country, continent, and connectivity between countries.\n__________________________________________________________\n"
				+ "	editcontinent -add continentID continentvalue -remove continentID\n"
				+ "	editcountry -add countryID continentID -remove countryID\n"
				+ "	editneighbor -add countryID neighborcountryID -remove countryID neighborcountryID\n__________________________________________________________\n"
				+ "showmap: \n	show all countries and continents, armies on each country, ownership, and connectivity\n__________________________________________________________\n"
				+ "savemap: \n	save a map\n__________________________________________________________\n"
				+ "assigncountries: \n	countries get randomly assigned to players\n__________________________________________________________\n"
				+ "gameplayer -add playername -remove playername : \n	Command to add or remove players from the game\n__________________________________________________________\n");
	}
	
	// go to next phase
	abstract public void next();
	
	abstract public void validateMap(Connectivity l_connectivity);
	
	/**
	 *  Common method to all States. 
	 */
	public void printInvalidCommandMessage() {
		System.out.println("Invalid command in state " + this.getClass().getSimpleName() );
	}
}
