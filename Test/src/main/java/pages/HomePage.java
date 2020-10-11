package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Constants;
import utility.TestBase;
import utils.ElementUtils;

public class HomePage extends TestBase{

	private WebDriver driver;
	ElementUtils util;

	private By header = By.tagName("h1");
	private By accountName = By.cssSelector("span.account-name ");
	private By settingsIcon = By.id("navSetting");
	private By contactsParentMenu = By.id("nav-primary-contacts-branch");
	private By contactsSubMenu = By.id("nav-secondary-contacts");

	public HomePage(WebDriver driver) {
		util = new ElementUtils(driver);
		this.driver = driver;
	}

	public String getHomePageTitle() {
		return util.waitForTitlePresent(Constants.HOME_PAGE_TITLE, 10);
	}

	public String getHeaderValue() {
		if(util.doIsDisplayed(header)){
			return util.doGetText(header);
		}
		return null;
	}

	public String getAccountName() {
		if(util.doIsDisplayed(accountName)){
			return util.doGetText(accountName);
		}
		return null;
	}

	public boolean isSettingsIconExist() {
		return util.doIsDisplayed(settingsIcon);
	}
	
	
	public ContactsPage goToContactsPage(){
		clickOnContacts();
		return new ContactsPage(driver);
	}
	
	
	
	private void clickOnContacts(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		util.waitForElementPresent(contactsParentMenu, 10);
		util.ClickElement(contactsParentMenu);
		util.waitForElementPresent(contactsSubMenu, 5);
		util.ClickElement(contactsSubMenu);
	}
	
	
	
}
