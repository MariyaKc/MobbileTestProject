package pages;

import baseObjects.AndroidBasePage;
import config.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.Steps;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.appium.SelenideAppium.$$;

public class SignUpPage extends AndroidBasePage {
    public SignUpPage(AndroidDriver driver) {
        super(driver);
    }

    @Step(Steps.SignUpPage.WAIT_PAGE)
    public SignUpPage waitPageOpen() {
       verifyVisibleElementContainsText( $(By.id(preId + ConfigReader.SIGN_UP_CONFIG.title())),
               ConfigReader.SIGN_UP_CONFIG.elementNameTitle(), ConfigReader.SIGN_UP_CONFIG.titleText());
        return this;
    }

    @Step(Steps.SignUpPage.VERIFY_ALL_ELEMENTS)
    public void verifyAllElements() {
        verifyVisibleElementContainsText($(By.id(preId + ConfigReader.SIGN_UP_CONFIG.title())),
                ConfigReader.SIGN_UP_CONFIG.elementNameTitle(), ConfigReader.SIGN_UP_CONFIG.titleText());
        verifyVisibleAndEnableElementContainsText($(By.id(preId + ConfigReader.SIGN_UP_CONFIG.fieldLogin())),
                ConfigReader.SIGN_UP_CONFIG.elementNameFieldLogin(), ConfigReader.SIGN_UP_CONFIG.labelLoginFieldText());
        verifyVisibleAndEnableElementContainsText($(By.id(preId + ConfigReader.SIGN_UP_CONFIG.fieldPassword())),
                ConfigReader.SIGN_UP_CONFIG.elementNameFieldPassword(), ConfigReader.SIGN_UP_CONFIG.labelPasswordFieldText());
        verifyVisibleOfElement($(By.id(preId + ConfigReader.SIGN_UP_CONFIG.buttonShowPassword())),
                ConfigReader.SIGN_UP_CONFIG.elementNameButtonShowPassword());
        verifyVisibleAndClickableElementContainsText($(By.id(preId + ConfigReader.SIGN_UP_CONFIG.buttonLogin())),
                ConfigReader.SIGN_UP_CONFIG.elementNameButtonLogin(), ConfigReader.SIGN_UP_CONFIG.labelButtonLoginText());
    }

    @Step(Steps.SignUpPage.SEND_KEYS_LOGIN)
    public SignUpPage sendKeysLogin(String login) {
        sendKeys( $(By.id(preId + ConfigReader.SIGN_UP_CONFIG.fieldLogin())),
                ConfigReader.SIGN_UP_CONFIG.elementNameFieldLogin(),login);
        return this;
    }

    @Step(Steps.SignUpPage.SEND_KEYS_PASSWORD)
    public SignUpPage sendKeysPassword(String password) {
        sendKeys( $(By.id(preId + ConfigReader.SIGN_UP_CONFIG.fieldPassword())),
                ConfigReader.SIGN_UP_CONFIG.elementNameFieldPassword(),password);
        return this;
    }

    @Step(Steps.SignUpPage.VERIFY_ERROR_INVALID_LOGIN)
    public void verifyErrorInvalidLoginValue() {
        verifyVisibleElementContainsText( $(By.id(preId +ConfigReader.SIGN_UP_CONFIG.labelError())),
                ConfigReader.SIGN_UP_CONFIG.elementNameErrorLoginInvalidValue(),ConfigReader.SIGN_UP_CONFIG.errorInvalidDataText());
    }

    /**if case of existence error UserNotFound after try login with not exist data*/
    @Step(Steps.SignUpPage.VERIFY_ERROR_USER_NOT_FOUND)
    public void verifyErrorUserNotFound() {
        verifyVisibleElementContainsText( $(By.id(preId +ConfigReader.SIGN_UP_CONFIG.labelError())),
                ConfigReader.SIGN_UP_CONFIG.elementNameErrorUserNotFound(),ConfigReader.SIGN_UP_CONFIG.errorUserNotFoundText());
    }

    /**if case of not existence of error after try login with not exist data*/
    @Step(Steps.SignUpPage.VERIFY_NO_ERROR)
    public boolean isNotExistError() {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < ConfigReader.CONSTANTS_CONFIG.wait1S() * 5){
            if(!$$(By.id(preId + ConfigReader.SIGN_UP_CONFIG.labelError())).isEmpty()){
                break;
            }
        }
        return isNotExistElement( $(By.id(preId +ConfigReader.SIGN_UP_CONFIG.labelError())),  ConfigReader.SIGN_UP_CONFIG.elementNameErrorLoginInvalidValue());
    }

    @Step(Steps.SignUpPage.CLICK_BTN_LOGIN)
    public SignUpPage clickBtnLogin() {
        clickOnClickableElement( $(By.id(preId + ConfigReader.SIGN_UP_CONFIG.buttonLogin())), ConfigReader.SIGN_UP_CONFIG.elementNameButtonLogin());
        return this;
    }

   // @Step(Steps.SignUpPage.CLICK_BTN_LOGIN)
    public SignUpPage clickBtnShowOrHiddenPassword() {
        clickOnClickableElement($(By.id(preId + ConfigReader.SIGN_UP_CONFIG.buttonShowPassword())), ConfigReader.SIGN_UP_CONFIG.elementNameButtonShowPassword());
        return this;
    }

    private SignUpPage verifyPasswordAttributeValue(String value) {
        verifyAttributeWithValue($(By.id(preId + ConfigReader.SIGN_UP_CONFIG.fieldPassword())),
                ConfigReader.SIGN_UP_CONFIG.elementNameFieldPassword(),
                ConfigReader.SIGN_UP_CONFIG.attributePassword(), value);
        return this;
    }

    private SignUpPage verifyBtnPasswordAttributeValue(String value) {
        verifyAttributeWithValue($(By.id(preId + ConfigReader.SIGN_UP_CONFIG.buttonShowPassword())),
                ConfigReader.SIGN_UP_CONFIG.elementNameButtonShowPassword(),
                ConfigReader.SIGN_UP_CONFIG.attributeChecked(), value);
        return this;
    }

   // @Step(Steps.SignUpPage.CLICK_BTN_LOGIN)
    public SignUpPage verifyPasswordHidden() {
        verifyPasswordAttributeValue(ConfigReader.SIGN_UP_CONFIG.trueValue());
        return this;
    }

    public SignUpPage verifyPasswordShow() {
        verifyPasswordAttributeValue(ConfigReader.SIGN_UP_CONFIG.falseValue());
        return this;
    }

    public SignUpPage verifyBtnPasswordShow() {
        verifyBtnPasswordAttributeValue(ConfigReader.SIGN_UP_CONFIG.falseValue());
        return this;
    }

    public SignUpPage verifyBtnPasswordHidden() {
        verifyBtnPasswordAttributeValue(ConfigReader.SIGN_UP_CONFIG.trueValue());
        return this;
    }

}
