package pages;

import baseObjects.AndroidBasePage;
import config.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

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
   
}
