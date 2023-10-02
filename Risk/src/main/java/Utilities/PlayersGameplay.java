package Utilities;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Models.Continent;
import Models.Country;
import Models.Player;

public class PlayersGameplay {
	

	public static void assigncountries(ArrayList<Player> p_playersArray,ArrayList<Country> p_countryList,ArrayList<Continent> p_continentList) 
	{	
		int[] l_num = new int[p_playersArray.size()];
		int sum = 0;
		 int range = (p_countryList.size() - 1) + 1;
		do
		{
			sum=0;
			for(int i=0; i<p_playersArray.size(); i++)
			{
				int ran = (int) ((Math.random()*range) + 1);
				
				sum=sum+ran;
				l_num[i]=ran;
			}
			
		}while(sum>p_countryList.size());
		
		for(int i=0; i<p_playersArray.size();i++)
		{
			ArrayList<Country> l_removeCountry=new ArrayList<>();
			for(int j=0; j<l_num[i];j++)
			{
				Country l_country = p_countryList.get(j);
				
				l_removeCountry.add(l_country);
				
				p_playersArray.get(i).addCountry(l_country);
			}
			p_countryList.removeAll(l_removeCountry);
			
		}
		for(int i=0;i<p_playersArray.size();i++)
		{
			ArrayList<String> l_tempCountry=new ArrayList<>();
			ArrayList<String> tempCountryInContinent=new ArrayList<>();
			ArrayList<Continent> l_continentsOwned=new ArrayList<>();
			for(int j=0;j<p_playersArray.get(i).getD_Country().size();j++)
			{
				l_tempCountry.add(p_playersArray.get(i).getD_Country().get(j).getD_countryName());
			}
			for(int j=0;j<p_continentList.size();j++)
			{
				tempCountryInContinent=new ArrayList<>();
				for(int k=0;k<p_continentList.get(j).getD_countries().size();k++)
				{
					tempCountryInContinent.add(p_continentList.get(j).getD_countries().get(k).getD_countryName());
				}
				if(l_tempCountry.containsAll(tempCountryInContinent))
				{
					l_continentsOwned.add(p_continentList.get(j));
					p_playersArray.get(i).setD_playerContinent(l_continentsOwned);
				}
			}
			
				
		}
		
		
	}
	public static void assignArmiesToPlayers(ArrayList<Player> d_playersList)
	{
		for(int i=0;i<d_playersList.size();i++)
		{
			//System.out.println("Country count:"+d_playersList.get(i).getD_Country().size());
			int l_countryListSize=d_playersList.get(i).getD_Country().size()/3;
			int l_armyCount=Math.max(3, l_countryListSize);
			int l_tempContinentCount=0;
			if(d_playersList.get(i).getD_playerContinent().size()!=0)
			{
				
				for(int j=0;j<d_playersList.get(i).getD_playerContinent().size();j++)
				{
					l_tempContinentCount=l_tempContinentCount+ d_playersList.get(i).getD_playerContinent().get(j).getD_continentArmyValue();
				}
			}
			l_armyCount+=l_tempContinentCount;
			d_playersList.get(i).setD_armyCount(l_armyCount);
			System.out.println(l_armyCount);
		}
	}
	
	public static void showPlayersCountry(Player p_player,int l_flag)
	{
		ArrayList<Country> l_country = new ArrayList<>();
		System.out.println("Player 1:" + p_player.getD_playerName());
			l_country=	p_player.getD_Country();
		for(Country c:l_country)
		{
			System.out.println(c.getD_countryName());
		}
		
	}
	

}
