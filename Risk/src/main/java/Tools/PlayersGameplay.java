package Tools;

import java.util.ArrayList;
import java.util.Random;

import Controllers.GameEngine;
import Models.Continent;
import Models.Country;
import Models.LogEntryBuffer;
import Models.Player;
import state.Play;

/**
 * The class PlayerGameplay manages the player perspective.
 *
 */

public class PlayersGameplay {
	
	static ArrayList<Integer> l_armiesOfPlayers=new ArrayList<>();
	static ArrayList<Country> l_neutralCountry = new ArrayList<>();
	
	/**
	 *
	 * This method is used to randomly assign the countries to the user once the game begins.
	 * @param p_playersArray refers to the number of players playing the game.
	 * @param p_countryList refers to the list of countries in the Map.
	 * @param p_continentList refers to all the continents present in the Map.
	 * 
	 * @return error types
	 *
	 */
	
	public static int assigncountries(ArrayList<Player> p_playersArray,ArrayList<Country> p_countryList,ArrayList<Continent> p_continentList) {	
		if (p_playersArray.size() == 0) {
			System.out.println(ColorCoding.red + "Error: Insufficient Players to assign countries" + ColorCoding.blank);
			return 1;
		}

		if (p_countryList.size() < p_playersArray.size()) {
			System.out.println(
					ColorCoding.red + "Error: Insufficient country to assign to all players" + ColorCoding.blank);
			return 1;
		}
		
		int[] l_playerCountArray = new int[p_playersArray.size()];
		int sum = 0;
		int range = (p_countryList.size() - 1) + 1;
		
		do
		{
			sum=0;
			for(int i=0; i<p_playersArray.size(); i++) {
				int randomResult = (int) ((Math.random()*range) + 1);
				sum=sum+randomResult;
				l_playerCountArray[i]=randomResult;
			}
		}	while(sum>p_countryList.size());
		
		int index = 0;
		for(int i=0; i<p_playersArray.size();i++) {
			ArrayList<Country> l_removeCountry=new ArrayList<>();
			for(int j=0; j<l_playerCountArray[i];j++) {
				Country l_country = p_countryList.get(index);
				index++;
				l_removeCountry.add(l_country);
				p_playersArray.get(i).addCountry(l_country);
			}
			//removes the countries assigned to players from the main list 
			//p_countryList.removeAll(l_removeCountry);
			
		}
		
		//add neutral countries to neutral Country ArrayList
		for(int i=index; i<p_countryList.size(); i++)
		{
			l_neutralCountry.add(p_countryList.get(i));
		}
		
		updateContinent(p_playersArray,p_continentList);	
		
		return 0;
	}
	/**
	 *
	 * This method is used to assign armies to the players once the game begins.
	 * @param p_playersList refers to the list of players.
	 *
	 */
	 
	public static void assignArmiesToPlayers(ArrayList<Player> p_playersList) {
		for(int i=0;i<p_playersList.size();i++)
		{
			int l_countryListSize=p_playersList.get(i).getD_Country().size()/3;
			int l_armyCount=Math.max(3, l_countryListSize);
			int l_tempContinentCount=0;
			if(p_playersList.get(i).getD_playerContinent().size()!=0)
			{
				for(int j=0;j<p_playersList.get(i).getD_playerContinent().size();j++)
					l_tempContinentCount=l_tempContinentCount+ p_playersList.get(i).getD_playerContinent().get(j).getD_continentBonusValue();
			}
			l_armyCount+=l_tempContinentCount;
			p_playersList.get(i).setD_armyCount(l_armyCount);
		}
	}
	
	/**
	 *
	 * This method is used to show the countries owned by a particular player.
	 * @param p_player refers to the individual player.
	 * @param p_Displayflag refers to flag being displayed.
	 * 
	 * @return list of countries
	 *
	 */
	 
	public static ArrayList<String> showPlayersCountry(Player p_player,int p_Displayflag) {
		ArrayList<Country> l_country = new ArrayList<>();
		if(p_Displayflag==1) System.out.println("\nPlayer:" + p_player.getD_playerName()+ " has following countries assigned");
		l_country=	p_player.getD_Country();
		ArrayList<String> l_countryList=new ArrayList<>();
		for(Country c:l_country)
		{
			if(p_Displayflag==1) System.out.println(c.getD_countryName());
			l_countryList.add(c.getD_countryName());
		}
		return l_countryList;
	}
	
