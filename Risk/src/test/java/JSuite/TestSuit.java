package JSuite;


import org.junit.platform.suite.api.SelectClasses;

import Models.CountryTest;
import Strategy.AggressivePlayerStrategyTest;
import Strategy.BenevolentPlayerStrategyTest;
import Tools.MapEditorTest;
import Tools.MapLoaderTest;
import Tools.PlayersGameplayTest;
import state.phaseValidation;
import state.tournamentValidation;
@org.junit.platform.suite.api.Suite
@SelectClasses(
		{MapEditorTest.class, MapLoaderTest.class, PlayersGameplayTest.class,CountryTest.class, AggressivePlayerStrategyTest.class,BenevolentPlayerStrategyTest.class, phaseValidation.class, tournamentValidation.class
})

public class TestSuit {

}
