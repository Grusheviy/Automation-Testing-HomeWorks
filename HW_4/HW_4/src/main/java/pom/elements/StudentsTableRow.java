package pom.elements;

import com.codeborne.selenide.SelenideElement;

public class StudentsTableRow {
    public SelenideElement root;

    public StudentsTableRow(SelenideElement root) {
        this.root = root;
    }

    public String getID() {
        return root.$x(".//td[1]").getText();
    }
    public String getTitle() {
        return root.$x("").getText();
    }

    public String getStudentName() {
        return root.$x(".//td[2]").getText();
    }
}