	/**
	 *
	 * This method is used to check whether the army is available or not.
	 * @param p_army refers to the army left with player.
	 * @param p_player refers to the player object
	 * 
	 * @return boolean type
	 *
	 */
	
	public static boolean checkArmyAvailable(int p_army,Player p_player) {
		if(p_player.getD_armyCount() >= p_army) 
			return true;
		else 
			return false;			
	}
	
	public static int advance(Player p_player,ArrayList<Player> p_playersArray,Country p_fromCountry,Country p_toCountry,int p_troops,ArrayList<Continent> p_continent)
	{
		//Check whether the fromCountry belongs to player or not
		if(p_player.getD_Country().contains(p_fromCountry))
		{
			//Check whether the toCountry belongs to player or not
			if(p_player.getD_Country().contains(p_toCountry))
			{
				//Check whether the toCountry is the neighbor of fromCountry or not 
				if(p_fromCountry.getD_neighbours().contains(p_toCountry.getD_countryId()))
				{
					//check p_troops is less then p_formCountry.getDarmyDeployed
					if(p_troops<=p_fromCountry.getD_armyDeployedOnCountry())
					{
						System.out.println("Calling Advance");
						int l_troopsaddition = p_toCountry.getD_armyDeployedOnCountry() + p_troops;
						p_toCountry.setD_armyDeployedOnCountry(l_troopsaddition); 
						int l_troopsDeduction = p_fromCountry.getD_armyDeployedOnCountry() - p_troops;
						p_fromCountry.setD_armyDeployedOnCountry(l_troopsDeduction);
						System.out.println(ColorCoding.green+p_troops+" Troops advanced from "+p_fromCountry.getD_countryName()+" to "+p_toCountry.getD_countryName()+ColorCoding.blank);
						System.out.println("After change "+p_fromCountry.getD_countryName()+" has "+p_fromCountry.getD_armyDeployedOnCountry()+" troops");
						System.out.println("After change "+p_toCountry.getD_countryName()+" has "+p_toCountry.getD_armyDeployedOnCountry()+" troops" );
						return 0;
					}
					else
					{
						System.out.println(ColorCoding.red+"Error: Can't move more armies than the armies in the country"+ColorCoding.blank);
						return 1;
					}

				}
				else
				{
					System.out.println(ColorCoding.red+"Error: "+p_fromCountry.getD_countryName()+ " is not the neighbour of "+ p_toCountry.getD_countryName()+" troops can't be advanced or country can't be attacked"+ColorCoding.blank);
					return 1;
				}	
			}
			else //Attack on toCountry as it doesn't belong to player 
			{
				Player l_toplayer = findPlayerWithCountry(p_playersArray,p_toCountry);
				
				if(l_toplayer != null)
				{
					System.out.println(p_player.getDiplomacyWith());
					
					if(!p_player.getDiplomacyWith().contains(l_toplayer.getD_playerId()))
					{
						//Already checked whether fromCountry belongs to player or not 
						attack(p_player,p_playersArray,p_fromCountry,p_toCountry,p_troops,p_continent);
						return 0;
					}
					else
					{
						System.out.println(ColorCoding.red+"Attack is not possible between "+p_fromCountry+" and "+p_toCountry+" because of diplomacy"+ColorCoding.blank);
						return 1;
					}
					
				}
				else
				{
					//This will be called if to country is a neutral country
					attack(p_player,p_playersArray,p_fromCountry,p_toCountry,p_troops,p_continent);
					return 0;
				}
				
				
						
			}
		}
		else
		{
			System.out.println(ColorCoding.red+"Error: "+p_fromCountry.getD_countryName()+" doesn't belongs to player from where he wants to advance the troops"+ColorCoding.blank);
			return 1;
		}

	}


	private static Player findPlayerWithCountry(ArrayList<Player> p_playersArray, Country p_toCountry) {
		
		for(Player p: p_playersArray)
		{
			if(p.getD_Country().contains(p_toCountry))
				return p;
		}
		return null;
	}
	
