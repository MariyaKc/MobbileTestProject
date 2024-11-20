package pages;

import baseObjects.AndroidBasePage;
import config.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.appium.SelenideAppium.$$;

public class SignUpPage extends AndroidBasePage {
    public SignUpPage(AndroidDriver driver) {
        super(driver);
    }

   // @Step(Steps.AboutAppPage.WAIT_PAGE)
    public SignUpPage waitPageOpen() {
       verifyVisibleElementContainsText( $(By.id(preId + ConfigReader.SIGN_UP_CONFIG.title())),
               "TITLE", ConfigReader.SIGN_UP_CONFIG.titleText());
        return this;
    }

    public void verifyAllElements() {
        verifyVisibleElementContainsText($(By.id(preId + ConfigReader.SIGN_UP_CONFIG.title())),
                "TITLE", ConfigReader.SIGN_UP_CONFIG.titleText());
        verifyVisibleAndEnableElementContainsText($(By.id(preId + ConfigReader.SIGN_UP_CONFIG.fieldUsername())),
                "FieldUserName", ConfigReader.SIGN_UP_CONFIG.labelLoginFieldText());
        verifyVisibleAndEnableElementContainsText($(By.id(preId + ConfigReader.SIGN_UP_CONFIG.fieldPassword())),
                "FieldPassword", ConfigReader.SIGN_UP_CONFIG.labelPasswordFieldText());
        verifyVisibleOfElement($(By.id(preId + ConfigReader.SIGN_UP_CONFIG.buttonShowPassword())),
                "ShowPasswordButton");
        verifyVisibleAndClickableElementContainsText($(By.id(preId + ConfigReader.SIGN_UP_CONFIG.buttonLogin())),
                "ButtonLogin", ConfigReader.SIGN_UP_CONFIG.labelButtonLoginText());
    }

    public SignUpPage sendKeysLogin(String login) {
        sendKeys( $(By.id(preId + ConfigReader.SIGN_UP_CONFIG.fieldUsername())),
                "fieldUsr",login);
        return this;
    }

    public SignUpPage sendKeysPassword(String password) {
        sendKeys( $(By.id(preId + ConfigReader.SIGN_UP_CONFIG.fieldPassword())),
                "fieldPassword",password);
        return this;
    }

    public void verifyErrorInvalidLoginValue() {
        verifyVisibleElementContainsText( $(By.id(preId +ConfigReader.SIGN_UP_CONFIG.labelError())),
                "errorlogin","Введены неверные данные");
    }

    //if case of existence error UserNotFound after try login with not exist data
    public void verifyErrorUserNotFound() {
        verifyVisibleElementContainsText( $(By.id(preId +ConfigReader.SIGN_UP_CONFIG.labelError())),
                "errorlogin","Пользователь не найден");
    }

    //if case of not existence of error after try login with not exist data
    public boolean isNotExistError() {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 5000){
            if($$(By.id(preId +ConfigReader.SIGN_UP_CONFIG.labelError())).size() > 0){
                break;
            }
        }
        return isNotExistElement( $(By.id(preId +ConfigReader.SIGN_UP_CONFIG.labelError())), "errorlogin");
    }

    public SignUpPage clickBtnLogin() {
        clickOnClickableElement( $(By.id(preId + ConfigReader.SIGN_UP_CONFIG.buttonLogin())), "btn");
        return this;
    }

}
