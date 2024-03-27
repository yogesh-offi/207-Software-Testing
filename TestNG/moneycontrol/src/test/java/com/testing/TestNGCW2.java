package com.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNGCW2 {

    WebDriver driver;

    @BeforeTest
    public void BeforeTest()
    {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
    }

    @Test
    public void Test()throws Exception
    {
        driver.get("https://www.moneycontrol.com/");
        driver.manage().window().maximize();
        Thread.sleep(10000);
        driver.findElement(By.id("search_str")).sendKeys("Reliance Industries");
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"autosuggestlist\"]/ul/li[1]/a")).click();
        Thread.sleep(3000);
        JavascriptExecutor JS=(JavascriptExecutor)driver;
        JS.executeScript("window.scrollBy(0,200,'smooth')", "");
        Thread.sleep(2000);
        String RI=driver.findElement(By.xpath("//*[@id=\"stockName\"]/h1")).getText();
        if(RI.equals("Reliance Industries Ltd."))
        System.out.println("Found");
        else
        System.out.println("Not Found");
        Thread.sleep(2000);
        WebElement MF=driver.findElement(By.xpath("//*[@id=\"common_header\"]/div[1]/div[3]/nav/div/ul/li[10]"));
        Actions A=new Actions(driver);
        A.moveToElement(MF).perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"common_header\"]/div[1]/div[3]/nav/div/ul/li[10]/div/div/ul/li[2]/ul/li[5]/a")).click();
        Thread.sleep(2000);
        JS.executeScript("window.scrollBy(0,400,'smooth')", "");
        Thread.sleep(2000);
        Select S=new Select(driver.findElement(By.xpath("//*[@id=\"ff_id\"]")));
        S.selectByIndex(2);
        Thread.sleep(2000);
        JS.executeScript("window.scrollBy(0,400,'smooth')", "");
        Thread.sleep(2000);
        S=new Select(driver.findElement(By.xpath("//*[@id=\"im_id\"]")));
        S.selectByIndex(1);
        Thread.sleep(2000);
        JS.executeScript("window.scrollBy(0,400,'smooth')", "");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"invamt\"]")).sendKeys("100000");
        Thread.sleep(2000);
        S=new Select(driver.findElement(By.xpath("//*[@id=\"frq\"]")));
        S.selectByIndex(0);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"stdt\"]")).sendKeys("2021-08-02");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"endt\"]")).sendKeys("2023-08-17");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"mc_mainWrapper\"]/div[2]/div/div[3]/div[2]/div[2]/form/div[8]/input")).click();
        Thread.sleep(3000);
        String IP=driver.findElement(By.xpath("//*[@id=\"mc_mainWrapper\"]/div[2]/div/div[3]/div[2]/div[6]/table/tbody/tr[1]/td[1]")).getText();
        String TD=driver.findElement(By.xpath("//*[@id=\"mc_mainWrapper\"]/div[2]/div/div[3]/div[2]/div[6]/table/tbody/tr[1]/td[2]")).getText();
        if(IP.equals("Investment Period")&&TD.equals("Aug 02, 2021 to Aug 17, 2023"))
        System.out.println("Investment Test Passed");
        else
        System.out.println("Investment Test Failed");
        Thread.sleep(3000);
        System.out.println(IP+" : "+TD);
    }

    @AfterTest
    public void AfterTest()
    {
        driver.quit();
    }

}
