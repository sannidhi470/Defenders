package state;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import Controllers.GameEngine;
import Models.Player;
import Strategy.AggressivePlayerStrategy;
import Strategy.BenevolentPlayerStrategy;
import Strategy.CheaterPlayerStrategy;
import Strategy.HumanPlayerStrategy;
import Strategy.RandomPlayerStrategy;
import Tools.ColorCoding;
import Tools.Connectivity;
import Tools.LoadGame;
import Tools.MapCheck;
import Tools.MapLoader;

public class PlayGame extends Phase {
	Connectivity l_connectivity=new Connectivity();
	ArrayList<String> l_mapList = new ArrayList<>();
	HashSet<String> l_behaviourList = new HashSet<String>();
	int l_gameCount = 0;
	int l_roundCount = 0;
	GameEngine gameEngine = new GameEngine();
	ArrayList<String> d_gameResult = new ArrayList<>();
	String d_gameplayerCommand = "";

	public PlayGame(GameEngine p_ge) {
		super(p_ge);
	}
	
	public void enableTournament(String p_usercommand) {

		String[] l_splitUserCommand;
		l_splitUserCommand = p_usercommand.split("-");
		if(l_splitUserCommand[0].equals("tournament ") && l_splitUserCommand.length == 5)
		{
			for(int i=1; i<l_splitUserCommand.length; i++)
			{
				String[] l_parameters = l_splitUserCommand[i].split(" ");
				switch(l_parameters[0])
				{
				case "M":
					for(int j=1; j<l_parameters.length; j++)
					{
						File f = new File("");
						String absolute = f.getAbsolutePath();
						String l_pathName = absolute+ File.separator+"src/main/resources";
						if(!MapCheck.validateMap(l_parameters[j], l_pathName))
						{
							System.out.println("Map file "+l_parameters[j]+" does not exist");
							return;
						}
						else
							l_mapList.add(l_parameters[j]);
					}
					break;
				
					
				case "P":
					d_gameplayerCommand = l_splitUserCommand[i];
					if(l_parameters.length < 3 || l_parameters.length >5)
					{
						System.out.println("ERROR: Invalid number of players for tournament");
						System.out.println("Player limit allowed for the tournament: 2-4");
						return;
					}
					break;	
					
					
				case "G":
					if(l_parameters.length !=2)
					{
						System.out.println("ERROR: Invalid number of parameters found in G");
						return;
					}
					if(Integer.parseInt(l_parameters[1]) >= 1 && Integer.parseInt(l_parameters[1]) <=5)
						l_gameCount = Integer.parseInt(l_parameters[1]);
					else
					{
						System.out.println("INVALID COMMAND! Parameter G Limit: 1-5 games");
						return;
					}
					break;
							
							
				case "D":
					if(l_parameters.length !=2)
					{
						System.out.println("ERROR: Invalid number of parameters found in D");
						return;
					}
					if(Integer.parseInt(l_parameters[1]) >= 10 && Integer.parseInt(l_parameters[1]) <=50)
						l_roundCount = Integer.parseInt(l_parameters[1]);
					else
					{
						System.out.println("INVALID COMMAND! Parameter D Limit: 10-50 turns");
						return;
					}
					break;
					
				
				default:
					System.out.println("ERROR: Invalid Parameter "+l_parameters[0]+" in tournament command found");
					return;
				}			
			}
		}
		else
		{
			System.out.println("Invalid tournament command entered");
			return;
		}
		
		
		System.out.println("*********************STARTING TOURNAMENT*********************");		
		for(int i=0; i<l_mapList.size(); i++)
		{
			for(int j=0; j<l_gameCount; j++)
			{
				System.out.println("----------------GAME "+(j+1)+" BEGINS"+"----------------");
				startGame(l_mapList.get(i));
				String l_winner = d_gameResult.remove(d_gameResult.size()-1);
				if(l_winner.equals("DRAW"))
					l_winner = "Result --> "+l_winner + " for Map "+(i+1)+" Game "+(j+1);
				else
					l_winner = "Winner --> "+l_winner + " for Map "+(i+1)+" Game "+(j+1);
				d_gameResult.add(l_winner);
			}

		}
		System.out.println();
		System.out.println("TOURNAMENT SUMMARY");
		for(int i=0; i<d_gameResult.size(); i++)
			System.out.println(d_gameResult.get(i));
		System.out.println();
		
	}
	
