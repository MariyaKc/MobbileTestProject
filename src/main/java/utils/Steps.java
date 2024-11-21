package utils;

import io.qameta.allure.Step;

public class Steps {
    public static class SignUpPage {
        public static final String WAIT_PAGE = "Ожидание окрытия страницы авторизации";
        public static final String VERIFY_ALL_ELEMENTS = "Проверка отображения всех элементов на странице авторизации";
        public static final String SEND_KEYS_LOGIN = "Отправка данных в поле логина на странице авторизации";
        public static final String SEND_KEYS_PASSWORD = "Отправка данных в поле пароля на странице авторизации";
        public static final String CLICK_BTN_LOGIN = "Нажать на кнопку 'Войти' на странице авторизации";
        public static final String VERIFY_ERROR_INVALID_LOGIN = "Проверка появления ошибки о невалидном логине на странице авторизации";
        public static final String VERIFY_ERROR_USER_NOT_FOUND = "Проверка появления ошибки о том, что такой пользователь не найден на странице авторизации";
        public static final String VERIFY_NO_ERROR = "Проверка, что никакой ошибки не появляется  на странице авторизации";
    }
    public static class DataProviderName {
        public static final String INVALID_LOGIN_PROVIDER = "InvalidLoginProvider";
        public static final String VALID_LOGIN_PROVIDER = "ValidLoginProvider";

    }
}
