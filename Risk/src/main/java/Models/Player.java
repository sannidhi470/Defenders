package Models;

public class Player {

	private String d_playerName;
	private int d_playerId;
	private ArrayList<Country> d_playerCountry;
	private ArrayList<Order> d_playerOrder;
	
	public Player() {
		d_playerName = "Player1";
		d_playerId = 1;
	}
	
	
	public Player(String p_playerName,int p_playerID){
		d_playerName = p_playerName;
		d_playerId = p_playerID;
	}

	public void issue_order(){
		
		
	}
	
	public void next_order(){
		
		
	}
	
	public String getD_playerName() {
		return d_playerName;
	}
	
	public int getD_playerId() {
		return d_playerId;
	}
	
	public void setD_playerName(String p_playerName) {
		d_playerName = p_playerName;
	}
	
	public void serD_playerId(int p_playerId) {
		d_playerId = p_playerId;
	}
	
}
