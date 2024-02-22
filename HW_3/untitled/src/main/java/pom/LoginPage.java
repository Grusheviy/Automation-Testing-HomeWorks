    package pom;

    import com.codeborne.selenide.SelenideElement;
    import static com.codeborne.selenide.Condition.visible;
    import static com.codeborne.selenide.Selenide.$x;

    public class LoginPage {
        private SelenideElement usernameField =
                $x("//*[@id=\"login\"]/div[1]/label/input");
        private SelenideElement passwordField =
                $x("//*[@id=\"login\"]/div[2]/label/input");
        private SelenideElement loginButton =
                $x("//*[@id=\"login\"]/div[3]/button/span");
        private SelenideElement errorMessageBox =
                $x("/html/body/div/main/div/div/div[2]/p[1]");

        public LoginPage(){
        }

        public void login(String username, String password){
            typeUsernameInField(username);
            typePasswordInField(password);
            clickLoginButton();
        }

        private void typeUsernameInField(String username) {
            usernameField.shouldBe(visible).setValue(username);
        }

        private void typePasswordInField(String password) {
            passwordField.shouldBe(visible).setValue(password);

        }

        private void clickLoginButton() {
            loginButton.shouldBe(visible).click();
        }

        public String getErrorText() {
            return errorMessageBox.shouldBe(visible).text();
        }
    }
