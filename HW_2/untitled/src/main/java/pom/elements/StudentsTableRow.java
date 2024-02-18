package pom.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class StudentsTableRow {
    private WebElement root;

    public StudentsTableRow(WebElement root){
        this.root = root;
    }

    public String getID(){
        return root.findElement(By.xpath("//td[1]")).getText();
    }

    public String getStudentName() {
        return root.findElement(By.xpath("//td[2]")).getText();
    }
}
