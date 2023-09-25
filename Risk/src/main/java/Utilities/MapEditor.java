package Utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Models.Continent;
import Models.Country;
import Models.Map;

public class MapEditor {
	
	//static int NeighbourID=0;
	
	public static void addContinent(String p_continentId,int p_continentvalue,Connectivity p_connectivity,Map p_map) throws IOException
	{
		
		    Continent l_continent=new Continent();
			l_continent.setD_continentId(p_connectivity.getD_continentList().size()+1);
			l_continent.setD_continentName(p_continentId);
			l_continent.setD_continentArmyValue(p_continentvalue);
			p_connectivity.getD_continentList().add(l_continent);
			
	}
	public static void addCountry(String p_countryId,String p_continentId, Map p_map,Connectivity p_connectivity) throws IOException
	{
		Country l_country=new Country();
		if(p_connectivity.getD_continentList().size()==0)
		{
			System.out.println("Enter the values of continents first..");
			return;
		}
		
			l_country.setD_countryId(p_connectivity.getD_countryList().size()+1);
			l_country.setD_countryName(p_countryId);
			l_country.setD_continentId(Integer.parseInt(p_continentId));
			p_connectivity.getD_countryList().add(l_country);
			
			
	}
	
	public static void addNeighbour(int p_countryId,int p_neighbourcountryId, Map p_map,Connectivity p_connectivity) throws IOException
	{
		if(p_connectivity.getD_continentList().size()==0)
		{
			System.out.println("Enter the values of continents first..");
			return;
		}
		if(p_connectivity.getD_countryList().size()==0)
		{
			System.out.println("Enter the values of countries first..");
			return;
		}
		for(int i=0;i<p_connectivity.getD_countryList().size();i++)
		{
			if(p_connectivity.getD_countryList().get(i).getD_countryId()==p_countryId)
			{
				p_connectivity.getD_countryList().get(i).getD_neighbours().add(p_neighbourcountryId);
			}
		}
//		HashMap<Integer,ArrayList<Integer>> l_neighbouringCountries = new HashMap<Integer, ArrayList<Integer>>();
//		ArrayList<Integer> neighbours = new ArrayList<Integer>();
//		for(int i=0; i<p_neighbourcountryId.length; i++) {
//			++NeighbourID;
//			neighbours.add(NeighbourID);
//		}
//		l_neighbouringCountries.put(Integer.parseInt(p_countryId), neighbours);
//		current_country.setD_neighbours(Integer.parseInt(p_countryId), l_neighbouringCountries);
		
	}
	
	public static void removeNeighbour(int p_countryId,int p_neighbourcountryId, Map p_map,Connectivity p_connectivity)
	{
		
		for(int i=0;i<p_connectivity.getD_countryList().size();i++)
		{
			if(p_connectivity.getD_countryList().get(i).getD_countryId()==p_countryId)
			{
				for(int j=0;j<p_connectivity.getD_countryList().get(i).getD_neighbours().size();j++)
				{
					if(p_neighbourcountryId==p_connectivity.getD_countryList().get(i).getD_neighbours().get(j))
					{
						p_connectivity.getD_countryList().get(i).getD_neighbours().remove(j);
					}
				}
			}
		}
		
	}
	
	public static void removeCountry(String p_countryId,Map p_map,Connectivity p_connectivity)
	{
		int l_requiredCountryId=0;
		for(int i=0;i<p_connectivity.getD_countryList().size();i++)
		{
			
			if(p_countryId.equals(p_connectivity.getD_countryList().get(i).getD_countryName()))
			{
				l_requiredCountryId=p_connectivity.getD_countryList().get(i).getD_countryId();
				p_connectivity.getD_countryList().remove(i);
			}

		}
		for(int i=0;i<p_connectivity.getD_countryList().size();i++)
		{
			removeNeighbour(p_connectivity.getD_countryList().get(i).getD_countryId(),l_requiredCountryId,p_map,p_connectivity);
		}
	}
	
}
