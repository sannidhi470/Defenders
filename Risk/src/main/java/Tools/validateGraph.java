package Tools;

import java.util.ArrayList;
import java.util.LinkedList;
import Models.LogEntryBuffer;

public class validateGraph {
	public int d_noOfCountries;
	public LinkedList[] d_adjacentCountriesList;

	public int isConnected(validateGraph graph){
		LogEntryBuffer d_logEntryBuffer = new LogEntryBuffer();
		  
        int vertices = graph.d_noOfCountries+1; 
        LinkedList<Integer> adjacencyList [] = graph.d_adjacentCountriesList;
  
        boolean[] visited = new boolean[vertices]; 

        DFS(1, adjacencyList, visited);
        boolean connected = true; 
       // System.out.println("test"+visited.length);
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
	
	public void DFS(int source, LinkedList<Integer> adjacencyList [], boolean[] visited){ 
		  
        visited[source] = true; 
        for (int i = 0; i <adjacencyList[source].size() ; i++) { 
            int neighbour = adjacencyList[source].get(i); 
            if(visited[neighbour]==false){ 
                DFS(neighbour, adjacencyList, visited); 
            } 
        }
    }
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
