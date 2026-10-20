package com.rahushettyacademy.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.rahulshettyacademy.frameworkconcepts.LandingPage;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {

		// properties class can read .properties extension files and get the key value
		// pairs to be set
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\rahulshettyacademy\\resources\\GlobalData.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser")!=null? System.getProperty("browser"):prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions options=new ChromeOptions();
			if(browserName.contains("headless")) {
				options.addArguments("headless");
				options.addArguments("--window-size=1440,900");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}

	public List<HashMap<String, String>> getJsonDatatoMap(String filepath) throws IOException {

		// read json to string
		String jsoncontent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

		// convert string to hashmap- ise jackson databind utility dependency
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsoncontent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	public String getScreenshot(String testcasename,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File(System.getProperty("user.dir") + "//reports//" + testcasename + ".png"));
		return System.getProperty("user.dir") + "//reports//" + testcasename + ".png";
	}

	// common step is to launch page for all tests
	// so we put it in a method
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		// login to web url
		landingPage.goTo();
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		// close the driver
		driver.close();
	}
}
