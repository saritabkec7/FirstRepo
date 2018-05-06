package com.github.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeviceListPage {
	WebDriver wd;
	WebDriverWait wait;
	
	public DeviceListPage(WebDriver wdr){
		wd = wdr;
		wait = new WebDriverWait(wd, 30);
	}

	public boolean verifyDeviceListPage(){
		WebElement we = wd.findElement(By.xpath("//h1/div[contains(text(),'Cellphones & Smartphones')]"));
		if(we.isDisplayed()){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @Desc : To select a device from device list page
	 * @return boolean -> true/false
	 */
	public boolean selectDevice(){
		wait.until(ExpectedConditions.visibilityOf(wd.findElements(By.xpath("//a[@data-sku ='sku8510273']")).get(0)));
		wd.findElements(By.xpath("//a[@data-sku ='sku8510273']")).get(0).click();
		return true;
	}

}
