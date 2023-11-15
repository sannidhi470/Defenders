package Strategy;

import Models.Country;
import Models.Order;
import Models.Player;
import Tools.Connectivity;

public abstract class PlayerStrategy 
{
	Connectivity d_connectivity;
	Player d_player;
	
	PlayerStrategy(Player p_player, Connectivity p_connectivity){
		d_player = p_player;
		d_connectivity = p_connectivity;
		}

	public abstract Order createOrder();
	protected abstract Country toAttack(); 
	protected abstract Country toAttackFrom();
	protected abstract Country toMoveFrom();
	protected abstract Country toDefend();

}
