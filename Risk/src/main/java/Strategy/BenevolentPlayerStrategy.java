package Strategy;

import java.util.ArrayList;

import Controllers.GameEngine;
import Models.Country;
import Models.Order;
import Models.Player;
import Tools.Connectivity;
import Views.ViewMap;
import state.Play;

/**
 * The BenevolentPlayerStrategy class represents the player strategy that focuses on protecting its weak countries.
 */
public class BenevolentPlayerStrategy extends PlayerStrategy{

	/**
	 * This constructor refers to the benevolent player strategy.
	 * @param p_player refers to the object of the player class
	 * @param p_connectivity refers to connectivity object
	 */
	public BenevolentPlayerStrategy(Player p_player, Connectivity p_connectivity) {
		super(p_player, p_connectivity);
	}


	/**
	 * Override method to attack
	 * @return null in case of benevolent player
	 */
	@Override
	protected Country toAttack() {
		return null;
	}

	/**
	 * Override method to get country on which attack happens
	 * @return null in case of benevolent player
	 */
	@Override
	protected Country toAttackFrom() {
		return null;
	}

	/**
	 * Override method to get country from which attack happens
	 * @return null in case of benevolent player
	 */
	@Override
	protected Country toMoveFrom() {
		return null;
	}

	/**
	 * Override method to get country with Minimum number of troops
	 * @return that country
	 */
	@Override
	protected Country toDefend() 
	{  if(d_player.getD_Country().size()==0)
		return null;
		Country result = d_player.getD_Country().get(0);
		int min= result.getD_armyDeployedOnCountry();
		
		for(Country c:d_player.getD_Country())
		{
			if(c.getD_armyDeployedOnCountry() < min)
			{
				min = c.getD_armyDeployedOnCountry() ;
				result = c;
			}
		}
		return result;
	}

	/**
	 * Override method to apply benevolent player strategy during main game play by creating order
	 * @return order
	 */
	@Override
	public Order createOrder()
	{
		Order o =new Order();
		String str;
		if(toDefend()==null)
			return null;
		if(GameEngine.getPhaseName().equals("Reinforcement"))
		{
			str="deploy ";
			str = str+ toDefend().getD_countryId()+" " + d_player.getD_armyCount();
			System.out.println(str);
			o.setOrderContent(str);
			return o;
		}
		else if(GameEngine.getPhaseName().equals("Attack") || GameEngine.getPhaseName().equals("Fortify") )
		{
			str="advance ";
			Country[] c = level(toDefend().getD_countryId());
			
			if(c!=null)
			{
				str = str+ c[0].getD_countryName() +" "+ c[1].getD_countryName()+" "+c[0].getD_armyDeployedOnCountry();
				System.out.println("advance "+c[0].getD_countryName()+" "+c[1].getD_countryName()+" "+c[0].getD_armyDeployedOnCountry());
				o.setOrderContent(str);
				ViewMap.viewMap(d_connectivity.getD_continentList(), d_connectivity.getD_countryList(), Play.getL_playersArray());
				return o;
			}
			else
				return null;
			
		}
		
		
		
		return o;
		
	}
	
	/**
	 * Method to get neighboring countries of the country ID of the country with minimum number of troops 
	 * @param countryID refers to the country ID of the country with minimum number of troops
	 * @return countries
	 */
	public Country[] level(int countryID)
	{
		Country[] c = new Country[2];
		ArrayList<Integer> neighbourCountryID = d_connectivity.getCountryByID(countryID).getD_neighbours();
		
		for(int i: neighbourCountryID)
		{
			if(d_player.getD_Country().contains(d_connectivity.getCountryByID(i)))
			{
				if(d_connectivity.getCountryByID(i).getD_armyDeployedOnCountry()>0)
				{
					c[0] = d_connectivity.getCountryByID(i); //from Country
					c[1] = d_connectivity.getCountryByID(countryID); // to country 
					return c;
				}
			}
			
		}
		//2nd Neighbour
		for(int i:neighbourCountryID)
		{
			
			ArrayList<Integer> neighbourCountryID2 = d_connectivity.getCountryByID(i).getD_neighbours();
			for(int j: neighbourCountryID2)
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
					}
				}
				
			}
		}
		return null;
	}

}
