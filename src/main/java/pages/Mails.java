package pages;

import core.LazyElements;
import org.openqa.selenium.By;

import static conditions.CustomConditions.*;
import static core.ConciseAPI.*;
import static core.LazyElements.$$;

public class Mails {

    //public static By emails = byCSS("[role='main'] .zA");

    public static LazyElements emails = $$("[role='main'] .zA");

    public static void send(String email, String subject) {
        $(byText("COMPOSE")).click();
        $(By.name("to")).sendKeys(email);
        $(By.name("subjectbox")).sendKeys(subject);
        $(byText("Send")).click();
    }

    public static void assertMails(String... subjects) {
        emails.shouldBe(listVisible).shouldHave(textsOf(subjects));
        //assertThat(emails, textsOf(subjects));
    }

    public static void assertMail(int index, String subject) {
        emails.shouldHave(listNthElementHasText(index, subject));
        //assertThat(emails, listNthElementHasText(index, subject));
    }


}
