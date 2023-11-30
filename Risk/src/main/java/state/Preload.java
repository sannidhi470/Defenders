package state;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Controllers.GameEngine;
import Models.LogEntryBuffer;
import Models.Map;
import Models.Player;
import Tools.ColorCoding;
import Tools.Connectivity;
import Tools.ConquestMapLoader;
import Tools.MapEditor;
import Tools.MapLoader;
import Tools.SaveGame;

/**
 * Concrete state representing the phase before loading a map.
 * Allows the user to load, edit, and validate the map before entering PostLoad phase.
 */
public class Preload extends Edit 
{
	LogEntryBuffer d_logEntryBuffer= new LogEntryBuffer();
	/**
	 * Constructor for Preload phase.
	 * 
	 * @param p_ge The GameEngine object associated with this phase.
	 */
	public Preload(GameEngine p_ge) 
	{
		super(p_ge);
	}

	/**
	 * Loads a map using the provided commands.
	 * 
	 * @param p_connectivity  represents the map content
	 * @param p_commands Array of user commands for loading a map.
	 */
	public void loadMap(Connectivity p_connectivity, String[] p_commands) 
	{
		int l_newMapCreated = 0;
		if(p_commands.length == 2)
		{
			Map.checkMap(p_connectivity, p_commands[1]);
			
		} else {
			d_logEntryBuffer.log("No map entered. Please enter name of map to be loaded");
			System.out.println(ColorCoding.red+"No map entered. Please enter name of map to be loaded"+ColorCoding.blank);
		}
		if(l_newMapCreated == 0 )
		{
			d_logEntryBuffer.log("Validating the loaded Map");
			System.out.println(ColorCoding.cyan+"\n--------Validating the loaded map--------\n"+ColorCoding.blank);
			Tools.Graph l_graph=new Tools.Graph(p_connectivity.getD_countryList().size(),p_connectivity);
			if(l_graph.continentConnection(p_connectivity, l_graph))
				l_graph.isConnected(l_graph);		
			
		}
		else
		{
			System.out.println(ColorCoding.green+"Skipping Map Validation as it is a newly created map"+ColorCoding.blank);
		}
		next(p_connectivity);
	}
	
	/**
	 * Edits (add/remove) country information based on the provided commands.
	 * 
	 * @param p_commands Array of user commands for editing countries.
	 * @param p_connectivity  represents the map content
	 */
	public void editCountry(String[] p_commands,Connectivity p_connectivity) 
	{
		for(int i=1; i<p_commands.length;)
		{
			if(p_commands[i].equals("-add"))
			{
				MapEditor.addCountry(p_commands[i+1], p_commands[i+2], p_connectivity);
				i = i+3;
			}
			else if(p_commands[i].equals("-remove"))
			{
				MapEditor.removeCountry(p_commands[i+1], p_connectivity);
				i=i+2;
			}
			else
			{
				d_logEntryBuffer.log("ERROR: Invalid Command");
				System.out.println(ColorCoding.red+"ERROR: Invalid Command"+ColorCoding.blank);
			}
		} 
		next(p_connectivity);
	}
	
	/**
	 * Edits (add/remove) continent information based on the provided commands.
	 * 
	 * @param p_commands Array of user commands for editing continents.
	 * @param p_connectivity  represents the map content
	 */
	public void editContinent(String[] p_commands,Connectivity p_connectivity) 
	{
		for(int i=1; i<p_commands.length;)
		{
			if(p_commands[i].equals("-add"))
			{
				d_logEntryBuffer.log("Added Country");
				MapEditor.addContinent(p_commands[i+1],Integer.parseInt(p_commands[i+2]), p_connectivity);
				i=i+3;
			}
			else if(p_commands[i].equals("-remove") )
			{
					MapEditor.removeContinent(p_commands[i+1], p_connectivity);
					i=i+2;
			}
			else
			{
				d_logEntryBuffer.log("Invalid Command");
				System.out.println("Invalid Command");
				break;
			}
		}
		next(p_connectivity);
	}
	
	/**
	 * Edits (add/remove) neighbor information based on the provided commands.
	 * 
	 * @param p_commands Array of user commands for editing neighbors.
	 * @param p_connectivity  represents the map content
	 */
	public void editNeighbor(String[] p_commands,Connectivity p_connectivity) 
	{
		for(int i=1; i<p_commands.length;)
		{
			if(p_commands[i].equals("-add"))
			{
				MapEditor.addNeighbour(Integer.parseInt(p_commands[i+1]), Integer.parseInt(p_commands[i+2]), p_connectivity);
				i=i+3;
			}
			else if(p_commands[i].equals("-remove"))
			{
				MapEditor.removeNeighbour(Integer.parseInt(p_commands[i+1]), Integer.parseInt(p_commands[i+2]), p_connectivity,1);
				i=i+3;
			}
			else
			{
				d_logEntryBuffer.log("ERROR: Invalid Command");
				System.out.println(ColorCoding.red+"ERROR: Invalid Command"+ColorCoding.blank);
			}
		}
		next(p_connectivity);
	}

	/**
	 * Validates the loaded map.
	 * 
	 * @param p_connectivity  represents the map content
	 */
	public void validateMap(Connectivity p_connectivity)
	{
		System.out.println(ColorCoding.cyan+"\n--------Validating the loaded map--------\n"+ColorCoding.blank);
		Tools.Graph l_graph=new Tools.Graph(p_connectivity.getD_countryList().size(),p_connectivity);
		if(l_graph.continentConnection(p_connectivity, l_graph))
			l_graph.isConnected(l_graph);
		next(p_connectivity);
		}
	
	/**
	 * Saves the map (invalid command in this state).
	 * 
	 * @param p_connectivity  represents the map content
	 * @param p_mapName The name of the map to save.
	 */
	public void saveMap(Connectivity p_connectivity, String p_mapName) 
	{
		printInvalidCommandMessage(); 
	}

	/**
	 * Moves to the next phase or exits the program based on user input.
	 */
	public void next(Connectivity p_connectivity) 
	{
		Scanner sc = new Scanner(System.in);
		String user_output;
		if(ge.getCheckIfTest()) {
			user_output = "no";
		}
		else if(ge.getCheckIfTournament()) {
			user_output = "no";
		}
		else if(ge.getCheckIfLoad())
		{
			user_output = "no";
		}
		else if(ge.getCheckIfSave())
		{
			user_output = "no";
		}
		else
		{
			System.out.println("Do you want to make more edits on the map (yes/no)?");
			user_output = sc.nextLine();
		}

		if(user_output.equalsIgnoreCase("exit"))
		{
	    	ge.setPhase(new End(ge));
	    	ge.getPhase().endGame(p_connectivity);
		}
		else if(user_output.equalsIgnoreCase("no"))
		{
			ge.setPhase(new PostLoad(ge));
		}
		else
		{
			System.out.println("Continue editing map:");
		}
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



	@Override
	public
	void enableTournament(String mycommand) {
		// TODO Auto-generated method stub
		
	}
		@Override
	public void loadgame(String[] p_commands, Connectivity p_connectivity, GameEngine ge) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
	}

}
