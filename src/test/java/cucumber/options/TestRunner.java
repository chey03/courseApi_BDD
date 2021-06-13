package cucumber.options;


import io.cucumber.junit.Cucumber;
//import cucumber.junit.Cucumber.Options;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
//import cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features/", glue ={"stepDefination"})
//@Cucumber.Options(format = {"pretty", "html:target/cucumber"})


public class TestRunner {

}
