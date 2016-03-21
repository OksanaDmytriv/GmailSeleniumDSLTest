package pages;

import static conditions.CustomConditions.elementVisible;
import static core.ConciseAPI.*;

public class Gmail {

    public static void login(String email, String password) {
        $(byCSS("#Email")).shouldBe(elementVisible).sendKeys(email).pressEnter();
        $(byCSS("#Passwd")).shouldBe(elementVisible).sendKeys(password).pressEnter();
    }

    public static void visit() {
        open("http://gmail.com");
    }
}
