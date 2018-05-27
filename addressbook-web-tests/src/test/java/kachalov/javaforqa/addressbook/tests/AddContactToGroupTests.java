package kachalov.javaforqa.addressbook.tests;

import kachalov.javaforqa.addressbook.model.ContactData;
import kachalov.javaforqa.addressbook.model.Contacts;
import kachalov.javaforqa.addressbook.model.GroupData;
import kachalov.javaforqa.addressbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class AddContactToGroupTests extends TestBase{

    private ContactData contact;
    private GroupData group;

    @BeforeClass
    public void ensurePreconditions () {

        Groups groups;
        Contacts contacts;

        groups = app.db().groups();
        contacts = app.db().contacts();

        if (contacts.size() <= 0) {
            app.contact().create(new ContactData().withFirstname("Petr").withLastname("Petrov").withPhotoPath("src/test/resources/avatar.png"));
            contacts = app.db().contacts();
            contact = contacts.stream().iterator().next();
        } else {
            contact = contacts.stream().iterator().next();
        }

        if (contact.getGroups().size() == groups.size() | groups.size() <= 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Test group 1"));
            groups = app.db().groups();
            group = groups.stream().max(Comparator.comparing(GroupData::getId)).orElse(null);
        } else if (contact.getGroups().size() <= 0 | groups.size() >= 1){
            group = groups.stream().iterator().next();
        } else {
            group = groups.stream().filter(g -> !contact.getGroups().iterator().next().equals(g)).findFirst().orElse(null);
        }

    }

    @Test
    public void addContactToGroupTest () {
        Contacts before = app.db().contacts();
        app.goTo().gotoHomePage();
        app.contact().addToGroup(contact, group);
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.inGroup(contact, group)));
    }
}
