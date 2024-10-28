package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import utils.DataLibrary;

public class ProgramExecution extends AbstractTestNGCucumberTests {

	private static final ThreadLocal<RemoteWebDriver> rd = new ThreadLocal<RemoteWebDriver>();
	private static final ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	private static final ThreadLocal<String> testName = new ThreadLocal<String>();
	private static final ThreadLocal<ExtentTest> node = new ThreadLocal<ExtentTest>();
	private static final ThreadLocal<Properties> prop = new ThreadLocal<Properties>();

	public ExtentReports extent;
	// public static ExtentTest node;
	public String testCaseName, testCaseDescription, authorName, categoryName, excelName;

	public RemoteWebDriver getDriver() {
		return rd.get();
	}

	public void setDriver() {
		rd.set(new ChromeDriver());
	}

	@BeforeSuite
	public void startReport() {
		ExtentHtmlReporter report = new ExtentHtmlReporter("./report/result.html");
		report.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(report);
	}

	@BeforeClass
	public void setReport() {
		ExtentTest test = extent.createTest(testCaseName, testCaseDescription);
		test.assignAuthor(authorName);
		test.assignCategory(categoryName);

		parentTest.set(test);
		testName.set(testCaseName);
		/*
		 * Node helps to create sub folders for a single testcase node =
		 * test.createNode(testCaseName);
		 */
	}

	public ExtentTest getTest() {
		return parentTest.get();
	}

	public String getTestName() {
		return testName.get();
	}

	public void setNode() {
		ExtentTest childNode = getTest().createNode(getTestName());
		node.set(childNode);
	}

	public ExtentTest getNode() {
		return node.get();
	}

	public int takeSnap() throws IOException {
		int random = (int) ((Math.random()) * 99999);
		File src = getDriver().getScreenshotAs(OutputType.FILE);
		File desc = new File("./Snaps/img" + random + ".png");
		FileUtils.copyFile(src, desc);
		return random;
	}

	public void reportStatus(String status, String description) throws IOException {
		if (status.equalsIgnoreCase("pass")) {
			getNode().pass(description,
					MediaEntityBuilder.createScreenCaptureFromPath(".././Snaps/img" + takeSnap() + ".png").build());
		} else if (status.equalsIgnoreCase("fail")) {
			getNode().fail(description,
					MediaEntityBuilder.createScreenCaptureFromPath(".././Snaps/img" + takeSnap() + ".png").build());
		}
	}

	public Properties getProp() {
		return prop.get();
	}

	public void setProp() {
		prop.set(new Properties());
	}

	@Parameters({"propName"})
	@BeforeMethod
	public void preCondition(String propName) throws IOException {
		setNode();
		setProp();
		
		FileInputStream fis = new FileInputStream("src/main/resources/" + propName + ".properties");
		getProp().load(fis);

		setDriver();
		getDriver().get("http://leaftaps.com/opentaps/");
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@AfterMethod
	public void postCondition() {
		getDriver().close();
	}

	@AfterSuite
	public void endReport() {
		extent.flush();
	}

	@DataProvider(name = "data", indices = { 0, 1 })
	public String[][] excelData() throws IOException {
		return DataLibrary.readExcel(excelName);
	}
}
