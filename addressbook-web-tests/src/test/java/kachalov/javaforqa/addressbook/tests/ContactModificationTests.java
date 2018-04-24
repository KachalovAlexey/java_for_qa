package kachalov.javaforqa.addressbook.tests;

import kachalov.javaforqa.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {

        app.getNavigationManager().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "0123456789", "ivan@test.com", "[none]"));
            app.getNavigationManager().gotoHomePage();
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Petr", "Petrov", "123124155", "qwe@qwe.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationManager().gotoHomePage();

    }
}
