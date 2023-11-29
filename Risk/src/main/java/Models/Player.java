 package Models;

import java.util.ArrayList;

import Controllers.GameEngine;
import Strategy.HumanPlayerStrategy;
import Strategy.PlayerStrategy;
import Tools.ColorCoding;
import Tools.PlayersGameplay;

/**
 * The class Player defines Players and it's properties such as Name, player ID,Order list of the player,countries owned by the player
 * army count of the player, continents owned by the player, cards owned by the player , a diplomacy variable to be used by the diplomacy
 * card and a static variable to keep track of the object of players.
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
	private ArrayList<Integer> d_diplomacyWith = new ArrayList<>();
	private static int d_objCount = 1;
	
	private PlayerStrategy strategy;
	
	
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
	 * Verifies the orders that are issued by the player.
	 *
	 */
	
	public boolean verifyOrder(Order p_order, Player p_player) {
		String l_userOrder = p_order.getOrderContent();
		boolean l_validUserCommand =false;
		if(l_userOrder.equalsIgnoreCase("exit"))
		{
			System.out.println("Thank you for Playing the Game");
			System.exit(0);
		}
		String[] l_tempOrderListArray=l_userOrder.split(" ");
		for(int j=0;j<p_player.getD_Country().size();j++)
		{
			if(Integer.parseInt(l_tempOrderListArray[1])==(p_player.getD_Country().get(j).getD_countryId()))
			{
				p_order.setD_fromCountry(p_player.getD_Country().get(j));
				l_validUserCommand= true;
			}
		}
		if(l_validUserCommand)
		{
			if(PlayersGameplay.checkArmyAvailable(Integer.parseInt(l_tempOrderListArray[2]),p_player))
			{
				p_order.setD_numberOfArmies(Integer.parseInt(l_tempOrderListArray[2]));
				p_player.setD_Order(p_order);
			}
			else
			{
				l_validUserCommand= false;
				System.out.println(ColorCoding.red+"Error: Please enter valid number of troops"+ColorCoding.blank);
			}
		}
		else
		{
			l_validUserCommand= false;
			System.out.println(ColorCoding.red+"INVALID Command as player "+p_player.getD_playerName()+" doesn't control country with countryID "+l_tempOrderListArray[1]+ColorCoding.blank);
		}
		return l_validUserCommand;
	}
	
	/**
	 * Orders that are issued by the player.
	 *
	 */
	public boolean issue_order(){
		
		Order l_order;
		l_order = strategy.createOrder();
		if(l_order !=null)
		{
			if(GameEngine.getPhaseName().equals("Reinforcement"))
			{
				while(!verifyOrder(l_order, this))
				{
					System.out.println("INVALID ORDER! Please enter the order again");
					l_order = strategy.createOrder();
				}
			}
			this.d_playerOrder.add(l_order);
			return true;
		}
		else
		{
			return false;
		}

	}
	
	/**
	 * Once the order is executed it gets removed from the list.
	 * @return returns the order of the player
	 */
	
	public Order next_order(){
		if(d_playerOrder.size() == 0)
		{
			System.out.println("d_playerOrder = "+d_playerOrder.size());
			return null;
		}
		else
		{
			Order l_order =d_playerOrder.remove(0);
			return l_order;
		}

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
	 * This function sets the country list owned by the player
	 * @param p_countryList is the country list owned by the player
	 */
	
	public void setD_Country(ArrayList<Country> p_countryList)
	{
		d_playerCountry = p_countryList;
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
		LogEntryBuffer d_logEntryBuffer = new LogEntryBuffer();
		d_order = p_order;
		this.d_armyCount = this.d_armyCount - p_order.getD_numberOfArmies();
		d_logEntryBuffer.log("Army count changed to "+ d_armyCount);
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
	/**
	 * This function returns the cards of the given player
	 * @return list of cards
	 */
	public ArrayList<String> getCards() {
		return d_cards;
	}
	/**
	 * This function sets the cards owned by the players
	 * @param cards cards owned by player
	 */

	public void setCards(ArrayList<String> cards) 
	{
		d_cards = cards;
	}
	/**
	 * This function removes the card from the list of cards owned by the player
	 * @param card Card to be removed from the array list
	 */
	public void removeCard(String card)
	{
		d_cards.remove(card);
	}
	/**
	 * This function adds the card to the list of cards
	 * @param card refers to the card to be added to list
	 */
	public void addCard(String card)
	{
		d_cards.add(card);
	}
	/**
	 * This function returns the countries the player set the diplomacy with
	 * @return refers to the second country the player set the diplomacy with
	 */
	public ArrayList<Integer> getDiplomacyWith() 
	{
		return d_diplomacyWith;
	}
	/**
	 * This function sets the countries the player sets the diplomacy with
	 * @param diplomacyWith refers to the second country the player sets the diplomacy with
	 */
	public void setDiplomacyWith(ArrayList<Integer> diplomacyWith) 
	{
		d_diplomacyWith = diplomacyWith;
	}
	/**
	 * This function adds the country to the diplomacy list
	 * @param l_toPlayerID represents the playerId to perform diplomacy with
	 */
	public void addDiplomacyWith(Integer l_toPlayerID) 
	{
		d_diplomacyWith.add(l_toPlayerID);
	}
	/**
	 * This function clears the diplomacy list of the player
	 */
	public void clearDiplomacyWith()
	{
		d_diplomacyWith.clear();
	}
	/**
	 * This function removes all the assigned continents and countries to the player
	 */
	public void removeAllCountryAndContinentAssigned()
	{
		d_playerCountry.clear();
		d_playerContinent.clear();
	}
	/**
	 * This function remove the country from the player country list
	 * @param c Refers to the country
	 */
	public void removeCountry(Country c)
	{
		d_playerCountry.remove(c);
	}

	/**
	 * Gets the player strategy
	 * @return player strategy
	 */
	public PlayerStrategy getStrategy() {
		return strategy;
	}

	/**
	 * Sets the player strategy
	 * @param player strategy
	 */
	public void setStrategy(PlayerStrategy strategy) {
		this.strategy = strategy;
	}

	
}