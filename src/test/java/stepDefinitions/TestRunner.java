package stepDefinitions;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features/karros.feature", glue= {"stepDefinitions"}, 
monochrome=true,
plugin= {"pretty", "html:target/HtmlReports/htmlreport.html", "json:target/JsonReports/Jsonreport.json"})

public class TestRunner {
	
}
