package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

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
        $(By.name("q")).shouldBe(elementVisible).clear();
        $(By.name("q")).shouldBe(elementVisible).sendKeys("subject:" + subject + Keys.ENTER);
    }
}
