package Tools;

import java.util.LinkedList;

public class Graph extends validateGraph{
	 
	
	public Graph(int d_noOfCountries,Connectivity p_connectivity)
	{
		this.d_noOfCountries=d_noOfCountries;
		this.d_adjacentCountriesList=new LinkedList[d_noOfCountries+1];
		for(int i=1;i<=d_noOfCountries;i++)
		{
			d_adjacentCountriesList[i]=new LinkedList();
		}
		for(int i=0;i<p_connectivity.getD_countryList().size();i++)
		{
			for(int j=0;j<p_connectivity.getD_countryList().get(i).getD_neighbours().size();j++)
				this.addCountries(p_connectivity.getD_countryList().get(i).getD_countryId(), p_connectivity.getD_countryList().get(i).getD_neighbours().get(j));
		}

	}
	public void addCountries(int d_noOfCountries,int p_countryId)
	{
		d_adjacentCountriesList[d_noOfCountries].add(p_countryId);
		
	}
	
}