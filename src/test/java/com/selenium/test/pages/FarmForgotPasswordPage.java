package com.selenium.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FarmForgotPasswordPage {

private     String exceptedForgotPasswordUrl="https://farmtogether.com/account/forgot-password";
    WebDriver driver;
    @FindBy(linkText = "Send Instructions")
    private WebElement btnSubmit;
    @FindBy(id="email")
    private WebElement tbEmail;

    public FarmForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getBtnSubmit() {
        return btnSubmit;
    }

    public WebElement getTbEmail() {
        return tbEmail;
    }

    public String getExceptedForgotPasswordUrl() {
        return exceptedForgotPasswordUrl;
    }
}
