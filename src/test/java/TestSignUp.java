import config.ConfigReader;
import driver.DriverManager;
import org.testng.annotations.DataProvider;
import pages.SignUpPage;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.Test;
import pages.SuccessSignUpPage;
import utils.Steps;

import static utils.SignUpDataProvider.invalidLoginProvider;
import static utils.SignUpDataProvider.validLoginProvider;

@Log4j
public class TestSignUp extends BaseTest {
    @DataProvider(name = Steps.DataProviderName.INVALID_LOGIN_PROVIDER)
    public Object[][] dpInvalidLogin() {
        return invalidLoginProvider();
    }
    @DataProvider(name = Steps.DataProviderName.VALID_LOGIN_PROVIDER)
    public Object[][] dpValidLogin() {
        return validLoginProvider();
    }

    @Test(priority = 1)
    public void verifyAllElementsTest() {
        new SignUpPage(DriverManager.getEmulatorDriver().getDriver()).verifyAllElements();
    }

  /** текст ожидаемой ошибки внести в тестовые данные*/
    @Test(priority = 2, dataProvider = Steps.DataProviderName.INVALID_LOGIN_PROVIDER)
    public void verifyInvalidValueLoginTest(String login) {
        new SignUpPage(DriverManager.getEmulatorDriver().getDriver()).waitPageOpen()
                .sendKeysLogin(login).clickBtnLogin().verifyErrorInvalidLoginValue();
    }

    /** Уточнить ожидаемое поведение при попытке логина с валидными но несуществующими данными:
     * или текст ошибки или отсутствие ошибки*/
    @Test(dataProvider = Steps.DataProviderName.VALID_LOGIN_PROVIDER)
    public void verifyValidValueLoginTest(String login) {
        //или текст ошибки
        new SignUpPage(DriverManager.getEmulatorDriver().getDriver()).waitPageOpen()
                .sendKeysLogin(login).sendKeysPassword("Password").clickBtnLogin()
                .verifyErrorUserNotFound();
        //или отсутствие ошибки
        assert new SignUpPage(DriverManager.getEmulatorDriver().getDriver()).waitPageOpen()
                .sendKeysLogin(login).sendKeysPassword("Password").clickBtnLogin()
                .isNotExistError(): "The is error after try login with valid but not existence data";
    }

    @Test(priority = 3)
    public void verifyHiddenPasswordTest() {
        new SignUpPage(DriverManager.getEmulatorDriver().getDriver()).waitPageOpen()
                .sendKeysPassword(ConfigReader.CREDENTIALS_CONFIG.password())
                .verifyPasswordHidden().verifyBtnPasswordShow();
    }

    @Test(priority = 4, dependsOnMethods = "verifyHiddenPasswordTest")
    public void verifyShowPasswordTest() {
        new SignUpPage(DriverManager.getEmulatorDriver().getDriver()).waitPageOpen()
                .clickBtnShowOrHiddenPassword()
                .verifyPasswordShow().verifyBtnPasswordHidden();
    }

    @Test(priority = 5)
    public void successLoginTest() {
        new SignUpPage(DriverManager.getEmulatorDriver().getDriver()).waitPageOpen()
                .sendKeysLogin(ConfigReader.CREDENTIALS_CONFIG.login())
                .sendKeysPassword(ConfigReader.CREDENTIALS_CONFIG.password())
                .clickBtnLogin();
        new SuccessSignUpPage(DriverManager.getEmulatorDriver().getDriver())
                .waitPageOpen()
                .isPageOpen();
    }
}
