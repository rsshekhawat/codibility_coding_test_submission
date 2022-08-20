package Test_Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/Features/Task.feature",
        glue= {"Step_Defs"},
        plugin = { "pretty", "html:target/reports" },
        monochrome = true
)

public class runner extends AbstractTestNGCucumberTests {

}
