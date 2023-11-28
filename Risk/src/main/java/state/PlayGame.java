package state;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import Controllers.GameEngine;
import Models.Player;
import Strategy.AggressivePlayerStrategy;
import Strategy.BenevolentPlayerStrategy;
import Strategy.CheaterPlayerStrategy;
import Strategy.HumanPlayerStrategy;
import Strategy.RandomPlayerStrategy;
import Tools.ColorCoding;
import Tools.Connectivity;
import Tools.MapCheck;

public class PlayGame extends Phase {
	Connectivity l_connectivity=new Connectivity();
	ArrayList<String> l_mapList = new ArrayList<>();
	HashSet<String> l_behaviourList = new HashSet<String>();
	int l_gameCount = 0;
	int l_roundCount = 0;
	GameEngine gameEngine = new GameEngine();
	ArrayList<String> d_gameResult = new ArrayList<>();

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
				System.out.println(l_splitUserCommand[i]);
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
					System.out.println("Final mapList = "+l_mapList);
					break;
				
					
				case "P":
					if(Play.getL_playersArray().size() == 0) 
					{
						System.out.println("ERROR: No players have been set. Please set players before entering tournament command");
						return;
					}
					if(Play.getL_playersArray().size() <2 || Play.getL_playersArray().size() >5)
					{
						System.out.println("ERROR: Invalid number of players for tournament");
						System.out.println("Player limit allowed for the tournament: 2-4");
						return;
					}
					if(!(Play.getL_playersArray().size() == (l_parameters.length -1)))
					{
						System.out.println("ERROR: Number of players added does not match the number of strategies defined in the tournament command");
						return;
					}

					for(int j=1; j<l_parameters.length; j++)
					{
						if(!l_behaviourList.contains(l_parameters[j]))
							l_behaviourList.add(l_parameters[j]);
						else
						{
							System.out.println("INVALID COMMAND! Duplicate behavior "+l_parameters[j]+" found");
							return;
						}
					}
					String[] l_behaviours = l_behaviourList.toArray(new String[l_behaviourList.size()]);
					for(int j=0; j<l_behaviours.length; j++)
					{
						switch(l_behaviours[j])
						{
						case "Aggressive":
							Play.getL_playersArray().get(j).setStrategy(new AggressivePlayerStrategy(Play.getL_playersArray().get(j),l_connectivity));
							break;
						case "Human":
							Play.getL_playersArray().get(j).setStrategy(new HumanPlayerStrategy(Play.getL_playersArray().get(j),l_connectivity));
							break;
						case "Benevolent":
							Play.getL_playersArray().get(j).setStrategy(new BenevolentPlayerStrategy(Play.getL_playersArray().get(j),l_connectivity));
							break;
						case "Random":
							Play.getL_playersArray().get(j).setStrategy(new RandomPlayerStrategy(Play.getL_playersArray().get(j),l_connectivity));
							break;
						case "Cheater":
							Play.getL_playersArray().get(j).setStrategy(new CheaterPlayerStrategy(Play.getL_playersArray().get(j),l_connectivity));
							break;
						default:
							System.out.println("Invalid strategy name");		
						}
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
					System.out.println("l_gameCount set to "+l_gameCount);
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
					System.out.println("l_roundCount set to "+l_roundCount);
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
			}

		}
		System.out.println("TOURNAMENT SUMMARY");
		
	}
	
	public void startGame(String p_mapName) {
		System.out.println("Begin");
		gameEngine.setCheckIfTournament(true);
		gameEngine.setConnectivity(l_connectivity);
		gameEngine.setPhase(new Preload(gameEngine));
		String[] mapCommand = ("loadmap "+p_mapName).split(" ");
		gameEngine.getPhase().loadMap(gameEngine.getConnectivity(), mapCommand);
		gameEngine.getPhase().saveMap(gameEngine.getConnectivity(), p_mapName);
		gameEngine.getPhase().assignCountries(gameEngine.getConnectivity());
		gameEngine.getPhase().next();
		for(int i=0; i<l_roundCount; i++)
		{
			if(gameEngine.getConnectivity().getD_winnerPlayer().getD_playerName() == null)
			{
				System.out.println("///////////////////////ROUND "+(i+1)+" BEGINS"+"///////////////////////");
				gameEngine.getPhase().reinforce(gameEngine.getConnectivity());
				gameEngine.getPhase().attack(gameEngine.getConnectivity());
				if(i == l_roundCount-1)
				{
					if(gameEngine.getConnectivity().getD_winnerPlayer().getD_playerName() == null)
					{
						System.out.println("GAME RESULT = DRAW!!!");
						d_gameResult.add("DRAW");
						gameEngine.getPhase().endGame();
						break;
					}

				}
				gameEngine.getPhase().fortify(gameEngine.getConnectivity());
			}
			else
			{
				System.out.println("WE HAVE A WINNER!!! --> "+gameEngine.getConnectivity().getD_winnerPlayer().getD_playerName());
				d_gameResult.add(gameEngine.getConnectivity().getD_winnerPlayer().getD_playerName());
				gameEngine.getPhase().endGame();
				break;
			}

		}
		
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
		for(int i=1;i<p_commands.length;)
		{
			if(p_commands[i].equals("-add"))
			{
				Player l_player = new Player();
				l_player.setD_playerName(p_commands[i+1]);
				Play.l_playersArray.add(l_player);
				//d_logEntryBuffer.log(l_player.getD_playerName() + "added successfully");
				System.out.println(ColorCoding.green+l_player.getD_playerName()+" added successfully"+ColorCoding.blank);
				i=i+2;
				
			}
			else if(p_commands[i].equals("-remove"))
			{
				for(int j=0;j<Play.l_playersArray.size();j++)
				{
					if(p_commands[i+1].equals(Play.l_playersArray.get(j).getD_playerName()))
					{
						//d_logEntryBuffer.log(l_playersArray.get(j).getD_playerName()+ "removed successfully");
						System.out.println(ColorCoding.green+Play.l_playersArray.get(j).getD_playerName()+" removed successfully"+ColorCoding.blank);
						Play.l_playersArray.remove(j);
						i=i+2;
						break;
					}
				}
			}
		}
		
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

	public void endGame() {
		ge.setPhase(new End(ge));
		System.exit(0);
	}
	
	public void loadMap(Connectivity p_connectivity, String[] p_commands) 
	{
		printInvalidCommandMessage(); 	
	}

	public void next() {
		
	}

	public void validateMap(Connectivity p_connectivity) {
		printInvalidCommandMessage(); 
	}
	
}
