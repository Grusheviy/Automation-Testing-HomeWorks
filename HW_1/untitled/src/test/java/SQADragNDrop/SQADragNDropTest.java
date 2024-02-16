package SQADragNDrop;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SQADragNDropTest extends SQAGlobalAbstract {

    @Test
    public void dragNDropTest() throws InterruptedException {
        goToSQA();
        Thread.sleep(5000);

        WebElement iframeElement = driver.findElement(By.className("demo-frame"));

        driver.switchTo().frame(iframeElement);

        WebElement dragElement = driver.findElement(By.xpath("//*[@id=\"gallery\"]/li[1]"));
        WebElement dropElement = driver.findElement(By.xpath("//*[@id='trash']"));

        Actions actions = new Actions(driver);

        actions.dragAndDrop(dragElement, dropElement).build().perform();

        Thread.sleep(2000);
    }
}
