package kachalov.javaforqa.addressbook.tests;

import kachalov.javaforqa.addressbook.model.ContactData;
import kachalov.javaforqa.addressbook.model.GroupData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class HbConnectionTest {

    private SessionFactory sessionFactory;

    @BeforeClass
    protected void setUp() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    @Test
    public void testHbConnection () {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List <ContactData> result1 = session.createQuery( "from ContactData where deprecated = '0000-00-00'" ).list();
        for ( ContactData contact : result1 ) {
            System.out.println(contact);
            System.out.println(contact.getGroups());
        }
        List <GroupData> result2 = session.createQuery( "from GroupData" ).list();
        for ( GroupData group : result2 ) {
            System.out.println(group);
            System.out.println(group.getContacts());
        }
        session.getTransaction().commit();
        session.close();
    }
}
