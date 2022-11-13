package listeners;

import base.TestBase;
import org.testng.*;
import utilities.ExtentManager;
import utilities.TestUtilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class CustomListeners extends TestBase implements ITestListener, ISuiteListener {

    static Date d = new Date();
    static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
    static String messageBody;
    private static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir")+"\\reports\\"+fileName);
    public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getTestClass().getName()+"     @TestCase : "+result.getMethod().getMethodName());
        testReport.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {

        String excepionMessage= Arrays.toString(result.getThrowable().getStackTrace());
        testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
                + "</font>" + "</b >" + "</summary>" +excepionMessage.replaceAll(",", "<br>")+"</details>"+" \n");

        try {

            TestUtilities.captureScreenshot();
            testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
                    MediaEntityBuilder.createScreenCaptureFromPath(TestUtilities.screenshotName)
                            .build());
        } catch (IOException e) {

        }

        String failureLogg="TEST CASE FAILED";
        Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
        testReport.get().log(Status.FAIL, m);

//        System.setProperty("org.uncommons.reportng.escape-output","false");
//
//        Reporter.log("Capturing screenshot");
//        try {
//            TestUtilities.captureScreenshot();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Reporter.log("<a target=\"_blank\" href="+ TestUtilities.screenshotName2+">Screenshot</a>");
//        Reporter.log("<br>");
//        Reporter.log("<a target=\"_blank\" href="+ TestUtilities.screenshotName2+"><img src="+ TestUtilities.screenshotName2+" height=200 width=200></img></a>");

//        Reporter.log("<a target=\"_blank\" href="+ "C:\\Salesforce\\screenshot\\error.jpg\">Screenshot</a>");
//        Reporter.log("<br>");
//        Reporter.log("<a target=\"_blank\" href="+ "C:\\Salesforce\\screenshot\\error.jpg><img src=\"+ \"C:\\Salesforce\\screenshot\\error.jpg\" height=200 width=200></img></a>");
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }
}