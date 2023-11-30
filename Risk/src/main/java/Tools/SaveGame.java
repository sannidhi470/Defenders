package Tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Controllers.GameEngine;
import Models.Country;
import Models.Map;
import Models.Player;
import Strategy.AggressivePlayerStrategy;
import Strategy.BenevolentPlayerStrategy;
import Strategy.CheaterPlayerStrategy;
import Strategy.HumanPlayerStrategy;
import Strategy.RandomPlayerStrategy;
import state.Phase;
import state.Play;
import state.PlaySetup;

/**
 * The SaveGame class has the functionality to save the current game state to a file.
 */
public class SaveGame 
{
    /**
     * Saves the current game state to a file.
     *
     * @param gamePhase   The current phase of the game.
     * @param connectivity Connectivity object
     * @param p_file_name The name of the file to save the state
     * @throws IOException To handle I/O error
     */
	public boolean saveGame(Phase gamePhase, Connectivity connectivity,String p_file_name) throws IOException
	{

	ArrayList<String> l_mapData=new ArrayList<String>();
	GameEngine ge = new GameEngine();
	l_mapData.add("[map]"+"\n");
	String map_name = connectivity.getD_mapName();
	l_mapData.add(map_name+"\n");
	l_mapData.add("\n");
	l_mapData.add("[Continents]"+"\n");
	for(int i=0;i<connectivity.getD_continentList().size();i++)
	{
		l_mapData.add(connectivity.getD_continentList().get(i).getD_continentId()+" "+connectivity.getD_continentList().get(i).getD_continentName()+"\n");
	}
	l_mapData.add("\n");
	l_mapData.add("[Countries]"+"\n");

	for(int i=0;i<connectivity.getD_countryList().size();i++)
	{
		l_mapData.add(connectivity.getD_countryList().get(i).getD_countryName()+" "+connectivity.getD_countryList().get(i).getD_countryId()+" "+connectivity.getD_countryList().get(i).getD_continentId()+" "+connectivity.getD_countryList().get(i).getD_armyDeployedOnCountry()+"\n");
	}
	l_mapData.add("\n");
	l_mapData.add("[Players]"+"\n");
	//PlaySetup play=new PlaySetup(ge);
	for(int i=0;i<Play.l_playersArray.size();i++)
	{
		l_mapData.add(Play.getL_playersArray().get(i).getD_playerId()+" "+Play.getL_playersArray().get(i).getD_playerName()+"\n");
		l_mapData.add("Player Countries:"+"\n");
		for(int j=0;j<Play.getL_playersArray().get(i).getD_Country().size();j++)
		{
		l_mapData.add(Play.getL_playersArray().get(i).getD_Country().get(j).getD_countryName()+" ");
		}
		l_mapData.add("\n");
		l_mapData.add("Cards: "+Play.getL_playersArray().get(i).getCards());
		l_mapData.add("\n");
		l_mapData.add("Armies: "+Play.getL_playersArray().get(i).getD_armyCount()+"\n");
		//l_mapData.add("\n");
		if(Play.l_playersArray.get(i).getStrategy() instanceof HumanPlayerStrategy)
		{
			l_mapData.add("Behaviour: Human"+"\n");
		}
		else if(Play.l_playersArray.get(i).getStrategy() instanceof CheaterPlayerStrategy)
		{
			l_mapData.add("Behaviour: Cheater"+"\n");
		}
		else if (Play.l_playersArray.get(i).getStrategy() instanceof BenevolentPlayerStrategy)
		{
			l_mapData.add("Behaviour: Benevolent"+"\n");
		}
		else if (Play.l_playersArray.get(i).getStrategy() instanceof AggressivePlayerStrategy)
		{
			l_mapData.add("Behaviour: Aggressive"+"\n");
		}
		else if (Play.l_playersArray.get(i).getStrategy() instanceof RandomPlayerStrategy)
		{
			l_mapData.add("Behaviour: Random"+"\n");
		}
		l_mapData.add("\n");
	}
	//l_mapData.add("\n");
	l_mapData.add("[phase]"+"\n");
	String phase_name = gamePhase.getClass().getSimpleName();
	l_mapData.add(phase_name+"\n");
	l_mapData.add("\n");
//	System.out.println(l_mapData);
	System.out.println(connectivity.getD_FilepathName());
	File f = new File("");
	String absolute = f.getAbsolutePath();
    connectivity.setD_pathName(absolute+ File.separator+"src/main/resources");
    String l_fileName = absolute + File.separator + "src/main/resources" + File.separator + p_file_name+".game";
	FileWriter l_input=new FileWriter(l_fileName);
	for(String lines:l_mapData)
	{
		//System.out.println(lines);
		l_input.write(lines);	
	}
	l_input.close();	
	System.out.println("Game file has been successfully saved");
	return true;
	}
}
