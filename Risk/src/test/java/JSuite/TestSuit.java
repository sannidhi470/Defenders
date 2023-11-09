package JSuite;


import org.junit.platform.suite.api.SelectClasses;

import Tools.MapEditorTest;
import Tools.MapLoaderTest;
import Tools.PlayersGameplayTest;
@org.junit.platform.suite.api.Suite
@SelectClasses(
		{MapEditorTest.class, MapLoaderTest.class, PlayersGameplayTest.class
})

public class TestSuit {

}
