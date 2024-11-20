package utils;

import config.ConfigReader;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

import static org.apache.commons.lang3.RandomUtils.nextInt;
import static utils.RandomManager.generateRandomString;

public class SignUpDataProvider {

    /**
     * 1 Смесь всех символов
     2 Валидный логин+цифры в середине
     3 Валидный + русские буквы в начале
     4 Валидный + инвалидные символы в конце
     5 Пустое значение
     6 51 валидный символ*/
    public static Object[][] invalidLoginProvider() {
        Object [][] array = new Object[6][1];
        array[0][0] = RandomManager.generateInvalidLoginRandomString();
        array[1][0] = RandomManager.generateValidWithNumbers();
        array[2][0] = RandomManager.generateValidWithRussian();
        array[3][0] = RandomManager.generateValidWithInvalidChars();
        array[4][0] ="";
        array[5][0] = RandomStringUtils.randomAlphabetic(51); // в конфиги
        return array;
    }

    /**ВАЛИДНЫЙ ПРОВАЙДЕР
     1 Смесь всех символов
     2 Латинские символы + пробел в начале
     3 Латинские символы + спец символ в конце
     4 1 спецсимвол
     5 Смесь всех символов  длиной 50
     */
    public static Object[][] validLoginProvider() {
        Object [][] array = new Object[5][1];
        array[0][0] = RandomManager.generateValidLoginRandomString(new Random().nextInt(ConfigReader.TEST_DATA_CONFIG.maxLengthLogin()) + 1);
        array[1][0] = " " + RandomStringUtils.randomAlphabetic(1,49);
        array[2][0] =RandomStringUtils.randomAlphabetic(1,49) + generateRandomString(ConfigReader.TEST_DATA_CONFIG.validCharsLogin(), 1) ;
        array[3][0] = generateRandomString(ConfigReader.TEST_DATA_CONFIG.validCharsLogin(), 1);
        array[4][0] = RandomManager.generateValidLoginRandomString(50);
        return array;
    }
}
