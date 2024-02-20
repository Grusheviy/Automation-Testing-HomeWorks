package pom.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class StudentsTable {
    private WebDriverWait wait;

    @FindBy(xpath = "/html/body/div/main/div/div/div[1]/div[1]/table/tbody/tr")
    private List<WebElement> studentRows;

    public StudentsTable(WebDriver driver, WebDriverWait wait) {
//        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public List<StudentsTableRow> getStudentRows() {
        wait.until(ExpectedConditions.visibilityOfAllElements(studentRows));
        return studentRows.stream().map(StudentsTableRow::new).collect(Collectors.toList());
    }

    public StudentsTableRow findStudentByName(String name) {
        return getStudentRows().stream()
                .filter(row -> row.getStudentName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public StudentsTableRow findStudentById(String studentId) {
        return getStudentRows().stream()
                .filter(row -> row.getID().equals(studentId))
                .findFirst()
                .orElse(null);
    }
}
