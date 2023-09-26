package Models;
import java.util.List;
public class GameState {
	
	Map d_map;
	List<Player> d_players;
	List<Order> d_OrdersRemaining;
	String d_error;
	
	public List<Player> getd_players() 
	{
		return d_players;
	}
	
	public void setd_players(List<Player> p_players) 
	{
		this.d_players = p_players;
	}

	public List<Order> getd_unexecutedOrders() 
	{
		return d_OrdersRemaining;
	}
	
	public void setd_unexecutedOrders(List<Order> p_OrdersRemaining) 
	{
		this.d_OrdersRemaining = p_OrdersRemaining;
	}
	
	public Map getd_map() {
		return d_map;
	}
	
	public void setd_map(Map p_map) {
		this.d_map = p_map;
	}
	
	public String getError() {
		return d_error;
	}

	public void setError(String p_error) {
		this.d_error = p_error;
	}
}
