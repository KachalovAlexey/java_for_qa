package kachalov.javaforqa.addressbook.tests;

import kachalov.javaforqa.addressbook.model.ContactData;
import kachalov.javaforqa.addressbook.model.Contacts;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {

        app.goTo().gotoHomePage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("Petr").withLastname("Petrov").withGroup("[none]");
        app.contact().create(contact);
        Contacts after = app.contact().all();


        assertThat(after.size(),equalTo( before.size() + 1));
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}
