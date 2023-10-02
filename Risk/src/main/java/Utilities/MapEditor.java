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
	
	public static int addContinent(String p_continentId,int p_continentvalue,Connectivity p_connectivity) throws IOException
	{
		
		//System.out.println("hey");
		    Continent l_continent=new Continent();
			l_continent.setD_continentId(p_connectivity.getD_continentList().size()+1);
			l_continent.setD_continentName(p_continentId);
			l_continent.setD_continentArmyValue(p_continentvalue);
			p_connectivity.getD_continentList().add(l_continent);
			System.out.println("\u001B[32m"+"Continent Added Successfully"+"\u001B[0m");
			return 0; //Successful execution
	}
	public static int addCountry(String p_countryId,String p_continentId,Connectivity p_connectivity) throws IOException
	{
		Country l_country=new Country();
		if(p_connectivity.getD_continentList().size()==0)
		{
			System.out.println("\u001B[31m"+"ERROR: Enter the values of continents first.."+"\u001B[0m");
			return 1;
		}
		
			l_country.setD_countryId(p_connectivity.getD_countryList().size()+1);
			l_country.setD_countryName(p_countryId);
			l_country.setD_continentId(Integer.parseInt(p_continentId));
			p_connectivity.getD_countryList().add(l_country);
			
			System.out.println("\u001B[32m"+"Country Added Successfully"+"\u001B[0m");
			return 0;
			
			
	}
	
	public static int addNeighbour(int p_countryId,int p_neighbourcountryId,Connectivity p_connectivity) throws IOException
	{
		if(p_connectivity.getD_continentList().size()==0)
		{
			System.out.println("ERROR: Enter the values of continents first..");
			return 1;
		}
		if(p_connectivity.getD_countryList().size()==0)
		{
			System.out.println("ERROR: Enter the values of countries first..");
			return 1;
		}
		for(int i=0;i<p_connectivity.getD_countryList().size();i++)
		{
			if(p_connectivity.getD_countryList().get(i).getD_countryId()==p_countryId)
			{
				p_connectivity.getD_countryList().get(i).getD_neighbours().add(p_neighbourcountryId);
			}
		}
		
		return 0;
		
	}
	
	public static void removeNeighbour(int p_countryId,int p_neighbourcountryId, Connectivity p_connectivity)
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
	
	public static void removeCountry(String p_countryId,Connectivity p_connectivity)
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
			removeNeighbour(p_connectivity.getD_countryList().get(i).getD_countryId(),l_requiredCountryId,p_connectivity);
		}
	}
	public static void removeContinent(String p_continentId,Connectivity p_connectivity)
	{
		int l_requiredContinentId=0;
		for(int i=0;i<p_connectivity.getD_continentList().size();i++)
		{
			System.out.println(p_connectivity.getD_continentList());
			System.out.println(p_connectivity.getD_countryList());
			if(p_connectivity.getD_continentList().get(i).getD_continentName().equals(p_continentId))
			{
				l_requiredContinentId=p_connectivity.getD_continentList().get(i).getD_continentId();
				p_connectivity.getD_continentList().remove(i);
			}
		}
		for(int j=p_connectivity.getD_countryList().size()-1;j>=0;j--)
		{
			System.out.println(p_connectivity.getD_countryList());

			if(p_connectivity.getD_countryList().get(j).getD_continentId()==l_requiredContinentId)
			{
				removeCountry(p_connectivity.getD_countryList().get(j).getD_countryName(),p_connectivity);
			}
			
		}
		
	}	
	
}
