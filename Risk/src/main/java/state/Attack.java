package state;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import Controllers.GameEngine;
import Models.Order;
import Tools.ColorCoding;
import Tools.Connectivity;
import Tools.PlayersGameplay;

/**
 *	ConcreteState of the State pattern. In this example, defines behavior 
 *  for commands that are valid in this state, and for the others signifies  
 *  that the command is invalid. 
 */
public class Attack extends MainPlay {

	Attack(GameEngine p_ge) {
		super(p_ge);
	}

	/**
	 *  Call this method to go the the next state in the sequence. 
	 */
	public void next() {
		ge.setPhase(new Fortify(ge));
	}
	
	public void reinforce(Connectivity l_connectivity) {
		printInvalidCommandMessage(); 
	}

	public void attack(Connectivity l_connectivity) {
		int terminateFlag=0;
		int l_winner=0;
		int l_flag1=0;
		int l_executeOrder=0;
		while(l_winner==0) {
			terminateFlag=0;
			l_flag1=0;
			ArrayList<String> playerNames=new ArrayList<>();
		do{
			if(PlayersGameplay.winnerPlayer(l_playersArray, l_connectivity)!=null)
			{
				l_winner++;
				break;
			}
		for(int i=0;i<l_playersArray.size();i++)
		{
			 l_flag1=1;
			do {
			if(playerNames.contains(l_playersArray.get(i).getD_playerName()))
				continue;
			//d_logEntryBuffer.log(l_playersArray.get(i).getD_playerName()+"Asked whether he/she wants to give a command");
			System.out.println(ColorCoding.cyan+"\n"+l_playersArray.get(i).getD_playerName()+"!! Do you want to give command or pass?(Press enter to continue / pass)"+ColorCoding.blank);
			Scanner l_sc = new Scanner(System.in);
			String l_passContinue=l_sc.nextLine();
			if(l_passContinue.equalsIgnoreCase("exit"))
			{
				System.out.println("Thank you for Playing the Game");
				System.exit(0);
			}
			if(l_passContinue.equals("pass"))
			{
				playerNames.add(l_playersArray.get(i).getD_playerName());
				terminateFlag++;
				continue;
			}
			Order l_order=new Order();
			//d_logEntryBuffer.log(l_playersArray.get(i).getD_playerName()+ "Asked for Command");
			System.out.println("\nEnter the Command for player: "+l_playersArray.get(i).getD_playerName());
			System.out.println("Cards available: "+l_playersArray.get(i).getCards());
			String l_orderinput=l_sc.nextLine();
			if(l_orderinput.equalsIgnoreCase("exit"))
			{
				System.out.println("Thank you for Playing the Game");
				System.exit(0);
			}
			String[] l_inputOrderArray=l_orderinput.split(" ");
			//make a function to validate command..
			switch(l_inputOrderArray[0])
			{
			case "advance":
				//System.out.println("Call Advance");
				l_order.setOrderContent(l_orderinput);
				l_playersArray.get(i).getD_playerOrder().add(l_order);
				l_flag1=1;
				break;
			case "bomb":
				//System.out.println("Call Bomb");
				l_order.setOrderContent(l_orderinput);
				l_playersArray.get(i).getD_playerOrder().add(l_order);
				l_flag1=1;
				break;
			case "blockade":
				//System.out.println("Call Blockade");
				l_order.setOrderContent(l_orderinput);
				l_playersArray.get(i).getD_playerOrder().add(l_order);
				l_flag1=1;
				break;
			case "airlift":
				//System.out.println("Call airlift");
				l_order.setOrderContent(l_orderinput);
				l_playersArray.get(i).getD_playerOrder().add(l_order);
				l_flag1=1;
				break;
			case "negotiate":
				//System.out.println("Call negotiate");
				l_order.setOrderContent(l_orderinput);
				l_playersArray.get(i).getD_playerOrder().add(l_order);
				l_flag1=1;
				break;
			default:
				//d_logEntryBuffer.log("Invalid Command!!");
				System.out.println(ColorCoding.red+"Invalid Command!!"+ColorCoding.blank);
				l_flag1=0;
			}
			
			}while(l_flag1==0);
			
		
	}
//After countries are deployed
		
		}while(terminateFlag!=l_playersArray.size());
		
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
				l_playersArray.get(j).getD_Order().execute(l_playersArray.get(j), l_playersArray.get(j).next_order(),l_connectivity,1);
			}
		}
		
		PlayersGameplay.resetDiplomacy(l_playersArray);
		
	}
		System.out.println("attack done");
		ge.setPhase(new Fortify(ge));
	}

	public void fortify() {
		printInvalidCommandMessage(); 
	}

	@Override
	public void loadMap(Connectivity l_connectivity, String[] l_commands) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateMap(Connectivity l_connectivity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editCountry(String[] l_commands, Connectivity l_connectivity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editContinent(String[] l_commands, Connectivity l_connectivity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editNeighbor(String[] l_commands, Connectivity l_connectivity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveMap(Connectivity l_connectivity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPlayers(String[] l_commands) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean assignCountries(Connectivity l_connectivity) {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endGame() {
		// TODO Auto-generated method stub
		System.out.println("Thank you for Playing the Game");
		System.exit(0);
	}

}
