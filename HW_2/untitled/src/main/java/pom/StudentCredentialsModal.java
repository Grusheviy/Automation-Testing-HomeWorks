package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class StudentCredentialsModal {
    private final WebDriverWait wait;
    private final WebDriver driver;

    @FindBy(xpath = "//*[@id=\"app\"]/main/div/div/div[1]/div[1]/table/tbody/tr[1]/td[7]/button[1]")
    private WebElement credentialsOpenButton;

    @FindBy(xpath = "//*[@id=\"app\"]/main/div/div/div[2]/div[2]/div/div[2]/button/span")
    private WebElement closeCredentialsButton;
    @FindBy(xpath = "//*[@id=\"simple-title\"]")
    private WebElement studentCredentialsTitle;

    @FindBy(xpath = "//*[@id=\"simple-content\"]")
    private WebElement studentStaticContent;


    public StudentCredentialsModal(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void openCredentialsModal() {
        wait.until(ExpectedConditions.visibilityOf(credentialsOpenButton)).click();
    }
    public String getCredentialsModalTitle() {
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOf(studentCredentialsTitle));
            return titleElement.getText();
    }

    public String getCredentialsModalContent() {
        WebElement contentElement = wait.until(ExpectedConditions.visibilityOf(studentStaticContent));
            return contentElement.getText();
    }
    public void closeCredentialsModal() {
        wait.until(ExpectedConditions.visibilityOf(closeCredentialsButton)).click();
    }
}
