package pages;

import baseObjects.AndroidBasePage;
import config.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import utils.Steps;

import static com.codeborne.selenide.Selenide.$;

@Log4j
public class SuccessSignUpPage  extends AndroidBasePage {
    public SuccessSignUpPage(AndroidDriver driver) {
        super(driver);
    }

   // @Step(Steps.SignUpPage.WAIT_PAGE)
    public SuccessSignUpPage waitPageOpen() {
        verifyVisibleOfElement($(By.xpath(ConfigReader.SUCCESS_SIGN_UP_CONFIG.titleSuccessSignUpX())),
                ConfigReader.SUCCESS_SIGN_UP_CONFIG.elementNameTitleSuccessSignUp());
        return this;
    }

    public boolean isPageOpen() {
      return  isElementContainsText($(By.xpath(ConfigReader.SUCCESS_SIGN_UP_CONFIG.titleSuccessSignUpX())),
                       ConfigReader.SUCCESS_SIGN_UP_CONFIG.elementNameTitleSuccessSignUp(), ConfigReader.SUCCESS_SIGN_UP_CONFIG.textSuccessSignUpTitle());
    }
}
