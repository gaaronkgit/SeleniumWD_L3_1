package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginTest
{
    static void RunTest()
    {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("http://automationpractice.com/index.php/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("gaaronk8325@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys("qwerty123456");

        driver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]")).click();

        List<WebElement> lstElm = driver.findElements(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span"));

        if(lstElm.size() == 1 && lstElm.get(0).getText().equals("Stepan Ivanov"))
        {
            System.out.println("Тест TestLogin успешно пройден");
        }
        else
        {
            System.out.println("Тест TestLogin провален");
        }

        driver.quit();
    }
}
