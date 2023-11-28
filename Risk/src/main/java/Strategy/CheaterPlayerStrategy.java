package Strategy;

import java.util.ArrayList;

import Controllers.GameEngine;
import Models.Country;
import Models.Order;
import Models.Player;
import Tools.Connectivity;
import Tools.MapLoader;
import Tools.PlayersGameplay;
import Views.ViewMap;
import state.Phase;
import state.Play;

public class CheaterPlayerStrategy extends PlayerStrategy{

	public CheaterPlayerStrategy(Player p_player, Connectivity p_connectivity) {
		super(p_player, p_connectivity);
	}

	

	@Override
	protected Country toAttack() {
		
		return null;
	}

	@Override
	protected Country toAttackFrom() {
		return null;
	}

	@Override
	protected Country toMoveFrom() {	
		return null;
	}

	@Override
	protected Country toDefend() {	
		return null;
	}
	
	
	@Override
	public Order createOrder() {
		
		//conquers all the immediate neighboring enemy countries
		ArrayList<Country> l_palyerCountry = d_player.getD_Country();
		ArrayList<Country> l_countriesToAdd = new ArrayList<Country>();
		for(Country c:l_palyerCountry)
		{
			for(int n:c.getD_neighbours())
			{
				Country c1 = c.getCountryFromID(d_connectivity.getD_countryList(), n);
				
				if(!PlayersGameplay.l_neutralCountry.contains(c1) && !l_countriesToAdd.contains(c1))
				{
					Player p =PlayersGameplay.findPlayerWithCountry(Play.getL_playersArray(), c1);
					
//					System.out.println("C1: "+c1.getD_countryName());
					if(p!=null && !l_palyerCountry.contains(c1) )
					{
						l_countriesToAdd.add(c1);
						p.getD_Country().remove(c1);
						
					}
				}
			}
		}
		
		//doubles the number of armies on its countries that have enemy neighbors
		for(Country c:l_countriesToAdd)
		{
			if(!d_player.getD_Country().contains(c))
				c.setD_armyDeployedOnCountry(c.getD_armyDeployedOnCountry()*2);
		}
				
		//add all counties to player
		for(Country c:l_countriesToAdd)
		{
			if(!d_player.getD_Country().contains(c))
				d_player.getD_Country().add(c);
				
		}
		
		
		
		
		
		ViewMap.viewMap(d_connectivity.getD_continentList(), d_connectivity.getD_countryList(), Play.getL_playersArray());
		return null;
	}

}
