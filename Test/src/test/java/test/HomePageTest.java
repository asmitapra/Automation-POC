package test;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import utility.TestBase;
import utils.Constants;

public class HomePageTest extends TestBase{

	HomePage page;
	Properties prop;
	
	
	@Test(priority = 1)
	public void verifyHomePageTitleTest() {
		String title = page.getHomePageTitle();
		System.out.println("home page title is : " + title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void verifyHomePageHeaderTest() {
		String header = page.getHeaderValue();
		System.out.println("home page header is : " + header);
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER);
	}

	@Test(priority = 3)
	public void verifyAccountTest() {
		String account = page.getAccountName();
		System.out.println("account name is : " + account);
		Assert.assertEquals(account, prop.getProperty("accountname").trim());
	}

	@Test(priority = 4)
	public void verifySettingsIconTest() {
		Assert.assertTrue(page.isSettingsIconExist());
	}
	
	
	
	
	
	
	
	
	
}
