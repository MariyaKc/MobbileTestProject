package config;

import org.aeonbits.owner.ConfigFactory;

/**
 * Класс для читалки файлов .properties
 */
public class ConfigReader {

    /**
     * Читалка для emulator.properties
     */
      public static final EmulatorConfig EMULATOR_CONFIG = ConfigFactory.create(EmulatorConfig.class, System.getProperties());

    /**
     * Читалка для app.properties
     */
     public static final AppConfig APP_CONFIG = ConfigFactory.create(AppConfig.class, System.getProperties());

    /**
     * Читалка для executor.properties
     */
    public static final ExecutorConfig EXECUTOR_CONFIG = ConfigFactory.create(ExecutorConfig.class, System.getProperties());

    /**
     * Читалка для constants.properties
     */
    public static final ConstantsConfig CONSTANTS_CONFIG = ConfigFactory.create(ConstantsConfig.class, System.getProperties());
    /**
     * Читалка для testData.properties
     */
    public static final  TestDataConfig TEST_DATA_CONFIG = ConfigFactory.create(TestDataConfig.class, System.getProperties());
    /**
     * Читалка для signup.properties
     */
    public static final SignUpConfig SIGN_UP_CONFIG = ConfigFactory.create(SignUpConfig.class, System.getProperties());
    /**
     * Читалка для successSignUp.properties
     */
    public static final SuccessSignUpConfig SUCCESS_SIGN_UP_CONFIG = ConfigFactory.create(SuccessSignUpConfig.class, System.getProperties());
}