package pages;

import core.ConciseAPI;
import org.openqa.selenium.By;

import static core.ConciseAPI.byCSS;

public class Menu {

    public static void refresh() {
        ConciseAPI.$(byCSS(".asf")).click();
    }

    public static void openSent() {
        ConciseAPI.$(By.linkText("Sent Mail")).click();
    }

    public static void openInbox() {
        ConciseAPI.$(byCSS("a[aria-label^='Inbox']")).click();
    }

    public static void search(String subject) {
        ConciseAPI.$(By.name("q")).setValue("subject:" + subject).pressEnter();
    }
}
