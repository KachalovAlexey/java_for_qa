package kachalov.javaforqa.addressbook.tests;

import kachalov.javaforqa.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() {

        app.getNavigationManager().gotoGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }

}
