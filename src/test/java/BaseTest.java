
import driver.DriverManager;
import config.ConfigReader;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.BaseUtils;

@Log4j
public abstract class BaseTest {

   // @Step(Descriptions.SelenideBaseTest.INIT_DRIVERS)
    @BeforeSuite
    @SneakyThrows
    public void initDrivers() {
        log.debug("BEFORE SUITE");
         //BaseUtils.executeCommand(ConfigReader.EXECUTOR_CONFIG.killAppium());
         DriverManager.initEmulatorDriver();
         DriverManager.getEmulatorDriver().launchApp();


    }

    //@Step(Descriptions.SelenideBaseTest.CLOSE)
    @AfterSuite
    public void close() {
        log.debug("AFTER SUITE");
        DriverManager.getEmulatorDriver().removeApp();

        // 4. Завершение работы приложения
        DriverManager.getEmulatorDriver().quitDriver();
        BaseUtils.stopEmulator();

        BaseUtils.executeCommand(ConfigReader.EXECUTOR_CONFIG.killAppium());
    }

}
