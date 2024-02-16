package GeekBrains;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public abstract class GBAbstractTest {

    protected WebDriver driver;
    private static final String USERNAME = "GB202309fef085";
    private static final String PASSWORD = "17ef7a6395";

    @BeforeAll
    public static void setupClass(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
    }

    @BeforeEach
    public void setupTest(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void login() {
//        System.out.println("USERNAME: " + USERNAME);
//        System.out.println("PASSWORD: " + PASSWORD);

        driver.get("https://test-stand.gb.ru/login");
        driver.findElement(By.xpath("/html/body/div/main/div/div/div/form/div[1]/label/input")).sendKeys(USERNAME);
        driver.findElement(By.xpath("/html/body/div/main/div/div/div/form/div[2]/label/input")).sendKeys(PASSWORD);
        driver.findElement(By.xpath("/html/body/div/main/div/div/div/form/div[3]/button/span")).click();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
