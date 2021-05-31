package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.TestNGCucumberRunner;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/java/featureFiles/facebook.feature",
        glue={"stepDefination"},
        plugin={"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:target/html/ExtentReport.html"}
)
public class testRunner{

    private TestNGCucumberRunner testNGCucumberRunner;
}