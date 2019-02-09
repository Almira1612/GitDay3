package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Logintests {
    WebDriver driver;
    Map<String,String> myMap = new HashMap<String,String>();

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void test1(){
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test"+ Keys.ENTER);

        Assert.assertEquals(driver.getTitle(),"Web Orders");
    }
    @Test
    public void negativeTest(){

            driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");
            driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester2");
            driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test2"+ Keys.ENTER);
            String errorMsg = driver.findElement(By.id("ctl00_MainContent_status")).getText();

            Assert.assertEquals(errorMsg, "Invalid Login or Password.");
    }
    @Test
    public void test2(){
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test"+ Keys.ENTER);

        driver.findElement(By.id("ctl00_logout")).click();
        Assert.assertEquals(driver.getTitle(),"Web Orders Login");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
