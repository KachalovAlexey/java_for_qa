package kachalov.javaforqa.addressbook.tests;

import kachalov.javaforqa.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupDeletionTests extends TestBase {

    
    @Test
    public void testGroupDeletion() {
        app.getNavigationManager().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        if (! app.getGroupHelper().isThereAGroup()){
            GroupData group = new GroupData("test1", null, null);
            app.getGroupHelper().createGroup(group);
            before.add(group);
        }
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Comparator<? super GroupData> byID = Comparator.comparingInt(GroupData::getId);
        before.sort(byID);
        after.sort(byID);
        Assert.assertEquals(after, before);
    }

}
