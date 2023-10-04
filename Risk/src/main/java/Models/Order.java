package Models;

/**
 * The class Order defines Orders and it's properties such as from country, to country and transfer of number of armies.
 *
 */

public class Order {
	
	private Country d_fromCountry;
	private Country d_toCountry;
	private int d_numberOfArmies;
	
	/**
	 * Gets the country from which order is taken.
	 * @return the country name.
	 *
	 */
	
	public Country getD_fromCountry() {
		return d_fromCountry;
	}
	
	/**
	 * Sets the country from which order is taken.
	 * @param p_fromCountry refers the country name from which order is taken.
	 *
	 */
	
	public void setD_fromCountry(Country p_fromCountry) {
		this.d_fromCountry = p_fromCountry;
	}
	
	/**
	 * Gets the country to which order is taken.
	 * @return country name to which order is taken
	 *
	 */
	
	public Country getD_toCountry() {
		return d_toCountry;
	}
	
	/**
	 * Sets the country to which order is taken.
	 * @param p_toCountry refers to the country name to which order is taken.
	 *
	 */
	
	public void setD_toCountry(Country p_toCountry) {
		this.d_toCountry = p_toCountry;
	}
	
	/**
	 * Gets the number of armies to be deployed.
	 * @return number of armies.
	 *
	 */
	
	public int getD_numberOfArmies() {
		return d_numberOfArmies;
	}
	
	/**
	 * Sets the number of armies deployed.
	 * @param p_numberOfArmies refers to the number of armies to be deployed.
	 *
	 */
	
	public void setD_numberOfArmies(int p_numberOfArmies) {
		this.d_numberOfArmies = p_numberOfArmies;
	}
	
	/**
	 * Execute the orders given by the user.
	 * @param p_player refers to the player object
	 * @param p_order refers to the order object
	 *
	 */
	public void execute(Player p_player,Order p_order)
	{
		if(p_player.getD_armyCount() !=0)
		{
			p_player.setD_armyCount(p_player.getD_armyCount()-p_order.d_numberOfArmies);
		}
	}
	
	

}