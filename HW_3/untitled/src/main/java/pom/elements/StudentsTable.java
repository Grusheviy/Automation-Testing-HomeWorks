package pom.elements;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;

    public class StudentsTable {
        public final ElementsCollection studentRows = $$(By.xpath("/html/body/div/main/div/div/div[1]/div[1]/table/tbody/tr"));

        public List<StudentsTableRow> getStudentRows() {
            return studentRows.stream().map(StudentsTableRow::new).toList();
        }
    }
