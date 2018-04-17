package kachalov.javaforqa.addressbook.appmanager;

import kachalov.javaforqa.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initNewContact() {
        click(By.linkText("add new"));
    }

    public void confirmContactCreation() {
        click(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());
    }

    public void selectContact() {
        click(By.cssSelector("[name=\"entry\"] [type]"));
    }

    public void deleteSelectedContact() {
        click(By.cssSelector("[name=\"MainForm\"] [value=\"Delete\"]"));
        accept();
    }

    public void initContactModification() {
        click(By.cssSelector("[name=\"entry\"] [title=\"Edit\"]")); //плохой локатор, находит все кнопки на странице
    }

    public void submitContactModification() {
        click(By.cssSelector("[value=\"Update\"]")); //плохой локатор, находит все кнопки на странице
    }
}
