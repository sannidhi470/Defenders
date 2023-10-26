package Tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import Models.Continent;
import Models.Country;

public class validateGraph {
	public int d_noOfCountries;
	public LinkedList[] d_adjacentCountriesList;

	public void isConnected(validateGraph graph){ 
		  
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
        	System.out.println(ColorCoding.green+"Graph is connected"+ColorCoding.blank);
        	System.out.println(ColorCoding.green+"\n----- ✓ Loaded Map is a valid map ✓ -----\n"+ColorCoding.blank);      	
        }
        else 
        	System.out.println(ColorCoding.red+"Graph is disconnected"+ColorCoding.blank);
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
	public boolean continentConnection(Connectivity p_connectivity,validateGraph graph)
	{
		ArrayList<String> l_countries = new ArrayList<>();
		ArrayList<String> l_continents = new ArrayList<>();
		for(int i=0; i<p_connectivity.getD_continentList().size();i++)
		{
			System.out.println(p_connectivity.getD_continentList().get(i).getD_continentName());
			if(p_connectivity.getD_continentList().get(i).getD_countries().size() == 0)
			{
				System.out.println(ColorCoding.red+"Graph is disconnected"+" as "+p_connectivity.getD_continentList().get(i).getD_continentName()+" has no countries assigned to it"+ColorCoding.blank);
				return false;
			}
				if(l_continents.contains(p_connectivity.getD_continentList().get(i).getD_continentName()))
				{
					System.out.println(ColorCoding.red+"Map is INVALID as Duplicate continent "+p_connectivity.getD_continentList().get(i).getD_continentName()+" detected"+ColorCoding.blank);
					return false;
				}
				else
					l_continents.add(p_connectivity.getD_continentList().get(i).getD_continentName());
			for(int j=0; j<p_connectivity.getD_continentList().get(i).getD_countries().size(); j++)
			{

				if(l_countries.contains(p_connectivity.getD_continentList().get(i).getD_countries().get(j).getD_countryName()))
				{
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
