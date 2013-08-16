package co.edu.eafit.tvl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
		TVLTransformerExpressionToGNUPrologTest.class,
		TVLTransformerTest.class,
		GNUPrologVariablesTransformerTest.class,
		TVLToGNUPrologTransformerTest.class })
public class AllTests {

}
