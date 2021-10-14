package com.selenium.test.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FarmHomePage {
    WebDriver driver;

    private String pageUrl = "https://farmtogether.com/";

    @FindBy(linkText = "LOGIN")
    private WebElement btnLogin;

    public FarmHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickLoginBtn() {
        btnLogin.click();
    }

    public WebElement getBtnLogin() {
        return btnLogin;
    }
}
