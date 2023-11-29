package state;

import org.junit.platform.suite.api.SelectClasses;


@org.junit.platform.suite.api.Suite
@SelectClasses(
		{phaseValidation.class,
		tournamentValidation.class
})

public class JunitTestState {

}
