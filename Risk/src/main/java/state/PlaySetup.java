package state;

import java.util.Scanner;

import Controllers.GameEngine;
import Models.Player;
import Strategy.AggressivePlayerStrategy;
import Strategy.BenevolentPlayerStrategy;
import Strategy.CheaterPlayerStrategy;
import Strategy.HumanPlayerStrategy;
import Tools.ColorCoding;
import Tools.Connectivity;
import Tools.PlayersGameplay;

/**
 * Concrete state representing the setup phase of the game.
 * This phase allows players to add or remove players, assign countries, and start the game.
 */
public class PlaySetup extends Play 
{

	/**
	 * Constructor for PlaySetup phase.
	 * 
	 * @param p_ge The GameEngine object associated with this phase.
	 */
	public PlaySetup(GameEngine p_ge) 
	{
		super(p_ge);
	}

	/**
	 * Sets the players based on the provided commands.
	 * 
	 * @param p_commands Array of user commands for setting up players.
	 */
	public void setPlayers(String[] p_commands,Connectivity p_connectivity) 
	{
		for(int i=1;i<p_commands.length;)
		{
			if(p_commands[i].equals("-add"))
			{
				Player l_player = new Player();
				System.out.println("Enter the behaviour of the player: Aggressive, Human, Benevolent, Random, Cheater");
				Scanner l_sc=new Scanner(System.in);
				String l_strategy=l_sc.nextLine();
				switch(l_strategy)
				{
				case "Aggressive":	
					l_player.setStrategy(new AggressivePlayerStrategy(l_player,p_connectivity));
					break;
				case "Human":
					l_player.setStrategy(new HumanPlayerStrategy(l_player,p_connectivity));
					break;
				case "Benevolent":
					l_player.setStrategy(new BenevolentPlayerStrategy(l_player,p_connectivity));
					break;
//				case "Random":
//					l_player.setStrategy(new RandomPlayerStrategy(l_player,p_connectivity));
//					break;
				case "Cheater":
					l_player.setStrategy(new CheaterPlayerStrategy(l_player,p_connectivity));
					break;
				default:
					System.out.println("Invalid strategy name");
					
				}
				
				
				
				l_player.setD_playerName(p_commands[i+1]);
				l_playersArray.add(l_player);
				//d_logEntryBuffer.log(l_player.getD_playerName() + "added successfully");
				System.out.println(ColorCoding.green+l_player.getD_playerName()+" added successfully"+ColorCoding.blank);
				i=i+2;
				
			}
			else if(p_commands[i].equals("-remove"))
			{
				for(int j=0;j<l_playersArray.size();j++)
				{
					if(p_commands[i+1].equals(l_playersArray.get(j).getD_playerName()))
					{
						//d_logEntryBuffer.log(l_playersArray.get(j).getD_playerName()+ "removed successfully");
						System.out.println(ColorCoding.green+l_playersArray.get(j).getD_playerName()+" removed successfully"+ColorCoding.blank);
						l_playersArray.remove(j);
						i=i+2;
						break;
					}
				}
			}
		}
		System.out.println("players have been set");
	}

	/**
	 * Assigns countries to players.
	 * 
	 * @param p_connectivity refers to connectivity object
	 * @return True if countries are successfully assigned, false otherwise.
	 */
	public boolean assignCountries(Connectivity p_connectivity) {
		if(l_playersArray.size()>0)
		{
			if(PlayersGameplay.assigncountries(l_playersArray,p_connectivity.getD_countryList(),p_connectivity.getD_continentList())==0)
			{
				//d_logEntryBuffer.log("Countries assigned to players Successfully");
				System.out.println(ColorCoding.green+"Countries assigned to players Successfully"+ColorCoding.blank+"\n");
			}
			else
			{
			return false;
			}
		}
		else
		{
			//d_logEntryBuffer.log("ERROR: No players to assign Countries");
			System.out.println(ColorCoding.red+"ERROR: No players to assign Countries"+ColorCoding.blank);
			return false;
		}
		System.out.println("countries have been assigned");
		for(Player p:l_playersArray)
		{
			PlayersGameplay.showPlayersCountry(p,1);
		}
		PlayersGameplay.assignArmiesToPlayers(l_playersArray);
		//d_logEntryBuffer.log("Game Begins");
		System.out.println("Game Begins!!!!!!!!!!!\n");
		for(int i=0;i<l_playersArray.size();i++)
		{
			//d_logEntryBuffer.log("Player "+ Integer.sum(i,1) +"("+l_playersArray.get(i).getD_playerName()+") has Army Count: "+l_playersArray.get(i).getD_armyCount());
			System.out.println("Player "+ Integer.sum(i,1) +"("+l_playersArray.get(i).getD_playerName()+") has Army Count: "+l_playersArray.get(i).getD_armyCount());
			PlayersGameplay.showPlayersCountry(l_playersArray.get(i),1);
			System.out.println();
		}
		return true;
	}
    /**
     * {@inheritDoc}
     */
	public void reinforce(Connectivity p_connectivity) 
	{
		printInvalidCommandMessage(); 
	}
	/**
	 * Attack phase is not applicable in PlaySetup and results in an invalid command.
	 * 
	 * @param p_connectivity refers to connectivity object
	 */
	public void attack(Connectivity p_connectivity) 
	{
		printInvalidCommandMessage(); 
	}
	/**
	 * Fortify phase is not applicable in PlaySetup and results in an invalid command.
	 * 
	 * @param p_connectivity refers to connectivity object
	 */
	public void fortify(Connectivity p_connectivity) 
	{
		printInvalidCommandMessage(); 
	}
	/**
	 * Ends the game and exits the program.
	 */
	public void endGame() 
	{
		System.out.println("Thank you for Playing the Game");
		System.exit(0);
	}
	/**
	 * Moves to the Reinforcement phase.
	 */
	public void next() 
	{
		ge.setPhase(new Reinforcement(ge));
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
	public void validateMap(Connectivity p_connectivity) 
	{
		// TODO Auto-generated method stub
		printInvalidCommandMessage();
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
	public void saveMap(Connectivity p_connectivity, String p_mapName) 
	{
		// TODO Auto-generated method stub
		printInvalidCommandMessage();
	}
}
