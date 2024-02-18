    package pom;

    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.FindBy;
    import org.openqa.selenium.support.PageFactory;
    import org.openqa.selenium.support.ui.ExpectedConditions;
    import org.openqa.selenium.support.ui.WebDriverWait;

    public class LoginPage {
        private final WebDriverWait wait;

        @FindBy(xpath = "//*[@id=\"login\"]/div[1]/label/input")
        private WebElement usernameField;

        @FindBy(xpath = "//*[@id=\"login\"]/div[2]/label/input")
        private WebElement passwordField;

        @FindBy(xpath = "//*[@id=\"login\"]/div[3]/button/span")
        private WebElement loginButton;

        public LoginPage(WebDriver driver, WebDriverWait wait){
            PageFactory.initElements(driver, this);
            this.wait = wait;
        }

        public void login(String username, String password){
            typeUsernameInField(username);
            typePasswordInField(password);
            clickLoginButton();
        }

        private void typeUsernameInField(String username) {
            wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
        }

        private void typePasswordInField(String password) {
            wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);

        }

        private void clickLoginButton() {
            wait.until(ExpectedConditions.visibilityOf(loginButton)).click();
        }
    }
