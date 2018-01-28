package com.sam_solutions.ealys.test.selenium;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

@Ignore
public class ProjectTest {

    private WebDriver browser;
    private final static String USERNAME = "maximus";
    private final static String PASSWORD = "123456";

    private final static String PROJECT_NAME = "Тестовый процесс";
    private final static String PROJECT_TARGET = "Алгоритм запуска ядерного реактора";
    private final static String PROJECT_DESCRIPTION = "Описаны очень умные вещи и формулы.";

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.get("http://localhost:8080/");
        browser.findElement(By.id("username")).sendKeys(USERNAME);
        browser.findElement(By.id("password")).sendKeys(PASSWORD);
        browser.findElement(By.id("loginButton")).submit();
    }

    @Test
    public void createProject(){
        browser.get("http://localhost:8080/project?pageNumber=1");
        browser.findElement(By.cssSelector("button[data-target='#newProjectModal']")).click();
        browser.findElement(By.id("name")).sendKeys(PROJECT_NAME);
        browser.findElement(By.id("target")).sendKeys(PROJECT_TARGET);
        browser.findElement(By.id("description")).sendKeys(PROJECT_DESCRIPTION);
        WebElement projectModal = browser.findElement(By.id("newProjectModal"));
        projectModal.findElement(By.tagName("a")).click();
    }

    @Test
    public void removeProject(){
        browser.get("http://localhost:8080/project?pageNumber=1");
        browser.findElement(By.cssSelector("a span[class='oi oi-trash']")).click();
    }

    @Test
    public void editProject(){
        browser.get("http://localhost:8080/project?pageNumber=1");
        browser.findElement(By.cssSelector("a h3")).click();
        browser.findElement(By.cssSelector("a span[class='oi oi-pencil small']")).click();
        browser.findElement(By.id("name")).sendKeys("Edit");
        browser.findElement(By.id("target")).sendKeys("Edit");
        browser.findElement(By.id("description")).sendKeys("Edit");
        WebElement updateProject = browser.findElement(By.id("updateForm"));
        updateProject.findElement(By.cssSelector("input[onclick='saveUpdatedProject']")).click();
    }

    @Test
    public void addRisk() throws InterruptedException {
        browser.get("http://localhost:8080/project?pageNumber=1");
        browser.findElement(By.cssSelector("a h3")).click();
        WebElement projectNav = browser.findElement(By.id("projectNav"));
        projectNav.findElements(By.tagName("a")).get(2).click();
        browser.findElement(By.cssSelector("a[data-target='#riskModal']")).click();
        browser.findElement(By.id("name")).sendKeys("Очень опасный риск");
        browser.findElement(By.id("description")).sendKeys("Возможно что-то произойдет...");
        browser.findElement(By.id("riskModal")).findElement(By.tagName("a")).click();
    }

    @After
    public void close(){
        browser.close();
    }
}
