package kachalov.javaforqa.mantis.appmanager;

import kachalov.javaforqa.mantis.model.UserData;
import org.openqa.selenium.By;

public class UserActionsHelper extends HelperBase {

    public UserActionsHelper (ApplicationManager app) {
        super(app);
    }

    public void goToUserManage () {
        wd.findElement(By.linkText("Manage")).click();
        wd.findElement(By.linkText("Manage Users")).click();
    }

    public void goToUserProfile (UserData user) {
        wd.findElement(By.linkText(user.getUsername())).click();
    }

    public void resetUserPassword(UserData user) {
        goToUserManage();
        goToUserProfile(user);
        wd.findElement(By.cssSelector("input[value='Reset Password']")).click();
    }

    public void changePassword(String confirmationLink, String newPassword) {
        wd.get(confirmationLink);
        type(By.name("password"), newPassword);
        type(By.name("password_confirm"), newPassword);
        click(By.cssSelector("input[value='Update User']"));
    }

}
