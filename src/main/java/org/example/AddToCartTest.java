package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddToCartTest
{
    static void RunTest()
    {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        //Иногда возвращает ошибку 508
        driver.get("http://automationpractice.com/index.php/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("gaaronk8325@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys("qwerty123456");

        driver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]")).click();

        List<WebElement> lstElm = driver.findElements(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span"));

        if(lstElm.size() != 1 || !lstElm.get(0).getText().equals("Stepan Ivanov"))
        {
            System.out.println("Авторизация на сайте не выполнена! Тест AddToCartTest провален");
            driver.quit();
            return;
        }

        driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[6]/div/div[1]/div/a[1]/img")).click();

        driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button/span")).click();
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/span")).click();

        driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a")).click();
        if(driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr/td[2]/p/a")).getText().equals("Printed Summer Dress"))
        {
            System.out.println("Товар найден в корзине! Тест AddToCartTest проден");
        }
        else
        {
            System.out.println("Товар не найден в корзине! Тест AddToCartTest провален");
        }
        driver.quit();
    }
}
