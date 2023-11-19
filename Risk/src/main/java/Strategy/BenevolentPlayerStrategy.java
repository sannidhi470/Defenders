package Strategy;

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
			str = str+ toDefend().getD_countryId() +" "+ d_player.getD_armyCount();
			System.out.println(str);
			o.setOrderContent(str);
			return o;
		}
		
		
		
		return o;
		
	}

}
