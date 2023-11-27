package Strategy;

import java.util.ArrayList;

import Controllers.GameEngine;
import Models.Country;
import Models.Order;
import Models.Player;
import Tools.Connectivity;
import Views.ViewMap;
import state.Play;

public class AggressivePlayerStrategy extends PlayerStrategy{

	public AggressivePlayerStrategy(Player p_player, Connectivity p_connectivity) {
		super(p_player, p_connectivity);
	}

	@Override
	protected Country toAttack() {
		return null;
	}

	@Override
	protected Country toAttackFrom() {
		Country d_StrongestCountry = toMoveFrom();
		Country l_neighborCountry = null;
		if(d_StrongestCountry!=null) {
			int l_neighborCount = d_StrongestCountry.getD_neighbours().size();
            for (int i=0;i<l_neighborCount-1;i++) {
            	//l_neighborCountry = d_StrongestCountry.getD_neighbours().get(i);
	            if (!d_player.getD_Country().contains(l_neighborCountry)) {
	                Country d_OpponentCountry = l_neighborCountry;           
	                return d_OpponentCountry;
	            }
            }
        }
		System.out.println("No neighbours present for attack!");
		return null;

	}

	@Override
	protected Country toMoveFrom() {
		int l_maxArmies = 0;
		Country d_StrongestCountry = null;
        for(Country l_country : d_player.getD_Country()) {
            int l_numArmies = l_country.getD_armyDeployedOnCountry();
            if( l_numArmies > l_maxArmies) {
                l_maxArmies = l_numArmies;
                d_StrongestCountry = l_country;
            }
        }
        if(l_maxArmies == 0) {
            d_StrongestCountry = d_player.getD_Country().get(0);
        }
        
		return d_StrongestCountry;
	}

	@Override
	protected Country toDefend() {
		//null
		return null;
	} 
	
	@Override
	public Order createOrder() {
		Order o= new Order();
		String str;
		if(GameEngine.getPhaseName().equals("Reinforcement")) {
			str= "deploy ";
			str= str+toMoveFrom().getD_countryId()+" " + d_player.getD_armyCount();
			System.out.println(str);
			o.setOrderContent(str);
			return o;
		} 
		else if(GameEngine.getPhaseName().equals("Attack")) {
			str= "advance ";
			Country[] c= level(toMoveFrom().getD_countryId());
			
			if(c!=null) {
				str= str+ c[0].getD_countryName()+ " "+ c[1].getD_countryName()+ " "+c[0].getD_armyDeployedOnCountry();
				System.out.println(str+ c[0].getD_countryName()+ " "+ c[1].getD_countryName()+ " "+c[0].getD_armyDeployedOnCountry());
				o.setOrderContent(str);
				ViewMap.viewMap(d_connectivity.getD_continentList(), d_connectivity.getD_countryList(), Play.getL_playersArray());
				return o;
			} else {
				return null;
			}
			
		}
		return o;
	}
	
	public Country[] level(int countryID)
	{
		Country[] c = new Country[2];
		ArrayList<Integer> neighbourCountryID = d_connectivity.getCountryByID(countryID).getD_neighbours();
		
		for(int i: neighbourCountryID)
		{
			
			//if a neighboring country belongs to same player and has troops>0 then advance to strongest country. 
			if(d_player.getD_Country().contains(d_connectivity.getCountryByID(i)))
			{
				if(d_connectivity.getCountryByID(i).getD_armyDeployedOnCountry()>0)
				{
					c[0] = d_connectivity.getCountryByID(i); //from Country
					c[1] = d_connectivity.getCountryByID(countryID); // to country 
					return c;
				}
			}
			//if neighboring country belongs to different player then attack it with the strongest country. 
			else if(!d_player.getD_Country().contains(d_connectivity.getCountryByID(i))) 
			{
				c[0] = d_connectivity.getCountryByID(countryID); //from Country
				c[1] = d_connectivity.getCountryByID(i); // to country 
				return c;
			}
			
		}
		
		//if All neighboring country belongs to player but all have 0 troops to advance
		//then this loop goes to 2nd level of neighbors.
		for(int i:neighbourCountryID)
		{
			
			neighbourCountryID = d_connectivity.getCountryByID(i).getD_neighbours();
			for(int j: neighbourCountryID)
			{
				if(d_player.getD_Country().contains(d_connectivity.getCountryByID(j)))
				{
					if(d_connectivity.getCountryByID(j).getD_armyDeployedOnCountry()>0)
					{
						c[0] = d_connectivity.getCountryByID(j); //from country
						c[1] = d_connectivity.getCountryByID(i);// to country
						return c;
					}
				}
				//if neighboring country belongs to different player then attack it with the strongest country. 
				else if(!d_player.getD_Country().contains(d_connectivity.getCountryByID(j))) 
				{
					c[0] = d_connectivity.getCountryByID(i); //from Country
					c[1] = d_connectivity.getCountryByID(j); // to country 
					return c;
				}
				
			}
		}
		
		//3rd Neighbour
		for(int i:neighbourCountryID)
		{
					
			ArrayList<Integer> neighbourCountryID2 = d_connectivity.getCountryByID(i).getD_neighbours();
			for(int j: neighbourCountryID2)
			{
				ArrayList<Integer> neighbourCountryID3 = d_connectivity.getCountryByID(j).getD_neighbours();
				for(int k:neighbourCountryID3)
				{
					if(d_player.getD_Country().contains(d_connectivity.getCountryByID(k)))
					{
						if(d_connectivity.getCountryByID(k).getD_armyDeployedOnCountry()>0)
						{
							c[0] = d_connectivity.getCountryByID(k); //from country
							c[1] = d_connectivity.getCountryByID(i);// to country
							return c;
						}
						else if(!d_player.getD_Country().contains(d_connectivity.getCountryByID(k))) 
						{
							c[0] = d_connectivity.getCountryByID(i); //from Country
							c[1] = d_connectivity.getCountryByID(k); // to country 
							return c;
						}
					}
				}
						
			}
		}
		return null;
	}

}