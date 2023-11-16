package Strategy;

import Models.Country;
import Models.Order;
import Models.Player;
import Tools.Connectivity;

public class AggressivePlayerStrategy extends PlayerStrategy{

	AggressivePlayerStrategy(Player p_player, Connectivity p_connectivity) {
		super(p_player, p_connectivity);
	}

	@Override
	protected Country toAttack() {
		Country d_StrongestCountry = toMoveFrom();
		if(d_StrongestCountry!=null) {
			int l_neighborCount = d_StrongestCountry.getD_neighbours().size();
            for (int i=0;i<l_neighborCount-1;i++) {
            	//Country l_neighborCountry = d_StrongestCountry.getD_neighbours().get(i);
            	Country l_neighborCountry = null;
	                if (!d_player.getD_Country().contains(l_neighborCountry)) {
	                    Country d_OpponentCountry = l_neighborCountry;
	                    return d_OpponentCountry;
	                }
                
            }
        }
		return null;
	}

	@Override
	protected Country toAttackFrom() {
		return null;
	}

	@Override
	protected Country toMoveFrom() {
		int l_maxArmies = 0;
		Country d_StrongestCountry = null;
        for(Country l_country : d_player.getD_Country()) {
            int l_numArmies = l_country.getD_armyDeployedOnCountry();
            if( l_numArmies > l_maxArmies) {
                l_maxArmies = l_numArmies;
                d_StrongestCountry = l_country;
            }
        }
        if(l_maxArmies == 0) {
            d_StrongestCountry = d_player.getD_Country().get(0);
        }
        
		return d_StrongestCountry;
	}

	@Override
	protected Country toDefend() {
		//null
		return null;
	} 
	
	@Override
	public Order createOrder() {
		
		return null;
	}

}