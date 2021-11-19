package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AddProjectTest
{
    static void RunTest()
    {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebElement element;
        Actions action = new Actions(driver);
        String projName = "IvanFrm2";

        driver.get("https://crm.geekbrains.space/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Находим поле логина по ID
        element = driver.findElement(By.id("prependedInput"));
        //Кликаем и вводим данные поле логина по ID
        ClickAndWrite(element,"Applanatest1");
        //Находим поле пароля по ID
        element = driver.findElement(By.id("prependedInput2"));
        //Кликаем и вводим данные в поле пароля
        ClickAndWrite(element,"Student2020!");
        driver.findElement(By.id("_submit")).click();

        //Проверяем есть ли Паненли инструментов
        List<WebElement> lstElements = driver.findElements(By.xpath("//*[@id=\"container\"]/div/div[1]/div/div/div[3]/label"));
        if(lstElements.size() != 1)
        {
            System.out.println("Элемент Панели инструментов не найден! Тест провален!");
            driver.quit();
            return;
        }
        lstElements.clear();

        element = driver.findElement(By.cssSelector(".dropdown:nth-child(3) > .unclickable > .title"));
        action.moveToElement(element).perform();

        driver.findElement(By.cssSelector(".dropdown:nth-child(3) .single:nth-child(4) .title")).click();
        lstElements = driver.findElements(By.xpath("(//a[contains(text(),\'Создать проект\')])[3]"));
        if(lstElements.size() != 1)
        {
            System.out.println("Не найден элемент Создать проект! Тест провален");
            driver.quit();
            return;
        }
        lstElements.clear();
        driver.findElement(By.linkText("Создать проект")).click();

        driver.findElement(By.name("crm_project[name]")).sendKeys(projName);
        element = driver.findElement(By.xpath("//*[@class=\"select2-container select2 input-widget\"]/a/span[1]"));
        action.moveToElement(element).clickAndHold().perform();

        //Работает как-то странно... Три раза работает потом сбой, потом опять работает
        //Надо спросить на след. занятии почему так...
        element = driver.findElement(By.id("select2-drop-mask"));
        action.moveToElement(element).release().perform();
        driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul[2]/li[3]/div")).click();

        element = driver.findElement(By.name("crm_project[businessUnit]"));
        element.findElement(By.xpath("//option[. = 'Research & Development']")).click();

        element = driver.findElement(By.name("crm_project[curator]"));
        element.findElement(By.xpath("//option[. = 'Applanatest1 Applanatest1 Applanatest1']")).click();

        element = driver.findElement(By.name("crm_project[rp]"));
        element.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[3]/form/div[2]/div[3]/div/div[1]/div[2]/fieldset/div[2]/div[7]/div[2]/div/select/option[3]")).click();

        element = driver.findElement(By.name("crm_project[manager]"));
        element.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[3]/form/div[2]/div[3]/div/div[1]/div[2]/fieldset/div[2]/div[9]/div[2]/div/select/option[4]")).click();

        driver.findElement(By.xpath("//*[@name=\"crm_project\"]/div[1]/div/div/div[2]/div[1]/div[4]/button")).click();

        lstElements = driver.findElements(By.xpath("/html/body/div[2]/div/div[2]/div[3]/form/div[2]/div[3]/div/div[1]/div[2]/fieldset/div[1]/div[1]/div[2]/span"));

        //Как удалить проект так и не нашел,
        //поэтому решил сделать так
        if(IsProjCreated(lstElements))
        {
            Random rand = new Random();
            projName = "IvanFrm_" + rand.nextInt(50);
            driver.findElement(By.name("crm_project[name]")).sendKeys(projName);
        }
        lstElements.clear();
        driver.findElement(By.xpath("//*[@name=\"crm_project\"]/div[1]/div/div/div[2]/div[1]/div[4]/button")).click();
        lstElements = driver.findElements((By.cssSelector(".btn-group:nth-child(4) > .btn")));

        if(lstElements.size()!=1)
        {
            System.out.println("Проект не создан! Тест AddProjectTest провален");
            driver.quit();
            return;
        }
        else
        {
            System.out.println("Проект успешно создан! Тест AddProjectTest пройден");
            driver.quit();
            return;
        }


    }

    static void ClickAndWrite(WebElement element, String text)
    {
        element.click();
        element.sendKeys(text);
    }

    static boolean IsProjCreated(List<WebElement> lstElements)
    {
        if(lstElements.size()>0)
            return true;
        return false;
    }
}
