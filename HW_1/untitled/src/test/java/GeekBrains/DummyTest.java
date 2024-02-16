package GeekBrains;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DummyTest extends GBAbstractTest {

    private static final String DUMMY_NAME = "DubiDubi";
    private static final String DUMMY_LOGIN = "DubiDubi";


    @Test
    public void testCreateDummy() throws InterruptedException{
        login();

        // Открытие модального окна для создания dummy
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Thread.sleep(5000); // Задержка в 5 секунды
        driver.findElement(By.xpath("//*[@id='create-btn']")).click();

//        System.out.println("DUMMY_NAME: " + DUMMY_NAME);
//        System.out.println("DUMMY_LOGIN: " + DUMMY_LOGIN);

        // Ввод данных для dummy
        driver.findElement(By.xpath
                ("//*[@id=\"upsert-item\"]/div[1]/label/input"))
                .sendKeys(DUMMY_NAME);
        driver.findElement(By.xpath
                ("//*[@id=\"upsert-item\"]/div[5]/label/input"))
                .sendKeys(DUMMY_LOGIN);

        // Нажатие кнопки SAVE
        driver.findElement(By.xpath("//*[@id=\"upsert-item\"]/div[8]/button/span")).click();

        // Проверка, что dummy с именем появился в таблице
        By dummyTitleLocator = By.xpath("//*[@id=\"app\"]/main/div/div/div[1]/div[1]");
        assertTrue(driver.findElement(dummyTitleLocator).isDisplayed(),
                "Dummy с именем '" + DUMMY_NAME + "' не появился в таблице.");
        Thread.sleep(5000); // Задержка в 5 секунды

    }
}
