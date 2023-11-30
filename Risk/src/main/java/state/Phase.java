package state;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Controllers.GameEngine;
import Models.Continent;
import Models.Country;
import Models.LogEntryBuffer;
import Models.Player;
import Tools.Connectivity;
import Tools.SaveGame;
import Views.ViewMap;

/**
 *	The abstract class Phase manages the states in the State pattern.
 *  
 */
public abstract class Phase 
{

	/**
	 *  Contains a reference to the State of the GameEngine 
	 *  so that the state object can change the state of 
	 *  the GameEngine to transition between states. 
	 */
	GameEngine ge;

	Phase(GameEngine p_ge) 
	{
		ge = p_ge;
	}

	/**
	 * This method is used to load the Map in the Preload state.
	 * @param p_connectivity Connectivity object
	 * @param p_commands is used to pass user command
	 */	
	public void loadMap(Connectivity p_connectivity, String[] p_commands) 
	{
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method is used to display the map in an tabular manner.
	 * @param p_continentList refers to the list of continents.
	 * @param p_countryList refers to the list of countries.
	 * @param p_players refers to the list of players.
	 */
	public void viewMap(ArrayList<Continent> p_continentList, ArrayList<Country> p_countryList, ArrayList<Player> p_players) 
	{
		ViewMap.viewMap(p_continentList, p_countryList,p_players);
	}


	/**
	 * This abstract method is used to add/remove country in the Preload state.
	 * @param p_connectivity Connectivity object
	 * @param p_commands is used to pass user command
	 */	
	abstract public void editCountry(String[] p_commands,Connectivity p_connectivity);
	/**
	 * This abstract method is used to add/remove continent in the Preload state.
	 * @param p_connectivity Connectivity object
	 * @param p_commands is used to pass user command
	 */	
	abstract public void editContinent(String[] p_commands,Connectivity p_connectivity);
	/**
	 * This abstract method is used to add/remove neighbor in the Preload state.
	 * @param p_connectivity Connectivity object
	 * @param p_commands is used to pass user command
	 */	
	abstract public void editNeighbor(String[] p_commands,Connectivity p_connectivity);
	/**
	 * This abstract method is used to save map in the Postload state.
	 * @param p_connectivity Connectivity object
	 * @param p_mapName is used to pass map name
	 */	
	abstract public void saveMap(Connectivity p_connectivity, String p_mapName);
	/**
	 * This abstract method is used to add/remove players in the PlaySetup state.
	 * @param p_commands is used to pass user command
	 */	
	abstract public void setPlayers(String[] p_commands,Connectivity p_connectivity);
	/**
	 * This abstract method is used to assign countries in the PlaySetup state.
	 * @param p_connectivity Connectivity object
	 * @return True/False
	 */	
	abstract public boolean assignCountries(Connectivity p_connectivity);
	/**
	 * This abstract method is used to deploy troops in the Reinforcement state.
	 * @param p_connectivity Connectivity object
	 */	
	abstract public void reinforce(Connectivity p_connectivity);
	/**
	 * This abstract method is used for attacking in the Attack state.
	 * @param p_connectivity Connectivity object
	 */	
	abstract public void attack(Connectivity p_connectivity);
	/**
	 * This abstract method is used for deploying more armies in order to determine the winner.
	 * @param p_connectivity Connectivity object
	 */	
	abstract public void fortify(Connectivity p_connectivity);
	/**
	 * This abstract method is used to end the game.
	 * @throws FileNotFoundException 
	 */
	public void endGame(Connectivity p_connectivity)
	{
		Scanner sc= new Scanner(System.in);
    	System.out.println("Do you want to save the game?");
    	String choice = sc.nextLine();
    	if(choice.equalsIgnoreCase("yes"))
    	{
    		System.out.println("Enter the command: ");
    		String[] l_command= sc.nextLine().split(" ");
    		String l_filename=l_command[1];
    		SaveGame sg = new SaveGame();
    		try 
    		{
				sg.saveGame(this,p_connectivity,l_filename);
			} catch (IOException e) 
    		{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println("Thank you for Playing the Game");
            System.exit(0);
    	}
    	else if (choice.equalsIgnoreCase("no"))
    	{
        System.out.println("Thank you for Playing the Game");
    	}
    	return;
	}
	abstract public void loadgame(String[] p_commands,Connectivity p_connectivity,GameEngine ge) throws FileNotFoundException;

	/**
	 * This method is used to display the help instructions.
	 */
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
	
	/**
	 * This method is used to go to next phase.
	 */
	abstract public void next(Connectivity p_connectivity);
	/**
	 * This method is used validate the loaded map.
	 * @param p_connectivity Connectivity object
	 */
	abstract public void validateMap(Connectivity p_connectivity);
	/**
	 *  This method is used to display invalid command in the state. Common to all states.
	 */
	public void printInvalidCommandMessage() 
	{
		System.out.println("Invalid command in state " + this.getClass().getSimpleName() );
	}

	public abstract void enableTournament(String mycommand);
}
