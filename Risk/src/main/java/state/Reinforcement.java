package state;

import java.util.ArrayList;
import java.util.Scanner;

import Controllers.GameEngine;
import Models.Order;
import Tools.ColorCoding;
import Tools.Connectivity;
import Tools.PlayersGameplay;
import Views.ViewMap;

/**
 * Concrete state representing the Reinforcement phase of the game.
 * Allows players to deploy armies and proceed to the Attack phase.
 */
public class Reinforcement extends MainPlay 
{

	/**
	 * Constructor for Reinforcement phase.
	 * 
	 * @param p_ge The GameEngine object associated with this phase.
	 */
	Reinforcement(GameEngine p_ge) 
	{
		super(p_ge);
	}

	/**
	 * Allows players to deploy armies during the reinforcement phase.
	 * 
	 * @param p_connectivity
	 */
	public void reinforce(Connectivity p_connectivity) 
	{
		int l_temp =1;
		int flag=0;
		ArrayList<String> l_tempName=new ArrayList<>();
		while(l_temp>0) 
		{
			for(int i=0;i<l_playersArray.size();i++)
			{
				String l_userOrder="";
				Order l_order=new Order();
				boolean l_validUserCommand =false;
				if(l_playersArray.get(i).getD_armyCount()!=0)
				{
					//d_logEntryBuffer.log("Player "+l_playersArray.get(i).getD_playerName()+" deploy your troops:");
					System.out.println("Player "+l_playersArray.get(i).getD_playerName()+" deploy your troops:");
					Scanner l_sc = new Scanner(System.in);
					if(ge.getCheckIfTest())
					{
						int l_countryID = l_playersArray.get(i).getD_Country().get(0).getD_countryId();
						int l_armycount = l_playersArray.get(i).getD_armyCount();
						l_userOrder = "deploy "+l_countryID+" "+l_armycount;
						System.out.println("For testcase, we have the following command\n"+l_userOrder);
					}
					else
						l_userOrder=l_sc.nextLine();
					if(l_userOrder.equalsIgnoreCase("exit"))
					{
						System.out.println("Thank you for Playing the Game");
						System.exit(0);
					}
					String[] l_tempOrderListArray=l_userOrder.split(" ");
					for(int j=0;j<l_playersArray.get(i).getD_Country().size();j++)
					{
						if(Integer.parseInt(l_tempOrderListArray[1])==(l_playersArray.get(i).getD_Country().get(j).getD_countryId()))
						{
							l_order.setD_fromCountry(l_playersArray.get(i).getD_Country().get(j));
							l_validUserCommand= true;
						}
					}
					if(l_validUserCommand)
					{
						if(PlayersGameplay.checkArmyAvailable(Integer.parseInt(l_tempOrderListArray[2]),l_playersArray.get(i)))
						{
							l_order.setD_numberOfArmies(Integer.parseInt(l_tempOrderListArray[2]));
							l_playersArray.get(i).setD_Order(l_order);
							l_playersArray.get(i).issue_order();
						}
						else
						{
							System.out.println(ColorCoding.red+"Error: Please enter valid number of troops"+ColorCoding.blank);
						}
					}
					else
					{
						System.out.println(ColorCoding.red+"INVALID Command as player "+l_playersArray.get(i).getD_playerName()+" doesn't control country with countryID "+l_tempOrderListArray[1]+ColorCoding.blank);
					}
					for(int j=0;j<l_playersArray.size();j++)
					{
						if(l_tempName.contains(l_playersArray.get(j).getD_playerName()))
							continue;
						else
						{
						if(l_playersArray.get(j).getD_armyCount()==0)
							{
								l_tempName.add(l_playersArray.get(j).getD_playerName());
								flag++;	
							}
						}
					}

				}
				if(flag==l_playersArray.size())
				{
					l_temp=0;
					break;
				}
			}
		}
		PlayersGameplay.assignArmiesToPlayers(l_playersArray);
		l_temp=1;
		flag=0;
		while(l_temp>0) 
		{
			for(int i=0;i<l_playersArray.size();i++)
			{
				if(l_playersArray.get(i).getD_armyCount()!=0)
				{
					l_playersArray.get(i).getD_Order().execute(l_playersArray.get(i), l_playersArray.get(i).next_order(),p_connectivity,0,0);
					if(l_playersArray.get(i).getD_armyCount()==0)
					{
						flag+=1;
					}
				}
				if(flag==l_playersArray.size())
				{
					l_temp=0;
					break;
				}
				
			}
			
			
		}
		System.out.println(ColorCoding.green+"All Armies have been successfully deployed. Enter command to proceed"+ColorCoding.blank);
		ViewMap.viewMap(p_connectivity.getD_continentList(), p_connectivity.getD_countryList(), Play.getL_playersArray());
		System.out.println("reinforcement done");
		ge.setPhase(new Attack(ge));
	}

	/**
	 * Invalid command in Reinforcement phase.
	 * Players cannot attack during the Reinforcement phase.
	 * 
	 * @param p_connectivity
	 */
	public void attack(Connectivity p_connectivity) 
	{
		printInvalidCommandMessage(); 
	}

	/**
	 * Invalid command in Reinforcement phase.
	 * Players cannot fortify during the Reinforcement phase.
	 * 
	 * @param p_connectivity
	 */
	public void fortify(Connectivity p_connectivity) 
	{
		printInvalidCommandMessage(); 
	}

	/**
	 * Moves to the Attack phase.
	 */
	public void next() 
	{
		ge.setPhase(new Attack(ge));
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public void loadMap(Connectivity p_connectivity, String[] p_commands) 
	{
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

    /**
     * {@inheritDoc}
     */
	@Override
	public void setPlayers(String[] p_commands) 
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
	public void endGame() 
	{
		// TODO Auto-generated method stub
		System.out.println("Thank you for Playing the Game");
		System.exit(0);
		
	}
}
