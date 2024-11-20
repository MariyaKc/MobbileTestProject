package baseObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import config.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import lombok.extern.log4j.Log4j;

import java.time.Duration;


import static com.codeborne.selenide.Condition.clickable;
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
        (element.shouldBe(Condition.visible, Duration.ofSeconds(15)))
                .shouldHave(text(partOfText));
    }

    public void verifyVisibleAndEnableElementContainsText(SelenideElement element, String elementName, String partOfText) {
        log.debug(" Verify then visible and enable element:: " + elementName + "  contains exact text:: " + partOfText +
                "  Element locator is::  " + element);
        (element.shouldBe(Condition.visible, Duration.ofSeconds(15)))
                .shouldHave(text(partOfText))
                .shouldBe(Condition.enabled, Duration.ofSeconds(10));
    }

    public void verifyVisibleAndClickableElementContainsText(SelenideElement element, String elementName, String partOfText) {
        log.debug(" Verify then visible and clickable element:: " + elementName + "  contains exact text:: " + partOfText +
                "  Element locator is::  " + element);
        (element.shouldBe(Condition.visible, Duration.ofSeconds(15)))
                .shouldHave(text(partOfText))
                .shouldBe(clickable, Duration.ofSeconds(10));
    }

    public void verifyEnableOfElement(SelenideElement element, String elementName) {
        log.debug( " Verify then element:: " + elementName + "  is enabled. Element locator is:: " + element);
        element.shouldBe(Condition.enabled, Duration.ofSeconds(15));
    }

    public void sendKeys(SelenideElement element, String elementName, String text) {
        log.debug( " Send keys::" + text + "  to the element:: " + elementName + "  Element locator is:: " + element);
        verifyEnableOfElement(element, elementName);
        element.sendKeys(text);
    }

    public void clickOnClickableElement(SelenideElement element, String elementName) {
        log.debug( " Verify then element::  " + elementName + "  is clickable and click. Element locator is:: " + element);
        element.shouldBe(clickable, Duration.ofSeconds(20)).click();
    }

    public boolean isNotExistElement(SelenideElement element, String elementName) {
        log.debug(" Verify then element:: " + elementName + " is not exist in DOM. Element locator is:: " + element);
        return element.has(Condition.not(Condition.exist));
    }

    }

