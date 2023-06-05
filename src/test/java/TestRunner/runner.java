package TestRunner;

//import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//import org.junit.runner.RunWith;

//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(
       features={"src/test/java/Features/register.feature"},
        glue= {"StepDefinitions"},
       /// plugin={"html:Reports/CucumberReport.html","junit:Reports/CucumberReport.xml","json:Reports/CucumberReport.json", "pretty", "html:target/cucumber-reports", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
        		plugin= {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)
public class runner extends AbstractTestNGCucumberTests  {

}