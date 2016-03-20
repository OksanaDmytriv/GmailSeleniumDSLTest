package pages;

import org.openqa.selenium.Keys;

import static conditions.CustomConditions.elementVisible;
import static core.ConciseAPI.*;

public class Gmail {

    public static void login(String email, String password) {
        $(byCSS("#Email")).shouldBe(elementVisible).sendKeys(email + Keys.ENTER);
        $(byCSS("#Passwd")).shouldBe(elementVisible).sendKeys(password + Keys.ENTER);
    }

    public static void visit() {
        open("http://gmail.com");
    }
}
