import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pom.StudentPage;
import pom.elements.StudentsTable;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class GBStandTest extends AbstractTest {

    /* 1) Логин в систему не вводя ни логин, ни пароль.
     На странице появляется ошибка, нужно проверить её текст
     */
    @Test
    public void loginWithoutUsernamePasswordExpectedErrorTest() throws IOException {
        driver.get("https://test-stand.gb.ru/login");
        loginPage.login("", "");

        WebElement errorMessageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("/html/body/div/main/div/div/div[2]/p[1]")));

        String errorMessage = errorMessageElement.getText();
        assertTrue(errorMessage.contains("Invalid credentials."));
        takeScreenshot("loginWithoutUsernamePasswordExpectedErrorTest");
    }


/*
2) Более сложный тест на редактирование dummy (здесь будет важно добавить методы для поиска ID dummy по имени,
 чтобы сохранить его в переменную и потом в завершении теста использовать для проверки). Сценарий теста:
- Открываем модальное окно для редактирования dummy
- Редактируем имя
- Сохраняем
- Проверяем в таблице Dummies по ID, что имя изменилось
 */
@Test
public void findStudentNameByIdAfterRenameTest() throws IOException, InterruptedException {
    driver.get("https://test-stand.gb.ru/login");
    loginPage.login(USERNAME, PASSWORD);
    studentPage = new StudentPage(driver, wait);

    String testStudentName = "NewStudent" + System.currentTimeMillis();
    String testStudentLogin = "NewStudent" + System.currentTimeMillis();
    studentPage.createStudent(testStudentName, testStudentLogin);

    studentPage.closeEditStudentModalWindow();
    Thread.sleep(2000);

    String originalStudentId = studentPage.getStudentIdByName(testStudentName);

    String newStudentName = "EditedStudent" + System.currentTimeMillis();
    studentPage.editStudent(newStudentName);

    String editedStudentId = studentPage.getStudentIdByName(newStudentName);

    String editedStudentName = studentPage.getStudentNameById(originalStudentId);
    assertEquals(newStudentName, editedStudentName);

    System.out.println("Original Student Name: " + testStudentName);
    System.out.println("Edited Student Name: " + newStudentName);
    System.out.println("Original Student ID: " + originalStudentId);
    System.out.println("Edited Student ID: " + editedStudentId);

    takeScreenshot("editStudentAndGetNameByID");
}

/*
3) Тест на проверку модального окна Credentials
- Открыть модальное окно на созданном Dummy
- Проверить заголовок и статический контент
 */
@Test
public void credentialsModalTest() throws InterruptedException, IOException {
    driver.get("https://test-stand.gb.ru/login");
    loginPage.login(USERNAME, PASSWORD);
    studentPage = new StudentPage(driver, wait);

    Thread.sleep(2000);
    studentCredentialsModal.openCredentialsModal();

    String actualTitle = studentCredentialsModal.getCredentialsModalTitle();
    String actualContent = studentCredentialsModal.getCredentialsModalContent();

    String expectedTitle = "Dummy credentials";
    String expectedContent = "Login: NewStudent1708262327247\n" +
            "PW: 39a93bce18";


    assertNotNull(actualContent);
    assertNotNull(actualTitle);
    assertEquals(expectedTitle, actualTitle);


    Thread.sleep(2000);
    takeScreenshot("credentialsModalTest");
    studentCredentialsModal.closeCredentialsModal();
}
}