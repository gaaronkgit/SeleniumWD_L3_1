package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddContactTest
{
    static void RunTest()
    {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebElement element;
        Actions action = new Actions(driver);

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

        element = driver.findElement(By.xpath("//span[contains(.,\'Контрагенты\')]"));
        action.moveToElement(element).perform();
        driver.findElement(By.xpath("//span[contains(.,\'Контактные лица\')]")).click();

        element = driver.findElement(By.xpath("//*[@id=\"container\"]/div[1]/div/div/div[2]/div/div/a"));
        element.click();

        driver.findElement(By.name("crm_contact[lastName]")).sendKeys("Ivan");
        driver.findElement(By.name("crm_contact[firstName]")).sendKeys("Ivanov");

        element = driver.findElement(By.cssSelector(".select2-chosen"));
        action.moveToElement(element).clickAndHold().perform();

        element = driver.findElement(By.id("select2-drop-mask"));
        action.moveToElement(element).release().perform();

        driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul[2]/li[3]/div")).click();


        driver.findElement(By.name("crm_contact[jobTitle]")).sendKeys("Manager");
        driver.findElement(By.xpath("//*[@name=\"crm_contact\"]/div[1]/div/div/div[2]/div[1]/div[4]/button")).click();

        lstElements.clear();
        lstElements = driver.findElements(By.cssSelector(".message"));
        if(lstElements.size()!=1)
        {
            System.out.println("Не удалось найти сообщение о сохранении! Тест AddContact провален!");
            driver.quit();
            return;
        }
        else
        {
            System.out.println("Тест AddContact успешно завершен!");
            driver.quit();
        }
    }
    static void ClickAndWrite(WebElement element, String text)
    {
        element.click();
        element.sendKeys(text);
    }

}
