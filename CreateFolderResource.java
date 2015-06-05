package org.automation.test;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateFolderResource {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeTest
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://172.20.208.174:4044/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test(priority= 1)
  public void testCreateFolderResource() throws Exception {
    driver.get(baseUrl + "/admin/#/login");
    driver.manage().window().maximize() ;
    driver.findElement(By.xpath("//button")).click();
    driver.findElement(By.linkText("Resources")).click();
    driver.findElement(By.xpath("//div/div/button")).click();
    //Wait until the create resource form appears
    (new WebDriverWait(driver, 60))
    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.info")));
    
    driver.findElement(By.id("convert")).click();
    driver.findElement(By.xpath("//button[@value='fa-folder']")).click();
    driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("Folder");
    driver.findElement(By.xpath("(//input[@type='text'])[4]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys("Folder");
    driver.findElement(By.cssSelector("button.info")).click();
    //Wait until the create resource form disappears
    (new WebDriverWait(driver, 60))
    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div/button")));
    
    driver.findElement(By.xpath("//input[@type='text']")).clear();
    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Folder");
    //Wait until the resource appears on the grid resource
    (new WebDriverWait(driver, 60))
    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.ng-scope > span.ng-binding")));
    assertEquals("Folder", driver.findElement(By.cssSelector("div.ng-scope > span.ng-binding")).getText());
  }

  @AfterTest
  public void tearDown() throws Exception {
	    /*FILTER THE RESOURCE*/
	    driver.findElement(By.xpath("//input[@type='text']")).clear();
	    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Folder");
	    //Wait until the resource appears on the grid resource
	    (new WebDriverWait(driver, 60))
	    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.ng-scope > span.ng-binding")));
	    /*REMOVE THE RESOURCE*/
	    driver.findElement(By.cssSelector("input.ngSelectionCheckbox")).click();
	    //Wait until the remove button is available
	    (new WebDriverWait(driver, 60))
	    .until(ExpectedConditions.elementToBeClickable(By.id("btnRemove")));
	    driver.findElement(By.id("btnRemove")).click();
	    driver.findElement(By.cssSelector("button.info")).click();
		driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
