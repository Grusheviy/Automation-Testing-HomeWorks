package pom.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class StudentsTable {
    private WebDriver driver;
    private WebDriverWait wait;

    public StudentsTable(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public List<StudentsTableRow> getStudentRows() {
        By studentRowLocator = By.xpath("/html/body/div/main/div/div/div[1]/div[1]/table/tbody/tr");
        List<WebElement> rowElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(studentRowLocator));
        return rowElements.stream().map(StudentsTableRow::new).collect(Collectors.toList());
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