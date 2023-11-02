package state;

import java.util.Scanner;

import Controllers.GameEngine;
import Tools.ColorCoding;
import Tools.Connectivity;
import Tools.MapEditor;
import Tools.MapLoader;

public class Preload extends Edit {

	public Preload(GameEngine p_ge) {
		super(p_ge);
	}

	public void loadMap(Connectivity l_connectivity, String[] l_commands) {
		int l_newMapCreated = 0;
		if(l_commands.length == 2)
		{
			l_newMapCreated =MapLoader.loadMap(l_connectivity,l_commands[1]);
		} else {
			//d_logEntryBuffer.log("No map entered. Please enter name of map to be loaded");
			System.out.println(ColorCoding.red+"No map entered. Please enter name of map to be loaded"+ColorCoding.blank);
		}
		if(l_newMapCreated == 0 )
		{
			//d_logEntryBuffer.log("Validating the loaded Map");
			System.out.println(ColorCoding.cyan+"\n--------Validating the loaded map--------\n"+ColorCoding.blank);
			Tools.Graph l_graph=new Tools.Graph(l_connectivity.getD_countryList().size(),l_connectivity);
			if(l_graph.continentConnection(l_connectivity, l_graph))
				l_graph.isConnected(l_graph);		
			
		}
		else
		{
			System.out.println(ColorCoding.green+"Skipping Map Validation as it is a newly created map"+ColorCoding.blank);
		}
		//System.out.println(p_mapName);
		next();
	}

	public void editCountry(String[] l_commands,Connectivity l_connectivity) {
		for(int i=1; i<l_commands.length;)
		{
			if(l_commands[i].equals("-add"))
			{
				MapEditor.addCountry(l_commands[i+1], l_commands[i+2], l_connectivity);
				i = i+3;
			}
			else if(l_commands[i].equals("-remove"))
			{
				MapEditor.removeCountry(l_commands[i+1], l_connectivity);
				i=i+2;
			}
			else
			{
				//d_logEntryBuffer.log("ERROR: Invalid Command");
				System.out.println(ColorCoding.red+"ERROR: Invalid Command"+ColorCoding.blank);
			}
		} 
		next();
	}
	
	public void editContinent(String[] l_commands,Connectivity l_connectivity) {
		for(int i=1; i<l_commands.length;)
		{
			if(l_commands[i].equals("-add"))
			{
				//d_logEntryBuffer.log("Added Country");
				System.out.println("add");
					MapEditor.addContinent(l_commands[i+1],Integer.parseInt(l_commands[i+2]), l_connectivity);
					i=i+3;
			}
			else if(l_commands[i].equals("-remove") )
			{
					MapEditor.removeContinent(l_commands[i+1], l_connectivity);
					i=i+2;
			}
			else
			{
				//d_logEntryBuffer.log("Invalid Command");
				System.out.println("Invalid Command");
				break;
			}
		}
		next();
	}
	
	public void editNeighbor(String[] l_commands,Connectivity l_connectivity) {
		for(int i=1; i<l_commands.length;)
		{
			if(l_commands[i].equals("-add"))
			{
				MapEditor.addNeighbour(Integer.parseInt(l_commands[i+1]), Integer.parseInt(l_commands[i+2]), l_connectivity);
				i=i+3;
			}
			else if(l_commands[i].equals("-remove"))
			{
				MapEditor.removeNeighbour(Integer.parseInt(l_commands[i+1]), Integer.parseInt(l_commands[i+2]), l_connectivity,1);
				i=i+3;
			}
			else
			{
				//d_logEntryBuffer.log("ERROR: Invalid Command");
				System.out.println(ColorCoding.red+"ERROR: Invalid Command"+ColorCoding.blank);
			}
		}
		next();
	}

	public void validateMap(Connectivity l_connectivity)
	{
		System.out.println(ColorCoding.cyan+"\n--------Validating the loaded map--------\n"+ColorCoding.blank);
		Tools.Graph l_graph=new Tools.Graph(l_connectivity.getD_countryList().size(),l_connectivity);
		if(l_graph.continentConnection(l_connectivity, l_graph))
			l_graph.isConnected(l_graph);
		next();
		}
	
	public void saveMap(Connectivity l_connectivity, String p_mapName) {
		printInvalidCommandMessage(); 
	}

	public void next() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Do you want to make more edits on the map (yes/no)?");
		String user_output = sc.nextLine();
		if(user_output.equalsIgnoreCase("exit"))
		{
			System.out.println("Thank you for Playing the Game");
			System.exit(0);
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
	public void endGame() {
		// TODO Auto-generated method stub
		System.out.println("Thank you for Playing the Game");
		System.exit(0);
	}
}
