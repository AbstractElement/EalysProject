package com.sam_solutions.ealys.test.selenium;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

@Ignore
public class LoginTest {

    private WebDriver browser;
    private final static String USERNAME = "maximus";
    private final static String PASSWORD = "123456";

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void startTestLogin() throws InterruptedException {
        browser.get("http://localhost:8080/");
        browser.findElement(By.id("username")).sendKeys(USERNAME);
        browser.findElement(By.id("password")).sendKeys(PASSWORD);
        browser.findElement(By.id("loginButton")).submit();
        Thread.sleep(3000);
        Assert.assertEquals(USERNAME, browser.findElement(By.id("dropdownMenuLink")).getText());
    }

    @Test
    public void startTestRegistration() throws InterruptedException {
        browser.get("http://localhost:8080/");
        browser.findElement(By.id("registration")).click();
        browser.findElement(By.id("usernameReg")).sendKeys("TestUser");
        browser.findElement(By.id("email")).sendKeys("vladislavtretyak@bk.ru");
        browser.findElement(By.id("firstName")).sendKeys("Petr");
        browser.findElement(By.id("lastName")).sendKeys("Emelyanov");
        browser.findElement(By.name("sex")).click();
        browser.findElement(By.id("passwordReg")).sendKeys("12345678");
        browser.findElement(By.id("repeatedPassword")).sendKeys("12345678");
        browser.findElement(By.id("confirmRegistration")).click();
        Thread.sleep(10000);
        Assert.assertEquals("alert alert-success alert-dismissible fade show col-lg", browser.findElement(By.id("successReg")).getAttribute("class"));
    }


    @After
    public void close(){
        browser.close();
    }
}