	public static int attack(Player p_player,ArrayList<Player> p_playersArray,Country p_fromCountry,Country p_toCountry,int p_troops,ArrayList<Continent> p_continent)
	{
		
		System.out.println("Calling Attack");
		
		//handles if to_Country name is wrong
		try{
			//check toCountry is neighbor of fromCountry or not 
			if(p_fromCountry.getD_neighbours().contains(p_toCountry.getD_countryId()))
			{
				//check p_troops is less then p_formCountry.getDarmyDeployed
				if(p_troops<=p_fromCountry.getD_armyDeployedOnCountry())
				{
					//attacking country: 60% chance of killing 1 unit
					//int l_troopsSource = p_fromCountry.getD_armyDeployedOnCountry();
					int l_troopsSource = p_troops;
					
					//defending country: 70% chance of killing 1 unit
					int l_troopsDestination = p_toCountry.getD_armyDeployedOnCountry();
					
					if(l_troopsDestination>0 && l_troopsDestination> 0)
					{
						//increase troops strength by 60 %
						int attackRange =(60 - 0)+1; 
						int attackRandom = (int)(Math.random() * attackRange) + 0;
						System.out.println("attackRandom = "+attackRandom);
						l_troopsSource = Math.round(l_troopsSource*((float)attackRandom/100));
						System.out.println("source Troops = "+l_troopsSource);
						
						int defendRange =(70 - 0)+1; 
						int defendRandom = (int)(Math.random() * defendRange) + 0;
						System.out.println("defendRandom = "+defendRandom);
						l_troopsDestination = Math.round (l_troopsDestination*((float)defendRandom/100));
						System.out.println("Destination Troops = "+l_troopsDestination);

					}
					

					
					
				
					if(l_troopsSource>l_troopsDestination)
					{
						//Attacker captures the country.
						p_player.getD_Country().add(p_toCountry);
						
						System.out.println(ColorCoding.green+"Attack Successfull!!"+ColorCoding.blank);
					
						//add card to player
						p_player.getCards().add(generateCard());

						//Remove p_toCountry from the player which it is assigned to, or form neutral country.
						removeCountry(Play.getL_playersArray(),p_toCountry,p_continent);
						
						//Remove Continent if Player doesn't have any country belonging to it. OR Add Continent if player
						//has won All countries in that Continent
						updateContinent(p_playersArray, p_continent);

						int l_troopsLeft = l_troopsSource - l_troopsDestination; 
						p_toCountry.setD_armyDeployedOnCountry(l_troopsLeft);
						p_fromCountry.setD_armyDeployedOnCountry(p_fromCountry.getD_armyDeployedOnCountry()-p_troops);
						//p_fromCountry.setD_armyDeployedOnCountry(0);
						
						System.out.println("Attack on "+p_toCountry.getD_countryName()+ " from "+p_fromCountry.getD_countryName()+" was successful.");
						System.out.println("After change "+p_fromCountry.getD_countryName()+" has "+p_fromCountry.getD_armyDeployedOnCountry()+" troops");
						System.out.println("After change "+p_toCountry.getD_countryName()+" has "+p_toCountry.getD_armyDeployedOnCountry()+" troops" );
					}
					else if(l_troopsSource<l_troopsDestination)
					{
						//Defender defeats the attacker
						int l_troopsLeft = l_troopsDestination - l_troopsSource;
						p_toCountry.setD_armyDeployedOnCountry(l_troopsLeft);
						//p_fromCountry.setD_armyDeployedOnCountry(0);
						p_fromCountry.setD_armyDeployedOnCountry(p_fromCountry.getD_armyDeployedOnCountry()-p_troops);
						System.out.println(p_toCountry.getD_countryName()+" defended itself successuflly from "+p_fromCountry.getD_countryName());
						System.out.println("After change "+p_fromCountry.getD_countryName()+" has "+p_fromCountry.getD_armyDeployedOnCountry()+" troops");
						System.out.println("After change "+p_toCountry.getD_countryName()+" has "+p_toCountry.getD_armyDeployedOnCountry()+" troops" );
					}
					else
					{
						p_toCountry.setD_armyDeployedOnCountry(0);
						//p_fromCountry.setD_armyDeployedOnCountry(0);
						p_fromCountry.setD_armyDeployedOnCountry(p_fromCountry.getD_armyDeployedOnCountry()-p_troops);
						System.out.println(p_toCountry.getD_countryName()+" defended itself successuflly from "+p_fromCountry.getD_countryName());
						System.out.println("After change "+p_fromCountry.getD_countryName()+" has "+p_fromCountry.getD_armyDeployedOnCountry()+" troops");
						System.out.println("After change "+p_toCountry.getD_countryName()+" has "+p_toCountry.getD_armyDeployedOnCountry()+" troops" );
					}

					return 0;
				}
				else
				{
					System.out.println(ColorCoding.red+"Error: Can't attack with more armies then the armies in the country"+ColorCoding.blank);
					return 1;
				}

			}
			else
			{
				System.out.println(ColorCoding.red+"Error: Can't attack the country "+p_toCountry.getD_countryName()+" as it is not a neighbour of "+p_fromCountry.getD_countryName()+ColorCoding.blank);
				return 1;
			}
		}catch(Exception e)
		{
			System.out.println(ColorCoding.red+"Error: Coutry "+p_toCountry+" Does not exist"+ColorCoding.blank);
			return 1;
		}
	
	
	}

