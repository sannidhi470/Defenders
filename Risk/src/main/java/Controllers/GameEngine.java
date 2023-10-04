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

public class GameEngine {
	
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

		Scanner l_sc = new Scanner(System.in);
		Connectivity l_connectivity=new Connectivity();
		l_connectivity.setD_continentList(new ArrayList<Continent>());
		l_connectivity.setD_countryList(new ArrayList<Country>());
		
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
				System.out.println("Enter the Commands of Warzone"+"\nGame Commands\n"+"1. loadmap\n"+"2. editmap (editcontinent,editcountry,editneighbor)\n"+"3. showmap\n"+"4. savemap\n"+"5. gameplayer\n"+"6. assigncountries");
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
								MapEditor.removeNeighbour(Integer.parseInt(l_commands[i+1]), Integer.parseInt(l_commands[i+2]), l_connectivity,1);
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
								System.out.println(ColorCoding.green+l_player.getD_playerName()+" added successfully"+ColorCoding.blank);
								i=i+2;
								
							}
							else if(l_commands[i].equals("-remove"))
							{
								for(int j=0;j<l_playersArray.size();j++)
								{
									if(l_commands[i+1].equals(l_playersArray.get(j).getD_playerName()))
									{
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
								System.out.println(ColorCoding.green+"Countries assigned to players Successfully"+ColorCoding.blank+"\n");
								l_gamePhase="mainGameLoop";
							}
						}
						else
						{
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
						System.out.println(ColorCoding.red+"Invalid Command"+ColorCoding.blank);
							
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
				System.out.println("Game Begins!!!!!!!!!!!\n");
				for(int i=0;i<l_playersArray.size();i++)
				{
					System.out.println("Player "+ Integer.sum(i,1) +"("+l_playersArray.get(i).getD_playerName()+") has Army Count: "+l_playersArray.get(i).getD_armyCount());
					PlayersGameplay.showPlayersCountry(l_playersArray.get(i),1);
					System.out.println();
				}
				
				int l_temp =1;
				int flag=0;
				while(l_temp>0) {
					for(int i=0;i<l_playersArray.size();i++)
					{
						String l_userOrder="";
						Order l_order=new Order();
						if(l_playersArray.get(i).getD_armyCount()!=0)
						{
							System.out.println("Player "+l_playersArray.get(i).getD_playerName()+" deploy your troops:");
							l_userOrder=l_sc.nextLine();
							String[] l_tempOrderListArray=l_userOrder.split(" ");
							for(int j=0;j<l_connectivity.getD_countryList().size();j++)
							{
								if(Integer.parseInt(l_tempOrderListArray[1])==(l_connectivity.getD_countryList().get(j).getD_countryId()))
									l_order.setD_fromCountry(l_connectivity.getD_countryList().get(j));
							}
							if(PlayersGameplay.checkArmyAvailable(Integer.parseInt(l_tempOrderListArray[2]),l_playersArray.get(i)))
							{
								l_order.setD_numberOfArmies(Integer.parseInt(l_tempOrderListArray[2]));
								l_playersArray.get(i).setD_Order(l_order);
								l_playersArray.get(i).issue_order();
							}
							else
							{
								System.out.println(ColorCoding.red+"Error: Please enter valid number of troops"+ColorCoding.blank);
								i--;
							}
							
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
				System.out.println("All Armies have been successfully deployed. Enter command to proceed");
			}
			
		}while(l_option !="exit");
		
		System.out.println("Thank you for Playing the Game");
		l_sc.close();
		System.exit(0);
		
	}

}
