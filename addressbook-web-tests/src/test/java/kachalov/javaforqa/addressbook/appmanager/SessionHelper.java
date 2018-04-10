package kachalov.javaforqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SessionHelper extends HelperBase{

    public SessionHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void login(String username, String userpass) {

        type(By.name("user"), username);
        type(By.name("pass"), userpass);
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
