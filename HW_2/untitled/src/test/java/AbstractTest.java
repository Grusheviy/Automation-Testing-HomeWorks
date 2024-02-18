import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pom.LoginPage;
import pom.StudentCredentialsModal;
import pom.StudentPage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.nio.file.StandardCopyOption;


public class AbstractTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;
    public StudentPage studentPage;

    public StudentCredentialsModal studentCredentialsModal;
    public static final String USERNAME = "GB202309fef085";
    public static final String PASSWORD = "17ef7a6395";




    @BeforeAll
    public static void setupClass(){
        System.setProperty("webDriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        System.setProperty("USERNAME", USERNAME);
        System.setProperty("PASSWORD", PASSWORD);
    }

    @BeforeEach
    public void setupTest(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://test-stand.gb.ru/login");
        loginPage = new LoginPage(driver, wait);
        studentCredentialsModal = new StudentCredentialsModal(driver, wait);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    public void takeScreenshot(String testName) throws IOException {
        String directoryPath = "src\\test\\resources";
        String fileName = "screenshot_" + testName + "_" + System.currentTimeMillis() + ".png";

        String filePath = directoryPath + fileName;

        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Files.write(Path.of(filePath), screenshotBytes);
    }

}
