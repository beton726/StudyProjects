package yusov.interfacesExample;

public class Circle implements Shape{

    private double radius;

    public Circle (double radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Рисуем круг.");
    }

    @Override
    public double getArea() {
        return radius*2;
    }

    public double getRadius() {
        return this.radius;
    }
}
