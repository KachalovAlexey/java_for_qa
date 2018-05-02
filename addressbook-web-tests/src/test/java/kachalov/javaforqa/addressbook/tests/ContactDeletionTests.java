package kachalov.javaforqa.addressbook.tests;

import kachalov.javaforqa.addressbook.model.ContactData;
import kachalov.javaforqa.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            ContactData contact = new ContactData().withFirstname("Petr").withLastname("Petrov").withGroup("[none]");
            app.contact().create(contact);
        }
    }

    @Test
    public void testContactDeletion() {

        app.goTo().gotoHomePage();
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.contact().all();

        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));
    }


}
