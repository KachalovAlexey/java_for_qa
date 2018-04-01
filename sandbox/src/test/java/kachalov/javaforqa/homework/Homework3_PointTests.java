package kachalov.javaforqa.homework;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework3_PointTests {

    @Test
    public void testDistance1(){
        Point l = new Point(3,4,5,6);
        Assert.assertEquals(l.distance(), 2.8284271247461903);
    }

    @Test
    public void testDistance2(){
        Point l = new Point(7,1,8,2);
        Assert.assertEquals(l.distance(), 1.4142135623730951);
    }

    @Test
    public void testDistance3(){
        Point l = new Point(12,23,1,1);
        Assert.assertEquals(l.distance(), 24.596747752497688);
    }

}
