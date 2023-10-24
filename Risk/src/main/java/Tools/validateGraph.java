package Tools;

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
        	System.out.println(ColorCoding.green+"Graph is connected"+ColorCoding.blank); 
        else 
        	System.out.println(ColorCoding.red+"Graph is disconnected"+ColorCoding.blank);
    }

}
