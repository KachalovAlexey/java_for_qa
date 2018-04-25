package kachalov.javaforqa.addressbook.appmanager;

import kachalov.javaforqa.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

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

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());

        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectContact(int index) {
        wd.findElements(By.cssSelector("[name=\"entry\"] [type]")).get(index).click();
    }

    public void deleteSelectedContact() {
        click(By.cssSelector("[name=\"MainForm\"] [value=\"Delete\"]"));
        accept();
    }

    public void initContactModification(int index) {
        wd.findElements(By.cssSelector("[name=\"entry\"] [title=\"Edit\"]")).get(index).click(); //плохой локатор, находит все кнопки на странице
    }

    public void submitContactModification() {
        click(By.cssSelector("[value=\"Update\"]")); //плохой локатор, находит все кнопки на странице
    }

    public void createContact(ContactData contact) {
        initNewContact();
        fillContactForm(contact, true);
        confirmContactCreation();

    }

    public boolean isThereAContact() {
        return isElementPresent(By.cssSelector("[name=\"entry\"] [type]"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tbody [name=\"entry\"]"));
        for (WebElement element : elements) {

            String firstname = element.findElement(By.cssSelector("tbody [name=\"entry\"] td:nth-of-type(3)")).getText();
            String lastname = element.findElement(By.cssSelector("tbody [name=\"entry\"] td:nth-of-type(2)")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(id, firstname, lastname, null, null, "[none]");
            contacts.add(contact);
        }
        return contacts;
    }
}
