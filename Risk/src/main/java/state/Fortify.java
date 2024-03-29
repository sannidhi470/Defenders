package state;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import Controllers.GameEngine;
import Models.Continent;
import Models.Country;
import Models.LogEntryBuffer;
import Models.Order;
import Models.Player;
import Strategy.HumanPlayerStrategy;
import Tools.ColorCoding;
import Tools.Connectivity;
import Tools.PlayersGameplay;
import Tools.SaveGame;
import Views.ViewMap;

/**
* The Fortify class represents the phase where players can choose to fortify their countries.
*
* @author Sannidhi Shetty
*/
public class Fortify extends MainPlay 
{

    /**
     * Constructs a new Fortify phase with the given GameEngine.
     *
     * @param p_ge The GameEngine instance associated with this Fortify phase.
     */
	Fortify(GameEngine p_ge) 
	{
		super(p_ge);
	}
    /**
     * This method is not applicable in the Fortify phase.
     *
     * @param p_connectivity refers to the connection of the countries and continents.
     */
	public void reinforce(Connectivity p_connectivity) 
	{
		printInvalidCommandMessage(); 
	}

    /**
     * This method is not applicable in the Fortify phase.
     *
     * @param p_connectivity refers to the connection of the countries and continents.
     */
	public void attack(Connectivity p_connectivity) 
	{
		printInvalidCommandMessage(); 
	}

