package state;

import Controllers.GameEngine;

/**
 *	ConcreteState of the State pattern. MainPlay defines behavior 
 *  for commands that are valid for Reinforcement, Attack and fortify states, and for the others signifies  
 *  that the command is invalid. 
 *  
 *  This state represents a group of states, and defines the behavior 
 *  that is common to all the states in its group. All the states in its 
 *  group need to extend this class. 
 *  
 */
public abstract class MainPlay extends Play {

	/**
	 * Constructor for MainPlay phase.
	 * 
	 * @param p_ge The GameEngine object associated with this phase.
	 */
	MainPlay(GameEngine p_ge) 
	{
		super(p_ge);
	}

	/**
	 * Loads a map for the current phase.
	 */
	public void loadMap() 
	{
		this.printInvalidCommandMessage();
	}

	/**
	 * Sets the players for the phase.
	 */
	public void setPlayers() 
	{
		this.printInvalidCommandMessage();	
	}

	/**
	 * Assigns countries to players in the phase.
	 */
	public void assignCountries() 
	{
		this.printInvalidCommandMessage();
	}
}
