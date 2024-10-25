package pages;

import java.io.IOException;

import org.openqa.selenium.By;

import base.ProgramExecution;
import io.cucumber.java.en.When;

public class HomePage extends ProgramExecution{

	@When("Click create Lead")
	public CreateLeadPage createLeadLink() throws IOException {
		try {
			getDriver().findElement(By.linkText("Create Lead")).click();
			reportStatus("pass", "Clicked Create Lead Link Successfully");
		} catch (Exception e) {
			reportStatus("fail", "Not Clicked Create Lead Link");
		}
		return new CreateLeadPage();
	}
}
