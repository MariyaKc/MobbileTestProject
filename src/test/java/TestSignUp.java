import driver.DriverManager;
import pages.SignUpPage;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.Test;

@Log4j
public class TestSignUp extends BaseTest {

   @Test
    public void visibleElementTest() {
        new SignUpPage(DriverManager.getEmulatorDriver().getDriver()).waitPageOpen();
    }
}
