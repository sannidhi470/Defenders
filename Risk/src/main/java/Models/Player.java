package Models;

import java.util.ArrayList;
import java.util.List;

import Utilities.Connectivity;

public class Player {

	private String d_playerName;
	private int d_playerId;
	private ArrayList<Country> d_playerCountry;
	private ArrayList<Order> d_playerOrder;
	private Order d_order;
	
	public Player(String p_playerName, int p_playerId, ArrayList<Country> p_playerCountry,
			ArrayList<Order> p_playerOrder) {
		super();
		this.d_playerName = p_playerName;
		this.d_playerId = p_playerId;
		this.d_playerCountry = p_playerCountry;
		this.d_playerOrder = p_playerOrder;
	}


	

	public void issue_order(){
		
		d_playerOrder.add(d_order);
		
	}
	
	public Order next_order(){
	
		Order l_order =d_playerOrder.remove(0);
		return l_order;
	}
	
	public String getD_playerName() {
		return d_playerName;
	}
	
	public int getD_playerId() {
		return d_playerId;
	}
	
	public Order getD_Order() {
		return d_order;
	}
	
	public void setD_playerName(String p_playerName) {
		d_playerName = p_playerName;
	}
	
	public void setD_playerId(int p_playerId) {
		d_playerId = p_playerId;
	}
	
	public void setD_Order(Order p_order) {
		d_order = p_order;
	}
}