package yusov.interfacesExample;

public interface Shape extends Searchable, Printable {
    // неявно public, static, final
    String LABLE = "Вызов поля интерфейса.";
    // неявно public, abstract
    void draw();
    // неявно public, abstract
    double getArea();
}
