package com.qa.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Demo {
	
 public static WebDriver driver;
 
 @Test (priority=1)
  public void setup(){
		System.setProperty("webdriver.chrome.driver", "D:\\Goutham\\Workspace\\Eclipse\\Selenium_project\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();	
		driver.get("https://www.seleniumeasy.com/test/");
	}

  @Test (priority=2)
 public void closelightbox(){
	  try {
		String ver = waittime( driver,"a[id=at-cv-lightbox-close]");
		if(ver.equalsIgnoreCase("Yes")){
		driver.findElement(By.cssSelector("a[id=at-cv-lightbox-close]")).click();
		}
	} catch (InterruptedException e) {
      // TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
 
  @Test (priority=3)
  public void editbox1() throws InterruptedException{ 
	  String ver = waittime( driver,"#treemenu > li > ul > li:nth-child(1) > a");
		driver.findElement(By.cssSelector("#treemenu > li > ul > li:nth-child(1) > a")).click();
		
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#treemenu > li > ul > li:nth-child(1) > ul > li:nth-child(1) > a")).click();
		ver = waittime( driver,"#user-message");
		driver.findElement(By.cssSelector("#user-message")).sendKeys("fhvhvbhb");
		driver.findElement(By.cssSelector("#get-input > button")).click();
		Thread.sleep(1000);
	String k= driver.findElement(By.cssSelector("#display")).getText();
	
	if (k.equals("fhvhvbhb")){
	System.out.println("pass");
	}else{
	System.out.println("fail");
	}
  }
  
  
  @Test (priority=4)
 public void quit(){
	 driver.quit();
	}

  
  
  
 public String waittime(WebDriver driver,String css) throws InterruptedException{
	 String ver = "No";
	 driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		for (int c=0;c<=60;c++){
			Thread.sleep(1000);
  		try{
	     		if(driver.findElement(By.cssSelector(css)).isDisplayed()){
	     			ver = "Yes";
	     			break;
	     		}
  		}catch (Exception e) {
		      System.out.println(e.getMessage());
		    }
		}
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return ver;
	}
	
 
	
}
