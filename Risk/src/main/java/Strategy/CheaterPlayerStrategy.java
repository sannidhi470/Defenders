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

/**
 * The CheaterPlayerStrategy class represents the player strategy y whose issueOrder() method conquers all the immediate 
 * neighboring enemy countries, and then doubles the number of armies on its countries that have enemy neighbors.
 */
public class CheaterPlayerStrategy extends PlayerStrategy{

	/**
	 * This constructor refers to the cheater player strategy.
	 * @param p_player refers to the object of the player class
	 * @param p_connectivity refers to connectivity object
	 */
	public CheaterPlayerStrategy(Player p_player, Connectivity p_connectivity) {
		super(p_player, p_connectivity);
	}

	/**
	 * Override method to attack
	 * @return null in case of cheater player
	 */
	@Override
	protected Country toAttack() {
		return null;
	}

	/**
	 * Override method to get country on which attack happens
	 * @return null in case of cheater player
	 */
	@Override
	protected Country toAttackFrom() {
		return null;
	}

	/**
	 * Override method to get country from which attack happens
	 * @return null in case of cheater player
	 */
	@Override
	protected Country toMoveFrom() {	
		return null;
	}

	/**
	 * Override method to defend the country
	 * @return null in case of cheater player
	 */
	@Override
	protected Country toDefend() {	
		return null;
	}
	
	/**
	 * Override method to apply cheater player strategy during main game play
	 * @return null
	 */
	@Override
	public Order createOrder() {
		
//		d_connectivity.getD_countryList();
		System.out.println("////////////////////////////////////////");
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
					System.out.println(c1.getD_countryName());
					System.out.println(PlayersGameplay.findPlayerWithCountry(Play.getL_playersArray(), c1).getD_playerName());
					Player p =PlayersGameplay.findPlayerWithCountry(Play.getL_playersArray(), c1);
					
					System.out.println("C1: "+c1.getD_countryName());
					if(p!=null && !l_palyerCountry.contains(c1) )
					{
						//System.out.println(p.getD_playerName());
						System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
						l_countriesToAdd.add(c1);
						p.getD_Country().remove(c1);
						System.out.println("hhh: "+c1.getD_countryName());
					}
				}
				
				
				
			}
		}
		//add all counties to player
		d_player.getD_Country().addAll(l_countriesToAdd);
		
		System.out.println("xxxxxxxxxxxx");
		//doubles the number of armies on its countries that have enemy neighbors
		for(Country c:l_countriesToAdd)
		{
			c.setD_armyDeployedOnCountry(c.getD_armyDeployedOnCountry()*2);
		}
		
//		for(Country c:d_player.getD_Country())
//		{
//			for(int n:c.getD_neighbours())
//			{
//				Country c1 = c.getCountryFromID(d_connectivity.getD_countryList(), n);
//				
//				Player p = PlayersGameplay.findPlayerWithCountry(Play.getL_playersArray(), c1);
//				
//				if(p!=null)
//				{
//					c.setD_armyDeployedOnCountry(c.getD_armyDeployedOnCountry()*2);
//				}
//			}
//		}
		
		ViewMap.viewMap(d_connectivity.getD_continentList(), d_connectivity.getD_countryList(), Play.getL_playersArray());
		return null;
	}

}
