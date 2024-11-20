import driver.DriverManager;
import org.testng.annotations.DataProvider;
import pages.SignUpPage;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.Test;

import static utils.SignUpDataProvider.invalidLoginProvider;
import static utils.SignUpDataProvider.validLoginProvider;

@Log4j
public class TestSignUp extends BaseTest {
    @DataProvider(name = "InvalidLoginProvider")
    public Object[][] dpInvalidLogin() {
        return invalidLoginProvider();
    }
    @DataProvider(name = "ValidLoginProvider")
    public Object[][] dpValidLogin() {
        return validLoginProvider();
    }

   @Test
    public void verifyAllElementsTest() {
        new SignUpPage(DriverManager.getEmulatorDriver().getDriver()).verifyAllElements();
    }

  /** текст ожидаемой ошибки внести в тестовые данные*/
    @Test(dataProvider = "InvalidLoginProvider")
    public void verifyInvalidValueLoginTest(String login) {
        new SignUpPage(DriverManager.getEmulatorDriver().getDriver()).waitPageOpen()
                .sendKeysLogin(login).clickBtnLogin().verifyErrorInvalidLoginValue();
    }

    /** Уточнить ожидаемое поведение при попытке логина с валидными но несуществующими данными:
     * или текст ошибки или отсутствие ошибки*/
    @Test(dataProvider = "ValidLoginProvider")
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
}
