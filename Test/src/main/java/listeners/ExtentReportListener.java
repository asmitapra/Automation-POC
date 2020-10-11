

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

import constants.ConfigConstants;
import extentReport.ExtentManager;
import extentReport.ExtentTestManager;
import utility.TestBase;


public class ExtentReportListener extends TestBase implements ITestListener {

	// private static final LoggerController logger = new
	// LoggerController(ExtentReportListner.class);

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(),
				iTestResult.getMethod().getDescription() == null ? iTestResult.getMethod().getMethodName()
						: iTestResult.getMethod().getDescription());
		logger.info("Test started:- " + getTestMethodName(iTestResult));
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		logger.info("Test success:- " + iTestResult.getMethod().getMethodName());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		Object testClass = iTestResult.getInstance();
		WebDriver webDriver = ((TestBase) testClass).getDriver();
		String base64Screenshot = "data:image/png;base64,"
				+ ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);

		ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed",
				ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
		logger.error("Test failed:- " + iTestResult.getMethod().getQualifiedName(), iTestResult.getThrowable());

	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		logger.debug("Test skipped:- " + iTestResult.getMethod().getMethodName());
		ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		logger.debug("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));

	}

	@Override
	public void onStart(ITestContext iTestContext) {
		// logger.debug("Test is in onStart method " + iTestContext.getName());
		iTestContext.setAttribute("WebDriver", this.driver);
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		logger.debug("Result file path -" + ConfigConstants.COMMON_RESULT_FOLDER_NAME);
		logger.debug("Test is in onFinish method " + iTestContext.getName());
		ExtentManager.getReporter().setTestRunnerOutput("BVTFILE");
		ExtentTestManager.endTest();
		ExtentManager.getReporter().flush();
	}

}
