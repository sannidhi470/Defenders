package state;

import java.util.ArrayList;

import Controllers.GameEngine;
import Models.*;
import Tools.Connectivity;

/**
 *	ConcreteState of the State pattern. Play state defines behavior 
 *  for commands that are valid in the MainPlay and PlaySetup states, and for the others signifies  
 *  that the command is invalid. 
 *  
 *  This state represents a group of states, and defines the behavior 
 *  that is common to all the states in its group. All the states in its 
 *  group need to extend this class. 
 *  
 */
public abstract class Play extends Phase 
{
	
	public static ArrayList<Player> l_playersArray = new ArrayList<Player>();
	
	/**
	 * Retrieves the ArrayList containing Player objects.
	 * 
	 * @return ArrayList containing Player objects.
	 */
	public static ArrayList<Player> getL_playersArray()
	{
		return l_playersArray;
	}

	/**
	 * Constructor for Play phase.
	 * 
	 * @param p_ge The GameEngine object associated with this phase.
	 */
	Play(GameEngine p_ge) 
	{
		super(p_ge); 
	}
	
	/**
	 * Sets the players for the phase.
	 */
	public void setPlayers() 
	{
		printInvalidCommandMessage(); 
	}

	/**
	 * Assigns countries to players in the phase.
	 */
	public void assignCountries() 
	{
		printInvalidCommandMessage(); 
	}

	/**
	 * Reinforces the players' armies in the phase.
	 */
	public void reinforce() 
	{
		printInvalidCommandMessage(); 
	}

	/**
	 * Initiates an attack between players in the phase.
	 */
	public void attack() 
	{
		printInvalidCommandMessage(); 
	}

	/**
	 * Allows players to fortify their positions in the phase.
	 */
	public void fortify() 
	{
		printInvalidCommandMessage(); 
	}

	
	public void loadgame()
	{
		printInvalidCommandMessage();
	}
	
	/**
	 * Shows the map for the current phase.
	 */
	public void showMap() 
	{
		printInvalidCommandMessage(); 
	}
}
