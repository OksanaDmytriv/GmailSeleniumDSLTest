package pages;

import static core.ConciseAPI.*;

public class Gmail {

    public static void login(String email, String password) {
        $(byCSS("#Email")).sendKeys(email).pressEnter();
        $(byCSS("#Passwd")).sendKeys(password).pressEnter();
    }

    public static void visit() {
        open("http://gmail.com");
    }
}
