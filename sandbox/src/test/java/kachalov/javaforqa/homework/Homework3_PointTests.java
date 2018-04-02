package kachalov.javaforqa.homework;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework3_PointTests {

    @Test
    public void testDistance1(){
        Point p1 = new Point(3,4);
        Point p2 = new Point(5,6);
        Assert.assertEquals(p1.distance(p2), 2.8284271247461903);
    }

    @Test
    public void testDistance2(){
        Point p1 = new Point(7,1);
        Point p2 = new Point(8,2);
        Assert.assertEquals(p1.distance(p2), 1.4142135623730951);
    }

    @Test
    public void testDistance3(){
        Point p1 = new Point(12,23);
        Point p2 = new Point(1,1);
        Assert.assertEquals(p1.distance(p2), 24.596747752497688);
    }

}
