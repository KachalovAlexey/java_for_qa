package kachalov.javaforqa.mantis.appmanager;

import kachalov.javaforqa.mantis.model.UserData;
import org.openqa.selenium.By;

public class AuthorizationHelper extends HelperBase{

    public AuthorizationHelper (ApplicationManager app) {
        super(app);
    }

    public void logIn(UserData user) {
        fillLogInForm(user);
        wd.findElement(By.cssSelector("[type='submit']")).click();
    }

    private void fillLogInForm(UserData user) {
        type(By.name("username"), user.getUsername());
        type(By.name("password"), user.getPassword());
    }
}
