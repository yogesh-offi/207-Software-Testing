package com.testing;

import static org.junit.Assert.assertEquals;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoBlaze {

    WebDriver driver;
    ExtentReports ER = new ExtentReports("D:\\Programs\\Web Develouping\\Software Testing\\Testing FrameWorks1\\DB.html");
    ExtentTest test;

    @BeforeMethod
    public void BeforeMethod()throws Exception
    {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("https://www.demoblaze.com/");
        driver.manage().window().maximize();
        Thread.sleep(3000);
    }
    
    @Test
    public void Test1()throws Exception
    {
        test=ER.startTest("Test 1");
        JavascriptExecutor JS=(JavascriptExecutor) driver;
        JS.executeScript("window.scrollBy(0,200,'smooth')", "");
        Thread.sleep(3000);
		driver.findElement(By.linkText("Laptops")).click();
        Thread.sleep(3000);
		driver.findElement(By.linkText("MacBook air")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Add to cart")).click();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Cart")).click();
		Thread.sleep(3000);
		String title=driver.findElement(By.xpath("//*[@id='tbodyid']/tr/td[2]")).getText();
        if(title.equals("MacBook air"))
        test.log(LogStatus.PASS, "Test Passed");
        else
        test.log(LogStatus.FAIL, "Test Failed");
        assertEquals(title, "MacBook air");
    }

    @DataProvider(name = "LogInData")
    public String[][] logdata()throws Exception
    {
        String a[][]=new String[1][2];

        FileInputStream fis=new FileInputStream("D:\\Programs\\Web Develouping\\Software Testing\\Testing FrameWorks1\\DB LogInData.xlsx");
        XSSFWorkbook wb=new XSSFWorkbook(fis);
        Sheet S=wb.getSheetAt(0);
        a[0][0]=S.getRow(1).getCell(0).getStringCellValue();
        a[0][1]=S.getRow(1).getCell(1).getStringCellValue();
        wb.close();
        fis.close();

        return a;
    }

    @Test(dataProvider = "LogInData")
    public void Test2(String a,String b)throws Exception
    {
        test=ER.startTest("Test 2");
        Thread.sleep(2000);
        driver.findElement(By.linkText("Log in")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("loginusername")).sendKeys(a);
        Thread.sleep(2000);
        driver.findElement(By.id("loginpassword")).sendKeys(b);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]")).click();
        Thread.sleep(5000);
        String wm=driver.findElement(By.linkText("Welcome Testalpha")).getText();
        if(wm.equals("Welcome Testalpha"))
        test.log(LogStatus.PASS, "Test Passed");
        else
        test.log(LogStatus.FAIL, "Test Failed");
        assertEquals(wm,"Welcome Testalpha");
        Thread.sleep(2000);
    }
    
    @AfterMethod
    public void AfterMethod()throws Exception
    {
        Thread.sleep(2000);
        ER.endTest(test);
        ER.flush();
        driver.quit();
    }

    @AfterTest
    public void AfterTest()throws Exception
    {
        Desktop.getDesktop().browse(new File("Testing FrameWorks1\\DB.html").toURI());
    }

}