	public static int removeCountry(ArrayList<Player> p_playersArray,Country p_country,ArrayList<Continent> p_continent)
	{
		//if p_country is assigned to players
		for(Player p:p_playersArray)
		{
			if(p.getD_Country().contains(p_country))
			{
//////////////////////////need to check whether the country actually been removed from player or not 
			    p.getD_Country().remove(p_country);
			    updateContinent(p_playersArray, p_continent);
				return 0;
			}
		}
		
		//if p_country is not assigned to country and belongs to neutral country 
		if(l_neutralCountry.contains(p_country))
		{
			l_neutralCountry.remove(p_country);
			updateContinent(p_playersArray, p_continent);
			return 0;
		}
		else
			System.out.println(ColorCoding.red+"Error: "+p_country.getD_countryName()+"doesnot exist."+ColorCoding.blank);
		return 1;
	}
	
	public static void bomb(Player p_player,ArrayList<Player> p_playersArray,Country p_fromCountry,Country p_toCountry,int p_troops,ArrayList<Continent> p_continent) {
		if(p_fromCountry.getD_neighbours().contains(p_toCountry.getD_countryId())) {
			int l_targetArmies=(int) Math.floor(p_toCountry.getD_armyDeployedOnCountry()/2);
			p_toCountry.setD_armyDeployedOnCountry(l_targetArmies);
			if(p_toCountry.getD_armyDeployedOnCountry()==0) {
				removeCountry(p_playersArray, p_toCountry, p_continent);
				updateContinent(p_playersArray, p_continent);
				System.out.println("The Country "+ p_toCountry + " is a now a neutral Country");
			}
			else {
				System.out.println("The country "+ p_toCountry + "now has " + l_targetArmies+ " armies");
			}
		}
	}
	
	public static int updateContinent(ArrayList<Player> p_playersArray, ArrayList<Continent> p_continentList)
	{
		
		for(int i=0;i<p_playersArray.size();i++) {
			ArrayList<String> l_tempCountry=new ArrayList<>();
			ArrayList<String> tempCountryInContinent=new ArrayList<>();
			ArrayList<Continent> l_continentsOwned=new ArrayList<>();
			for(int j=0;j<p_playersArray.get(i).getD_Country().size();j++) 
				l_tempCountry.add(p_playersArray.get(i).getD_Country().get(j).getD_countryName());
			for(int j=0;j<p_continentList.size();j++)
			{
				tempCountryInContinent=new ArrayList<>();
				for(int k=0;k<p_continentList.get(j).getD_countries().size();k++) tempCountryInContinent.add(p_continentList.get(j).getD_countries().get(k).getD_countryName());
				if(l_tempCountry.containsAll(tempCountryInContinent))
				{
					l_continentsOwned.add(p_continentList.get(j));
					p_playersArray.get(i).setD_playerContinent(l_continentsOwned);
				}
			}				
		}
		return 0;
	}
	
