package baseObjects;

import config.ConfigReader;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import static utils.BaseUtils.startProcess;
import static utils.BaseUtils.waitForProcessCompletion;

@Log4j

public class EmulatorManager {

    @SneakyThrows
    public static void startEmulator() {
        log.info("Start emulator");
        // Запуск эмулятора с GPU ускорением для отображения на экране
        System.out.println(ConfigReader.EMULATOR_CONFIG.androidHome() + ConfigReader.EMULATOR_CONFIG.EmulatorPath());
       startProcess(
                System.getenv(ConfigReader.EMULATOR_CONFIG.androidHome()) + ConfigReader.EMULATOR_CONFIG.EmulatorPath(),
               ConfigReader.EMULATOR_CONFIG.avd(),  ConfigReader.EMULATOR_CONFIG.emulatorName(), // Название вашего AVD
               "-gpu", "on"
               //ConfigReader.EMULATOR_CONFIG.gpu(), ConfigReader.EMULATOR_CONFIG.on() // Включение графического ускорения
        );

       // waitForEmulatorToBoot();
       log.info("The emulator is running.");
    }

    /**
     * Ожидает завершения загрузки эмулятора, проверяя статус sys.boot_completed.
     */
    @SneakyThrows
    private static void waitForEmulatorToBoot() {
        log.info("Checking the emulator loading...");
        boolean bootCompleted = false;
        while (!bootCompleted) {
            Process checkBoot = startProcess(
                    System.getenv(ConfigReader.EMULATOR_CONFIG.androidHome()) + ConfigReader.EMULATOR_CONFIG.adbPath(),
                    ConfigReader.EMULATOR_CONFIG.shellCommand(),
                    ConfigReader.EMULATOR_CONFIG.getPropCommand(),
                    ConfigReader.EMULATOR_CONFIG.bootCompletedProperty());
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(checkBoot.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if ("1".equals(line.trim())) {
                        bootCompleted = true;
                        break;
                    }
                }
            }

            if (!bootCompleted) {
                log.info("The emulator is loading, check again in 2 seconds...");
                Thread.sleep(2000);
            }
        }
        log.info("The emulator completed loading successfully.");
    }

    @SneakyThrows
    public static void closeEmulator() {
        log.info("Trying to close the emulator...");
        // Команда для завершения эмулятора через ADB
        Process process = startProcess(
                System.getenv(ConfigReader.EMULATOR_CONFIG.androidHome()) + ConfigReader.EMULATOR_CONFIG.adbPath(),
                ConfigReader.EMULATOR_CONFIG.emuCommand(),
                ConfigReader.EMULATOR_CONFIG.killCommand()
        );
        waitForProcessCompletion(process, 10, TimeUnit.SECONDS); // Используем общий метод
    }
}
