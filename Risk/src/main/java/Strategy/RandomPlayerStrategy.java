package Strategy;

import java.util.ArrayList;
import java.util.Random;

import Controllers.GameEngine;
import Models.Country;
import Models.Order;
import Models.Player;
import Tools.Connectivity;
import state.Play;

/**
 * The AggressivePlayerStrategy class represents the player strategy that focuses on centralization of forces and then attack.
 */
public class RandomPlayerStrategy extends PlayerStrategy{

	public RandomPlayerStrategy(Player p_player, Connectivity p_connectivity) {
		super(p_player, p_connectivity);
	}

	/**
	 * Override method to attack
	 * @return null in case of random player
	 */
	@Override
	protected Country toAttack() {
		return null;
	}

	/**
	 * Override method to get country on which attack happens
	 * @return null in case of random player
	 */
	@Override
	protected Country toAttackFrom() {
		return null;
	}

	/**
	 * Override method to get country from which attack happens
	 * @return null in case of random player
	 */
	@Override
	protected Country toMoveFrom() {	
		return null;
	}

	/**
	 * Override method to get random countries from the player's country
	 * @return those random countries
	 */
	@Override //returns the random countries form the players country
	protected Country toDefend() {
		
		ArrayList<Country> l_countries = d_player.getD_Country();
		if(l_countries.size() == 0)
			return null;
		Random rndm = new Random(); 
	    int rndmIndex = rndm.nextInt(l_countries.size());    
		return l_countries.get(rndmIndex);
	}
	
	/**
	 * Override method to apply random player strategy during main game play by creating order, generating and deploying random 
	 * troops to countries and randomly attacking once finds a neighboring enemy.
	 * @return order
	 */
	@Override
	public Order createOrder() {
		
		Random rndm = new Random();       
		Order o =new Order();
		String str="";
		if(toDefend() == null)
			return null;
		
		//generates deploy for random country with random number of troops 
		if(GameEngine.getPhaseName().equals("Reinforcement"))
		{
			str="deploy ";
			str = str+ toDefend().getD_countryId()+" " + rndm.nextInt(d_player.getD_armyCount()+1);
			o.setOrderContent(str);
			System.out.println(str);
			return o;
		}
		else if(GameEngine.getPhaseName().equals("Attack"))
		{
			//runs until valid command is not generated
			for(;;)
			{
				String[] commands = {"advance","bomb","blockade","airlift","negotiate"};
				str = commands[rndm.nextInt(commands.length)];
				switch(str)
				{
				case "advance": 
					Country l_fromCountry = toDefend();
					ArrayList<Integer> l_neighbour = l_fromCountry.getD_neighbours();
					
					int rndmIndex = rndm.nextInt(l_neighbour.size());
					Country l_toCountry = Country.getCountryFromID(d_connectivity.getD_countryList(), l_neighbour.get(rndmIndex));
					
					int rndmTroops = rndm.nextInt(l_fromCountry.getD_armyDeployedOnCountry()+1); 
					str = str+" "+l_fromCountry.getD_countryName()+" "+l_toCountry.getD_countryName()+" "+rndmTroops;
					o.setOrderContent(str);
					System.out.println(str);
					return o;
					
				case "bomb":
					if(d_player.getCards().contains(str))
					{
						for(;;)//runs till it finds the neighboring enemy territory
						{
							Country l_Country = toDefend();
							l_neighbour = l_Country.getD_neighbours();
							rndmIndex = rndm.nextInt(l_neighbour.size());
							l_toCountry = Country.getCountryFromID(d_connectivity.getD_countryList(), l_neighbour.get(rndmIndex));
							
							if(!d_player.getD_Country().contains(l_toCountry))
							{
								str = str+" "+l_toCountry.getD_countryId();
								o.setOrderContent(str);
								System.out.println(str);
								return o;
							}
						}
					}
					else
						break;
					
				case "airlift":
					if(d_player.getCards().contains(str))
					{
						Country l_fCountry = toDefend();
						Country l_tCountry = toDefend();
						if(l_fCountry.getD_countryId() != l_tCountry.getD_countryId())
							break;
						else
							l_tCountry = toDefend();
						
						
						rndmTroops = rndm.nextInt(l_fCountry.getD_armyDeployedOnCountry()+1);
						str = str +" "+l_fCountry.getD_countryId()+" "+l_tCountry.getD_countryId()+" "+rndmTroops;
						o.setOrderContent(str);
						System.out.println(str);
						return o;
					}
					else
						break;
					
				case "blockade":
					if(d_player.getCards().contains(str))
					{
						str = str+" "+toDefend().getD_countryId();
						o.setOrderContent(str);
						System.out.println(str);
						return o;
					}
					else
						break;
					
				case "negotiate":
					if(d_player.getCards().contains("diplomacy"))
					{
						int l_playersSize = Play.l_playersArray.size();
						rndmIndex = rndm.nextInt(l_playersSize);
						Player p2 = Play.l_playersArray.get(rndmIndex);
						if(d_player.getD_playerId() != p2.getD_playerId())
						{
							str = str+" "+d_player.getD_playerId()+" "+p2.getD_playerId();
							o.setOrderContent(str);
							System.out.println(str);
							return o;
						}
						//else part is not written becz if we keep finding different player whern there is
						//only one player it will be in infinite loop
					}
					else
						break;
				}
			}
			
		}
		System.out.println(str);
		return null;
	}

}