	public static boolean AirliftDeploy(Country p_sourceCountry, Country p_targetCountry, int p_armiesToAirlift, Player p_player) {
		
		Country l_sourceCountryObj = p_sourceCountry;
		Country l_targetCountryObj = p_targetCountry;
		
		String l_sourceCountryVar = l_sourceCountryObj.getD_countryName();
	    String l_targetCountryVar = l_targetCountryObj.getD_countryName();
	    
	    int l_armiesToAirlift = p_armiesToAirlift;
	    
	    Player l_player = p_player;
	    
	    
	    int flag =0;
	    int flag1 =0;

	    ArrayList<Country> l_country = new ArrayList<>();
	    l_country=	p_player.getD_Country();
	    Country l_sourceCountry=new Country();
	    Country l_targetCountry=new Country();
	    
	    
		for(Country c:l_country)
		{
			if(c.getD_countryName().equals(l_sourceCountryVar)) {
				
				flag=1;
				l_sourceCountry=c;
			}
		}
	    if (flag==0) {
            System.out.println(l_player.getD_playerName() + " can not use Airlift card as it doesn't own the source country.");
            return false;
	    }
	    
	    for(Country c:l_country)
		{
			if(c.getD_countryName().equals(l_targetCountryVar)) {
				flag1=1;
				l_targetCountry=c;
			}
		}
	    if (flag1==0) {
            System.out.println(l_player.getD_playerName() + " can not use Airlift card as it doesn't own the target country.");
            return false;
	    }
	    
	    if(l_sourceCountry.getD_armyDeployedOnCountry()<l_armiesToAirlift) {
	    	System.out.println(l_player.getD_playerName() + " doesn't have enough army on this country to airlift.");
            return false;
	    }
	  
	    int x = l_sourceCountry.getD_armyDeployedOnCountry();
	    x = x - l_armiesToAirlift;
	    
	    int y  = l_targetCountry.getD_armyDeployedOnCountry();
	    y = y + l_armiesToAirlift;
	    
	    l_sourceCountry.setD_armyDeployedOnCountry(x);
	    l_targetCountry.setD_armyDeployedOnCountry(y);
	    
		return false;
	}
	
	public static boolean Blockade(Country p_sourceCountry,Player p_player,ArrayList<Player> p_playersArray,ArrayList<Continent> p_continent) {
		Country l_sourceCountryObj = p_sourceCountry;
		String l_sourceCountry = l_sourceCountryObj.getD_countryName();
	    Player l_player = p_player;
	    int flag =0;
	    ArrayList<Country> l_country = new ArrayList<>();
	    l_country=	p_player.getD_Country();
	    Country l_sourceCountrynew = new Country();
	    
		for(Country c:l_country)
		{
			if(c.getD_countryName().equals(l_sourceCountry)) {
				
				flag=1;
				l_sourceCountrynew=c;
				}
		}
	    if (flag==0) {
            System.out.println(l_player.getD_playerName() + " can not use Blockade card as it doesn't own "+ l_sourceCountrynew.getD_countryName());
            return false;
	    }
	    
	    if(l_sourceCountrynew.getD_armyDeployedOnCountry()<0) {
	    	System.out.println(l_player.getD_playerName() + " doesn't have enough army on this country to blockade.");
            return false;
	    }
	    
	    int x = l_sourceCountrynew.getD_armyDeployedOnCountry();
	    x = x*3;
	    
	    l_sourceCountrynew.setD_armyDeployedOnCountry(x);
	    removeCountry(p_playersArray, l_sourceCountryObj, p_continent);
		updateContinent(p_playersArray, p_continent);
		System.out.println("The Country "+ l_sourceCountryObj.getD_countryName() + " is a now a neutral Country");
	    
	    return false;
	}
	
	public static String generateCard()
	{
		String[] l_cards = {"bomb","reinforcement","blockade","airlift","diplomacy"};
		Random ran = new Random();
		 
		return l_cards[ran.nextInt(l_cards.length)];
		
	}
	
	public static Player winnerPlayer(ArrayList<Player> p_players,Connectivity p_connectivity)
	{
		Player l_winner=new Player();
		for(Player p:p_players)
		{
			if(p.getD_Country().size()==p_connectivity.getD_countryList().size())
				return l_winner;
		}
		return null;
	}
	
	public static void negotiate(Player p_player,ArrayList<Player> p_playersArray, String p_toPlayerID) {
		
		
		p_player.addDiplomacyWith(Integer.parseInt(p_toPlayerID));
		
		for(Player p: p_playersArray)
		{
			if(p.getD_playerId() == Integer.parseInt(p_toPlayerID))
			{
				p.addDiplomacyWith(p_player.getD_playerId());
			}
			
			System.out.println(p.getD_playerName()+" = "+p.getDiplomacyWith());
		}
		
		
	}
	
	public static void resetDiplomacy(ArrayList<Player> p_playersArray) {

		for(Player p:p_playersArray)
		{
			p.clearDiplomacyWith();
		}
		
	}
}
