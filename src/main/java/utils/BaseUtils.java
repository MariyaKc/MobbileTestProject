package utils;

import config.ConfigReader;
import driver.DriverManager;
import driver.EmulatorDriver;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;

@Log4j
public class BaseUtils {
    /**
     * Общий метод для создания и запуска процесса с использованием ProcessBuilder.
     * Логирует запускаемую команду, обрабатывает ошибки и обеспечивает контроль завершения процесса.
     *
     * @param args Аргументы команды
     * @return Запущенный процесс
     * @throws IOException Если процесс не удалось запустить
     */
    public static Process startProcess(String... args) throws IOException {
        // Логируем запускаемую команду
        log.info("Running a command: " + String.join(" ", args));

        // Создаем процесс
        Process process = new ProcessBuilder(args).start();

        // Обработка ошибок (ErrorStream) в отдельном потоке
        new Thread(() -> {
            try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                String errorLine;
                while ((errorLine = errorReader.readLine()) != null) {
                    log.error("Process error: " + errorLine);
                }
            } catch (IOException e) {
                log.error("Error reading process error stream.", e);
            }
        }).start();
        return process;
    }


    /** METHODS FOR EMULATOR */

    @SneakyThrows
    public static void startEmulator() {
        log.info("Start emulator");
        // Запуск эмулятора с GPU ускорением для отображения на экране
        startProcess(
                System.getenv(ConfigReader.EMULATOR_CONFIG.androidHome()) + ConfigReader.EMULATOR_CONFIG.EmulatorPath(),
                ConfigReader.EMULATOR_CONFIG.avd(),  ConfigReader.EMULATOR_CONFIG.emulatorName(), // Название вашего AVD
                ConfigReader.EMULATOR_CONFIG.gpu(), ConfigReader.EMULATOR_CONFIG.on() // Включение графического ускорения
        );
        log.info("The emulator is running.");
        waitForEmulatorToBoot();
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
    public static void stopEmulator()  {
        executeCommand (ConfigReader.EXECUTOR_CONFIG.killEmulator());
        log.info("The emulator is closed.");
    }

    public static List<String> executeCommandWithGrep(String[] commands, String grepConditions) {
        try {
            Process commandExecutor = Runtime.getRuntime().exec(commands);
            InputStream input = commandExecutor.getInputStream();
            Process grepExecutor = Runtime.getRuntime().exec(new String[]{"grep", grepConditions});
            OutputStream output = grepExecutor.getOutputStream();
            IOUtils.copy(input, output);
            output.close();
            return IOUtils.readLines(grepExecutor.getInputStream());
        } catch (Exception e) {
            log.error("The command cannot be executed with grep " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static String setAppiumPort() {
        String port = null;
        for (String appiumPort : ConfigReader.APP_CONFIG.appiumPorts()) {
            log.debug("Try to execute command:: check if port " + appiumPort + " is in use");
            List<String> portOutput;
            portOutput = BaseUtils.executeCommandWithGrep(ConfigReader.EXECUTOR_CONFIG.portArray(), appiumPort);
            if ((portOutput.size()) == 0) {
                port = appiumPort;
                log.debug("Port " + port + " was set");
                try {
                    Runtime.getRuntime().exec(String.format(ConfigReader.EXECUTOR_CONFIG.startAppium(), port));
                    log.debug("Appium started on port " + port);
                } catch (IOException e) {
                    log.error("Appium not started on port " + port + e.getMessage());
                    e.printStackTrace();
                }
                break;
            } else {
                log.debug("Port " + appiumPort + " already is busy");
                if (appiumPort.equals("4731")) {
                    BaseUtils.executeCommand(ConfigReader.EXECUTOR_CONFIG.killAppium());
                }
            }
        }
        return port;
    }

    public static String executeCommandAndGetOutput(String cmd) throws IOException {
        Process process = Runtime.getRuntime().exec(cmd);
        java.io.InputStream inputStream = process.getInputStream();
        java.util.Scanner scanner = new java.util.Scanner(inputStream).useDelimiter("\\A");
        String value = "";
        if (scanner.hasNext()) {
            value = scanner.next();
        } else {
            value = "";
        }
        inputStream.close();
        process.destroy();
        return value.trim();
    }

    public static void killProcess(String command) {
        try {
            Process process;
            process = Runtime.getRuntime().exec(command);
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void executeCommand(String command) {
        try {
            Process process;
            log.info("Start execute command: " + command);
            process = Runtime.getRuntime().exec(command);
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

