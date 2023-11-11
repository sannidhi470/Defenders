package Tools;

import java.util.ArrayList;
import java.util.LinkedList;
import Models.LogEntryBuffer;

/**
 * This class is used for validating the graph representation of map
 */
public class validateGraph {
	public int d_noOfCountries;
	public LinkedList[] d_adjacentCountriesList;
/**
 * This method checks if the graph is connected or not.
 * @param graph represents the graph
 * @return returns 0 and 1 depending on isconnected.
 */
	public int isConnected(validateGraph graph){
		LogEntryBuffer d_logEntryBuffer = new LogEntryBuffer();
		  
        int vertices = graph.d_noOfCountries+1; 
        LinkedList<Integer> adjacencyList [] = graph.d_adjacentCountriesList;
  
        boolean[] visited = new boolean[vertices]; 

        DFS(1, adjacencyList, visited);
        boolean connected = true; 
        for (int i = 1; i <visited.length ; i++) { 
            if(!visited[i]){ 
                connected = false; 
                break; 
            } 
        }
        
        if(connected)
        {
        	d_logEntryBuffer.log("Graph is connected");
        	System.out.println(ColorCoding.green+"Graph is connected"+ColorCoding.blank);
        	d_logEntryBuffer.log("\n----- ✓ Loaded Map is a valid map ✓ -----\n");
        	System.out.println(ColorCoding.green+"\n----- ✓ Loaded Map is a valid map ✓ -----\n"+ColorCoding.blank);      	
        	return 0;
        }
        else {
        	d_logEntryBuffer.log("Graph is disconnected");
        	System.out.println(ColorCoding.red+"Graph is disconnected"+ColorCoding.blank);
        	return 1;
        }
        
    }
	/**
	 * This method is used for DFS route.
	 * @param source refers to the source country from where we are about to traverse from
	 * @param adjacencyList refers to the adjacent countries/neighbors list
	 * @param visited refers to the list of visited countries in the traversal
	 */
	
	public void DFS(int source, LinkedList<Integer> adjacencyList [], boolean[] visited){ 
		  
        visited[source] = true; 
        for (int i = 0; i <adjacencyList[source].size() ; i++) { 
            int neighbour = adjacencyList[source].get(i); 
            if(visited[neighbour]==false){ 
                DFS(neighbour, adjacencyList, visited); 
            } 
        }
    }
	/**
	 * This methods is used to check whether continents are connected or not.
	 * @param p_connectivity refers to the connectivity object 
	 * @param graph is the graph representation of the map
	 * @return true if continents are connected; false if continents are not connected
	 */
	public boolean continentConnection(Connectivity p_connectivity,validateGraph graph) {
		LogEntryBuffer d_logEntryBuffer = new LogEntryBuffer();
		
		ArrayList<String> l_countries = new ArrayList<>();
		ArrayList<String> l_continents = new ArrayList<>();
		for(int i=0; i<p_connectivity.getD_continentList().size();i++)
		{
			if(p_connectivity.getD_continentList().get(i).getD_countries().size() == 0)
			{
				d_logEntryBuffer.log("Graph is disconnected"+" as "+p_connectivity.getD_continentList().get(i).getD_continentName()+" has no countries assigned to it");
				System.out.println(ColorCoding.red+"Graph is disconnected"+" as "+p_connectivity.getD_continentList().get(i).getD_continentName()+" has no countries assigned to it"+ColorCoding.blank);
				return false;
			}
				if(l_continents.contains(p_connectivity.getD_continentList().get(i).getD_continentName()))
				{
					d_logEntryBuffer.log("Map is INVALID as Duplicate continent "+p_connectivity.getD_continentList().get(i).getD_continentName()+" detected");
					System.out.println(ColorCoding.red+"Map is INVALID as Duplicate continent "+p_connectivity.getD_continentList().get(i).getD_continentName()+" detected"+ColorCoding.blank);
					return false;
				}
				else
					l_continents.add(p_connectivity.getD_continentList().get(i).getD_continentName());
			for(int j=0; j<p_connectivity.getD_continentList().get(i).getD_countries().size(); j++)
			{

				if(l_countries.contains(p_connectivity.getD_continentList().get(i).getD_countries().get(j).getD_countryName()))
				{
					d_logEntryBuffer.log("Map is INVALID as Duplicate country "+p_connectivity.getD_continentList().get(i).getD_countries().get(j).getD_countryName()+" detected");
					System.out.println(ColorCoding.red+"Map is INVALID as Duplicate country "+p_connectivity.getD_continentList().get(i).getD_countries().get(j).getD_countryName()+" detected"+ColorCoding.blank);
					return false;
				}
				else
					l_countries.add(p_connectivity.getD_continentList().get(i).getD_countries().get(j).getD_countryName());
			}
		}
		return true;
	}
}
