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
    // От примера наследования в интерфейсе Shape
    @Override
    public void printableP() {
        System.out.println("Интерфейс: Printable");
    }

    @Override
    public void searchableM() {
        System.out.println("Интерфейс: Searchalbe");
    }
}
