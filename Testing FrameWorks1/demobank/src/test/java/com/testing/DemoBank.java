package com.testing;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.awt.Desktop;
import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoBank {

    WebDriver driver;
    ExtentReports ER=new ExtentReports("D:\\Programs\\Web Develouping\\Software Testing\\Testing FrameWorks1\\CCReport.html");
    ExtentTest test;

    @DataProvider(name = "LogData")
    public String[][] datas()
    {
        String a[][]=new String[1][2];
        a[0][0]="S@gmail.com";
        a[0][1]="P@ssword12";
        return a;
    }

    @BeforeMethod
    public void BeforeMethod() throws Exception
    {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("http://dbankdemo.com/bank/login");
        driver.manage().window().maximize();
        Thread.sleep(3000);
    }
    
    @Test(dataProvider = "LogData")
    public void testcase1(String name,String pass) throws Exception
    {
        test=ER.startTest("Test1");
        driver.findElement(By.id("username")).sendKeys(name);
        Thread.sleep(3000);
        driver.findElement(By.id("password")).sendKeys(pass);
        Thread.sleep(3000);
        driver.findElement(By.id("submit")).click();
        Thread.sleep(3000);
        String cur=driver.getCurrentUrl();
        String home="http://dbankdemo.com/bank/home";
        Thread.sleep(2000);
        if(cur.equals(home))
        test.log(LogStatus.PASS,"Test Passed");
        else
        test.log(LogStatus.FAIL,"Test Failed");
        assertEquals(cur,home);
    }
    
    @Test(dataProvider = "LogData")
    public void testcase2(String name,String pass)throws Exception
    {
        test=ER.startTest("Test2");
        driver.findElement(By.id("username")).sendKeys(name);
        Thread.sleep(3000);
        driver.findElement(By.id("password")).sendKeys(pass);
        Thread.sleep(3000);
        driver.findElement(By.id("submit")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText("Deposit")).click();
        Thread.sleep(3000);
        Select S=new Select(driver.findElement(By.id("selectedAccount")));
        S.selectByIndex(1);
        Thread.sleep(3000);
        driver.findElement(By.id("amount")).sendKeys("5000");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"right-panel\"]/div[2]/div/div/div/div/form/div[2]/button[1]")).click();
        Thread.sleep(3000);
        JavascriptExecutor JS=(JavascriptExecutor)driver;
        JS.executeScript("window.scrollTo(0,2000)", "");
        Thread.sleep(3000);
        String amount=driver.findElement(By.xpath("//*[@id='transactionTable']/tbody/tr[1]/td[4]")).getText();
        Thread.sleep(3000);
        if(amount.equals("$5000.00"))
        test.log(LogStatus.PASS, "Test Passed");
        else
        test.log(LogStatus.FAIL, "Test Failed");
        assertEquals(amount, "$5000.00");
    }
    
    @Test(dataProvider = "LogData")
    public void testcase3(String name,String pass)throws Exception
    {
        test=ER.startTest("Test3");
        driver.findElement(By.id("username")).sendKeys(name);
        Thread.sleep(3000);
        driver.findElement(By.id("password")).sendKeys(pass);
        Thread.sleep(3000);
        driver.findElement(By.id("submit")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText("Withdraw")).click();
        Thread.sleep(3000);
        Select S=new Select(driver.findElement(By.id("selectedAccount")));
        S.selectByIndex(1);
        Thread.sleep(3000);
        driver.findElement(By.id("amount")).sendKeys("3000");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='right-panel']/div[2]/div/div/div/div/form/div[2]/button[1]")).click();
        Thread.sleep(3000);
        JavascriptExecutor JS=(JavascriptExecutor)driver;
        JS.executeScript("window.scrollTo(0,2000)", "");
        Thread.sleep(3000);
        String amount=driver.findElement(By.xpath("//*[@id=\"transactionTable\"]/tbody/tr[1]/td[4]")).getText();
        Thread.sleep(3000);
        if(amount.equals("$-3000.00"))
        test.log(LogStatus.PASS, "Test Passed");
        else
        test.log(LogStatus.FAIL, "Test Failed");
        assertEquals(amount, "$-3000.00");
    }

    @AfterMethod
    public void AfterMethod()
    {
        ER.endTest(test);
        ER.flush();
        driver.quit();
    }

    @AfterTest
    public void displayReport()throws Exception
    {
        Desktop.getDesktop().browse(new File("CCReport.html").toURI());
    }

}
