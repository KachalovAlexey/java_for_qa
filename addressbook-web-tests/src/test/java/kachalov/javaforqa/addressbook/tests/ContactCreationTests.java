package kachalov.javaforqa.addressbook.tests;

import kachalov.javaforqa.addressbook.model.ContactData;
import kachalov.javaforqa.addressbook.model.Contacts;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {

        app.goTo().gotoHomePage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/avatar.png");
        ContactData contact = new ContactData()
                .withFirstname("Petr")
                .withLastname("Petrov")
                .withAddress("Moscow")
                .withHomePhone("1234141")
                .withMobilePhone("987967252")
                .withWorkPhone("5436236")
                .withEmail_1("qwe@zxc.com")
                .withGroup("[none]")
                .withPhoto(photo);
        app.contact().create(contact);
        assertThat(app.contact().count(),equalTo( before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }


}
