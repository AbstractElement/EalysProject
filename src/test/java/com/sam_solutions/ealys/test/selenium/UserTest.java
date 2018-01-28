package com.sam_solutions.ealys.test.selenium;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

@Ignore
public class UserTest {
    private WebDriver browser;
    private final static String USERNAME = "maximus";
    private final static String PASSWORD = "123456";

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        browser.get("http://localhost:8080/");
        browser.findElement(By.id("username")).sendKeys(USERNAME);
        browser.findElement(By.id("password")).sendKeys(PASSWORD);
        browser.findElement(By.id("loginButton")).submit();
    }

    @Test
    public void testChangeProfile(){
        browser.get("http://localhost:8080/user/settings");
        browser.findElement(By.id("firstName")).sendKeys("Edit");
        browser.findElement(By.id("lastName")).sendKeys("Edit");
        browser.findElement(By.id("organizationName")).sendKeys("Edit");
        browser.findElement(By.id("position")).sendKeys("Edit");
        browser.findElement(By.id("about")).sendKeys("Edit");
        WebElement profileForm = browser.findElement(By.id("profileNav"));
        profileForm.findElement(By.cssSelector("form input[type=submit]")).submit();
    }

    @Test
    public void testAddNewSkill() throws InterruptedException {
        browser.get("http://localhost:8080/user/settings");
        browser.findElement(By.cssSelector("a[href='#skillsNav']")).click();
        browser.findElement(By.id("inputNewSkill")).sendKeys("Spring MVC");
        browser.findElement(By.cssSelector("span[onclick='addNewSkill()']")).click();
        Thread.sleep(1000);
        browser.findElement(By.id("inputNewSkill")).sendKeys("Ajax");
        browser.findElement(By.cssSelector("span[onclick='addNewSkill()']")).click();
        Thread.sleep(1000);
        browser.findElement(By.id("inputNewSkill")).sendKeys("Hibernate");
        browser.findElement(By.cssSelector("span[onclick='addNewSkill()']")).click();
    }

    @Test
    public void testDeleteSkill() throws InterruptedException {
        browser.get("http://localhost:8080/user/settings");
        browser.findElement(By.cssSelector("a[href='#skillsNav']")).click();
        Thread.sleep(1000);
        browser.findElement(By.id("userSkills")).findElement(By.tagName("button")).click();
        Thread.sleep(1000);
    }

    @After
    public void close(){
        browser.close();
    }
}
