import org.junit.Test;
import pages.Gmail;
import pages.Mails;
import pages.Menu;
import testconfigs.BaseTest;
import testdata.AccountData;

import static core.Helpers.getUniqueText;


public class GmailTest extends BaseTest {

    @Test
    public void testGmailCommonFlow() {

        String subject = getUniqueText("test");

        Gmail.visit();
        Gmail.login(AccountData.email, AccountData.password);

        Mails.send(AccountData.email, subject);
        Menu.refresh();
        Mails.assertMail(0, subject);

        Menu.openSent();
        Mails.assertMail(0, subject);

        Menu.openInbox();
        Menu.search(subject);
        Mails.assertMails(subject);
    }

}

