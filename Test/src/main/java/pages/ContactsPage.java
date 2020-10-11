package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.TestBase;
import utils.Constants;
import utils.ElementUtils;

public class ContactsPage extends TestBase{

	ElementUtils util;
	private WebDriver driver;

	private By header = By.cssSelector("h1[class*=IndexPageRedesignHeader]");
	private By createContactPrimary = By.xpath("//span[text()='Create contact']");
	private By emailId = By.xpath("//input[@data-field='email']");
	private By firstName = By.xpath("//input[@data-field='firstname']");
	private By lastName = By.xpath("//input[@data-field='lastname']");
	private By jobTitle = By.xpath("//input[@data-field='jobtitle']");
	private By createContactSecondary = By.xpath("(//span[text()='Create contact'])[position()=2]");
	private By contactsBackLink = By.xpath("(//*[text()='Contacts'])[position()=1]");

	public ContactsPage(WebDriver driver) {
		util = new ElementUtils(driver);
		this.driver = driver;
	}

	public String getContactsPageTitle() {
		return util.waitForTitlePresent(Constants.CONTACTS_PAGE_TITLE, 10);
	}

	
	public void createContact(String emailId, String firstName, String lastName, String jobTitle) {

		util.clickWhenReady(createContactPrimary, 10);
		util.waitForElementToBeVisible(this.emailId, 10);
		util.InputText(this.emailId, emailId);
		util.InputText(this.firstName, firstName);
		util.InputText(this.lastName, lastName);
		util.waitForElementToBeVisible(this.jobTitle, 10);
		util.InputText(this.jobTitle, jobTitle);
		util.clickWhenReady(createContactSecondary, 5);
		util.clickWhenReady(contactsBackLink, 10);
		//return flag;

	}

}
	

