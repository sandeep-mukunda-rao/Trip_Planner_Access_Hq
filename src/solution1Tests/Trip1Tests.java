package solution1Tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
/**
 * 
 * @author Sandeep Mukunda Rao
 * This is the tests for solution 1
 */
public class Trip1Tests {
public WebDriver driver;


//This is the test to run solution 2
@Test(dataProvider="testdata")

public void Trip1Test(String From,String To) throws Exception {
	System.setProperty("webdriver.chrome.driver", "/Users/sandeeprao/Documents/selenium/chromedriver");
	driver = new ChromeDriver();
	//open the url
	driver.get("https://transportnsw.info/trip#/");
	
	//will read from station value from excel
	driver.findElement(By.name("search-input-From")).sendKeys(From);
	Thread.sleep(5000);
	
	//select the location
    driver.findElement(By.xpath("//*[@id=\"search-form-scrollable\"]/past-searches-container/div")).click();
	
	//will read to station value from excel
	driver.findElement(By.name("search-input-To")).sendKeys(To);
	Thread.sleep(5000);
	
	//select the location
	driver.findElement(By.xpath("//*[@id=\"search-form-scrollable\"]/past-searches-container/div")).click();
	
	//search the routes
	driver.findElement(By.id("search-button")).click();;
	Thread.sleep(2000);
	this.takeSnapShot(driver, "/Users/sandeeprao/Documents/solution1.png");
}
	

//close the browser

@AfterTest

public void CloseBrowse() {
	
	driver.close();;
}

//this is the function to read excel file
@DataProvider(name="testdata")
	public Object [][] readExcel() throws BiffException, IOException {
	
	//excel file path needs to be changes for mac or windows environment
		File f = new File("/Users/sandeeprao/Documents/Trip_Locations.xls");
		Workbook w = Workbook.getWorkbook(f);
		Sheet s = w.getSheet("Sheet1");
		int rows = s.getRows();
		int columns=s.getColumns();
		 
		String inputData [] [] = new String [rows] [columns];
		for (int i=0; i<rows; i++) {
		for (int j=0;j<columns;j++) {
			Cell c = s.getCell(j,i);
			inputData [i][j] =c.getContents();
			System.out.println(inputData[i][j]);
		}
		}
		return inputData;

	}

/*function to capture screenshot*/
public  void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

    //Convert web driver object to TakeScreenshot

    TakesScreenshot scrShot =((TakesScreenshot)webdriver);

    //Call getScreenshotAs method to create image file

            File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination

            File DestFile=new File(fileWithPath);

            //Copy file at destination

            FileUtils.copyFile(SrcFile, DestFile);

}

}

