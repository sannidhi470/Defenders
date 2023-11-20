package Strategy;

import java.util.ArrayList;
import java.util.Random;

import Controllers.GameEngine;
import Models.Country;
import Models.Order;
import Models.Player;
import Tools.Connectivity;
import state.Play;

public class RandomPlayerStrategy extends PlayerStrategy{

	RandomPlayerStrategy(Player p_player, Connectivity p_connectivity) {
		super(p_player, p_connectivity);
	}

	

	@Override
	protected Country toAttack() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Country toAttackFrom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Country toMoveFrom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override //returns the random countries form the players country
	protected Country toDefend() {
		
		ArrayList<Country> l_countries = d_player.getD_Country();
		Random rndm = new Random(); 
	    int rndmIndex = rndm.nextInt(l_countries.size());    
		return l_countries.get(rndmIndex);
	}
	
	@Override
	public Order createOrder() {
		
		Random rndm = new Random();       
		Order o =new Order();
		String str;
		
		//generates deploy for random country with random number of troops 
		if(GameEngine.getPhaseName().equals("Reinforcement"))
		{
			str="deploy ";
			str = str+ toDefend().getD_countryId()+" " + rndm.nextInt(d_player.getD_armyCount());
			System.out.println(str);
			o.setOrderContent(str);
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
					
					int rndmTroops = rndm.nextInt(l_fromCountry.getD_armyDeployedOnCountry()); 
					str = str+" "+l_fromCountry.getD_countryName()+" "+l_toCountry.getD_countryName()+" "+rndmTroops;
					o.setOrderContent(str);
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
						for(;;)// to generate two unique countries
						{
							if(l_fCountry.getD_countryId() != l_tCountry.getD_countryId())
								break;
							else
								l_tCountry = toDefend();
						}
						
						rndmTroops = rndm.nextInt(l_fCountry.getD_armyDeployedOnCountry());
						str = str +" "+l_fCountry+" "+l_tCountry+" "+rndmTroops;
						o.setOrderContent(str);
						return o;
					}
					else
						break;
					
				case "blockade":
					if(d_player.getCards().contains(str))
					{
						str = str+" "+toDefend().getD_countryId();
						o.setOrderContent(str);
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
		return null;
	}

}
