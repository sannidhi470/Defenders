package state;

import Controllers.GameEngine;
import Models.Player;
import Tools.ColorCoding;
import Tools.Connectivity;
import Tools.PlayersGameplay;

public class PlaySetup extends Play {

	public PlaySetup(GameEngine p_ge) {
		super(p_ge);
	}

	public void setPlayers(String[] l_commands) {
		for(int i=1;i<l_commands.length;)
		{
			if(l_commands[i].equals("-add"))
			{
				Player l_player = new Player();
				l_player.setD_playerName(l_commands[i+1]);
				l_playersArray.add(l_player);
				//d_logEntryBuffer.log(l_player.getD_playerName() + "added successfully");
				System.out.println(ColorCoding.green+l_player.getD_playerName()+" added successfully"+ColorCoding.blank);
				i=i+2;
				
			}
			else if(l_commands[i].equals("-remove"))
			{
				for(int j=0;j<l_playersArray.size();j++)
				{
					if(l_commands[i+1].equals(l_playersArray.get(j).getD_playerName()))
					{
						//d_logEntryBuffer.log(l_playersArray.get(j).getD_playerName()+ "removed successfully");
						System.out.println(ColorCoding.green+l_playersArray.get(j).getD_playerName()+" removed successfully"+ColorCoding.blank);
						l_playersArray.remove(j);
						i=i+2;
						break;
					}
				}
			}
		}
		System.out.println("players have been set");
	}

	public boolean assignCountries(Connectivity l_connectivity) {
		if(l_playersArray.size()>0)
		{
			if(PlayersGameplay.assigncountries(l_playersArray,l_connectivity.getD_countryList(),l_connectivity.getD_continentList())==0)
			{
				//d_logEntryBuffer.log("Countries assigned to players Successfully");
				System.out.println(ColorCoding.green+"Countries assigned to players Successfully"+ColorCoding.blank+"\n");
			}
			else
			{
			return false;
			}
		}
		else
		{
			//d_logEntryBuffer.log("ERROR: No players to assign Countries");
			System.out.println(ColorCoding.red+"ERROR: No players to assign Countries"+ColorCoding.blank);
			return false;
		}
		System.out.println("countries have been assigned");
		for(Player p:l_playersArray)
		{
			PlayersGameplay.showPlayersCountry(p,1);
		}
		PlayersGameplay.assignArmiesToPlayers(l_playersArray);
		//d_logEntryBuffer.log("Game Begins");
		System.out.println("Game Begins!!!!!!!!!!!\n");
		for(int i=0;i<l_playersArray.size();i++)
		{
			//d_logEntryBuffer.log("Player "+ Integer.sum(i,1) +"("+l_playersArray.get(i).getD_playerName()+") has Army Count: "+l_playersArray.get(i).getD_armyCount());
			System.out.println("Player "+ Integer.sum(i,1) +"("+l_playersArray.get(i).getD_playerName()+") has Army Count: "+l_playersArray.get(i).getD_armyCount());
			PlayersGameplay.showPlayersCountry(l_playersArray.get(i),1);
			System.out.println();
		}
		return true;
	}

	public void reinforce(Connectivity l_connectivity) {
		printInvalidCommandMessage(); 
	}

	public void attack(Connectivity l_connectivity) {
		printInvalidCommandMessage(); 
	}

	public void fortify(Connectivity l_connectivity) {
		printInvalidCommandMessage(); 
	}

	public void endGame() {
		System.out.println("Thank you for Playing the Game");
		System.exit(0);
	}

	public void next() {
		ge.setPhase(new Reinforcement(ge));
	}

	@Override
	public void loadMap(Connectivity l_connectivity, String[] l_commands) {
		// TODO Auto-generated method stub
		printInvalidCommandMessage();
		
	}

	@Override
	public void validateMap(Connectivity l_connectivity) {
		// TODO Auto-generated method stub
		printInvalidCommandMessage();
	}

	@Override
	public void editCountry(String[] l_commands, Connectivity l_connectivity) {
		// TODO Auto-generated method stub
		printInvalidCommandMessage();
	}

	@Override
	public void editContinent(String[] l_commands, Connectivity l_connectivity) {
		// TODO Auto-generated method stub
		printInvalidCommandMessage();
	}

	@Override
	public void editNeighbor(String[] l_commands, Connectivity l_connectivity) {
		// TODO Auto-generated method stub
		printInvalidCommandMessage();
	}

	@Override
	public void saveMap(Connectivity l_connectivity, String p_mapName) {
		// TODO Auto-generated method stub
		printInvalidCommandMessage();
	}
}
