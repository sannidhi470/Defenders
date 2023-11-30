package Tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import Controllers.GameEngine;
import Models.Continent;
import Models.Country;
import Models.Map;
import Models.Order;
import Models.Player;
import Strategy.AggressivePlayerStrategy;
import Strategy.BenevolentPlayerStrategy;
import Strategy.CheaterPlayerStrategy;
import Strategy.HumanPlayerStrategy;
import Strategy.RandomPlayerStrategy;
import Tools.Connectivity;
import state.Attack;
import state.Phase;
import state.Play;
import state.PlaySetup;

/**
 * The LoadGame class has the functionality to load a game state from a file.
 */
public class LoadGame {

	public static String l_phase = "";
	public static String l_map_name="";
	
	 /**
     * Gets the current game phase.
     *
     * @return The current game phase.
     */
    public static String getD_GamePhase() 
    {
        return l_phase;
    }
    
    /**
     * Gets the name of the map.
     *
     * @return The name of the map.
     */
    public static String getD_MapName() 
    {
        return l_map_name;
    }
    
    /**
     * Used to load the game state from a file.
     *
     * @param p_filename   The name of the file containing the game state.
     * @param connectivity Connectivity object
     * @param ge           GameEngine object.
     * @throws FileNotFoundException To handle if file is not found.
     */
    public static void loadgame(String p_filename, Connectivity connectivity,GameEngine ge) throws FileNotFoundException
    {
    	//File f = new File("");
    	

    	int l_countries_length=0;
    	int l_continents_length=0;
    	int l_players_length=0;
    	int l_phase_length=0;
    	
    	ArrayList<Continent> d_continentList = new ArrayList<Continent>();
    	ArrayList<Country> d_countryList = new ArrayList<Country>();
    	ArrayList<String> d_player_cards= new ArrayList<String>();
    	
    	//String absolute = f.getAbsolutePath();
    	Map l_map=new Map();
    	
    	//PlaySetup play=new PlaySetup(ge);
    	String l_fileName ="src/main/resources" + File.separator + p_filename+".game";
    	File f = new File(l_fileName);
    	if(f.exists())
    	{
    		Scanner l_input = new Scanner(f);
	   		ArrayList<String> l_fileContent=new ArrayList<String>();
	   		while(l_input.hasNextLine())
	   		{
	   			l_fileContent.add(l_input.nextLine());
	   		}
	   		for(int i=0;i<l_fileContent.size();i++)
	   		{
	   			String a=l_fileContent.get(i);
	   			if(a.equals("[map]"))
	   			{
	   				l_map_name = l_fileContent.get(i+1);
	   			}
	   			if(a.equals("[Continents]"))
	   			{
	   				l_continents_length=i;
	   			}
	   			if(a.equals("[Countries]"))
	   			{
	   				l_countries_length=i;
	   			}
	   			if(a.equals("[Players]"))
	   			{
	   				l_players_length=i;
	   			}
	   			if(a.equals("[phase]"))
	   			{
	   				l_phase_length=i;
	   				l_phase = l_fileContent.get(i+1);
	   			}
	   		}
	   		connectivity.setD_mapName(l_map_name);
	   		for(int i=l_countries_length+1;i<l_players_length-1;i++)
	   		{
	   			//System.out.println("Countries");
	   			String a=l_fileContent.get(i);
	  		    String[] aArr=a.split(" ");
	  		    Country obj=new Country();
	  		    obj.setD_countryName(aArr[0]);
	  		    obj.setD_countryId(Integer.parseInt(aArr[1]));
	  		    obj.setD_continentId(Integer.parseInt(aArr[2]));
	  		    obj.setD_armyDeployedOnCountry(Integer.parseInt(aArr[3]));
	  		    d_countryList.add(obj);
	  		 
	   		}
 		    connectivity.setD_countryList(d_countryList);
	   		for(int i=l_continents_length+1;i<l_countries_length-1;i++)
	   		{
	   			//System.out.println("Continents");
	   			String a=l_fileContent.get(i);
	  		    String[] aArr=a.split(" ");
	  		    Continent continent=new Continent();
	  		    continent.setD_continentId(Integer.parseInt(aArr[0]));
	  		    continent.setD_continentName(aArr[1]);
	  		   // ArrayList<Country> d__continent_countryList = new ArrayList<Country>();
//	  		    String d_countries= aArr[2];
//	   			for(int j=0;j<l_country_details.length;j++)
//	   			{
//	   			for(int k=0;i<connectivity.getD_countryList().size();k++)
//	   			{
//	   			}
//	   			}
	  		    d_continentList.add(continent);
	  		    connectivity.setD_continentList(d_continentList);
	   		}
	   		//System.out.println("Player length:"+l_players_length);
	   		for(int i=l_players_length+1;i<l_phase_length-1;)
	   		{
	   			System.out.println(i);
	   			//System.out.println("Reached here");
	   			Player player = new Player();
	   			ArrayList<Country> d__player_countryList = new ArrayList<Country>();
	   			String[] l_player_details = l_fileContent.get(i).split(" ");
	   			System.out.println(l_player_details[0]);
	   			int l_playerid =  Integer.parseInt(l_player_details[0]);
	   			String l_playername=l_player_details[1];
	   			player.setD_playerId(l_playerid);
	   			player.setD_playerName(l_playername);
	   			player.setD_Order(new Order());
	   			String[] l_country_details = l_fileContent.get(i+2).split(" ");
	   			for(int j=0;j<l_country_details.length;j++)
	   			{
	   			for(int k=0;k<connectivity.getD_countryList().size();k++)
	   			{
	   				if(l_country_details[j].equals(connectivity.getD_countryList().get(k).getD_countryName()))
	   				{
	   					d__player_countryList.add(connectivity.getD_countryList().get(k));
	   					break;
	   				}
	   					
	   			}
	   			}
	   			player.setD_Country(d__player_countryList);
	   			String[] l_card_details = l_fileContent.get(i+3).split(" ");
	   			if(l_card_details[1]!=null)
	   			{
	   			for(int s=0;i<l_card_details[1].length();s++)
	   			{
	   				d_player_cards.add(l_card_details[s]);
	   			}
	   			}
	   			player.setCards(d_player_cards);
	   			String[] l_army_details = l_fileContent.get(i+4).split(" ");
	   			player.setD_armyCount(Integer.parseInt(l_army_details[1]));
	   			String[] l_strategy_details = l_fileContent.get(i+5).split(" ");
	   			System.out.println(l_strategy_details);
	   			switch(l_strategy_details[1])
	   			{
	   			case "Human":
	   				player.setStrategy(new HumanPlayerStrategy(player, connectivity));
	   				break;
	   			case "Cheater":
	   				player.setStrategy(new CheaterPlayerStrategy(player, connectivity));
	   				break;
	   			case "Benevolent":
	   				player.setStrategy(new BenevolentPlayerStrategy(player, connectivity));
	   				break;
	   			case "Aggressive":
	   				player.setStrategy(new AggressivePlayerStrategy(player, connectivity));
	   				break;
	   			case "Random":
	   				player.setStrategy(new RandomPlayerStrategy(player, connectivity));
	   				break;
	   				
	   			}
	   				
	   			Play.l_playersArray.add(player);
	   			i=i+7;
	   		}
	   		if(!l_map_name.equalsIgnoreCase("null"))
	   		{
	   		MapLoader.loadMap(connectivity, l_map_name);
	   		}
	   		for(int i=l_countries_length+1;i<l_players_length-1;i++)
	   		{
	   			//System.out.println("second countries");
	   			String a=l_fileContent.get(i);
	  		    String[] aArr=a.split(" ");
	   			for(int k=0;k<connectivity.getD_countryList().size();k++)
	   			{
	   				if(aArr[0].equals(connectivity.getD_countryList().get(k).getD_countryName()))
	   				{
	   					connectivity.getD_countryList().get(k).setD_armyDeployedOnCountry(Integer.parseInt(aArr[3]));
	   				}
	   			}
	   		}
	   		}
	   		System.out.println("GAME SUMMARY: ");
			for(int i=0;i<Play.l_playersArray.size();i++)
			{
				System.out.println("Player "+ Integer.sum(i,1) +"("+Play.l_playersArray.get(i).getD_playerName()+") has Army Count: "+Play.l_playersArray.get(i).getD_armyCount());
				PlayersGameplay.showPlayersCountry(Play.l_playersArray.get(i),1);
			}
	   		Views.ViewMap.viewMap(connectivity.getD_continentList(),connectivity.getD_countryList(),Play.l_playersArray);
	   		
    	}
	   		
    	}

    
    

