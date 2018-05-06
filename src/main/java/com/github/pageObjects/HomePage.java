package com.github.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	WebDriver wd;

	public HomePage(WebDriver wdr){
		wd = wdr;
	}
	
	public void clickOnCellPhones(){
		WebElement we = wd.findElements(By.xpath("//h4[text()='Cell phones']")).get(0);
		we.click();
	}

}
