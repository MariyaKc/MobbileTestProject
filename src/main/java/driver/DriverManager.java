package driver;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;

@Log4j
public class DriverManager {
    private static EmulatorDriver emulatorDriver = null;

    @SneakyThrows
    public static void initEmulatorDriver( )  {
        emulatorDriver = new EmulatorDriver();
    }

    public static EmulatorDriver getEmulatorDriver() {
        return emulatorDriver;
    }
}
