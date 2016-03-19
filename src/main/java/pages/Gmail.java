package pages;

import org.openqa.selenium.Keys;
import static core.ConciseAPI.*;

public class Gmail {

    public static void login(String email, String password) {
        $(byCSS("#Email")).sendKeys(email + Keys.ENTER);
        $(byCSS("#Passwd")).sendKeys(password + Keys.ENTER);
    }

    public static void visit() {
        open("http://gmail.com");
    }
}
