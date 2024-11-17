package baseObjects;

import config.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.qameta.allure.Description;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import utils.BaseUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Log4j
public class DriverManager {

    private String port;
    private AndroidDriver driver = null;

    public DriverManager() {
        setPort();
        if (this.driver == null) {
            this.driver = new AndroidDriver(getUrl(this.port), setOptions());
        }
    }


    private static URL getUrl(String port) {
        try {
            URL url = new URL(ConfigReader.APP_CONFIG.protocol() + ConfigReader.APP_CONFIG.ip() + port);
            System.out.println(url);
            return url;
        } catch (MalformedURLException e) {
            log.error("URL is not correct" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void setPort() {
        System.out.println("set port");
        for (String port : ConfigReader.APP_CONFIG.appiumPorts()) {
            log.debug("Try to execute command:: check if port " + port + " is in use");
            List<String> portOutput;
            portOutput = BaseUtils.executeCommandWithGrep(ConfigReader.EXECUTOR_CONFIG.portArray(), port);
            if ((portOutput.size()) == 0) {
                this.port = port;
                log.debug("Port " + this.port + " was set");
                try {
                    Runtime.getRuntime().exec(String.format(ConfigReader.EXECUTOR_CONFIG.startAppium(), this.port));
                    log.debug("Appium started on port " + this.port);
                } catch (IOException e) {
                    log.error("Appium not started on port " + this.port + e.getMessage());
                    e.printStackTrace();
                }
                break;
            } else {
                log.debug("Port " + port + " already is busy");
                if (port.equals("4731")) {
                   // DriverManager.closeDrivers();
                    BaseUtils.killProcess(ConfigReader.EXECUTOR_CONFIG.killAppium());
                    BaseUtils.executeCommand(ConfigReader.EXECUTOR_CONFIG.killAppium());
                   // DriverManager.initDrivers();
                }
            }
        }
    }

    private UiAutomator2Options setOptions() {
       // UiAutomator2Options options = new UiAutomator2Options();
                //.setApp("/Users/mariya/StudioProjects/qa-mobile/app/build/outputs/apk/debug/app-debug.apk");
        //options.setCapability("autoGrantPermissions", "true");
     //   options.setCapability("newCommandTimeout", 360000);
        // options.setCapability("systemPort", 4723);
     //   options.setCapability("waitForSelectorTimeout", 100);
     //   options.setCapability("actionAcknowledgmentTimeout", 100);
     //   options.setCapability("adbExecTimeout", 90000);
        //options.setCapability("forceAppLaunch", true);
        //options.setCapability("fullReset", true);

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName("Android Emulator");
        options.setAutomationName("UiAutomator2");
        return options;
    }

    @SneakyThrows
    //@Description(Steps.AndroidDriverWrapper.ACTIVATE_APP)
    public void activateApp() {
        String appBundleId = "com.alfabank.qapp";
       // log.debug("driverNumber: " + driverNumber + " Perform activate app: " + appBundleId + " via android driver");
        driver.activateApp(appBundleId);
        log.info("after activate " + driver.queryAppState(appBundleId).toString());
        //long startTime = System.currentTimeMillis();
      //  while (!driver.queryAppState(appBundleId).toString()
      //          .equals(ConfigReader.appConfig.bundleStateForeground())) {
       //     log.info(driver.queryAppState(appBundleId).toString());
        //    Thread.sleep(ConfigReader.constantsConfig.waitSecond());
       //     if ((System.currentTimeMillis() - startTime) > ConfigReader.constantsConfig.waitConnect()) {
       //         throw new RuntimeException("Activate app is failed");
       //     }
        }

    @SneakyThrows
    //@Description(Steps.AndroidDriverWrapper.ACTIVATE_APP)
    public void closeDriver() {
        String appBundleId = "com.alfabank.qapp";
        // log.debug("driverNumber: " + driverNumber + " Perform activate app: " + appBundleId + " via android driver");
        driver.quit();
        log.info("QUIT");
        //long startTime = System.currentTimeMillis();
        //  while (!driver.queryAppState(appBundleId).toString()
        //          .equals(ConfigReader.appConfig.bundleStateForeground())) {
        //     log.info(driver.queryAppState(appBundleId).toString());
        //    Thread.sleep(ConfigReader.constantsConfig.waitSecond());
        //     if ((System.currentTimeMillis() - startTime) > ConfigReader.constantsConfig.waitConnect()) {
        //         throw new RuntimeException("Activate app is failed");
        //     }
    }
}
