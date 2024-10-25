package pages;

import java.io.IOException;

import org.openqa.selenium.By;

import base.ProgramExecution;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class CreateLeadPage extends ProgramExecution{

	@Given("Enter companyname (.*)$")
	public CreateLeadPage enterCompanyName(String cname) throws IOException {
		try {
			getDriver().findElement(By.id("createLeadForm_companyName")).sendKeys(cname);
			reportStatus("pass", "Company Name entered successfully");
		} catch (Exception e) {
			reportStatus("fail", "Company Name was not entered");
		}
		return this;
	}
	
	@Given("Enter firstname (.*)$")
	public CreateLeadPage enterFirstName(String fname) throws IOException {
		try {
			getDriver().findElement(By.id("createLeadForm_firstName")).sendKeys(fname);
			reportStatus("pass", "First Name entered successfully");
		} catch (Exception e) {
			reportStatus("fail", "First Name was not entered");
		}
		return this;
	}
	
	@Given("Enter lastname (.*)$")
	public CreateLeadPage enterLastName(String lname) throws IOException {
		try {
			getDriver().findElement(By.id("createLeadForm_lastName")).sendKeys(lname);
			reportStatus("pass", "Last Name entered successfully");
		} catch (Exception e) {
			reportStatus("fail", "Last Name was not entered");
		}
		return this;
	}
	
	@When("Click submit")
	public HomePage clickCreateLeadBtn() throws IOException {
		try {
			getDriver().findElement(By.className("smallSubmit")).click();
			reportStatus("pass", "Clicked Create Lead Button");
		} catch (Exception e) {
			reportStatus("fail", "Not Click Create Lead button");
		}
		return new HomePage();
	}

	
}