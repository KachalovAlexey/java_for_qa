package kachalov.javaforqa.homework;

public class Point {

    public double x1;
    public double y1;
    public double x2;
    public double y2;

    public Point (double x1, double y1, double x2, double y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public double distance(){
        double a = Math.pow((x2 - x1), 2);
        double b = Math.pow((y2 - y1), 2);
        return Math.sqrt(a + b);
    }
}

