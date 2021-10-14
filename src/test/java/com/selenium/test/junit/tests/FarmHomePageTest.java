package com.selenium.test.junit.tests;

import com.selenium.test.pages.FarmHomePage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

public class FarmHomePageTest extends BaseTest {
    FarmHomePage objFarmHomePage;
    WebDriver driver;
    String exceptedHomePageTitle = "FarmTogether - Invest in US Farmland";

//    @Rule
//   public ScreenShotOnFailRule screenShotOnFailRule = new ScreenShotOnFailRule();

    @Before
    public void beforeTest() {
        //Set the key/value property according to the browser you are using.
        System.setProperty("webdriver.chrome.driver", "/home/mko/Загрузки/Selenium/WebDrivers/ChromeDriverSelenium/chromedriver");

        //Open browser instance
        driver = new ChromeDriver();

        //Maximize Window
        driver.manage().window().maximize();

        //Open the AUT
        openHomePageBase(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @Test
    public void testHomePage() {
        String currentTitle = driver.getTitle();
        assertEquals(exceptedHomePageTitle, currentTitle);
    }


//    @After
//    public void afterTest() {
//        WebDriverFactory.finishBrowser();
//    }
}
