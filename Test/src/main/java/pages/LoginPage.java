package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Constants;

import base.BasePage;
import base.BaseTest;
import utils.ElementUtils;

public class LoginPage extends BaseTest{
	
	private WebDriver driver;
	ElementUtils utils;
	

	private By emailId = By.id("username");
	private By password = By.id("password");
	private By loginButton = By.id("loginBtn");
	private By signUpLink = By.linkText("Sign up");

	
	public LoginPage(WebDriver driver) {
		utils = new ElementUtils(driver);
		this.driver = driver;
	}

	
	public String getLoginPageTitle() {
		return utils.waitForTitlePresent(Constants.LOGIN_PAGE_TITLE, 10);
	}
	
	
	public boolean isSignUpLinkExist() {
		return utils.doIsDisplayed(signUpLink);
	}
	
	public HomePage doLogin(String un, String pwd) {
		System.out.println("login to app....");
		utils.InputText(emailId, un);
		utils.InputText(password, pwd);
		utils.ClickElement(loginButton);

		return new HomePage(driver);
	}

	
}
