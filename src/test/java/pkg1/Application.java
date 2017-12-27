package pkg1;

import org.junit.ClassRule;
import org.junit.rules.TestRule;
import org.junit.runners.model.Statement;

public class Application {
	@ClassRule
	public static TestRule dbRule = (statement, description) -> new Statement() {
		@Override
		public void evaluate() throws Throwable {
			System.out.println("Run evalute");
			TestSupport.setupWebDriver();
			statement.evaluate();
		}
	};
}
