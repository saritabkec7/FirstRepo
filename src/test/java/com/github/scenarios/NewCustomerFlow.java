package com.github.scenarios;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.pageObjects.DeviceListPage;
import com.github.pageObjects.HomePage;
import com.github.utility.ReadExcelData;

public class NewCustomerFlow {
	HashMap<String, String> map;
	WebDriver wd;
    WebDriverWait wait;
	
    @BeforeMethod
	public void preTestConditions(Method meth ){
		map = new HashMap<>();
		String testMethod = meth.getName();
		System.out.println("Test Method To be run: " + testMethod);
		System.setProperty("webdriver.chrome.driver", "F:/selenium/exe/chromedriver.exe");
		wd = new ChromeDriver();
		wd.get("https://www.att.com/");
		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		map = ReadExcelData.readData(testMethod);
	}

	@Test(priority=0)
	public void SelectPostpaidPlan(){
		HomePage homePage = new HomePage(wd);
		homePage.clickOnCellPhones();
		
		DeviceListPage deviceLstPage = new DeviceListPage(wd);
		Assert.assertTrue(deviceLstPage.verifyDeviceListPage(), "device list page not found");
		Assert.assertTrue(deviceLstPage.selectDevice(), "device not selected");
		
		wait.until(ExpectedConditions.visibilityOf(wd.findElements(By.xpath("//a[@data-sku ='sku8510273']")).get(0)));
		wd.findElements(By.xpath("//a[@data-sku ='sku8510273']")).get(0).click();
		wd.findElement(By.xpath("//a[contains(text(), 'Check availability')]")).click();
		wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.id("favoriteStoreListZip"))));
		wd.findElement(By.id("favoriteStoreListZip")).sendKeys(map.get("ZIP_CODE"));
		wd.findElements(By.cssSelector("button.btn-small")).get(1).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Northpark Center Store']/../../../..//button")));
		wd.findElement(By.xpath("//*[text()='Northpark Center Store']/../../../..//button")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("navBarClick")));
		wd.findElement(By.id("navBarClick")).click();
		wd.findElement(By.id("continue")).click();
	
		System.out.println("service is activated");
		System.out.println(map.get("DEVICE_NAME"));
		System.out.println(map.get("ZIP_CODE"));
	}

	@Test(priority=1)
	public void SelectLongTermServices(){
		System.out.println("service will be available for 2 months");
		System.out.println(map.get("ZIP_CODE"));
	}
}
