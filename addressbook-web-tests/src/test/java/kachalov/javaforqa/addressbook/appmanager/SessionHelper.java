package kachalov.javaforqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SessionHelper extends HelperBase{

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String username, String userpass) {

        type(By.name("user"), username);
        type(By.name("pass"), userpass);
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
