package Utilities;

import java.util.ArrayList;

import Models.Country;
import Models.Player;

public class PlayersGameplay {
	
	public static void assigncountries(ArrayList<Player> p_playersArray,ArrayList<Country> p_countryList) 
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
		
	}
	

}
