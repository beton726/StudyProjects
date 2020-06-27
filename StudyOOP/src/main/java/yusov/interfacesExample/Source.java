package yusov.interfacesExample;

public class Source {
    public static void main(String[] args) {
        // Круг
        // Можем вызывать все методы и поля класса Circle и интерфейса Shape
        Circle circle = new Circle(10);
        circle.draw();
        System.out.println(circle.getArea());
        System.out.println(circle.LABLE + "\n");
        // Можем вызывать методы и поля у интерфейса Shape
        Shape circleShape = new Circle(10);
        circleShape.draw();
        System.out.println(circleShape.getArea() + "\n");
        // Можно сделать явное преобразование
        Shape circleShapeExplicit = new Circle(10);
        double radiusCircle = ((Circle) circleShapeExplicit).getRadius();
        System.out.println("Вывод параметра круга через явное преобразование: " + radiusCircle + "\n");

        // Прямоугольник
        Rectangle rectangle = new Rectangle(20,20);
        rectangle.draw();
        System.out.println(rectangle.getArea());
        System.out.println(rectangle.LABLE + "\n");
        Shape rectangleShape = new Rectangle(20,20);
        rectangleShape.draw();
        System.out.println(rectangleShape.getArea() + "\n");
        Shape rectangleShapeExplicit = new Rectangle(20,20);
        double radiusRectangle = ((Rectangle) rectangleShapeExplicit).getArea();
        System.out.println("Вывод параметра прямоуголника через явное преобразование: " + radiusRectangle);
    }
}