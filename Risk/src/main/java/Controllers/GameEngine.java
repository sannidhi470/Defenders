package Controllers;
import java.io.IOException;
import java.util.*;
import Models.*;
import Models.Map;
import Tools.Connectivity;
import Tools.MapEditor;
import Tools.MapLoader;
import Tools.PlayersGameplay;
import Tools.SaveMap;
import Views.ViewMap;

public class GameEngine {

	public static void main(String[] args) throws IOException {

		Scanner l_sc = new Scanner(System.in);
		Connectivity l_connectivity=new Connectivity();
		l_connectivity.setD_continentList(new ArrayList<Continent>());
		l_connectivity.setD_countryList(new ArrayList<Country>());
		ArrayList<Player> l_playersArray = new ArrayList<Player>();
		String l_gamePhase="startup";
		System.out.println("\n\n--------Welcome to Warzone--------\n");
		System.out.println("Enter start to go to the warzone \nEnter exit to exit");
		String l_option = "";
		int l_flag = 0;
		do
		{	
			if(l_flag==0)
			{
				
				l_option = l_sc.next();
				l_sc.nextLine();
				l_flag =1;
				if(l_option.equals("exit")) break;
			}
			
			else if(l_flag == 1 && l_option.equals("start") && l_gamePhase.equals("startup"))
			{
				System.out.println("Enter the Commands of Warzone");
				System.out.println("Game Commands");
				System.out.println("1. loadmap\n"+"2. editmap (editcontinent,editcountry,editneighbor)\n"+"3. showmap\n"+"4. savemap");
				String l_str = l_sc.nextLine();
				String[] l_commands = l_str.split(" "); 
				if(l_commands[0]!= null)
				{
					switch(l_commands[0])
					{
					case "exit":
						l_option = "exit";
						break;
					case "editcontinent":
						l_gamePhase="startup";
						for(int i=1; i<l_commands.length;)
						{
							System.out.println(l_commands[i]);
							if(l_commands[i].equals("-add"))
							{
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
								System.out.println("Invalid Command");
								break;
							}
						}
						
						break;
						
					case "editcountry":
						l_gamePhase="startup";
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
								System.out.println("Invalid Command");
							}
						}
						
						break;
						
					case "editneighbor":
						l_gamePhase="startup";
						for(int i=1; i<l_commands.length;)
						{
							if(l_commands[i].equals("-add"))
							{
								MapEditor.addNeighbour(Integer.parseInt(l_commands[i+1]), Integer.parseInt(l_commands[i+2]), l_connectivity);
								i=i+3;
							}
							else if(l_commands[i].equals("-remove"))
							{
								MapEditor.addNeighbour(Integer.parseInt(l_commands[i+1]), Integer.parseInt(l_commands[i+2]), l_connectivity);
								i=i+3;
							}
							else
							{
								System.out.println("Invalid Command");
							}
						}
						
						
						break;
						
					case "showmap":
						l_gamePhase="startup";
						ViewMap.viewMap(l_connectivity.getD_continentList(), l_connectivity.getD_countryList());
						break;
						
					case "savemap":
						l_gamePhase="startup";
						SaveMap.saveMap(l_connectivity);
						break;
						
					case "validatemap":
						l_gamePhase="startup";
						break;
						
					case "loadmap":
						l_gamePhase="startup";
						if(l_commands.length == 2)
						{
							MapLoader.loadMap(l_connectivity,l_commands[1]);
						} else {
							System.out.println("No map entered. Please enter name of map to be loaded");
						}
						break;
						
					case "gameplayer":
						for(int i=1;i<l_commands.length;)
						{
							if(l_commands[i].equals("-add"))
							{
								Player l_player = new Player();
								l_player.setD_playerName(l_commands[i+1]);
								l_playersArray.add(l_player);
							
								i=i+2;
								
							}
							else if(l_commands[i].equals("-remove"))
							{
								for(int j=0;j<l_playersArray.size();j++)
								{
									if(l_commands[i+1].equals(l_playersArray.get(j).getD_playerName()))
									{
										l_playersArray.remove(j);
										i=i+2;
										break;
									}
								}
							}
						}
						
						break;
						
					case "assigncountries":
						l_gamePhase="startup";
						if(l_playersArray.size()>0)
						{
							PlayersGameplay.assigncountries(l_playersArray,l_connectivity.getD_countryList(),l_connectivity.getD_continentList());
							System.out.println("Countries assigned to players Successfully");
							l_gamePhase="assignArmies";
						}
						else
						{
							System.out.println("No players to assign Countries");
						}
						l_gamePhase="mainGameLoop";
						break;
						
					case "deploy":
						
						break;
					
					case "playersCountry":
						for(Player p:l_playersArray)
						{
							PlayersGameplay.showPlayersCountry(p,1);
						}
						break;
					default:
						System.out.println("Invalid Command");
							
					}	
				}
				
			}
			else
			{
				if(l_gamePhase.equals("startup"))
				{
					System.out.println("Invalid Commands");
					l_flag = 0;
				}
			}
			
			
			if(l_gamePhase.equals("mainGameLoop"))
			{
				PlayersGameplay.assignArmiesToPlayers(l_playersArray);
				System.out.println("Game Begins!!!!!!!!!!!");
				for(int i=0;i<l_playersArray.size();i++)
				{
					System.out.println("Player "+i+1 +" "+l_playersArray.get(i).getD_playerName()+" Army Count: "+l_playersArray.get(i).getD_armyCount());
					PlayersGameplay.showPlayersCountry(l_playersArray.get(i),1);
				}
				
//				l_gamePhase="issueOrder";
//				ArrayList<String> l_orders=new ArrayList<>();
//				l_orders.addAll(PlayersGameplay.issueOrder(l_playersArray));
//				PlayersGameplay.nextOrder(l_orders, l_playersArray);
//				l_flag=0;
				int l_temp =1;
				int flag=0;
				while(l_temp>0) {
					for(int i=0;i<l_playersArray.size();i++)
					{
						String l_userOrder="";
						Order l_order=new Order();
						System.out.println("player army count = "+l_playersArray.get(i).getD_armyCount());
						if(l_playersArray.get(i).getD_armyCount()!=0)
						{
							System.out.println("entered if");
							System.out.println("Player "+l_playersArray.get(i).getD_playerName()+" deploy your troops");
							l_userOrder=l_sc.nextLine();
							String[] l_tempOrderListArray=l_userOrder.split(" ");
							for(int j=0;j<l_connectivity.getD_countryList().size();j++)
							{
								if(Integer.parseInt(l_tempOrderListArray[1])==(l_connectivity.getD_countryList().get(j).getD_countryId()))
									l_order.setD_fromCountry(l_connectivity.getD_countryList().get(j));
							}
							l_order.setD_numberOfArmies(Integer.parseInt(l_tempOrderListArray[2]));
							l_playersArray.get(i).setD_Order(l_order);
							l_playersArray.get(i).issue_order();
						}
						else flag++;
						if(flag==l_playersArray.size())
						{
							l_temp=0;
							break;
						}
							

					}
				}
				PlayersGameplay.assignArmiesToPlayers(l_playersArray);
				
				for(int i=0;i<l_playersArray.size();i++)
					l_playersArray.get(i).getD_Order().execute(l_playersArray.get(i), l_playersArray.get(i).next_order());
				l_gamePhase = "execute";
				l_flag=0;
			}
			
		}while(l_option !="exit");
		
		System.out.println("Thank you for Playing the Game");
		System.exit(0);
		
	}
	
	
	
	
	
}
