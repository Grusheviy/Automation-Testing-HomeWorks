package pom;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import pom.elements.StudentsTableRow;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class StudentPage {


    private SelenideElement usernameLinkInBar =
            $x("//*[@id=\"app\"]/main/nav/ul/li[3]/a");
    private SelenideElement createStudentButton =
            $x("//*[@id=\"create-btn\"]");
    private final SelenideElement editSutdentButton =
            $x("//*[@id=\"app\"]/main/div/div/div[1]/div[1]/table/tbody/tr[1]/td[7]/button[2]");
    private final SelenideElement studentNameField =
            $x("//*[@id=\"upsert-item\"]/div[1]/label/input");
    private final SelenideElement studentLoginField =
            $x("//*[@id=\"upsert-item\"]/div[5]/label/input");
    private final SelenideElement submitButtonOnModalWindow =
            $x("//*[@id=\"upsert-item\"]/div[8]/button/span");
    private final SelenideElement closeCreateStudentButton =
            $x("//*[@id=\"app\"]/main/div/div/div[3]/div[2]/div/div[1]/button");
    private final SelenideElement closeEditStudentButton =
            $x("//*[@id=\"app\"]/main/div/div/div[3]/div[2]/div/div[1]/button");
    private final SelenideElement closeEditStudentModalWindow =
            $x("//*[@id=\"app\"]/main/div/div/div[3]/div[2]/div/div[1]/button");
    private final SelenideElement profileLinkInNavBar =
            $x("//*[@id=\"app\"]/main/nav/ul/li[3]/a");
    private final SelenideElement profileButtonInProfileLink =
            $x("//*[@id=\"app\"]/main/nav/ul/li[3]/div/ul/li[1]/span[1]");

    private final ElementsCollection rowsInStudentTable =
            $$x("//table[@aria-label='Dummies list']/tbody/tr");

    public void createStudent(String studentName, String studentLogin) throws InterruptedException {
        createStudentButton.shouldBe(visible).click();
        studentNameField.shouldBe(visible).setValue(studentName);
        Selenide.sleep(2000);
        studentLoginField.shouldBe(visible).setValue(studentLogin);
        submitButtonOnModalWindow.shouldBe(visible).click();
        waitAndGetStudentTitleByText(studentName);
    }

    public SelenideElement waitAndGetStudentTitleByText(String title) {
        return $x(String.format("//td[text()='%s']", title)).shouldBe(visible);
    }

    public void editStudent(String newStudentName) throws InterruptedException {
        editSutdentButton.shouldBe(visible).click();
        Selenide.sleep(2000);
        studentNameField.shouldBe(visible).clear();
        studentNameField.shouldBe(visible).setValue(newStudentName);
        Selenide.sleep(2000);
        submitButtonOnModalWindow.shouldBe(visible).click();
        Selenide.sleep(2000);
        closeEditStudentModalWindow.shouldBe(visible).click();
        Selenide.sleep(2000);
    }

    public String getFirstGeneratedStudentName() {
        return rowsInStudentTable.shouldHave(sizeGreaterThan(0))
                .stream()
                .map(StudentsTableRow::new)
                .findFirst().orElseThrow().getStudentName();
    }

    public StudentsTableRow getStudentRowByName(String name) {
        return rowsInStudentTable.shouldHave(sizeGreaterThan(0))
                .stream()
                .map(StudentsTableRow::new)
                .filter(row -> row.getStudentName().equals(name))
                .findFirst().orElseThrow();
    }

    public void closeEditStudentModalWindow() {
        closeEditStudentButton.shouldBe(visible).click();
    }

    public String getUsernameLabelText() {
        return usernameLinkInBar.shouldBe(visible).text();
    }

    public void clickUsernameLabel() {
        actions().click(profileLinkInNavBar.shouldBe(visible)).perform();
    }

    public void clickProfileLink() {
        actions().click(profileButtonInProfileLink.shouldBe(visible)).perform();
    }
}
