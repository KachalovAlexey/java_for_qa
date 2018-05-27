package kachalov.javaforqa.addressbook.tests;

import kachalov.javaforqa.addressbook.model.ContactData;
import kachalov.javaforqa.addressbook.model.Contacts;
import kachalov.javaforqa.addressbook.model.GroupData;
import kachalov.javaforqa.addressbook.model.Groups;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Comparator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroup extends TestBase{

    private ContactData contact;
    private GroupData group;
    private Groups groups;
    private Contacts contacts;

    @BeforeClass
    public void ensurePreconditions () {

        groups = app.db().groups();
        contacts = app.db().contacts();

        if (contacts.size() <= 0) {
            app.contact().create(new ContactData().withFirstname("Petr").withLastname("Petrov").withPhotoPath("src/test/resources/avatar.png"));
            contacts = app.db().contacts();
            contact = contacts.stream().iterator().next();
        } else {
            contact = contacts.stream().iterator().next();
        }

        if (groups.size() <= 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Test group 1"));
            groups = app.db().groups();
            group = groups.stream().iterator().next();
            app.contact().addToGroup(contact, group);
        } else if (contact.getGroups().size() == 0 & groups.size() >= 1){
            group = groups.stream().iterator().next();
            app.contact().addToGroup(contact, group);
        } else {
            group = groups.stream().filter(g -> contact.getGroups().iterator().next().equals(g)).findFirst().orElse(null);
        }

    }

    @Test
    public void addContactToGroupTest () {
        Contacts before = app.db().contacts();
        app.goTo().gotoHomePage();
        app.contact().removeFromGroup(contact, group);
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.notInGroup(contact, group)));
    }
}
