package runner;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/features/SearchTest.feature"},glue = {"stepDefinitions"},format={"pretty"})
public class TestRunner {

}
