package Controllers;
import java.io.IOException;
import java.util.*;
import Models.*;
import Tools.ColorCoding;
import Tools.Connectivity;
import Tools.MapEditor;
import Tools.MapLoader;
import Tools.PlayersGameplay;
import Tools.SaveMap;
import Views.ViewMap;

/**
* The class GameEngine have the main function, so it starts the game by giving options to players to choose different commands.
*
*/

public class GameEngine{
	
	private static ArrayList<Player> l_playersArray = new ArrayList<Player>();
		
	
	/**
	* Gets the list of the players.
	* @return the Player list.
	*
	*/
	
	public static ArrayList<Player> getL_playersArray()
	{
		return l_playersArray;
	}
	

	/**
	* Starts the game.
	* @param args The command line arguments.
	* @throws java.io.IOException when we can't read a file .
	*
	*/
	public static void main(String[] args) throws IOException {

		LogEntryBuffer d_logEntryBuffer = new LogEntryBuffer();
		d_logEntryBuffer.clearFile();

		Scanner l_sc = new Scanner(System.in);
		Connectivity l_connectivity=new Connectivity();
		l_connectivity.setD_continentList(new ArrayList<Continent>());
		l_connectivity.setD_countryList(new ArrayList<Country>());
		
		String l_gamePhase="startup";
		d_logEntryBuffer.log("Startup Phase");
		d_logEntryBuffer.log("--------Welcome to Warzone--------");
		System.out.println("\n\n--------Welcome to Warzone--------\n");
		d_logEntryBuffer.log("Entered Option to Enter the Game OR Could have exited the Game");
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
				d_logEntryBuffer.log("Entered the Commands of Warzone");
				System.out.println("Enter the Commands of Warzone"+"\nGame Commands\n"+"1. loadmap\n"+"2. validatemap\n"+"3. editmap (editcontinent,editcountry,editneighbor)\n"+"4. showmap\n"+"5. savemap\n"+"6. gameplayer\n"+"7. assigncountries\n"+"8. help");
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
							if(l_commands[i].equals("-add"))
							{
								d_logEntryBuffer.log("Added Country");
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
								d_logEntryBuffer.log("Invalid Command");
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
								d_logEntryBuffer.log("ERROR: Invalid Command");
								System.out.println(ColorCoding.red+"ERROR: Invalid Command"+ColorCoding.blank);
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
								MapEditor.removeNeighbour(Integer.parseInt(l_commands[i+1]), Integer.parseInt(l_commands[i+2]), l_connectivity,1);
								i=i+3;
							}
							else
							{
								d_logEntryBuffer.log("ERROR: Invalid Command");
								System.out.println(ColorCoding.red+"ERROR: Invalid Command"+ColorCoding.blank);
							}
						}
						
						
						break;
						
					case "showmap":
						l_gamePhase="startup";
						ViewMap.viewMap(l_connectivity.getD_continentList(), l_connectivity.getD_countryList(), getL_playersArray());
						break;
						
					case "savemap":
						l_gamePhase="startup";
						SaveMap.saveMap(l_connectivity);
						break;
						
					case "validatemap":
						l_gamePhase="startup";
						Tools.Graph graph=new Tools.Graph(l_connectivity.getD_countryList().size(),l_connectivity);
						if(graph.continentConnection(l_connectivity, graph))
							graph.isConnected(graph);				
						break;
						
					case "loadmap":
						l_gamePhase="startup";
						int l_newMapCreated = 0;
						if(l_commands.length == 2)
						{
							l_newMapCreated =MapLoader.loadMap(l_connectivity,l_commands[1]);
						} else {
							d_logEntryBuffer.log("No map entered. Please enter name of map to be loaded");
							System.out.println(ColorCoding.red+"No map entered. Please enter name of map to be loaded"+ColorCoding.blank);
						}
						if(l_newMapCreated == 0 )
						{
							d_logEntryBuffer.log("Validating the loaded Map");
							System.out.println(ColorCoding.cyan+"\n--------Validating the loaded map--------\n"+ColorCoding.blank);
							Tools.Graph l_graph=new Tools.Graph(l_connectivity.getD_countryList().size(),l_connectivity);
							if(l_graph.continentConnection(l_connectivity, l_graph))
								l_graph.isConnected(l_graph);							
						}
						else
						{
							System.out.println(ColorCoding.green+"Skipping Map Validation as it is a newly created map"+ColorCoding.blank);
						}

						break;
						
					case "help":
							d_logEntryBuffer.log("Help is being used by Player");
							System.out.println("\nloadmap filename: \n	Game starts by user selection of a user-saved map file, which loads the map as a connected directed graph\n__________________________________________________________\n"
									+ "validatemap: \n	Verification of map correctness\n__________________________________________________________\n"
									+ "editmap filename: \n	User-driven creation/deletion of map elements: country, continent, and connectivity between countries.\n__________________________________________________________\n"
									+ "	editcontinent -add continentID continentvalue -remove continentID\n"
									+ "	editcountry -add countryID continentID -remove countryID\n"
									+ "	editneighbor -add countryID neighborcountryID -remove countryID neighborcountryID\n__________________________________________________________\n"
									+ "showmap: \n	show all countries and continents, armies on each country, ownership, and connectivity\n__________________________________________________________\n"
									+ "savemap: \n	save a map\n__________________________________________________________\n"
									+ "assigncountries: \n	countries get randomly assigned to players\n__________________________________________________________\n"
									+ "gameplayer -add playername -remove playername : \n	Command to add or remove players from the game\n__________________________________________________________\n");
						
