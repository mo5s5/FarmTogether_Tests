package com.selenium.test.junit.tests;

/**
        * Created by Movses.
        * Uses Junit4 test framework
        */
import com.selenium.test.pages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class FarmLoginPageTest extends BaseTest {
    FarmHomePage objFarmHomePage;
    FarmLoginPage objFarmLoginPage;
    FarmProfilePage objFarmProfilePage;
    FarmForgotPasswordPage objFarmForgotPasswordPage;
    WebDriver driver;

    String exceptedLoggedInUrl = "https://farmtogether.com/offerings";


    @Before
    public void beforeTest() throws InterruptedException {
        //Set the key/value property according to the browser you are using.
        System.setProperty("webdriver.chrome.driver", "/home/mko/Загрузки/Selenium/WebDrivers/ChromeDriverSelenium/chromedriver");

        //Open browser instance
        driver = new ChromeDriver();

        //Maximize Window
        driver.manage().window().maximize();

        //Open the AUT
        openHomePageBase(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        objFarmLoginPage = new FarmLoginPage(driver);
        moveToLoginPage(driver);
        Thread.sleep(1000);         //other wait methods doesn't help here for waiting to change the url after click
        assertEquals(objFarmLoginPage.getExceptedUrl(), driver.getCurrentUrl());
        assertTrue(objFarmLoginPage.getTbEmail().isDisplayed());
        assertTrue(objFarmLoginPage.getTbPassword().isDisplayed());
        System.out.println("Login Page is Opened");
    }


    @Test
    public void testPasswordFieldType() {
        assertEquals(objFarmLoginPage.getExceptedPasswordType(), objFarmLoginPage.getTbPassword().getAttribute("type"));
        System.out.println("Password field is masked");
    }


    @Test
    public void testWithPasswordContainsSpace() throws InterruptedException {
        objFarmLoginPage.getTbEmail().sendKeys("mos.hovhannisyan@gmail.com");
        objFarmLoginPage.getTbPassword().sendKeys("     642508 ");
        objFarmLoginPage.getBtnLogin().click();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        assertEquals(objFarmLoginPage.getExceptedValidationMessage(), objFarmLoginPage.getValidationMessage().getText());
    }

    @Test
    public void testPasswordEyeIcon() {
        objFarmLoginPage.getEyeIcon().click();
        assertEquals("text", objFarmLoginPage.getTbPassword().getAttribute("type"));
        objFarmLoginPage.getEyeIcon().click();
        assertEquals("password", objFarmLoginPage.getTbPassword().getAttribute("type"));
    }

    @Test
    public void testForgotPasswordLink() throws InterruptedException {
        objFarmForgotPasswordPage = new FarmForgotPasswordPage(driver);
        assertTrue(objFarmLoginPage.getForgotPasswordLink().isDisplayed());
        objFarmLoginPage.getForgotPasswordLink().click();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
        Thread.sleep(2000);
        assertEquals(objFarmForgotPasswordPage.getExceptedForgotPasswordUrl(), driver.getCurrentUrl());
        assertTrue(objFarmForgotPasswordPage.getTbEmail().isDisplayed());
    }

    @Test
    public void testLoginWithRegisteredCredentials() throws InterruptedException {
        objFarmLoginPage = new FarmLoginPage(driver);
        objFarmLoginPage.getTbEmail().sendKeys("mos.hovhannisyan@gmail.com");
        objFarmLoginPage.getTbPassword().sendKeys("642508");
        objFarmLoginPage.getBtnLogin().click();
        Thread.sleep(2000);
//     driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        objFarmProfilePage = new FarmProfilePage(driver);
        assertEquals(exceptedLoggedInUrl, driver.getCurrentUrl());
        assertTrue(objFarmProfilePage.getAvatar().isDisplayed());
        System.out.println("User Logged in");
        logout(driver);
    }

    @Test
    public void testLoginWithEmptyFields() throws InterruptedException {
        objFarmLoginPage.getBtnLogin().click();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        assertTrue(objFarmLoginPage.getTextEmailHelper().isDisplayed());
        assertEquals(objFarmLoginPage.getExceptedEmailHelper(), objFarmLoginPage.getTextEmailHelper().getText());
        assertTrue(objFarmLoginPage.getTextPasswordHelper().isDisplayed());
        assertEquals(objFarmLoginPage.getExceptedPasswordHelper(), objFarmLoginPage.getTextPasswordHelper().getText());
        System.out.println("System doesn't let Login with empty Email and empty Password fields and shows validation messages /"
                + objFarmLoginPage.getExceptedEmailHelper() + "/" + " and /" + objFarmLoginPage.getExceptedPasswordHelper() + "/");
    }

    @Test
    public void testLoginWithInvalidEmail() {
        objFarmLoginPage.getTbEmail().sendKeys("123456gmail.com");
        objFarmLoginPage.getTbPassword().sendKeys("12345678");
        objFarmLoginPage.getBtnLogin().click();
        assertTrue(objFarmLoginPage.getTextEmailHelper().isDisplayed());
        assertEquals(objFarmLoginPage.getExceptedEmailHelper(), objFarmLoginPage.getTextEmailHelper().getText());
        objFarmLoginPage.getTbEmail().clear();
        System.out.println("System doesn't let Login with invalid Email and shows validation message /"
                + objFarmLoginPage.getExceptedEmailHelper() + "/");
    }

    @Test
    public void testLoginWithInvalidPassword() {
        objFarmLoginPage.getTbEmail().sendKeys("mos.hovhannisyan@gmail.com");
        objFarmLoginPage.getTbPassword().sendKeys("656565");
        objFarmLoginPage.getBtnLogin().click();
        assertTrue(objFarmLoginPage.getValidationMessage().isDisplayed());
        assertEquals(objFarmLoginPage.getExceptedValidationMessage(), objFarmLoginPage.getValidationMessage().getText());
        System.out.println("System doesn't let Login with wrong password and shows a Validation message /"
                + objFarmLoginPage.getValidationMessage() + "/");
    }

    @Test
    public void testClickBackButtonAfterLoginDoesntLogout() throws InterruptedException {
        objFarmProfilePage = new FarmProfilePage(driver);
        objFarmLoginPage.getTbEmail().sendKeys("mos.hovhannisyan@gmail.com");
        objFarmLoginPage.getTbPassword().sendKeys("642508");
        objFarmLoginPage.getBtnLogin().click();
//     driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        Thread.sleep(2000);
        driver.navigate().back();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        assertEquals(objFarmLoginPage.getExceptedUrl(), driver.getCurrentUrl());
        assertTrue(objFarmProfilePage.getAvatar().isDisplayed());
        System.out.println("Once logged in clicking 'Back' button doesn't logout the user ");
    }

    @Test
    public void testLoginWithEmailPassChangedByPlaces() {
        objFarmLoginPage.getTbPassword().sendKeys("mos.hovhannisyan@gmail.com");
        objFarmLoginPage.getTbEmail().sendKeys("642508");
        objFarmLoginPage.getBtnLogin().click();
        assertEquals(objFarmLoginPage.getExceptedEmailHelper(), objFarmLoginPage.getTextEmailHelper().getText());
        System.out.println("System doesn't let Login with password in email field and vice versa");
    }

    @Test
    public void testLoginPageSignUpBtn() throws InterruptedException {
        objFarmLoginPage.getBtnSignUp().click();
//     driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        Thread.sleep(2000);
        assertEquals("https://farmtogether.com/signup", driver.getCurrentUrl());
        System.out.println("SignUp button works correct");
    }

    @Test
    public void testFailureFindElement() {
        try {
            objFarmLoginPage = new FarmLoginPage(driver);
            objFarmLoginPage.getValidationMessage().click();
        } catch (NoSuchElementException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @After
    public void afterTest() {
        driver.close();
    }

}
