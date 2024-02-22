package pom;


import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;


public class ProfilePage {
    private SelenideElement fullNameInAdditionalInfo =
            $x("//div[@class='content svelte-vyyzan']");
    private SelenideElement fullNameInAvatarSection =
            $x("//*[@id=\"app\"]/main/div/div/div[1]/div/div[1]/div/div/div/div[2]/h2");

    public String getFullNameInAdditionalInfo() {
        return fullNameInAdditionalInfo.shouldBe(visible).text();
    }

    public String getFullNameInAvatarSection() {
        return fullNameInAvatarSection.shouldBe(visible).text();
    }
}
