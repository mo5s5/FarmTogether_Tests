package com.selenium.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FarmLoginPage {
    private final String exceptedUrl = "https://farmtogether.com/login";
    private final String exceptedPasswordType = "password";
    private final String exceptedValidationMessage = "Your email or password wasn't recognized.";
    private final String exceptedEmailHelper = "Please enter valid email address";
    private final String exceptedPasswordHelper = "Please enter your password";
    private final String exceptedProfileBlockValidationMessage = "There have been several failed attempts to sign in from this account. Please check your email for instructions.";

    @FindBy(id = "email")
    private WebElement tbEmail;

    @FindBy(css = "#email-helper-text")
    private WebElement textEmailHelper;

    @FindBy(css = ".MuiSvgIcon-fontSizeMedium > path")
    private WebElement eyeIcon;

    @FindBy(linkText = "Forgot password?")
    private WebElement forgotPasswordLink;

    @FindBy(linkText = "Password is not specified")
    private WebElement textPasswordIsNotSpecified;

    @FindBy(css = ".MuiTypography-body1")
    private WebElement validationMessage;

    @FindBy(css = "#password-helper-text")
    private WebElement textPasswordHelper;

    @FindBy(id = "password")
    private WebElement tbPassword;

    @FindBy(css = ".jss21")
    private WebElement btnLogin;

    @FindBy(linkText = "Sign Up")
    private WebElement btnSignUp;

    WebDriver driver;

    public FarmLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickBtnLogin() {
        btnLogin.click();
    }

    //Getters


    public String getExceptedUrl() {
        return exceptedUrl;
    }

    public String getExceptedPasswordType() {
        return exceptedPasswordType;
    }

    public String getExceptedValidationMessage() {
        return exceptedValidationMessage;
    }

    public String getExceptedEmailHelper() {
        return exceptedEmailHelper;
    }

    public String getExceptedPasswordHelper() {
        return exceptedPasswordHelper;
    }

    public String getExceptedProfileBlockValidationMessage() {
        return exceptedProfileBlockValidationMessage;
    }

    public WebElement getTbEmail() {
        return tbEmail;
    }

    public WebElement getTextEmailHelper() {
        return textEmailHelper;
    }

    public WebElement getEyeIcon() {
        return eyeIcon;
    }

    public WebElement getForgotPasswordLink() {
        return forgotPasswordLink;
    }

    public WebElement getTextPasswordIsNotSpecified() {
        return textPasswordIsNotSpecified;
    }

    public WebElement getValidationMessage() {
        return validationMessage;
    }

    public WebElement getTextPasswordHelper() {
        return textPasswordHelper;
    }

    public WebElement getTbPassword() {
        return tbPassword;
    }

    public WebElement getBtnLogin() {
        return btnLogin;
    }

    public WebElement getBtnSignUp() {
        return btnSignUp;
    }
}
