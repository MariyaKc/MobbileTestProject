package utils;

import config.ConfigReader;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;
import java.util.regex.Pattern;

@Log4j
public class RandomManager {
    private static final String ALLOWED_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.,/'_- ";
    private static final int MAX_LENGTH = 50;
    private static final String REGEX_PATTERN = "^[A-Za-z.,/'_\\-\\s]{1,50}$";

    public static String generateValidLoginRandomString(int length) {
        log.info("Start generate random login data");
        Random random = new Random();
        Pattern pattern = Pattern.compile(ConfigReader.TEST_DATA_CONFIG.regexPatternLogin());
        String randomString;
        long startTime = System.currentTimeMillis();
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                int index = random.nextInt(ConfigReader.TEST_DATA_CONFIG.allowedCharsLogin().length());
                sb.append(ConfigReader.TEST_DATA_CONFIG.allowedCharsLogin().charAt(index));
            }
            randomString = sb.toString();
            log.info("Random valid login is : " + randomString);
        } while ((!pattern.matcher(randomString).matches()) &&
                (System.currentTimeMillis() - startTime < ConfigReader.CONSTANTS_CONFIG.wait1S() * 5));// Проверяем строку на соответствие
        return randomString;
    }

    /**INVALID PROVIDER */
    public static String generateInvalidLoginRandomString() {
        log.info("Start generate random login data");
        Random random = new Random();
        Pattern pattern = Pattern.compile(ConfigReader.TEST_DATA_CONFIG.regexPatternLogin());
        String randomString;
        long startTime = System.currentTimeMillis();
        do {
            int length = random.nextInt(ConfigReader.TEST_DATA_CONFIG.maxLengthLogin()) + 1; // Длина от 1 до 50
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                int index = random.nextInt(ConfigReader.TEST_DATA_CONFIG.allowedInvalidCharsLogin().length());
                sb.append(ConfigReader.TEST_DATA_CONFIG.allowedInvalidCharsLogin().charAt(index));
            }
            randomString = sb.toString();
            log.info("Random invalid login is : " + randomString);
        } while (pattern.matcher(randomString).matches() &&
                (System.currentTimeMillis() - startTime < ConfigReader.CONSTANTS_CONFIG.wait1S() * 5));// Проверяем строку на соответствие
        return randomString;
    }

    // Генерация валидного логина с цифрами в середине
    public static String generateValidWithNumbers() {
        String validPart1 = generateRandomString(ConfigReader.TEST_DATA_CONFIG.allowedCharsLogin(), 5); // Валидная часть
        String numbers = RandomStringUtils.randomNumeric( 3);    // Числа
        String validPart2 = generateRandomString(ConfigReader.TEST_DATA_CONFIG.allowedCharsLogin(), 5); // Валидная часть
        return validPart1 + numbers + validPart2;
    }

    // Генерация валидного логина с русскими буквами в начале
    public static String generateValidWithRussian() {
        String russianPart = generateRandomString(ConfigReader.TEST_DATA_CONFIG.russianChars(), 3); // Русские буквы
        String validPart = generateRandomString(ConfigReader.TEST_DATA_CONFIG.allowedCharsLogin(), 10);  // Валидная часть
        return russianPart + validPart;
    }

    // Генерация валидного логина с недопустимыми символами в конце
    public static String generateValidWithInvalidChars() {
        String validPart = generateRandomString(ConfigReader.TEST_DATA_CONFIG.allowedCharsLogin(), 10);  // Валидная часть
        String invalidPart = generateRandomString(ConfigReader.TEST_DATA_CONFIG.invalidCharsLogin(), 3); // Недопустимые символы
        return validPart + invalidPart;
    }

    // Метод для генерации случайной строки из заданного множества символов
    public static String generateRandomString(String charSet, int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charSet.length());
            sb.append(charSet.charAt(index));
        }
        return sb.toString();
    }

}
