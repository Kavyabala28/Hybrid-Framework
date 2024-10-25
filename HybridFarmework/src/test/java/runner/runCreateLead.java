package runner;

import org.testng.annotations.BeforeTest;

import base.ProgramExecution;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/feature/createLead.feature", glue = "pages", publish = true, monochrome = true)
public class runCreateLead extends ProgramExecution {

	@BeforeTest
	public void sendData() {
		testCaseName = "Create Lead";
		testCaseDescription = "Creating Lead";
		authorName = "Kavya";
		categoryName = "Regression";
		excelName = "CreateLeadData";
	}
}
