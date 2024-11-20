package driver;

import config.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriverException;
import utils.BaseUtils;

import java.net.MalformedURLException;
import java.net.URL;
/** добавить авто старт аппиума и проброс портов*/
@Log4j
public class EmulatorDriver {
    private AndroidDriver driver = null;
    public EmulatorDriver () {
        if (driver == null) {
            String port = BaseUtils.setAppiumPort();
            BaseUtils.startEmulator();
            driver = new AndroidDriver(getUrl(port), setOptions());
            log.info("Driver initialized successfully.");
        }
    }

    public AndroidDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("The driver has not been initialized. Call initDriver() before use..");
        }
        return driver;
    }


    public UiAutomator2Options setOptions() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(ConfigReader.APP_CONFIG.platformName());
        options.setDeviceName(ConfigReader.APP_CONFIG.deviceName());
        options.setApp(ConfigReader.APP_CONFIG.pathFromApp());
        return options;
    }

    public URL getUrl(String port) {
        try {
            URL url = new URL(ConfigReader.APP_CONFIG.protocol() + ConfigReader.APP_CONFIG.ip() + port);
            log.info("Url is: " + url);
            return url;
        } catch (MalformedURLException e) {
            log.error("URL is not correct" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**доделать*/
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("Драйвер закрыт.");
        }
    }


    @SneakyThrows
    public void launchApp() {
        String appBundleId = ConfigReader.APP_CONFIG.bundleId();
        driver.activateApp(appBundleId);
        log.info("after activate " + driver.queryAppState(appBundleId).toString());
        long startTime = System.currentTimeMillis();
        while (!driver.queryAppState(appBundleId).toString()
                .equals(ConfigReader.APP_CONFIG.bundleStateForeground())) {
            log.info(driver.queryAppState(appBundleId).toString());
            Thread.sleep(ConfigReader.CONSTANTS_CONFIG.wait1S());
            if ((System.currentTimeMillis() - startTime) > ConfigReader.CONSTANTS_CONFIG.waitForConditionLaunch()) {
                throw new RuntimeException("Activate app is failed");
            }
        }
       log.info("The application is running.");
    }


    public void closeApp() {
        String appBundleId = ConfigReader.APP_CONFIG.bundleId();
        try {
            log.debug(" Perform terminate app: " + appBundleId + " via android driver");
            if (driver != null) {
                log.info("terminate: " + driver.terminateApp(appBundleId));
                log.info("App is closed");
            } else {
                log.error("Driver is null. Cannot terminate app: " + appBundleId);
                throw new RuntimeException("Driver is null.");
            }
        } catch (WebDriverException e) {
            log.error("Caught WebDriverException: " + e.getMessage() + " , in terminate app");
        }
    }

    public void removeApp() {
        if (driver.isAppInstalled(ConfigReader.APP_CONFIG.bundleId())) {
            driver.removeApp(ConfigReader.APP_CONFIG.bundleId());
            log.info("The application was successfully removed");
        } else {
            log.info("Application not installed");
        }
    }
}
