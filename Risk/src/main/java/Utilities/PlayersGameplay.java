package Utilities;

import java.util.ArrayList;
import java.util.Scanner;

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
	

	
	public static ArrayList<String> showPlayersCountry(Player p_player,int p_flag)
	{
		ArrayList<Country> l_country = new ArrayList<>();
		if(p_flag==1)
			System.out.println("Player:" + p_player.getD_playerName());
			l_country=	p_player.getD_Country();
		ArrayList<String> l_countryList=new ArrayList<>();
		for(Country c:l_country)
		{
			if(p_flag==1)
				System.out.println(c.getD_countryName());
			l_countryList.add(c.getD_countryName());
		}
		return l_countryList;
	}
	
	public static ArrayList<String> issueOrder(ArrayList<Player> p_player)
	{
		Scanner l_input=new Scanner(System.in);
		ArrayList<String> l_orders=new ArrayList<>();
		ArrayList<Integer> l_armiesOfPlayers=new ArrayList<>();
		for(int i=0;i<p_player.size();i++)
			l_armiesOfPlayers.add(p_player.get(i).getD_armyCount());
		System.out.println(l_armiesOfPlayers);
		int l_temp=1;
		ArrayList<String> l_playerOrders=new ArrayList<>();
		while(l_temp>0)
		{
			
			int l_flag=0;
			for(int i=0;i<l_armiesOfPlayers.size();i++)
			{
				if(l_armiesOfPlayers.get(i)==0)
					l_flag++;
			}
			System.out.println("l_flag:"+l_flag+" "+l_armiesOfPlayers);
			if(l_flag==l_armiesOfPlayers.size())
			{
				
				break;
			}
			for(int i=0;i<p_player.size();i++)
			{
				String l_tempOrderList="";
				if(l_armiesOfPlayers.get(i)==0)
					continue;
				System.out.println("Player "+p_player.get(i).getD_playerName()+" deploy your troops");
				l_tempOrderList=l_input.nextLine();
				l_playerOrders.add(l_tempOrderList);
				//String[] l_tempOrderListArray=l_tempOrderList.split(" ");
			//	ArrayList<Integer> l_storeCountryId=new ArrayList<>();
		//		for(int j=0;j<p_player.get(i).getD_Country().size();j++)
		//			l_storeCountryId.add(p_player.get(i).getD_Country().get(j).getD_countryId());
//				System.out.println(l_storeCountryId);
//				
//				if(l_tempOrderListArray[0].equals("deploy"))
//				{
//					
//					if(l_storeCountryId.contains(Integer.parseInt(l_tempOrderListArray[1])))
//					{
//						System.out.println("yes");
//						System.out.println(l_armiesOfPlayers.get(i)-Integer.parseInt(l_tempOrderListArray[2]));
//						l_armiesOfPlayers.set(i, l_armiesOfPlayers.get(i)-Integer.parseInt(l_tempOrderListArray[2]));
//					}
//					else
//						System.out.println("Country"+l_tempOrderListArray[1]+" has not been assigned to Player: "+p_player.get(i).getD_playerName());
//					System.out.println("list:"+l_armiesOfPlayers);
//				}
//				else
//					System.out.println("Please enter a valid command");
			}
		//	return l_playerOrders;
				
		}
		return l_playerOrders;
			
		
	}
}
