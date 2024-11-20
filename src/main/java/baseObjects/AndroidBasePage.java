package baseObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import config.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import lombok.extern.log4j.Log4j;

import java.time.Duration;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;
import static java.time.Duration.ofSeconds;

@Log4j
public abstract class AndroidBasePage  {

    public static String preId = ConfigReader.APP_CONFIG.bundleId();
    protected AndroidDriver driver;
    public AndroidBasePage(AndroidDriver driver) {
        this.driver = driver;
        WebDriverRunner.setWebDriver(driver);
    }

   // @Description(Descriptions.SelenideBasePage.VERIFY_VISIBLE_ELEMENT)
    public void verifyVisibleOfElement(SelenideElement element, String elementName) {
        log.debug(" Verify then element:: " + elementName + "  is visible. Element locator is:: " + element);
        element.shouldBe(Condition.visible, Duration.ofSeconds(5000));
    }

  //  @Description(Descriptions.SelenideBasePage.VERIFY_ELEMENT_CONTAINS_TEXT)
    public void verifyVisibleElementContainsText(SelenideElement element, String elementName, String partOfText) {
        log.debug(" Verify then visible element:: " + elementName + "  contains exact text:: " + partOfText +
                "  Element locator is::  " + element);
        (element.shouldBe(Condition.visible, Duration.ofSeconds(5)))
                .shouldHave(text(partOfText));
    }
    }

