package pages;

import org.openqa.selenium.By;

import static core.ConciseAPI.*;
import static conditions.CustomConditions.listNthElementHasText;
import static conditions.CustomConditions.textsOf;

public class Mails {

    public static By emails = byCSS("[role='main'] .zA");

    public static void send(String email, String subject) {
        $(byText("COMPOSE")).click();
        $(By.name("to")).sendKeys(email);
        $(By.name("subjectbox")).sendKeys(subject);
        $(byText("Send")).click();
    }

    public static void assertMails(String... subjects) {
        assertThat(emails, textsOf(subjects));
    }

    public static void assertMail(int index, String subject) {
        assertThat(emails, listNthElementHasText(index, subject));
    }
}
