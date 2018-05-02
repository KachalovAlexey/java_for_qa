package kachalov.javaforqa.addressbook.appmanager;

import kachalov.javaforqa.addressbook.model.ContactData;
import kachalov.javaforqa.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.util.List;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToContactPage() {
        click(By.linkText("home"));
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

    public void selectContactByID(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedContact() {
        click(By.cssSelector("[name=\"MainForm\"] [value=\"Delete\"]"));
        accept();
    }

    public void initContactModification(int id) {
        wd.findElement(By.cssSelector("[href='edit.php?id=" + id + "']")).click();

    }

    public void submitContactModification() {
        click(By.cssSelector("[value=\"Update\"]")); //плохой локатор, находит все кнопки на странице
    }

    public void create(ContactData contact) {
        initNewContact();
        fillContactForm(contact, true);
        confirmContactCreation();
        contactsCashe = null;
        returnToContactPage();
    }

    public void modify(ContactData contact) {
        initContactModification(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactsCashe = null;
        returnToContactPage();
    }

    public void delete (ContactData contact) {
        selectContactByID(contact.getId());
        deleteSelectedContact();
        contactsCashe = null;
        returnToContactPage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.cssSelector("[name=\"entry\"] [type]"));
    }

    private Contacts contactsCashe = null;

    public Contacts all() {

        if (contactsCashe != null) {
            return new Contacts(contactsCashe);
        }

        contactsCashe = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tbody [name=\"entry\"]"));
        for (WebElement element : elements) {

            String firstname = element.findElement(By.cssSelector("tbody [name=\"entry\"] td:nth-of-type(3)")).getText();
            String lastname = element.findElement(By.cssSelector("tbody [name=\"entry\"] td:nth-of-type(2)")).getText();

            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

            contactsCashe.add(new ContactData()
                    .withId(id)
                    .withFirstname(firstname)
                    .withLastname(lastname));
        }
        return new Contacts(contactsCashe);
    }

}
