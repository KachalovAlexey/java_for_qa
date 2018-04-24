package kachalov.javaforqa.addressbook.tests;

import kachalov.javaforqa.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {

        app.getNavigationManager().gotoHomePage();
        app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "0123456789", "ivan@test.com", "[none]"));

    }
}
