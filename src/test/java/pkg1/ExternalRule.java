package pkg1;

import org.junit.rules.ExternalResource;

public class ExternalRule extends ExternalResource {
    TestDescription testDescription;

    ExternalRule(TestDescription description) {
        this.testDescription = description;
    }

    @Override
    protected void before() throws Throwable {
        System.out.println("ExternalRule before");
    }

    @Override
    protected void after() {
        System.out.println("ExternalRule after");
    }
}