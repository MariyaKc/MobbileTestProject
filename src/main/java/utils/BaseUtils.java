package utils;

import lombok.extern.log4j.Log4j;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Log4j
public class BaseUtils {
    public static void executeAdbShellCommand(String command){
            try {
                ProcessBuilder pb = new ProcessBuilder(command);

                Process process = pb.start(); // Запуск процесса
                log.info("Эмулятор успешно запущен.");
            } catch (IOException e) {
                // Обработка ошибок ввода-вывода (например, файла/команды нет или ошибка запуска)
                log.error("Ошибка запуска эмулятора. Убедитесь, что путь к ANDROID_HOME верный и эмулятор установлен.", e);
            } catch (NullPointerException e) {
                // Обработка ошибок, связанных с отсутствием ANDROID_HOME
                log.error("Переменная окружения ANDROID_HOME не настроена.", e);
            } catch (SecurityException e) {
                // Обработка ошибок, если отсутствуют права на выполнение
                log.error("Недостаточно прав для запуска эмулятора.", e);
            } catch (Exception e) {
                // Ловим любые другие исключения
                log.error("Произошла неожиданная ошибка при запуске эмулятора.", e);
            }
        }

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
       // waitForProcessCompletion(process, 30, TimeUnit.SECONDS);
        return process;
    }

    public static void waitForProcessCompletion(Process process, long timeout, TimeUnit timeUnit) {
        try {
            boolean finished = process.waitFor(timeout, timeUnit);
            if (finished) {
                int exitCode = process.exitValue();
                if (exitCode == 0) {
                    log.info("Process completed successfully.");
                } else {
                    log.warn("The process completed with an error. Code: " + exitCode);
                }
            } else {
                log.error("The process did not complete within the allotted time.");
                process.destroy();
            }
        } catch (InterruptedException e) {
            log.error("Waiting for the process to complete was interrupted.", e);
            Thread.currentThread().interrupt();
            process.destroy();
        }
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

