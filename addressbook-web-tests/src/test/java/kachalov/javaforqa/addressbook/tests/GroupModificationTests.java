package kachalov.javaforqa.addressbook.tests;

import kachalov.javaforqa.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase{

    @Test
    public void testGroupModification(){
        app.getNavigationManager().gotoGroupPage();
        int before = app.getGroupHelper().getGroupCount();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        app.getGroupHelper().selectGroup(before - 1);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", "test3", "test4"));
        app.getGroupHelper().submitGroupModification();
        app.getNavigationManager().gotoGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before);
    }

}
