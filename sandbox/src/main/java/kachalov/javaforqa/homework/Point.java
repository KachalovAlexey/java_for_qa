package kachalov.javaforqa.homework;

public class Point {

    public double x;
    public double y;


    public Point (double x, double y){
        this.x = x;
        this.y = y;
    }

    public double distance(Point p2){
        double a = Math.pow((p2.x - this.x), 2);
        double b = Math.pow((p2.y - this.y), 2);
        return Math.sqrt(a + b);
    }
}

