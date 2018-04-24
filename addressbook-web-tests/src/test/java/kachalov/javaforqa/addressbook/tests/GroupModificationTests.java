package kachalov.javaforqa.addressbook.tests;

import kachalov.javaforqa.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase{

    @Test
    public void testGroupModification(){
        app.getNavigationManager().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", "test3", "test4"));
        app.getGroupHelper().submitGroupModification();
        app.getNavigationManager().gotoGroupPage();
    }

}
