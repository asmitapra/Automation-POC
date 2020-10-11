package base;


import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import constants.ConfigConstants;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

	WebDriver driver;
	
	public void driverInitaialization()
	{
		try {
			
			if(ConfigConstants.COMMON_BROWSER.equals("chrome"))
			{
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(getChromeOptions());
			}
			else if(ConfigConstants.COMMON_BROWSER.equals("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
			else if(ConfigConstants.COMMON_BROWSER.equals("ie"))
			{
				WebDriverManager.iedriver().setup();
				driver = new InterNetExplorerDriver();
			}
			driver.manage().window().maximize();
		}
		catch(Exception e)
		{
			logger.info("Driver initialization is failed");
		}
	}
	
	public WebDriver getDriver()
	{
		if(driver == null)
		{
			driverInitaialization();
		}
		return driver;
	}
	
	
	private ChromeOptions getChromeOptions()
	{
		HashMap<String,Object> chromeprefs = new HashMap<String,Object>();
		ChromeOptions chromeoptions = new ChromeOptions();
		chromeoptions.setExperimentalOption("prefs", chromeprefs);
		chromeoptions.setAcceptInsecureCerts(true);
		chromeoptions.addArguments("--incognito");
		chromeoptions.addArguments("start-Maximized");
		chromeoptions.addArguments("disable-infobars");
		chromeoptions.addArguments("ignore-certificate-errors");
		return chromeoptions;
	}
	
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	
}
