package utils;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import extentReport.LoggerController;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class ElementUtils {

	WebDriver driver;
	LoggerController logger; 
	
	public ElementUtils(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void InputText(By locator, String text)
	{
		WebElement ele = driver.findElement(locator);
		ele.clear();
		ele.sendKeys(text);
	}
	
	public void InputTextInTextArea(By locator, String text)
	{
		driver.switchTo().frame(driver.findElement(locator).findElement(By.cssSelector("iframe")));
		driver.findElement(By.cssSelector("body")).sendKeys(text);
		driver.switchTo().defaultContent();
		logger.info(String.format("Text entered is %s in field %s",text,locator));
	}
	
	
	public void SelectDropdownByVisibleText(By locator, String text)
	{
		Select select = new Select(driver.findElement(locator));
		select.selectByVisibleText(text);
	}
	
	public void SelectDropdownByVisibleIndex(By locator, int index)
	{
		Select select = new Select(driver.findElement(locator));
		select.selectByIndex(index);
	}
	
	public void ClickElement(By locator)
	{
		logger.info("---Start of ClickElement------");
		((JavascriptExecutor)driver).executeScript("$(arguments[0]).get(0).click();",driver.findElement(locator));
		logger.info("----End of ClickElement-----");
	}
	
	public void ClickElementByAction(By locator)
	{
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(locator)).click();
	}
	
	public void ScrollToElement(By locator)
	{
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView;", locator);
	}
	
	
	
	public void SwitchToTab(String text)
	{
		ArrayList<String> allWindows = new ArrayList<String>(driver.getWindowHandles());
		Boolean isWindowFound = false;
		String currentWindow = driver.getWindowHandle();
		for(String windowHandle:allWindows)
		{
			if(driver.switchTo().window(windowHandle).getTitle().contains(text))
			{
				driver.switchTo().window(windowHandle);
				isWindowFound = true;
				logger.info(String.format("Current window is %s", text));
			}
		}
		if(isWindowFound = false)
		{
			driver.switchTo().window(currentWindow);
		}		
	}
	
	public void SwitchToTab(int index)
	{
		ArrayList<String> allWindows = new ArrayList<String>(driver.getWindowHandles());
		String currentWindow = driver.getWindowHandle();
		try
		{
			driver.switchTo().window(allWindows.get(index));
		}
		catch(NoSuchWindowException e)
		{
			driver.switchTo().window(currentWindow);
		}
	}
	
	public void waitForElementToAppear(By bySelector, Boolean pageRefreshTillElementAppear) {
		@SuppressWarnings("deprecation")
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(300, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		wait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				if (pageRefreshTillElementAppear) {
					driver.navigate().refresh();
					logger.info(String.format("Waiting for element %s to appear", bySelector));
					return driver.findElement(bySelector);
				}
				return driver.findElement(bySelector);
			}
		});
	}

	public String waitForTitlePresent(String titleValue, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.titleIs(titleValue));
		return driver.getTitle();
	}

	
	public void clickWhenReady(By locator, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	public WebElement waitForElementToBeVisible(By locator, int time) {
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.visibilityOf(element));

	}
	
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}
	
	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	
	
	
	
	
}
