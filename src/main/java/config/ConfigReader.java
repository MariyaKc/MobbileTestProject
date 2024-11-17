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
}