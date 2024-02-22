package pom;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;


public class StudentCredentialsModal {
    private SelenideElement credentialsOpenButton =
            $x("//*[@id=\"app\"]/main/div/div/div[1]/div[1]/table/tbody/tr[1]/td[7]/button[1]");
    private SelenideElement closeCredentialsButton =
            $x("//*[@id=\"app\"]/main/div/div/div[2]/div[2]/div/div[2]/button/span");
    private SelenideElement studentCredentialsTitle =
            $x("//*[@id=\"simple-title\"]");
    private SelenideElement studentStaticContent =
            $x("//*[@id=\"simple-content\"]");

    public void openCredentialsModal() {
        credentialsOpenButton.shouldBe(visible).click();
    }

    public String getCredentialsModalTitle() {
        return studentCredentialsTitle.shouldBe(visible).text();
    }

    public String getCredentialsModalContent() {
        return studentStaticContent.shouldBe(visible).text();
    }

    public void closeCredentialsModal() {
        closeCredentialsButton.shouldBe(visible).click();
    }
}
