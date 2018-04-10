package kachalov.javaforqa.addressbook.tests;

import kachalov.javaforqa.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {

        app.getNavigationManager().gotoHomePage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Petr", "Petrov", "123124155", "qwe@qwe.com"));
        app.getContactHelper().submitContactModification();
        app.getNavigationManager().gotoHomePage();

    }
}
