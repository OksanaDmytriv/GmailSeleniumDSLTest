package pages;

import org.openqa.selenium.By;

import static core.ConciseAPI.$;
import static core.ConciseAPI.byCSS;

public class Menu {

    public static void refresh() {
        $(byCSS(".asf")).click();
    }

    public static void openSent() {
        $(By.linkText("Sent Mail")).click();
    }

    public static void openInbox() {
        $(byCSS("a[aria-label^='Inbox']")).click();
    }

    public static void search(String subject) {
        $(By.name("q")).setValue("subject:" + subject).pressEnter();
    }
}
