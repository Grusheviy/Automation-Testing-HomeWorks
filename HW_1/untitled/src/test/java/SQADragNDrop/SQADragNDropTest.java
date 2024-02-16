package SQADragNDrop;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SQADragNDropTest extends SQAGlobalAbstract {

    @Test
    public void dragNDropTest() throws InterruptedException, IOException {
        goToSQA();
        Thread.sleep(5000);

        WebElement iframeElement = driver.findElement(By.className("demo-frame"));

        driver.switchTo().frame(iframeElement);

        WebElement dragElement = driver.findElement(By.xpath("//*[@id=\"gallery\"]/li[1]"));
        WebElement dropElement = driver.findElement(By.xpath("//*[@id='trash']"));

        Actions actions = new Actions(driver);

        actions.dragAndDrop(dragElement, dropElement).build().perform();

        Thread.sleep(2000);

        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(screenshotFile.toPath(), new File("src/test/resources/SQADragNDropTest.png").toPath());

        Thread.sleep(2000);
    }
}