					case "gameplayer":
						for(int i=1;i<l_commands.length;)
						{
							if(l_commands[i].equals("-add"))
							{
								Player l_player = new Player();
								l_player.setD_playerName(l_commands[i+1]);
								l_playersArray.add(l_player);
								d_logEntryBuffer.log(l_player.getD_playerName() + "added successfully");
								System.out.println(ColorCoding.green+l_player.getD_playerName()+" added successfully"+ColorCoding.blank);
								i=i+2;
								
							}
							else if(l_commands[i].equals("-remove"))
							{
								for(int j=0;j<l_playersArray.size();j++)
								{
									if(l_commands[i+1].equals(l_playersArray.get(j).getD_playerName()))
									{
										d_logEntryBuffer.log(l_playersArray.get(j).getD_playerName()+ "removed successfully");
										System.out.println(ColorCoding.green+l_playersArray.get(j).getD_playerName()+" removed successfully"+ColorCoding.blank);
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
							if(PlayersGameplay.assigncountries(l_playersArray,l_connectivity.getD_countryList(),l_connectivity.getD_continentList())==0)
							{
								d_logEntryBuffer.log("Countries assigned to players Successfully");
								System.out.println(ColorCoding.green+"Countries assigned to players Successfully"+ColorCoding.blank+"\n");
								l_gamePhase="mainGameLoop";
							}
						}
						else
						{
							d_logEntryBuffer.log("ERROR: No players to assign Countries");
							System.out.println(ColorCoding.red+"ERROR: No players to assign Countries"+ColorCoding.blank);
						}
						
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
						d_logEntryBuffer.log("Invalid Command");
						System.out.println(ColorCoding.red+"Invalid Command"+ColorCoding.blank);
							
					}	
				}
				
			}
			else
			{
				if(l_gamePhase.equals("startup"))
				{
					d_logEntryBuffer.log("Invalid Command");
					System.out.println(ColorCoding.red+"ERROR: Invalid Command"+ColorCoding.blank);
					l_flag = 0;
				}
			}
			
			
			if(l_gamePhase.equals("mainGameLoop"))
			{
				PlayersGameplay.assignArmiesToPlayers(l_playersArray);
				d_logEntryBuffer.log("Game Begins");
				System.out.println("Game Begins!!!!!!!!!!!\n");
				for(int i=0;i<l_playersArray.size();i++)
				{
					d_logEntryBuffer.log("Player "+ Integer.sum(i,1) +"("+l_playersArray.get(i).getD_playerName()+") has Army Count: "+l_playersArray.get(i).getD_armyCount());
					System.out.println("Player "+ Integer.sum(i,1) +"("+l_playersArray.get(i).getD_playerName()+") has Army Count: "+l_playersArray.get(i).getD_armyCount());
					PlayersGameplay.showPlayersCountry(l_playersArray.get(i),1);
					System.out.println();
				}
				
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
							d_logEntryBuffer.log("Player "+l_playersArray.get(i).getD_playerName()+" deploy your troops:");
							System.out.println("Player "+l_playersArray.get(i).getD_playerName()+" deploy your troops:");
							l_userOrder=l_sc.nextLine();
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
									for(int j=0;j<l_playersArray.get(i).getD_Country().size();j++)
									{
										if(Integer.parseInt(l_tempOrderListArray[1])==(l_playersArray.get(i).getD_Country().get(j).getD_countryId()))
										{
											//Setting army count of country as per user's 'deploy' command
											l_playersArray.get(i).getD_Country().get(j).setD_armyDeployedOnCountry(l_playersArray.get(i).getD_Country().get(j).getD_armyDeployedOnCountry()+Integer.parseInt(l_tempOrderListArray[2]));
										}
									}
									//i++;
								}
								else
								{
									d_logEntryBuffer.log("Error: Please enter valid number of troops");
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
				l_gamePhase = "execute";
				d_logEntryBuffer.log("Execute Phase");
				l_flag=0;
				d_logEntryBuffer.log("All Armies have been successfully deployed. Enter command to proceed");
				System.out.println(ColorCoding.green+"All Armies have been successfully deployed. Enter command to proceed"+ColorCoding.blank);
				ViewMap.viewMap(l_connectivity.getD_continentList(), l_connectivity.getD_countryList(), getL_playersArray());
				
				
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
					d_logEntryBuffer.log(l_playersArray.get(i).getD_playerName()+"Asked whether he/she wants to give a command");
					System.out.println(ColorCoding.cyan+"\n"+l_playersArray.get(i).getD_playerName()+"!! Do you want to give command or pass?(Press enter to continue / pass)"+ColorCoding.blank);
					String l_passContinue=l_sc.nextLine();
					if(l_passContinue.equals("pass"))
					{
						playerNames.add(l_playersArray.get(i).getD_playerName());
						terminateFlag++;
						continue;
					}
					Order l_order=new Order();
					d_logEntryBuffer.log(l_playersArray.get(i).getD_playerName()+ "Asked for Command");
					System.out.println("\nEnter the Command for player: "+l_playersArray.get(i).getD_playerName());
					System.out.println("Cards available: "+l_playersArray.get(i).getCards());
					
					String l_orderinput=l_sc.nextLine();
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
						d_logEntryBuffer.log("Invalid Command!!");
						System.out.println(ColorCoding.red+"Invalid Command!!"+ColorCoding.blank);
						l_flag1=0;
					}
					
					}while(l_flag1==0);
					
				
			}
		//After countries are deployed
				
				}while(terminateFlag!=l_playersArray.size());
				
				 l_executeOrder=0;
				Set<String> l_emptyOrders=new HashSet<>();
				
				
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
				
                 l_flag=0;
			}
		}while(l_option !="exit");
		d_logEntryBuffer.log("The Game is Ended");
		System.out.println("Thank you for Playing the Game");
		l_sc.close();
		System.exit(0);
		
	}

}
