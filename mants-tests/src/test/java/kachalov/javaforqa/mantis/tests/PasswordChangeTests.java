package kachalov.javaforqa.mantis.tests;

import kachalov.javaforqa.mantis.appmanager.HttpSession;
import kachalov.javaforqa.mantis.model.MailMessage;
import kachalov.javaforqa.mantis.model.UserData;
import kachalov.javaforqa.mantis.model.Users;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordChangeTests extends TestBase{
    private UserData user;
    private String newPassword;

    @BeforeClass
    public void ensurePreconditions() {
        newPassword = "Qwerty12";
        Users users = app.db().users();
        user = users.stream().max(Comparator.comparing(UserData::getId)).get();
    }

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void changeUserPassword() throws Exception {
        app.authorization().logIn(new UserData()
                .setUsername(app.getProperty("web.adminLogin")).setPassword(app.getProperty("web.adminPassword")));
        app.actions().resetUserPassword(user);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessages, user.getEmail());
        app.actions().changePassword(confirmationLink, newPassword);
        HttpSession session = app.newSession();
        assertTrue(session.login(user.getUsername(), newPassword));
        assertTrue(session.isLoggedInAs(user.getUsername()));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
