package kachalov.javaforqa.addressbook.tests;

import kachalov.javaforqa.addressbook.model.ContactData;
import kachalov.javaforqa.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            ContactData contact = new ContactData().withFirstname("Petr").withLastname("Petrov").withGroup("[none]");
            app.contact().create(contact);
        }
    }

    @Test
    public void testContactModification() {
        app.goTo().gotoHomePage();
        Contacts before = app.contact().all();
        ContactData modifiedContanct = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContanct.getId())
                .withFirstname("Ivan")
                .withLastname("Ivanov")
                .withMobilePhone("123124155")
                .withEmail("qwe@qwe.com")
                .withGroup(null);
        app.contact().modify(contact);
        assertThat(app.contact().count(),equalTo( before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContanct).withAdded(contact)));

    }


}
