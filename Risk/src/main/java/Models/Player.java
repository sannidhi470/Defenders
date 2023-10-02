package Models;

import java.util.ArrayList;
import java.util.List;

import Utilities.Connectivity;

public class Player {

	private String d_playerName;
	private int d_playerId;
	private ArrayList<Country> d_playerCountry=new ArrayList<>();
	private ArrayList<Order> d_playerOrder=new ArrayList<>();
	private Order d_order;
	private int d_armyCount;
	private ArrayList<Continent> d_playerContinent=new ArrayList<>();
	public Player()
	{
		
	}
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
	
	//new Function
	public ArrayList<Country> getD_Country()
	{
		return d_playerCountry;
	}
	
	public void setD_playerName(String p_playerName) {
		d_playerName = p_playerName;
	}
	
	public void setD_playerId(int p_playerId) {
		d_playerId = p_playerId;
	}
	
	public void setD_Order(Order p_order) {
		d_order = p_order;
		this.d_armyCount = this.d_armyCount - p_order.getD_numberOfArmies();
		System.out.println("Army count chnaged to "+ d_armyCount);
		
	}
	
	
	public void addCountry(Country p_country)
	{
		d_playerCountry.add(p_country);
	}
	public ArrayList<Continent> getD_playerContinent() {
		return d_playerContinent;
	}
	public void setD_playerContinent(ArrayList<Continent> d_playerContinent) {
		this.d_playerContinent = d_playerContinent;
	}
	public int getD_armyCount() {
		return d_armyCount;
	}
	public void setD_armyCount(int d_armyCount) {
		this.d_armyCount = d_armyCount;
	}
	
}