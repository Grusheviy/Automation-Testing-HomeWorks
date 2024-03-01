package pom;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.actions;


public class ProfilePage {
    private final SelenideElement fullNameInAdditionalInfo =
            $x("//*[@id=\"app\"]/main/div/div/div[1]/div/div[2]/div/div[1]/div[2]");
    private final SelenideElement fullNameInAvatarSection =
            $x("//*[@id=\"app\"]/main/div/div/div[1]/div/div[1]/div/div/div/div[2]/h2");
    private final SelenideElement birthDateInAdditionalInfo =
            $x("//*[@id=\"app\"]/main/div/div/div[1]/div/div[2]/div/div[2]/div[2]");
    public final SelenideElement editProfileButton =
            $x("//*[@id=\"app\"]/main/div/div/div[1]/div/div[1]/div/div/div/div[3]/div/button[2]");
    private final SelenideElement birthdateInput = $x("//input[@type='date']");
    private final SelenideElement saveButton = $x("//*[@id=\"update-item\"]/div[8]/button");

    public String getFullNameInAdditionalInfo() {
        return fullNameInAdditionalInfo.shouldBe(visible).text();
    }

    public String getFullNameInAvatarSection() {
        return fullNameInAvatarSection.shouldBe(visible).text();
    }

    public String getBirthDateInAdditionalInfo() {
        return birthDateInAdditionalInfo.shouldBe(visible).text();
    }

    public void clickEditProfileButton(){
        editProfileButton.click();
    }

    public void setBirthdate(String formattedBirthdate) {
        birthdateInput.setValue(formattedBirthdate);
    }

    public void saveProfileChanges() {
        saveButton.click();
    }

    public void updateBirthdateOneYearAhead() {
        String currentBirthdateStr = getBirthDateInAdditionalInfo();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate currentBirthdate = LocalDate.parse(currentBirthdateStr, formatter);

        LocalDate nextYearDate = currentBirthdate.plusYears(1);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MM.dd.yyyy");
        String finalFormattedBirthdate = nextYearDate.format(outputFormatter);

        setBirthdate(finalFormattedBirthdate);
    }

}
