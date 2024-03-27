package com.testing;

import java.io.File;
import java.util.ArrayList;
import java.awt.Desktop;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

// import com.relevantcodes.extentreports.ExtentReports;
// import com.relevantcodes.extentreports.ExtentTest;
// import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNGCW1 {

        WebDriver driver;
        ExtentReports ER;
        ExtentSparkReporter ESR;
        ExtentTest ET;

        @BeforeTest
        public void BeforeTest()
        {
                WebDriverManager.chromedriver().setup();
                driver=new ChromeDriver();
                ER=new ExtentReports();
                ESR=new ExtentSparkReporter("D:\\Programs\\Web Develouping\\Software Testing\\TestNG\\TestNGCW1.html");
                ER.attachReporter(ESR);
        }
        
        @Test
        public void Test()throws Exception
        {
                driver.get("https://economictimes.indiatimes.com/et-now/results");
                driver.manage().window().maximize();
                Thread.sleep(2000);
                driver.findElement(By.linkText("Mutual Funds")).click();
                Thread.sleep(11000);
                JavascriptExecutor JS=(JavascriptExecutor)driver;
                JS.executeScript("window.scrollBy(0,100,'smooth')", "");
                Select S=new Select(driver.findElement(By.id("amcSelection")));
                S.selectByValue("8");
                Thread.sleep(2000);
                S=new Select(driver.findElement(By.id("schemenm")));
                S.selectByVisibleText("Canara Robeco Bluechip Equity Direct-G");
                Thread.sleep(2000);
                driver.findElement(By.id("getDetails")).click();
                Thread.sleep(20000);
                
                ArrayList<String>Tabs=new ArrayList<String>(driver.getWindowHandles());
                driver.switchTo().window(Tabs.get(1));
                Thread.sleep(2000);

                driver.findElement(By.xpath("//*[@id=\"installment_type\"]/li/span")).click();
                Thread.sleep(2000);
                driver.findElement(By.xpath("//*[@id=\"installment_type\"]/li/ul/li[1]/span")).click();
                Thread.sleep(2000);
                driver.findElement(By.id("installment_amt")).click();;
                Thread.sleep(2000);
                driver.findElement(By.xpath("//*[@id=\"installment_amt\"]/li/ul/li[3]")).click();
                Thread.sleep(2000);
                driver.findElement(By.id("installment_period")).click();;
                Thread.sleep(2000);
                driver.findElement(By.xpath("//*[@id=\"installment_period\"]/li/ul/li[4]")).click();
                Thread.sleep(2000);
                driver.findElement(By.xpath("//*[@id=\"mfNav\"]/div/ul/li[2]")).click();
                Thread.sleep(4000);
                String TR=ScreenShot("trailreturns.jpg");
                Thread.sleep(3000);
                ET=ER.createTest("Test 1");
                ET.log(Status.PASS, "Test Passed");
                ET.addScreenCaptureFromPath(TR);
        }

        public String ScreenShot(String a) throws Exception
        {
                TakesScreenshot TS=(TakesScreenshot)driver;
                File src=TS.getScreenshotAs(OutputType.FILE);
                File dst=new File("D:\\Programs\\Web Develouping\\Software Testing\\TestNG\\"+a);
                FileUtils.copyFile(src, dst);
                return dst.getAbsolutePath();
        }

        @AfterTest
        public void AfterTest()throws Exception
        {
                ER.flush();
                driver.quit();
                Desktop.getDesktop().browse(new File("D:\\Programs\\Web Develouping\\Software Testing\\TestNG\\TestNGCW1.html").toURI());
        }
}
