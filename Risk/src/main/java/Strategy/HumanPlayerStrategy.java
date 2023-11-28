package Strategy;

import java.util.Scanner;

import Models.Country;
import Models.Order;
import Models.Player;
import Tools.Connectivity;

/**
 * The HumanPlayerStrategy class represents the player strategy hat requires user interaction to make decisions.
 */
public class HumanPlayerStrategy extends PlayerStrategy{

	/**
	 * This constructor refers to the human player strategy.
	 * @param p_player refers to the object of the player class
	 * @param p_connectivity refers to connectivity object
	 */
	public HumanPlayerStrategy(Player p_player, Connectivity p_connectivity) {
		super(p_player, p_connectivity);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Override method to apply human player strategy during main game play by creating order
	 * @return order
	 */
	@Override
	public Order createOrder() {
		String l_userOrder="";
		Order l_order=new Order();
		Scanner l_sc = new Scanner(System.in);
		l_userOrder=l_sc.nextLine();
		l_order.setOrderContent(l_userOrder);
		return l_order;
	}

	/**
	 * Override method to attack
	 * @return null in case of human player
	 */
	@Override
	protected Country toAttack() {
		return null;
	}

	/**
	 * Override method to get country on which attack happens
	 * @return null in case of human player
	 */
	@Override
	protected Country toAttackFrom() {
		return null;
	}

	/**
	 * Override method to get country from which attack happens
	 * @return null in case of human player
	 */
	@Override
	protected Country toMoveFrom() {	
		return null;
	}

	/**
	 * Override method to defend the country
	 * @return null in case of human player
	 */
	@Override
	protected Country toDefend() {	
		return null;
	}

}
