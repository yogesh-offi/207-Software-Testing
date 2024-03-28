//extent2
package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class AppTest 
{
    public static void testing() throws Exception
    {
        WebDriverManager.chromedriver().setup();
        WebDriver driver =new ChromeDriver();
        driver.get("https://www.demoblaze.com/");
        driver.manage().window().maximize();
        Thread.sleep(5000);
        driver.findElement(By.linkText("Laptops")).click();
        Thread.sleep(5000);
        driver.findElement(By.linkText("MacBook air")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")).click();
        Thread.sleep(5000);
        driver.switchTo().alert().accept();
        Thread.sleep(5000);
        driver.findElement(By.linkText("Cart")).click();
        Thread.sleep(5000);
        String price=driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/tr/td[3]")).getText();
        System.out.println("price "+price);
        String title=driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/tr/td[2]")).getText();
        System.out.println("title "+title);

        Thread.sleep(5000);
        
        driver.close();

    }
}