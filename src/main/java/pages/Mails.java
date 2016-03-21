package pages;

import collection.LazyElements;
import org.openqa.selenium.By;

import static conditions.CustomConditions.*;
import static core.ConciseAPI.*;

public class Mails {

    public static LazyElements emails = $$("[role='main'] .zA");

    public static void send(String email, String subject) {
        $(byText("COMPOSE")).click();
        $(By.name("to")).sendKeys(email);
        $(By.name("subjectbox")).sendKeys(subject);
        $(byText("Send")).click();
    }

    public static void assertMails(String... subjects) {
        emails.shouldHave(textsOf(subjects));
    }

    public static void assertMail(int index, String subject) {
        emails.shouldHave(listNthElementHasText(index, subject));
    }


}
