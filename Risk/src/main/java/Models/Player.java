package Models;

import java.util.ArrayList;

/**
 * The class Player defines Players and it's properties such as Name, ID, countries owned by players and orders given by player.
 *
 */

public class Player {

	private String d_playerName;
	private int d_playerId;
	private ArrayList<Country> d_playerCountry=new ArrayList<>();
	private ArrayList<Order> d_playerOrder=new ArrayList<>();
	private Order d_order;
	private int d_armyCount;
	private ArrayList<Continent> d_playerContinent=new ArrayList<>();
	private ArrayList<String> d_cards = new ArrayList<>();
	//private int d_diplomacyWith=-1; //Holds ID of player with whom diplomacy is executed
	private ArrayList<Integer> d_diplomacyWith = new ArrayList<>();
	private static int d_objCount = 1;
	
	/**
	 * This is a default constructor.
	 *
	 */
	
	public Player()
	{
		this.setD_playerId(d_objCount);
		d_objCount++;
	}
	
	/**
	 * It initiates a new player.
	 * @param l_player refers to the object of the player class.
	 */
	
	public Player(Player l_player)
	{
		this.d_playerName = l_player.getD_playerName();
		this.d_playerId = l_player.getD_playerId();
		this.d_playerCountry = l_player.getD_Country();
		this.d_playerOrder = l_player.getD_playerOrder();
		this.d_order = l_player.getD_Order();
		this.d_armyCount = l_player.getD_armyCount();
		this.d_playerContinent = l_player.getD_playerContinent();
	}
	
	/**
	 * Gets the player orders.
	 * @return player orders
	 * 
	 */
	
	public ArrayList<Order> getD_playerOrder() {
		
		return d_playerOrder;
	}

	/**
	 * This initiates a new player.
	 * @param p_playerName refers to the name of the player.
	 * @param p_playerId refers to the ID.
	 * @param p_playerCountry refers to the countries owned by that player.
	 * @param p_playerOrder list of orders given by player.
	 *
	 */
	
	public Player(String p_playerName, int p_playerId, ArrayList<Country> p_playerCountry,
			ArrayList<Order> p_playerOrder) {
		super();
		this.d_playerName = p_playerName;
		this.d_playerId = p_playerId;
		this.d_playerCountry = p_playerCountry;
		this.d_playerOrder = p_playerOrder;
	}
	
	/**
	 * Orders that are issued by the player.
	 *
	 */

	public void issue_order(){
		
		this.d_playerOrder.add(d_order);
		
	}
	
	/**
	 * Once the order is executed it gets removed from the list.
	 *
	 */
	
	public Order next_order(){
	
		Order l_order =d_playerOrder.remove(0);
		return l_order;
	}
	
	/**
	 * Gets the player name.
	 * @return player name
	 *
	 */
	
	public String getD_playerName() {
		return d_playerName;
	}
	
	/**
	 * Gets the player ID.
	 * @return player ID
	 *
	 */
	
	public int getD_playerId() {
		return d_playerId;
	}
	
	/**
	 * Gets the orders from player
	 * @return orders given by player
	 *
	 */
	
	public Order getD_Order() {
		return d_order;
	}
	
	/**
	 * Gets the countries owned by player.
	 * @return countries owned by player.
	 *
	 */
	
	public ArrayList<Country> getD_Country()
	{
		return d_playerCountry;
	}
	
	/**
	 * Sets the player name.
	 * @param p_playerName refers to the name of the player
	 *
	 */
	
	public void setD_playerName(String p_playerName) {
		d_playerName = p_playerName;
	}
	
	/**
	 * Sets the player ID.
	 * @param p_playerId refers to the player ID
	 *
	 */
	
	public void setD_playerId(int p_playerId) {
		d_playerId = p_playerId;
	}
	
	/**
	 * Sets the order given by players.
	 * @param p_order refers the order object
	 *
	 */
	
	public void setD_Order(Order p_order) {
		//LogEntryBuffer d_logEntryBuffer = new LogEntryBuffer();
		d_order = p_order;
		this.d_armyCount = this.d_armyCount - p_order.getD_numberOfArmies();
		//d_logEntryBuffer.log("Army count changed to "+ d_armyCount);
		System.out.println("Army count changed to "+ d_armyCount);
		
	}
	
	/**
	 * Add countries to the list of countries owned by player.
	 * @param p_country refers to the list countries.
	 *
	 */
	
	public void addCountry(Country p_country)
	{
		d_playerCountry.add(p_country);
	}
	
	/**
	 * Gets the player continent.
	 * @return player continent.
	 *
	 */
	
	public ArrayList<Continent> getD_playerContinent() {
		return d_playerContinent;
	}
	
	/**
	 * Sets the player continent.
	 * @param d_playerContinent refers to the list of continents in which player is present.
	 *
	 */
	
	public void setD_playerContinent(ArrayList<Continent> d_playerContinent) {
		this.d_playerContinent = d_playerContinent;
	}
	
	/**
	 * Gets the army count.
	 * @return the count of army
	 *
	 */
	
	public int getD_armyCount() {
		return d_armyCount;
	}
	
	/**
	 * Sets the army count
	 * @param d_armyCount refers to the count of armies available.
	 *
	 */
	
	public void setD_armyCount(int d_armyCount) {
		this.d_armyCount = d_armyCount;
	}
	
	public ArrayList<String> getCards() {
		return d_cards;
	}

	public void setCards(ArrayList<String> cards) {
		d_cards = cards;
	}

	public void removeCard(String card)
	{
		d_cards.remove(card);
	}
	
	public ArrayList<Integer> getDiplomacyWith() {
		return d_diplomacyWith;
	}

	public void setDiplomacyWith(ArrayList<Integer> diplomacyWith) {
		d_diplomacyWith = diplomacyWith;
	}
	
	public void addDiplomacyWith(Integer l_toPlayerID) {
		d_diplomacyWith.add(l_toPlayerID);
	}
	
	public void clearDiplomacyWith() {
		d_diplomacyWith.clear();
	}
	
	
}