    /**
     * This method allows players to perform the fortification phase of their turn. Players can choose
     * to give commands or pass their turn. The method handles user input and executes orders accordingly.
     *
     * @param p_connectivity refers to the connection of the countries and continents.
     */
	public void fortify(Connectivity p_connectivity) 
	{
		Scanner sc = new Scanner(System.in);
		LogEntryBuffer d_logEntryBuffer= new LogEntryBuffer();
		Country l_getCountry=new Country();
		Player l_player;
		int l_countOrder =0;
		System.out.println("Do you want to fortify (yes/no)?");
		String user_output = "";
		if(ge.getCheckIfTest())
		{
			user_output = "no";
		}
		else if(ge.getCheckIfTournament())
		{
			user_output = "yes";
		}
		else
			user_output = sc.nextLine();
		if(user_output.equalsIgnoreCase("yes"))
		{
			int l_terminateFlag=0;
			int l_flag1=0;
			int l_executeOrder=0;
				l_terminateFlag=0;
				l_flag1=0;
				ArrayList<String> playerNames=new ArrayList<>();
			do
			{
			for(int i=0;i<l_playersArray.size();i++)
			{
				 l_flag1=1;
				do 
				{
				if(playerNames.contains(l_playersArray.get(i).getD_playerName()))
					continue;
				d_logEntryBuffer.log(l_playersArray.get(i).getD_playerName()+"Asked whether he/she wants to give a command");
				System.out.println(ColorCoding.cyan+"\n"+l_playersArray.get(i).getD_playerName()+"!! Do you want to give command or pass?(Press enter to continue / pass)"+ColorCoding.blank);
				Scanner l_sc = new Scanner(System.in);
				String l_passContinue = "";
				if(ge.getCheckIfTournament())
				{
					if(l_countOrder == l_playersArray.size())
						l_passContinue = "pass";
					else
					{
						l_passContinue = "continue";
						l_countOrder+=1;
					}
				}
				else
					l_passContinue=l_sc.nextLine();
				if(l_passContinue.equalsIgnoreCase("exit"))
				{
					System.out.println("Thank you for Playing the Game");
					System.exit(0);
				}
				if(l_passContinue.equals("pass"))
				{
					playerNames.add(l_playersArray.get(i).getD_playerName());
					l_terminateFlag++;
					break;
				}
				Order l_order=new Order();
				d_logEntryBuffer.log(l_playersArray.get(i).getD_playerName()+ "Asked for Command");
				System.out.println("\nEnter the Command for player: "+l_playersArray.get(i).getD_playerName());
				System.out.println("Cards available: "+l_playersArray.get(i).getCards());
				String l_orderinput="";
				
				if(l_playersArray.get(i).getStrategy() instanceof HumanPlayerStrategy)
				{
					l_orderinput=l_sc.nextLine();
				}
				else
				{
					if(l_playersArray.get(i).issue_order())
					{
						Order o = l_playersArray.get(i).next_order();
						l_orderinput=o.getOrderContent();
					}
					else
						continue;
				}
				
				if(l_orderinput.equalsIgnoreCase("exit"))
				{
					Scanner scanner= new Scanner(System.in);
			    	System.out.println("Do you want to save the game?");
			    	String choice = scanner.nextLine();
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
			        System.exit(0);
			    	}
				}
				String[] l_inputOrderArray=l_orderinput.split(" ");
				switch(l_inputOrderArray[0])
				{
				case "advance":
					l_order.setOrderContent(l_orderinput);
					l_playersArray.get(i).getD_playerOrder().add(l_order);
					l_flag1=1;
					break;
				default:
					System.out.println(ColorCoding.red+"Invalid Command!!"+ColorCoding.blank);
					l_flag1=0;
				}
				
				}while(l_flag1==0);
				
			
		}
			
			}while(l_terminateFlag!=l_playersArray.size());
			
			 l_executeOrder=0;
			 HashSet<String> l_emptyOrders=new HashSet<>();	
			
			 while(l_executeOrder!=l_playersArray.size())
				{
					for(int j=0;j<l_playersArray.size();j++)
					{
						
						if(l_emptyOrders.contains(l_playersArray.get(j).getD_playerName())) continue;
						if(l_playersArray.get(j).getD_playerOrder().size()==0) 
						{
							l_emptyOrders.add(l_playersArray.get(j).getD_playerName());
							l_executeOrder++;
							continue;	
						}
						
						l_playersArray.get(j).getD_Order().execute(l_playersArray.get(j), l_playersArray.get(j).next_order(),p_connectivity,1,1);
					}
				}
			ViewMap.viewMap(p_connectivity.getD_continentList(), p_connectivity.getD_countryList(), l_playersArray);
			PlayersGameplay.resetDiplomacy(l_playersArray);
			System.out.println("fortification done");
			ge.setPhase(new Reinforcement(ge));
			PlayersGameplay.assignArmiesToPlayers(l_playersArray);
		}
		else if (user_output.equalsIgnoreCase("no"))
		{
			ge.setPhase(new Reinforcement(ge));
			PlayersGameplay.assignArmiesToPlayers(l_playersArray);
		}
		
	}

    /**
     * Moves the game to the next phase (Reinforcement) after fortification.
     */
	public void next(Connectivity p_connectivity) 
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
		
	}
    /**
     * {@inheritDoc}
     */
	@Override
	public void validateMap(Connectivity p_connectivity) 
	{
		// TODO Auto-generated method stub
		
	}
    /**
     * {@inheritDoc}
     */
	@Override
	public void editCountry(String[] p_commands, Connectivity p_connectivity) 
	{
		// TODO Auto-generated method stub
		
	}
    /**
     * {@inheritDoc}
     */
	@Override
	public void editContinent(String[] p_commands, Connectivity p_connectivity) 
	{
		// TODO Auto-generated method stub
		
	}
    /**
     * {@inheritDoc}
     */
	@Override
	public void editNeighbor(String[] p_commands, Connectivity p_connectivity) 
	{
		// TODO Auto-generated method stub
		
	}
    /**
     * {@inheritDoc}
     */
	@Override
	public void saveMap(Connectivity p_connectivity, String p_mapName) 
	{
		// TODO Auto-generated method stub
		
	}
    /**
     * {@inheritDoc}
     */
	@Override
	public void setPlayers(String[] p_commands,Connectivity p_connectivity) 
	{
		// TODO Auto-generated method stub
		
	}
    /**
     * {@inheritDoc}
     */
	@Override
	public boolean assignCountries(Connectivity p_connectivity) 
	{
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public
	void enableTournament(String mycommand) {
		// TODO Auto-generated method stub
		
	}
}
