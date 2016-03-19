package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

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
        $(By.name("q")).clear();
        $(By.name("q")).sendKeys("subject:" + subject + Keys.ENTER);
    }
}
