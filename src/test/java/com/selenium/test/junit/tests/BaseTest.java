package com.selenium.test.junit.tests;

import com.selenium.test.pages.FarmHomePage;
import com.selenium.test.pages.FarmLoginPage;
import com.selenium.test.pages.FarmProfilePage;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    String homeUrl = "https://farmtogether.com/";
    FarmHomePage objFarmHomePage;
    FarmLoginPage objFarmLoginPage;
    FarmProfilePage objFarmProfilePage;

    protected void openHomePageBase(WebDriver driver) {
        driver.get(homeUrl);
    }

    protected void moveToLoginPage(WebDriver driver) {
        objFarmHomePage = new FarmHomePage(driver);
        objFarmHomePage.clickLoginBtn();
    }

    public void loginWithCorrectCredentials(WebDriver driver) {
        objFarmLoginPage = new FarmLoginPage(driver);
        objFarmLoginPage.getTbEmail().sendKeys("mos.hovhannisyan@gmail.com");
        objFarmLoginPage.getTbPassword().sendKeys("642508");
        objFarmLoginPage.getBtnLogin().click();
    }

    public void logout(WebDriver driver) {
        objFarmProfilePage = new FarmProfilePage(driver);
        objFarmProfilePage.getAvatar().click();
        objFarmProfilePage.getLogout().click();
    }
}
