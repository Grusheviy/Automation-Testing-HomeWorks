import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import pom.LoginPage;
import pom.ProfilePage;
import pom.StudentCredentialsModal;
import pom.StudentPage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(AllureJunit5.class)
public class AbstractTest {
    public LoginPage loginPage;
    public StudentPage studentPage;

    public ProfilePage profilePage;
    public StudentCredentialsModal studentCredentialsModal;

    public static final String USERNAME = "GB202309fef085";
    public static final String PASSWORD = "17ef7a6395";

    @BeforeAll
    public static void setupClass() {
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.browserCapabilities = new DesiredCapabilities();

        Map<String, Object> options = new HashMap<>();
        options.put("enableVNC", true);
        options.put("enableLog", true);

        Configuration.browserCapabilities.setCapability("selenoid:options", options);

        String browserName = Configuration.browserCapabilities.getBrowserName();
        String browserVersion = (String) Configuration.browserCapabilities.getCapability(CapabilityType.VERSION);

        Allure.addAttachment("Browser Information", "Browser: " + browserName + ", Version: " + browserVersion);
    }

    @BeforeEach
    public void setupTest(){
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
