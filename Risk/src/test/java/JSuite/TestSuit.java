package JSuite;


import org.junit.platform.suite.api.SelectClasses;

import Models.CountryTest;
import Tools.MapEditorTest;
import Tools.MapLoaderTest;
import Tools.PlayersGameplayTest;
@org.junit.platform.suite.api.Suite
@SelectClasses(
		{MapEditorTest.class, MapLoaderTest.class, PlayersGameplayTest.class,CountryTest.class
})

public class TestSuit {

}
