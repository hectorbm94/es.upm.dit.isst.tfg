package es.upm.dit.isst.t4.dao;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class FlujoWeb {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://1-dot-decent-tape-127214.appspot.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testFlujoWeb() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Es_upm_dit_isst_tfg")).click();
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("choose-account-0")).click();
    driver.findElement(By.id("Passwd")).clear();
    driver.findElement(By.id("Passwd")).sendKeys("contraseña");
    driver.findElement(By.id("signIn")).click();
    driver.findElement(By.id("titulo")).clear();
    driver.findElement(By.id("titulo")).sendKeys("prueba");
    driver.findElement(By.id("resumen")).clear();
    driver.findElement(By.id("resumen")).sendKeys("prueba");
    driver.findElement(By.id("tutor")).clear();
    driver.findElement(By.id("tutor")).sendKeys("hectorbm94@gmail.com");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    driver.findElement(By.linkText("Logout")).click();
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("choose-account-1")).click();
    driver.findElement(By.id("Passwd")).clear();
    driver.findElement(By.id("Passwd")).sendKeys("contraseña");
    driver.findElement(By.id("signIn")).click();
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    driver.findElement(By.linkText("Logout")).click();
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("choose-account-0")).click();
    driver.findElement(By.id("Passwd")).clear();
    driver.findElement(By.id("Passwd")).sendKeys("contraseña");
    driver.findElement(By.id("signIn")).click();
    driver.findElement(By.linkText("Logout")).click();
  }

  @After
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
