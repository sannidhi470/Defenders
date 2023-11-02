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
 *	
 */
public class Reinforcement extends MainPlay {

	Reinforcement(GameEngine p_ge) {
		super(p_ge);
	}

	public void reinforce(Connectivity l_connectivity) {
		//PlayersGameplay.assignArmiesToPlayers(l_playersArray);
		//d_logEntryBuffer.log("Game Begins");
//		System.out.println("Game Begins!!!!!!!!!!!\n");
//		for(int i=0;i<l_playersArray.size();i++)
//		{
//			//d_logEntryBuffer.log("Player "+ Integer.sum(i,1) +"("+l_playersArray.get(i).getD_playerName()+") has Army Count: "+l_playersArray.get(i).getD_armyCount());
//			System.out.println("Player "+ Integer.sum(i,1) +"("+l_playersArray.get(i).getD_playerName()+") has Army Count: "+l_playersArray.get(i).getD_armyCount());
//			PlayersGameplay.showPlayersCountry(l_playersArray.get(i),1);
//			System.out.println();
//		}
		
		int l_temp =1;
		int flag=0;
		ArrayList<String> l_tempName=new ArrayList<>();
		while(l_temp>0) {
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
							//i++;
						}
						else
						{
							//d_logEntryBuffer.log("Error: Please enter valid number of troops");
							System.out.println(ColorCoding.red+"Error: Please enter valid number of troops"+ColorCoding.blank);
							//i--;
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
					l_playersArray.get(i).getD_Order().execute(l_playersArray.get(i), l_playersArray.get(i).next_order(),l_connectivity,0);
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
		//d_logEntryBuffer.log("Execute Phase");
//		l_flag=0;
		//d_logEntryBuffer.log("All Armies have been successfully deployed. Enter command to proceed");
		System.out.println(ColorCoding.green+"All Armies have been successfully deployed. Enter command to proceed"+ColorCoding.blank);
		ViewMap.viewMap(l_connectivity.getD_continentList(), l_connectivity.getD_countryList(), Play.getL_playersArray());
		System.out.println("reinforcement done");
		ge.setPhase(new Attack(ge));
	}

	public void attack(Connectivity l_connectivity) {
		printInvalidCommandMessage(); 
	}

	public void fortify() {
		printInvalidCommandMessage(); 
	}

	public void next() {
		ge.setPhase(new Attack(ge));
	}

	@Override
	public void loadMap(Connectivity l_connectivity, String[] l_commands) {
		// TODO Auto-generated method stub
		printInvalidCommandMessage();
	}

	@Override
	public void validateMap(Connectivity l_connectivity) {
		// TODO Auto-generated method stub
		printInvalidCommandMessage();
	}

	@Override
	public void editCountry(String[] l_commands, Connectivity l_connectivity) {
		// TODO Auto-generated method stub
		printInvalidCommandMessage();
	}

	@Override
	public void editContinent(String[] l_commands, Connectivity l_connectivity) {
		// TODO Auto-generated method stub
		printInvalidCommandMessage();
	}

	@Override
	public void editNeighbor(String[] l_commands, Connectivity l_connectivity) {
		// TODO Auto-generated method stub
		printInvalidCommandMessage();
	}

	@Override
	public void saveMap(Connectivity l_connectivity) {
		// TODO Auto-generated method stub
		printInvalidCommandMessage();
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
	public void endGame() {
		// TODO Auto-generated method stub
		super.endGame();
		System.out.println("Thank you for Playing the Game");
		System.exit(0);
		
	}
}
