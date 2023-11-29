package state;

import java.util.Scanner;

import Controllers.GameEngine;
import Tools.Connectivity;
import Tools.ConquestSaveMap;
import Tools.SaveMap;

/**
 * Concrete state representing the phase after loading a map.
 * Allows the user to save the map and proceed to game play phases.
 */
public class PostLoad extends Edit 
{

	/**
	 * Constructor for PostLoad phase.
	 * 
	 * @param p_ge The GameEngine object associated with this phase.
	 */
	public PostLoad(GameEngine p_ge) 
	{
		super(p_ge);
	}
	
	/**
	 * Shows the map (invalid command in this state).
	 */
	public void showMap() 
	{
		printInvalidCommandMessage(); 
	}

	/**
	 * Loads a map (invalid command in this state).
	 */
	public void loadMap() 
	{
		printInvalidCommandMessage(); 
	}

	/**
	 * Edits a country (invalid command in this state).
	 */
	public void editCountry() 
	{
		printInvalidCommandMessage(); 
	}

	/**
	 * Saves the map.
	 * 
	 * @param p_connectivity represents the map content
	 * @param p_mapName The name of the map to save.
	 */
	public void saveMap(Connectivity p_connectivity, String p_mapName) 
	{
		System.out.println("Enter the format for savemap (conquest/domination)");
		Scanner l_sc=new Scanner(System.in);
		String l_type = "";
		if(ge.getCheckIfTournament())
			l_type = "domination";
		else
			l_type=l_sc.nextLine();
		int l_saveMapResult=0;
		int l_flag=0;
		do
		{
			switch(l_type)
			{
			case "conquest":
				System.out.println("in conquest");
				l_saveMapResult=ConquestSaveMap.conquestMapSaver(p_connectivity, p_mapName);
				l_flag=1;
				break;
			case "domination":
				System.out.println("in domination");
				l_saveMapResult=SaveMap.saveMap(p_connectivity, p_mapName);
				l_flag=1;
				break;
			default:
				System.out.println("Please enter the valid command");
			}
		}while(l_flag==0);
		 
		if(l_saveMapResult == 0) ge.setPhase(new PlaySetup(ge));
	}

	/**
	 * To move to next phase
	 */
	public void next() 
	{
		System.out.println("must save map");
	}


    /**
     * {@inheritDoc}
     */
	@Override
	public void editCountry(String[] p_commands, Connectivity p_connectivity) 
	{
		// TODO Auto-generated method stub
		printInvalidCommandMessage(); 
		
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public void editContinent(String[] p_commands, Connectivity p_connectivity) 
	{
		// TODO Auto-generated method stub
		printInvalidCommandMessage(); 
		
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public void editNeighbor(String[] p_commands, Connectivity p_connectivity) 
	{
		// TODO Auto-generated method stub
		printInvalidCommandMessage(); 
		
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public void loadMap(Connectivity p_connectivity, String[] p_commands) 
	{
		// TODO Auto-generated method stub
		printInvalidCommandMessage(); 
		
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public void setPlayers(String[] p_commands,Connectivity p_connectivity) 
	{
		// TODO Auto-generated method stub
		printInvalidCommandMessage(); 
		
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public boolean assignCountries(Connectivity p_connectivity) 
	{
		printInvalidCommandMessage(); 
		return false;
		// TODO Auto-generated method stub
		
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public void reinforce(Connectivity p_connectivity) 
	{
		// TODO Auto-generated method stub
		printInvalidCommandMessage(); 
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public void attack(Connectivity p_connectivity) 
	{
		// TODO Auto-generated method stub
		printInvalidCommandMessage(); 
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public void fortify(Connectivity p_connectivity) 
	{
		// TODO Auto-generated method stub
		printInvalidCommandMessage(); 
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public void validateMap(Connectivity p_connectivity) 
	{
		
		// TODO Auto-generated method stub
		printInvalidCommandMessage(); 
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public void endGame() 
	{
		// TODO Auto-generated method stub
		System.out.println("Thank you for Playing the Game");
		System.exit(0);
	}

	@Override
	public
	void enableTournament(String mycommand) {
		// TODO Auto-generated method stub
		
	}
	
	
}
