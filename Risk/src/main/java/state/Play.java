package state;

import java.util.ArrayList;

import Controllers.GameEngine;
import Models.*;

/**
 *	ConcreteState of the State pattern. In this example, defines behavior 
 *  for commands that are valid in this state, and for the others signifies  
 *  that the command is invalid. 
 *  
 *  This state represents a group of states, and defines the behavior 
 *  that is common to all the states in its group. All the states in its 
 *  group need to extend this class. 
 *  
 */
public abstract class Play extends Phase {
	
	public static ArrayList<Player> l_playersArray = new ArrayList<Player>();
	
	public static ArrayList<Player> getL_playersArray()
	{
		return l_playersArray;
	}

	Play(GameEngine p_ge) {
		super(p_ge); 
	}
	
	public void setPlayers() {
		printInvalidCommandMessage(); 
	}

	public void assignCountries() {
		printInvalidCommandMessage(); 
	}

	public void reinforce() {
		printInvalidCommandMessage(); 
	}

	public void attack() {
		printInvalidCommandMessage(); 
	}

	public void fortify() {
		printInvalidCommandMessage(); 
	}

	public void endGame() {
		printInvalidCommandMessage(); 
	}
	
	public void showMap() {
		printInvalidCommandMessage(); 
	}
}
