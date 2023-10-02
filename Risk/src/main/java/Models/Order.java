package Models;

public class Order {
	
	private Country d_fromCountry;
	private Country d_toCountry;
	private int d_numberOfArmies;
	
	
	public Country getD_fromCountry() {
		return d_fromCountry;
	}
	public void setD_fromCountry(Country p_fromCountry) {
		this.d_fromCountry = p_fromCountry;
	}
	public Country getD_toCountry() {
		return d_toCountry;
	}
	public void setD_toCountry(Country p_toCountry) {
		this.d_toCountry = p_toCountry;
	}
	public int getD_numberOfArmies() {
		return d_numberOfArmies;
	}
	public void setD_numberOfArmies(int p_numberOfArmies) {
		this.d_numberOfArmies = p_numberOfArmies;
	}
	public void execute(Player p_player,Order p_order)
	{
		if(p_player.getD_armyCount() !=0)
		{
			System.out.println("in execute");
			p_player.setD_armyCount(p_player.getD_armyCount()-p_order.d_numberOfArmies);
		}
	}
	
	

}