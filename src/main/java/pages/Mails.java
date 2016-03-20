package pages;

import core.LazyElements;
import org.openqa.selenium.By;

import static conditions.CustomConditions.*;
import static core.ConciseAPI.*;

public class Mails {

    public static LazyElements emails = $$("[role='main'] .zA");

    public static void send(String email, String subject) {
        $(byText("COMPOSE")).shouldBe(elementVisible).click();
        $(By.name("to")).shouldBe(elementVisible).sendKeys(email);
        $(By.name("subjectbox")).shouldBe(elementVisible).sendKeys(subject);
        $(byText("Send")).shouldBe(elementVisible).click();
    }

    public static void assertMails(String... subjects) {
        emails.shouldBe(listVisible).shouldHave(textsOf(subjects));
    }

    public static void assertMail(int index, String subject) {
        emails.shouldHave(listNthElementHasText(index, subject));
    }


}
