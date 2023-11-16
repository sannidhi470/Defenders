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

	CheaterPlayerStrategy(Player p_player, Connectivity p_connectivity) {
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
		
//		d_connectivity.getD_countryList();
		System.out.println("////////////////////////////////////////");
		//conquers all the immediate neighboring enemy countries
		for(Country c:d_player.getD_Country())
		{
			for(int n:c.getD_neighbours())
			{
				Country c1 = c.getCountryFromID(d_connectivity.getD_countryList(), n);
				
				System.out.println(c1.getD_countryName());
				System.out.println(PlayersGameplay.findPlayerWithCountry(Play.getL_playersArray(), c1));
				Player p =PlayersGameplay.findPlayerWithCountry(Play.getL_playersArray(), c1);
				
				if(p!=null)
				{
					//System.out.println(p.getD_playerName());
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
					d_player.getD_Country().add(c1);
					p.getD_Country().remove(c1);
				}
			}
		}
		
		//doubles the number of armies on its countries that have enemy neighbors
		for(Country c:d_player.getD_Country())
		{
			for(int n:c.getD_neighbours())
			{
				Country c1 = c.getCountryFromID(d_connectivity.getD_countryList(), n);
				
				Player p = PlayersGameplay.findPlayerWithCountry(Play.getL_playersArray(), c1);
				
				if(p!=null)
				{
					c.setD_armyDeployedOnCountry(c.getD_armyDeployedOnCountry()*2);
				}
			}
		}
		
		
		return null;
	}

//	public static void main(String args[])
//	{
//		
//		ArrayList<Player> d_playersArray = new ArrayList<Player>();
//		//Play.getL_playersArray();
//		Connectivity d_connectivity=new Connectivity();
//		
//		MapLoader.loadMap(d_connectivity, "VeryBasic");
//		
//		Player l_player1 = new Player();
//		l_player1.setD_playerName("player1");
//		
//		Player l_player2 = new Player();
//		l_player2.setD_playerName("player2");
//		
//		d_playersArray.add(l_player1);
//		d_playersArray.add(l_player2);
//		
//		
//		PlayersGameplay.assigncountries(d_playersArray, d_connectivity.getD_countryList(), d_connectivity.getD_continentList());
//		
//		ViewMap.viewMap(d_connectivity.getD_continentList(),d_connectivity.getD_countryList(),d_playersArray);
//		System.out.println("Player1:"+l_player1.getD_Country().size());
//		System.out.println("Player2:"+l_player2.getD_Country().size());
//		
//		PlayerStrategy s = new CheaterPlayerStrategy(l_player1,d_connectivity);
//		
//		l_player1.setStrategy(s);
//		System.out.println("...............................");
//		l_player1.issue_order();
//		ViewMap.viewMap(d_connectivity.getD_continentList(),d_connectivity.getD_countryList(),d_playersArray);
//		System.out.println("Player1:"+l_player1.getD_Country().size());
//		System.out.println("Player2:"+l_player2.getD_Country().size());
//		
//	}

}
