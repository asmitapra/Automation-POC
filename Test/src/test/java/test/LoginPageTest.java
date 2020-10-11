package test;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utility.TestBase;
import utils.Constants;



public class LoginPageTest extends TestBase
{
	LoginPage page;
	Properties prop;

	
	@Test(priority = 1)
	public void verifySignUpLinkTest() {
		Assert.assertTrue(page.isSignUpLinkExist());
	}
	
	
	@Test(priority = 2)
	public void verifyLoginTest()
	{		
		page.doLogin(prop.getProperty("username"), prop.getProperty("password")); 
	}
		
	@Test(priority = 3)
	public void verifyLoginPagetitleTest()
	{
		String title = page.getLoginPageTitle();
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	
}
	
	







