package Models;

import Tools.ColorCoding;
import Tools.Connectivity;
import Tools.PlayersGameplay;
import state.Play;

/**
 * The class Order defines Orders and it's properties such as from country, to country and transfer of number of armies and the Content of 
 * the order in the form of String. Execute function is implemented to execute the orders.
 */

public class Order {
	
	private Country d_fromCountry;
	private Country d_toCountry;
	private int d_numberOfArmies;
	private String orderContent;
	/**
	 * returns the order content
	 * @return returns the String of the order passed
	 * 
	 */
	public String getOrderContent() {
		return orderContent;
	}

	/**
	 * Sets the order content
	 * @param orderContent sets the String passed by the user in orderContent.
	 */
	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}
	
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
	 * @param p_connectivity refers to the list of map contents
	 * @param flag flag to choose between deploy or ataack commands
	 * @param fortify_flag to choose the fortify functionality 
	 *
	 */
	public void execute(Player p_player,Order p_order,Connectivity p_connectivity,int flag,int fortify_flag) {
		LogEntryBuffer d_logEntryBuffer = new LogEntryBuffer();
		
		if(p_order == null)
		{
			p_player.setD_armyCount(0);
			return;
		}
		if(flag==0)
		{
		if(p_player.getD_armyCount() !=0)
		{
			p_player.setD_armyCount(p_player.getD_armyCount()-p_order.d_numberOfArmies);
			for(int i=0;i<p_player.getD_Country().size();i++)
			{
				if(p_player.getD_Country().get(i).getD_countryId()==p_order.getD_fromCountry().getD_countryId())
				{
					p_player.getD_Country().get(i).setD_armyDeployedOnCountry(p_order.d_numberOfArmies+ p_player.getD_Country().get(i).getD_armyDeployedOnCountry());
					d_logEntryBuffer.log("Player "+p_player.getD_playerName()+": " + p_player.getD_Country().get(i).getD_countryName() + " has been assigned with " + p_order.d_numberOfArmies);
					System.out.println(ColorCoding.green+"Player "+p_player.getD_playerName()+": "+ p_player.getD_Country().get(i).getD_countryName()+" has been assigned with "+p_order.d_numberOfArmies+ColorCoding.blank);
				}
			}
		}
		}
		else
		{
			String[] l_orderContent=p_order.getOrderContent().split(" ");
			Country l_getCountry=new Country();
			switch(l_orderContent[0])
			{
			case "advance":
				d_logEntryBuffer.log(p_player.getD_playerName()+"is calling"+"Advance");
				//System.out.println(p_player.getD_playerName()+"is calling"+"Advance");
				System.out.println(p_order.getOrderContent());
				System.out.println(l_getCountry.getCountryFromName(p_connectivity.getD_countryList(), l_orderContent[1])+" seconf time "+l_getCountry.getCountryFromName(p_connectivity.getD_countryList(), l_orderContent[2]));
				PlayersGameplay.advance(p_player,Play.getL_playersArray(),l_getCountry.getCountryFromName(p_connectivity.getD_countryList(), l_orderContent[1]) , l_getCountry.getCountryFromName(p_connectivity.getD_countryList(), l_orderContent[2]), Integer.parseInt( l_orderContent[3]),p_connectivity.getD_continentList(),p_connectivity,fortify_flag);
				break;
			case "bomb":
				d_logEntryBuffer.log(p_player.getD_playerName()+"is calling"+"bomb");
				System.out.println(p_player.getD_playerName()+"is calling"+"bomb");
				if(p_player.getCards().contains("bomb")) {
					PlayersGameplay.bomb(p_player,Play.getL_playersArray(), Country.getCountryFromID(p_connectivity.getD_countryList(), Integer.parseInt(l_orderContent[1])) , p_connectivity.getD_continentList());
					p_player.removeCard("bomb");
				} else {
					System.out.println(ColorCoding.red+p_player.getD_playerName()+" doesn't have bomb card "+ColorCoding.blank);
				}
				break;
			case "blockade":
				d_logEntryBuffer.log(p_player.getD_playerName()+"is calling"+"blockade");
				System.out.println(p_player.getD_playerName()+"is calling"+"blockade");
				if(p_player.getCards().contains("blockade")){
					PlayersGameplay.Blockade(l_getCountry.get_nameFromId(p_connectivity.getD_countryList(), Integer.parseInt(l_orderContent[1])),p_player,Play.getL_playersArray(),p_connectivity.getD_continentList());
					p_player.removeCard("blockade");
				}
					else
					System.out.println(ColorCoding.red+p_player.getD_playerName()+" doesn't have blockade card "+ColorCoding.blank);
				break;
			case "airlift":
				d_logEntryBuffer.log(p_player.getD_playerName()+"is calling"+"airlift");
				System.out.println(p_player.getD_playerName()+"is calling"+"airlift");
				if(p_player.getCards().contains("airlift")) {
					
					PlayersGameplay.AirliftDeploy(l_getCountry.get_nameFromId(p_connectivity.getD_countryList(), Integer.parseInt(l_orderContent[1])),l_getCountry.get_nameFromId(p_connectivity.getD_countryList(), Integer.parseInt(l_orderContent[2])), Integer.parseInt( l_orderContent[3]), p_player);
					p_player.removeCard("airlift");
				}
				else
					System.out.println(ColorCoding.red+p_player.getD_playerName()+" doesn't have airlift card "+ColorCoding.blank);
				break;
			case "negotiate":
				d_logEntryBuffer.log(p_player.getD_playerName()+"is calling "+"negotiate");
				System.out.println(p_player.getD_playerName()+"is calling "+"negotiate");
				
				if(p_player.getCards().contains("diplomacy"))
				{
					PlayersGameplay.negotiate(p_player,Play.getL_playersArray(),l_orderContent[1]);
					p_player.removeCard("diplomacy");
				}
					
				
				else {
					d_logEntryBuffer.log(p_player.getD_playerName()+" doesn't have diplomacy card");
					System.out.println(ColorCoding.red+p_player.getD_playerName()+" doesn't have diplomacy card "+ColorCoding.blank);
				}
				break;
			} 
		
	}
	
	

}
	
	

}