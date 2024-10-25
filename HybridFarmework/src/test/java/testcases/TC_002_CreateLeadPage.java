package testcases;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProgramExecution;
import pages.LoginPage;

public class TC_002_CreateLeadPage extends ProgramExecution {

	@Test(dataProvider = "data")
	public void runCreateLeadPage(String cname, String lname, String fname) throws IOException {

		LoginPage lp = new LoginPage();
		lp.userName().password().login().crmsfa().createLeadLink().enterCompanyName(cname).enterFirstName(fname).enterLastName(lname)
				.clickCreateLeadBtn();
	}
	
	@BeforeTest
	public void setData() {
		testCaseName = "Create Lead";
		testCaseDescription = "Creating Lead using excel values";
		authorName = "Kavya";
		categoryName = "Regression";
		excelName = "CreateLeadData";
	}

}
