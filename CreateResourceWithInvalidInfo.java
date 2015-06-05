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

public class CreateResourceWithInvalidInfo {
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

  @Test(priority= 2)
  public void testCreateResourceWithInvalidInfo() throws Exception {
    driver.get(baseUrl + "/admin/#/login");
    driver.manage().window().maximize() ;
    driver.findElement(By.xpath("//button")).click();
    driver.findElement(By.linkText("Resources")).click();
    driver.findElement(By.xpath("//div/div/button")).click();
    //Wait until the create resource form appears
    (new WebDriverWait(driver, 60))
    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.info")));
    
    driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("Monitor-01");
    driver.findElement(By.cssSelector("button.info")).click();
    //Wait until the MESSAGE appears
    (new WebDriverWait(driver, 60))
    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//small[4]")));
    
    assertEquals("Invalid name, A-Z, a-z, 0-9 and \"_\" are the allowed characters only", driver.findElement(By.xpath("//small[4]")).getText());
  }

  @AfterTest
  public void tearDown() throws Exception {
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
