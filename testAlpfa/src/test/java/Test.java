import baseObjects.DriverManager;
import baseObjects.EmulatorManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static baseObjects.EmulatorManager.closeEmulator;
@Log4j
public class Test {

    private static AndroidDriver driver;

    static {
        PropertyConfigurator.configure("src/main/java/resources/log4j.properties");
    }

    public static void main(String[] args) {
        try {
            log.info("start");

            // Здесь можно выполнить нужные операции с эмулятором

            // Шаг 1: Запуск эмулятора
           EmulatorManager.startEmulator();

            System.out.println("setup");
            // Шаг 2: Настройка драйвера
            setupDriver();

            System.out.println("install");
            // Шаг 3: Установка APK
            installApk("/Users/mariya/StudioProjects/qa-mobile/app/build/outputs/apk/debug/app-debug.apk");

            // Шаг 4: Открытие приложения
            launchApp();

            closeEmulator();

        } catch (Exception e) {
            e.printStackTrace();
            // ТУТ ПОСМОТРЕТЬ ЗАКРЫВАТТЬ ДРАЙВЕР ИЛИ ЭМУЛЯТОР
       // } finally {
           // if (driver != null) {
              //  driver.quit();
         //   }
        }
    }

    private static void startEmulator() throws IOException {
        System.out.println("Запуск эмулятора...");
        ProcessBuilder pb = new ProcessBuilder(
                System.getenv("ANDROID_HOME") + "/emulator/emulator",
                "-avd", "TestEmulator"
        );
        pb.start();
        try {
            Thread.sleep(15000); // Ждём 15 секунд для инициализации
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Эмулятор запущен.");
    }


    private static void setupDriver() throws MalformedURLException {
        //  driver = new DriverManager();

        System.out.println("Инициализация драйвера...");
//
//        // Получаем значение переменной окружения ANDROID_HOME
//        String androidHome = System.getenv("ANDROID_HOME");
//
//        if (androidHome == null || androidHome.isEmpty()) {
//            // Если переменная окружения не установлена, можно задать её вручную:
//            androidHome = "/Users/mariya/Library/Android/sdk"; // Укажите свой путь к SDK
//            System.setProperty("ANDROID_HOME", androidHome);  // Устанавливаем её как свойство системы
//        }
//
//        // Логируем значение переменной для проверки
//        System.out.println("ANDROID_HOME: " + androidHome);
//
        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setDeviceName("Android Emulator");
        options.setAutomationName("UiAutomator2");
        options.setApp("/Users/mariya/StudioProjects/qa-mobile/app/build/outputs/apk/debug/app-debug.apk");  // Установка APK
        // options.setAppPackage("com.example.myapp");  // Пакет приложения

        // Дополнительные параметры для драйвера
        //  options.setCapability("appWaitActivity", "com.example.myapp.MainActivity");  // Главная Activity
        options.setCapability("autoGrantPermissions", "true");
        options.setCapability("newCommandTimeout", 360000);
        // options.setCapability("systemPort", 4723);
        options.setCapability("waitForSelectorTimeout", 100);
        options.setCapability("actionAcknowledgmentTimeout", 100);
        options.setCapability("adbExecTimeout", 90000);
//
//        // Подключаемся к серверу Appium
        String appiumServerURL = "http://0.0.0.0:4723";  // Убедитесь, что сервер работает на этом порту
        driver = new AndroidDriver(new URL(appiumServerURL), options);
//
//        System.out.println("Драйвер инициализирован.");

        // driver = new DriverManager();
    }

    private static void installApk(String apkPath) throws IOException {
        System.out.println("Установка APK...");
        ProcessBuilder pb = new ProcessBuilder(
                System.getenv("ANDROID_HOME") + "/platform-tools/adb",
                "install", apkPath
        );
        Process process = pb.start();
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("APK установлено.");
    }

    private static void launchApp() {
        System.out.println("Запуск приложения...");
        driver.activateApp("com.alfabank.qapp");
        System.out.println("Приложение запущено.");
    }
}
