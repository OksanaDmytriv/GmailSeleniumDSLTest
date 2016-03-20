package testconfigs;

import core.ConciseAPI;
import core.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {

    static {
        Configuration.timeout = 20000;
    }

    @BeforeClass
    public static void setUp(){
        ConciseAPI.setDriver(new FirefoxDriver());
    }

    @AfterClass
    public static void teardown() {
        ConciseAPI.getDriver().quit();
    }

}
