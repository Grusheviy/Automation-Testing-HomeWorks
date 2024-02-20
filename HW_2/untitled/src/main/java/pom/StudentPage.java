package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pom.elements.StudentsTable;
import pom.elements.StudentsTableRow;


public class StudentPage {
    private final WebDriverWait wait;
    private final StudentsTable studentsTable;
    @FindBy(xpath = "//*[@id=\"app\"]/main/nav/ul/li[3]/a")
    private WebElement usernameLinkInBar;

    @FindBy(xpath = "//*[@id=\"create-btn\"]")
    private WebElement createStudentButton;

    @FindBy(xpath = "/html/body/div/main/div/div/div[1]/div[1]/table/tbody/tr[1]/td[7]/button[2]")
    private WebElement editSutdentButton;

    @FindBy(xpath = "/html/body/div/main/div/div/div[3]/div[2]/div/div[2]/div/form/div[1]/label/input")
    private WebElement studentNameField;

    @FindBy(xpath = "/html/body/div/main/div/div/div[3]/div[2]/div/div[2]/div/form/div[5]/label/input")
    private WebElement studentLoginField;

    @FindBy(xpath = "/html/body/div/main/div/div/div[3]/div[2]/div/div[2]/div/form/div[8]/button/span")
    private WebElement submitButtonOnModalWindow;

    @FindBy(xpath = "/html/body/div/main/div/div/div[3]/div[2]/div/div[1]/button")
    private WebElement closeCreateStudentButton;

    @FindBy(xpath = "/html/body/div/main/div/div/div[3]/div[2]/div/div[1]/button")
    private WebElement closeEditStudentModalWindow;

    private String lastEditedStudentId;


    public StudentPage(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        PageFactory.initElements(driver, this);
        this.studentsTable = new StudentsTable(driver, wait);
    }

    public void createStudent(String StudentName, String studentLogin) throws InterruptedException {
        StudentsTableRow studentRowBeforeEdit = studentsTable.findStudentByName(StudentName);
        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOf(createStudentButton)).click();
        wait.until(ExpectedConditions.visibilityOf(studentNameField)).sendKeys(StudentName);
        wait.until(ExpectedConditions.visibilityOf(studentLoginField)).sendKeys(studentLogin);
        submitButtonOnModalWindow.click();
        waitAndGetStudentTitleByText(StudentName);

        // После сохранения, получаем ID студента
        String studentId = (studentRowBeforeEdit != null) ? studentRowBeforeEdit.getID() : null;
    }

    public void waitAndGetStudentTitleByText(String title){
        String xpath = String.format("//td[text()='%s']", title);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public void editStudent(String NewStudentName) throws InterruptedException {
        StudentsTableRow studentRowBeforeEdit = studentsTable.findStudentByName(NewStudentName);

        wait.until(ExpectedConditions.visibilityOf(editSutdentButton)).click();

        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(studentNameField)).clear();
        wait.until(ExpectedConditions.visibilityOf(studentNameField)).sendKeys(NewStudentName);

        Thread.sleep(2000);
        submitButtonOnModalWindow.click();

        Thread.sleep(2000);

        wait.until(ExpectedConditions.visibilityOf(closeEditStudentModalWindow)).click();
        Thread.sleep(2000);

        String editedStudentName = (studentRowBeforeEdit != null) ? studentRowBeforeEdit.getStudentName() : null;
    }

    public String getStudentNameById(String studentId) {
        StudentsTableRow student = studentsTable.findStudentById(studentId);
        if (student != null) {
            return student.getStudentName();
        } else {
            throw new RuntimeException("Student with ID " + studentId + " not found");
        }
    }
    public String getStudentIdByName(String name) {
        StudentsTableRow student = studentsTable.findStudentByName(name);
        if (student != null) {
            return student.getID();
        } else {
            throw new RuntimeException("Student with name " + name + " not found");
        }
    }

    public void closeEditStudentModalWindow(){
        wait.until(ExpectedConditions.visibilityOf(closeCreateStudentButton)).click();
    }
}
