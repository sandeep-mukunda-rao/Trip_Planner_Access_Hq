package com.accesshq.tripplanner.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 
 * @author Sandeep Mukunda Rao This class is to find all the elements of Trip
 *         Planning p
 */

public class TripPlan {

	WebDriver driver;

	By FromStation = By.name("search-input-From");
	By ToStation = By.name("search-input-To");
	By SelectLocation = By.xpath("//*[@id=\"search-form-scrollable\"]/past-searches-container/div");
	By GoButton = By.id("search-button");

	public TripPlan(WebDriver driver1) {
		this.driver = driver1;
	}

	public void Search() {
		driver.findElement(GoButton).click();
	}

	public void FromStation(String From) {
		driver.findElement(FromStation).sendKeys(From);
	}

	public void ToStation(String To) {
		driver.findElement(ToStation).sendKeys(To);
	}

	public void SelectLoc() {
		driver.findElement(SelectLocation).click();

	}
}
