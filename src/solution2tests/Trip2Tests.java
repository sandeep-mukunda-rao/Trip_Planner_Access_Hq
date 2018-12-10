package solution2tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import com.accesshq.tripplanner.webpages.*;

/**
 * 
 * @author Sandeep Mukunda Rao This is the tests for solution 2
 */
public class Trip2Tests {
	WebDriver driver;

	@BeforeTest

	/*
	 * This function is to invoke browser , please change drivers according to
	 * windows /mac env
	 */
	public void invokeBrowser() {
		try {
			System.setProperty("webdriver.chrome.driver", "/Users/sandeeprao/Documents/selenium/chromedriver");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
			driver.get("https://transportnsw.info/trip#/");
			String url = driver.getCurrentUrl();
			Assert.assertEquals(url, "https://transportnsw.info/trip#/");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// This is the test to run solution 2
	@Test
	public void TripPlan1() throws InterruptedException {

		TripPlan trip = new TripPlan(driver);

		// Enter From station
		trip.FromStation("North Sydney Station");
		// Select From Station
		trip.SelectLoc();
		// Enter To station
		trip.ToStation("Town Hall Station");
		Thread.sleep(2000);
		// Select To Station
		trip.SelectLoc();
		// Search the routes
		trip.Search();
		Thread.sleep(2000);

		try {
			/* change the screenshot path for mac/windows */
			this.takeSnapShot(driver, "/Users/sandeeprao/Documents/solution2.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* function to capture screenshot */

	public void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {

		// Convert web driver object to TakeScreenshot

		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

		// Call getScreenshotAs method to create image file

		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		// Move image file to new destination

		File DestFile = new File(fileWithPath);

		// Copy file at destination

		FileUtils.copyFile(SrcFile, DestFile);

	}

	@AfterTest

	/* This function is to close the Browser */
	public void closeBrowser() {

		driver.close();
	}
}
