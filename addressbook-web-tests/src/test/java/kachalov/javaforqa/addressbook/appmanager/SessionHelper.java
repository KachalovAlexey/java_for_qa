package kachalov.javaforqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SessionHelper {

    private FirefoxDriver wd;

    public SessionHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void login(String username, String userpass) {

        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).sendKeys(userpass);
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
    }
}
