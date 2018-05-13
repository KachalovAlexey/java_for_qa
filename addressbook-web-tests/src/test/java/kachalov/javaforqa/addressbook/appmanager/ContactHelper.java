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
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail_1());

        if (creation){
            atach(By.name("photo"), contactData.getPhotoPath());
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectContactByID(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedContact() {
        click(By.cssSelector("[name='MainForm'] [value='Delete']"));
        accept();
    }

    public ContactData infoFromEditForm(ContactData contact){
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        String email_1 = wd.findElement(By.name("email")).getAttribute("value");
        String email_2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email_3 = wd.findElement(By.name("email3")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId())
                .withFirstname(firstname)
                .withLastname(lastname)
                .withAddress(address)
                .withEmail_1(email_1)
                .withEmail_2(email_2)
                .withEmail_3(email_3)
                .withHomePhone(home)
                .withMobilePhone(mobile)
                .withWorkPhone(work);
    }

    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector("[href='edit.php?id=" + id + "']")).click();

    }

    public void submitContactModification() {
        click(By.cssSelector("[value='Update']"));
    }

    public void create(ContactData contact) {
        initNewContact();
        fillContactForm(contact, true);
        confirmContactCreation();
        contactsCashe = null;
        returnToContactPage();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
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
        return isElementPresent(By.cssSelector("[name='entry'] [type]"));
    }
    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactsCashe = null;

    public Contacts all() {

        if (contactsCashe != null) {
            return new Contacts(contactsCashe);
        }

        contactsCashe = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tbody [name='entry']"));
        for (WebElement element : elements) {

            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstname = element.findElement(By.cssSelector("tbody [name='entry'] td:nth-of-type(3)")).getText();
            String lastname = element.findElement(By.cssSelector("tbody [name='entry'] td:nth-of-type(2)")).getText();
            String address = element.findElement(By.cssSelector("tbody [name='entry'] td:nth-of-type(4)")).getText();
            String allEmails = element.findElement(By.cssSelector("tbody [name='entry'] td:nth-of-type(5)")).getText();
            String allPhones = element.findElement(By.cssSelector("tbody [name='entry'] td:nth-of-type(6)")).getText();

            String[] phones = allPhones.split("\n");
            String[] emails = allEmails.split("\n");

            contactsCashe.add(new ContactData()
                    .withId(id)
                    .withFirstname(firstname)
                    .withLastname(lastname)
                    .withAddress(address)
                    .withAllEmails(allEmails)
                    .withAllPhones(allPhones));
        }
        return new Contacts(contactsCashe);
    }

}