	public void startGame(String p_mapName) {
		gameEngine.setCheckIfTournament(true);
		l_connectivity.setD_winnerPlayer(new Player());
		gameEngine.setConnectivity(l_connectivity);
		gameEngine.setPhase(new Preload(gameEngine));
		String[] mapCommand = ("loadmap "+p_mapName).split(" ");
		gameEngine.getPhase().loadMap(gameEngine.getConnectivity(), mapCommand);
		gameEngine.getPhase().saveMap(gameEngine.getConnectivity(), p_mapName);
		Play.l_playersArray.clear();
		gameEngine.getPhase().setPlayers(d_gameplayerCommand.split(" "),gameEngine.getConnectivity());
		gameEngine.getPhase().assignCountries(gameEngine.getConnectivity());
		gameEngine.getPhase().next(l_connectivity);
		for(int i=0; i<l_roundCount; i++)
		{
			
			if(!checkIfWinnerExists())
			{
				System.out.println("///////////////////////ROUND "+(i+1)+" BEGINS"+"///////////////////////");
				gameEngine.getPhase().reinforce(gameEngine.getConnectivity());
				gameEngine.getPhase().attack(gameEngine.getConnectivity());
				if(checkIfWinnerExists())
				{
					recordResult();
					return;
				}
				else
					gameEngine.getPhase().fortify(gameEngine.getConnectivity());
				
				if(i == l_roundCount-1)
				{
					if(!checkIfWinnerExists())
					{
						System.out.println("GAME RESULT = DRAW!!!");
						recordResult();
						ge.setPhase(new End(ge));
						return;
					}

				}
			}
			else
			{
				System.out.println("WE HAVE A WINNER!!! --> "+gameEngine.getConnectivity().getD_winnerPlayer().getD_playerName());
				recordResult();
				gameEngine.getPhase().endGame(l_connectivity);
				break;
			}

		}
		
	}
	
	public boolean checkIfWinnerExists()
	{

		if(gameEngine.getConnectivity().getD_winnerPlayer().getD_playerName() == null)
			return false;
		else
			return true;
	}
	
	public void recordResult()
	{
		if(checkIfWinnerExists())
			d_gameResult.add(gameEngine.getConnectivity().getD_winnerPlayer().getD_playerName());
		else
			d_gameResult.add("DRAW");
	}

	public void editCountry(String[] p_commands, Connectivity p_connectivity) {
		printInvalidCommandMessage(); 
	}

	public void editContinent(String[] p_commands, Connectivity p_connectivity) {
		printInvalidCommandMessage(); 
	}


	public void editNeighbor(String[] p_commands, Connectivity p_connectivity) {
		printInvalidCommandMessage(); 
	}

	public void saveMap(Connectivity p_connectivity, String p_mapName) {
		printInvalidCommandMessage(); 
	}

	public void setPlayers(String[] p_commands, Connectivity p_connectivity) {
		printInvalidCommandMessage(); 
		
	}

	public boolean assignCountries(Connectivity p_connectivity) {
		printInvalidCommandMessage(); 
		return false;
	}

	public void reinforce(Connectivity p_connectivity) {
		printInvalidCommandMessage(); 
	}

	public void attack(Connectivity p_connectivity) {
		printInvalidCommandMessage(); 
	}

	public void fortify(Connectivity p_connectivity) {
		printInvalidCommandMessage(); 
	}


	
	public void loadMap(Connectivity p_connectivity, String[] p_commands) 
	{
		printInvalidCommandMessage(); 	
	}

	public void next(Connectivity p_connectivity) {
		
	}

	public void validateMap(Connectivity p_connectivity) {
		printInvalidCommandMessage(); 
	}

	@Override
	public void loadgame(String[] p_commands, Connectivity p_connectivity, GameEngine ge) throws FileNotFoundException 
	{

		LoadGame.loadgame(p_commands[1],p_connectivity,ge);
		String l_phase_name= LoadGame.getD_GamePhase();
		String l_map_name= LoadGame.getD_MapName();
		switch(l_phase_name)
		{
		case "Preload":
			System.out.println("Map has not been loaded. Starting game...");
			ge.setCheckIfLoad(true);
			ge.start();
		case "Reinforcement":
			ge.setPhase(new Reinforcement(ge));
			PlaySetup playsetup = new PlaySetup(ge);
			playsetup.next(p_connectivity);
			break;			
		case "Attack":
			ge.setPhase(new Attack(ge));
			break;
		case "PlaySetup":
			ge.setPhase(new PlaySetup(ge));
			break;
		case "PostLoad":
			ge.setCheckIfLoad(true);
			ge.setPhase(new PostLoad(ge));
			break;
		case "Fortify":
			ge.setPhase(new Fortify(ge));
			break;
			
		}	
		
	}
	
}
