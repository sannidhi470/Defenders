package Strategy;

import Models.Country;
import Models.Order;
import Models.Player;
import Tools.Connectivity;

/**
 * This is an abstract class to apply player strategy.
 */
public abstract class PlayerStrategy 
{
	Connectivity d_connectivity;
	Player d_player;
	
	PlayerStrategy(Player p_player, Connectivity p_connectivity){
		d_player = p_player;
		d_connectivity = p_connectivity;
		}

	/**
	 * Abstract class to create order
	 */
	public abstract Order createOrder();
	/**
	 * Abstract class to attack to a country
	 */
	protected abstract Country toAttack();
	/**
	 * Abstract class to get country to which attack should happen 
	 */
	protected abstract Country toAttackFrom();
	/**
	 * Abstract class to move armies from given country
	 */
	protected abstract Country toMoveFrom();
	/**
	 * Abstract class to defend the country
	 */
	protected abstract Country toDefend();

}
