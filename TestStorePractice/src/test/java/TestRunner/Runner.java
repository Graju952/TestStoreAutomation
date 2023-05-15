package TestRunner;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features={"src/test/java/Features"},
        glue= {"StepDefinitions"},
        plugin={"html:Reports/CucumberReport.html","junit:Reports/CucumberReport.xml","json:Reports/CucumberReport.json", "pretty", "html:target/cucumber-reports", "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm"}
)
public class Runner {

}
