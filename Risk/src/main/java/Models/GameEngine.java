package Models;
import java.io.IOException;
import java.util.*;

import Services.SaveMap;
import Utilities.Connectivity;
import Utilities.MapEditor;
import Utilities.MapLoader;

public class GameEngine {

	public static void main(String[] args) throws IOException {

		Scanner l_sc = new Scanner(System.in);
		Connectivity l_connectivity=new Connectivity();
		l_connectivity.setD_continentList(new ArrayList<Continent>());
		l_connectivity.setD_countryList(new ArrayList<Country>());
		ArrayList<Player> l_playersArray = new ArrayList<Player>();
		System.out.println("\n\n--------Welcome to Warzone--------\n");
		
		String l_option = "";
		
		int flag = 0;
		
		do
		{	
			if(flag==0)
			{
				System.out.println("Enter start to go to the warzone \nEnter exit to exit");
				l_option = l_sc.next();
				l_sc.nextLine();
				flag =1;
			}
			
			else if(flag == 1 && l_option.equals("start"))
			{
				System.out.println("Enter the Commands of Warzone");
				System.out.println("Game Commands");
				System.out.println("1. loadmap\n"+"2. editmap\n"+"3. showmap\n"+"4. savemap");
				String l_str = l_sc.nextLine();
				String[] l_commands = l_str.split(" "); 
				
				if(l_commands[0]!= null)
				{
					switch(l_commands[0])
					{
					case "end":
						l_option = "end";
						break;
					case "editcontinent":
						
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
						if(l_commands.length == 2)
						{
							//function call for "showmap filename" (Map editor command)
						}
						else
						{
							System.out.println("Invalid Command");
						}
						
						break;
						
					case "savemap":
						SaveMap.saveMap(l_connectivity);
						break;
						
					case "editmap":
						MapLoader.loadMap(l_connectivity, l_commands[1]);
						break;
						
					case "validatemap":
						//function call
						break;
						
					case "loadmap":
						if(l_commands.length == 2)
						{
							MapLoader.loadMap(l_connectivity,l_commands[1]);
						}
						
						break;
						
					case "gameplayer":
						for(int i=1;i<l_commands.length;)
						{
							if(l_commands[i].equals("-add"))
							{
								Player l_player = new Player();
								l_playersArray.add(l_player);
								i=i+2;
								
							}
							if(l_commands[i].equals("-remove"))
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
						//function call
						break;
						
					case "deploy":
						
						break;
						
					default:
						System.out.println("Invalid Command");
							
					}	
				}
				
			}
			else 
			{
				System.out.println("Invalid Commands");
				flag = 0;
			}
			
		}while(l_option !="end");
		
		System.out.println("Thank you for Playing the Game");
		
	}
;
}
