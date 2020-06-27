package yusov.interfacesExample;

public class Rectangle implements Shape {

    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("Рисуем прямоугольник.");
    }

    @Override
    public double getArea() {
        return width*height;
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
