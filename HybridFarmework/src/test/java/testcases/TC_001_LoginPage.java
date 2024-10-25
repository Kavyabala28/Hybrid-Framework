package testcases;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProgramExecution;
import pages.LoginPage;

public class TC_001_LoginPage extends ProgramExecution {

	@Test
	public void runLoginPage() throws IOException {
		LoginPage lb = new LoginPage();
		lb.userName().password().login();
	}
	
	@BeforeTest
	public void setData() {
		testCaseName = "Login Application";
		testCaseDescription = "Login an application using credentials";
		authorName = "Barath";
		categoryName = "Smoke";
	}
}

