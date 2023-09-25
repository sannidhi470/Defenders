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
