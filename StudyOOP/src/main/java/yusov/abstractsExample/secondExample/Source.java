package yusov.abstractsExample.secondExample;

public class Source {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(5,5, 10,10);
        System.out.println("Выводим площадь: " + rectangle.getArea());
        System.out.println("Выводим периметр: " + rectangle.getPerimetr());
        System.out.println("Просто сумма: " + rectangle.sum());
        Figure figure = new Rectangle(1,1,1,1);
        System.out.println(figure.getFigureSum());
    }
}