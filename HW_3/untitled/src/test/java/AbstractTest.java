import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pom.LoginPage;
import pom.ProfilePage;
import pom.StudentCredentialsModal;
import pom.StudentPage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;



public class AbstractTest {
    public LoginPage loginPage;
    public StudentPage studentPage;

    public ProfilePage profilePage;
    public StudentCredentialsModal studentCredentialsModal;

    public static final String USERNAME = "GB202309fef085";
    public static final String PASSWORD = "17ef7a6395";


    @BeforeEach
    public void setupTest(){
        //Selenide 6.12.3 использует драйвер 114
        //у меня хром 122.0.6261.57
        //указываю явный ChromeDriver 122.0.6261.57
        System.setProperty("webdriver.chrome.driver",
                "D:\\Study\\AutomatizationTesting\\Automatization_Java\\HomeWorks\\HW_3\\untitled\\src\\test\\resources\\chromedriver.exe");

        loginPage = Selenide.open("https://test-stand.gb.ru/login", LoginPage.class);
        studentPage = Selenide
                .open("https://test-stand.gb.ru/admin/student?page=1&limit=10",
                        StudentPage.class);
        profilePage = Selenide
                .open("https://test-stand.gb.ru/user/profile",
                        ProfilePage.class);
        studentCredentialsModal = new StudentCredentialsModal();

    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    public void takeScreenshot(String testName) throws IOException {
        String directoryPath = "src\\test\\resources";
        String fileName = "screenshot_" + testName + "_" + System.currentTimeMillis() + ".png";

        String filePath = directoryPath + fileName;

        byte[] screenshotBytes = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
        Files.write(Path.of(filePath), screenshotBytes);
    }

}
