package kachalov.javaforqa.homework;

public class Homework2 {

    public static void main(String[] args) {

        Point p1 = new Point();
        p1.x = 3;
        p1.y = 4;
        Point p2 = new Point();
        p2.x = 5;
        p2.y = 6;

        System.out.println("расстояние между точками 3.4 и 5.6 = " + distance(p1,p2));

        Point p3 = new Point();
        p3.x = 7;
        p3.y = 1;
        Point p4 = new Point();
        p4.x = 8;
        p4.y = 2;

        System.out.println("расстояние между точками 7.1 и 8.2 = " + distance(p3,p4));
    }

    public static double distance(Point p1, Point p2){
        double a = Math.pow((p2.x - p1.x), 2);
        double b = Math.pow((p2.y - p1.y), 2);
        return Math.sqrt(a + b);
    }

}
