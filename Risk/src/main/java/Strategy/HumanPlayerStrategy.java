package Strategy;

import java.util.Scanner;

import Models.Country;
import Models.Order;
import Models.Player;
import Tools.Connectivity;

public class HumanPlayerStrategy extends PlayerStrategy{

	public HumanPlayerStrategy(Player p_player, Connectivity p_connectivity) {
		super(p_player, p_connectivity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Order createOrder() {
		String l_userOrder="";
		Order l_order=new Order();
		Scanner l_sc = new Scanner(System.in);
		l_userOrder=l_sc.nextLine();
		l_order.setOrderContent(l_userOrder);
		l_sc.close();
		return l_order;

	}

	@Override
	protected Country toAttack() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Country toAttackFrom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Country toMoveFrom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Country toDefend() {
		// TODO Auto-generated method stub
		return null;
	}

}
