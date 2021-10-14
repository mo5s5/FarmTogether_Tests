package com.selenium.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FarmProfilePage {
    WebDriver driver;

    @FindBy(css = ".MuiAvatar-root")
   private WebElement avatar;

//    @FindBy(xpath = "//a[contains(text(),'Log Out')]")
    @FindBy(linkText = "LOG OUT")
    private WebElement logout;

    public FarmProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //GETTERS

    public WebElement getAvatar() {
        return avatar;
    }

    public WebElement getLogout() {
        return logout;
    }
}
