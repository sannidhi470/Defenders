package Strategy;

import java.util.ArrayList;

import Controllers.GameEngine;
import Models.Country;
import Models.Order;
import Models.Player;
import Tools.Connectivity;

public class BenevolentPlayerStrategy extends PlayerStrategy{

	public BenevolentPlayerStrategy(Player p_player, Connectivity p_connectivity) {
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

	
	@Override //returns country with Minimum number of troops 
	protected Country toDefend() 
	{
		Country result = d_player.getD_Country().get(0);
		System.out.println(d_player.getD_playerName());
		int min= result.getD_armyDeployedOnCountry();
		
		for(Country c:d_player.getD_Country())
		{
			if(c.getD_armyDeployedOnCountry() < min)
			{
				min = c.getD_armyDeployedOnCountry() ;
				result = c;
			}
		}
		System.out.println("resutl:"+result.getD_countryName()+" "+result.getD_countryId());
		return result;
	}

	
	@Override
	public Order createOrder()
	{
		Order o =new Order();
		String str;
		System.out.println("test");
		if(GameEngine.getPhaseName().equals("Reinforcement"))
		{
			str="deploy ";
			str = str+ toDefend().getD_countryId()+" " + d_player.getD_armyCount();
			System.out.println(str);
			o.setOrderContent(str);
			return o;
		}
		else if(GameEngine.getPhaseName().equals("Attack"))
		{
			str="advance ";
			Country[] c = level(toDefend().getD_countryId());
			
			if(c!=null)
			{
				str = str+ c[0] +" "+ c[1]+" "+c[0].getD_armyDeployedOnCountry();
				System.out.println("advance "+c[0].getD_countryName()+" "+c[1].getD_countryName()+" "+c[0].getD_armyDeployedOnCountry());
				o.setOrderContent(str);
				return o;
			}
			else
				return null;
			
		}
		
		
		
		return o;
		
	}
	
	
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
		for(int i:neighbourCountryID)
		{
			
			neighbourCountryID = d_connectivity.getCountryByID(i).getD_neighbours();
			for(int j: neighbourCountryID)
			{
				if(d_player.getD_Country().contains(d_connectivity.getCountryByID(i)))
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
		return null;
	}

}
