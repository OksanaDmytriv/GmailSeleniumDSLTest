package pages;

import org.openqa.selenium.By;

import static conditions.CustomConditions.elementVisible;
import static core.ConciseAPI.$;
import static core.ConciseAPI.byCSS;

public class Menu {

    public static void refresh() {
        $(byCSS(".asf")).shouldBe(elementVisible).click();
    }

    public static void openSent() {
        $(By.linkText("Sent Mail")).shouldBe(elementVisible).click();
    }

    public static void openInbox() {
        $(byCSS("a[aria-label^='Inbox']")).shouldBe(elementVisible).click();
    }

    public static void search(String subject) {
        $(By.name("q")).setValue("subject:" + subject).pressEnter();
    }
}
